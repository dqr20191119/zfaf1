package com.cesgroup.prison.zbgl.rygl.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zbgl.mbsz.web.MbszController;
import com.cesgroup.prison.zbgl.rlwh.dao.ZbRlwhMapper;
import com.cesgroup.prison.zbgl.rlwh.entity.ZbRlwhEntity;
import com.cesgroup.prison.zbgl.rygl.dao.RyglMapper;
import com.cesgroup.prison.zbgl.rygl.dao.RyztHistoryMapper;
import com.cesgroup.prison.zbgl.rygl.dto.DutyCountDto;
import com.cesgroup.prison.zbgl.rygl.dto.JyzzxxDto;
import com.cesgroup.prison.zbgl.rygl.entity.DutyFlagEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyztHistoryEntity;
import com.cesgroup.prison.zbgl.rygl.service.RyglService;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("ryglService")
public class RyglServiceImpl extends BaseDaoService<RyglEntity, String, RyglMapper> implements RyglService {

    @Resource
    private ZbRlwhMapper zbRlwhMapper;

    @Resource
    private RyztHistoryMapper ryztHistoryMapper;


	@Override
	public RyglEntity getById(String id) {
		
		return this.getDao().getById(id);
	}

	@Override
	public Page<RyglEntity> findList(RyglEntity ryglEntity, Pageable pageable) throws Exception{
          Page<RyglEntity> entityPage = this.getDao().findList(ryglEntity, pageable);
          List<RyglEntity> content = entityPage.getContent();
          Map<String, Object> map = new HashMap<>();
          if(StringUtil.isNull(ryglEntity.getYear())){
              map.put("year", Util.getCurrentYear());
          }else{
              map.put("year", ryglEntity.getYear());
          }
          map.put("cusNumber", ryglEntity.getCusNumber());
          if(StringUtil.isNull(ryglEntity.getYear())){
              ryglEntity.setYear(Util.getCurrentYear());
          }
        for (RyglEntity ryglEntity1:content){
            ryglEntity1.setYear(ryglEntity.getYear());
            //??????????????????????????? ???????????????
            RyztHistoryEntity ryztHistoryEntity = new RyztHistoryEntity();
            ryztHistoryEntity.setZbryId(ryglEntity1.getId());
            ryztHistoryEntity.setYear(ryglEntity1.getYear());
            List<RyztHistoryEntity> ryztHistoryEntities = ryztHistoryMapper.selectByZbryId(ryztHistoryEntity);
            if(ryztHistoryEntities.size() >0){
                ryglEntity1.setNoDuty(appendNoduty(ryztHistoryEntities));
            }
            map.put("id",ryglEntity1.getId());
            Integer yearCount = this.getDao().dutyCount(map);
            //???????????????
            ryglEntity1.setYearCount(yearCount);
            //?????????
            //???????????????
            map.put("type","1");
            List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "1");
            List<String> list = new ArrayList<>();
            for(ZbRlwhEntity zbRl:zbRlwhEntities){
                list.add(zbRl.getDutyDate());
            }
            map.put("list",list);
            Integer hCount = this.getDao().dutyCount(map);
            ryglEntity1.setHolidaysCount(hCount);
            //?????????
            List<ZbRlwhEntity> workDays = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "0");
            List<String> list2 = new ArrayList<>();
            for(ZbRlwhEntity zbRl:workDays){
                list2.add(zbRl.getDutyDate());
            }
            map.put("list",list2);
            Integer wCount = this.getDao().dutyCount(map);
            ryglEntity1.setWorkDayCount(wCount);

            //???????????????
            List<ZbRlwhEntity> fDays = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "2");
            if(fDays.size()<=0){
                ryglEntity1.setFddayCount(0);
            }else {
                List<String> list3 = new ArrayList<>();
                for(ZbRlwhEntity zbRl:fDays){
                    list3.add(zbRl.getDutyDate());
                }
                map.put("list",list3);
                Integer fCount = this.getDao().dutyCount(map);
                ryglEntity1.setFddayCount(fCount);
            }

            //??????
            // map.put("type","");
            map.remove("type");
            map.put("orderName", "??????");
            Integer zhCount = this.getDao().dutyCount(map);
            ryglEntity1.setMiddleCount(zhCount);
            //??????
            map.put("orderName", "??????");
            Integer wanCount = this.getDao().dutyCount(map);
            ryglEntity1.setNightCount(wanCount);
            map.remove("orderName");
        }
          return entityPage;
	}

    @Override
    @Transactional
    public Integer deleteByIds(String ids) {
        String[] strings = ids.split(",");
        List<String> list = new ArrayList<String>();
        for (String id:strings){
            list.add(id);
        }
        Integer deleteByIds = this.getDao().deleteByIds(list);
        return deleteByIds;
    }

    @Override
    public List<JyzzxxDto>listSjzzxx() {
        return this.getDao().listSjzzxx();
    }

    /**
     * ??????id ???????????????
     * @param dutyFlagEntity
     */
    @Override
    @Transactional
    public void updateDutyFlagById(DutyFlagEntity dutyFlagEntity){
        this.getDao().updateDutyFlagById(dutyFlagEntity);
    }

    @Override
    public  List<DutyFlagEntity> seachDutyFlagData(String cusNumber) {
        List<DutyFlagEntity> list = new ArrayList<>();
        list.add(this.getDao().getDutyFlag(cusNumber));
        return list;
    }

    @Override
    public String remindMessageByRyzy() {
       // Util.isAffterDate()
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> nextMonths = Util.getNextMonth(new Date());
        //???????????????????????????
       Date startDate = (Date)nextMonths.get("startDate");
       Date enDate =(Date)nextMonths.get("endDate");
       RyglEntity ryglEntity = new RyglEntity();
       ryglEntity.setStartDate(sdf.format(startDate));
       ryglEntity.setEndDate(sdf.format(enDate));
       String str ="";
       List<RyglEntity> ryglEntities = this.getDao().remindMessageByRyzy(ryglEntity);
        if(ryglEntities.size() >0){
            for(RyglEntity ry:ryglEntities){
                str +=ry.getName()+":"+converToName(ry.getRyzt(),ry.getRemark())+"????????????:"+ry.getEndDate()+";";
            }

        }
        return str;
    }

    @Override
    @Transactional
    public void insertRyztHistory(RyztHistoryEntity ryztHistoryEntity) {
         ryztHistoryMapper.insert(ryztHistoryEntity);
    }

    @Override
    @Transactional
    public Integer tZJob(RyglEntity ryglEntity) {
        RyglEntity maxPbOrderByJob = this.getDao().getMaxPbOrderByJob(ryglEntity.getCusNumber(), ryglEntity.getJob());
        Integer pbOrder = maxPbOrderByJob.getPbOrder();
        RyglEntity r = new RyglEntity();
        r.setCusNumber(ryglEntity.getCusNumber());
        r.setPbOrder(pbOrder);
        //????????????
        this.getDao().updateBypbOrder(r);
        return pbOrder;
    }

    @Override
    @Transactional
    public void afterMove(RyglEntity ryglEntity) {
        //????????????
        this.getDao().updateBypbOrder(ryglEntity);
    }


    private String appendNoduty(List<RyztHistoryEntity> list){
        String noDuty = "";
        if(list !=null && list.size() >0){
            for(RyztHistoryEntity ryztHistoryEntity:list){
                noDuty +=converToName(ryztHistoryEntity.getRyzt(),ryztHistoryEntity.getRemark())+"????????????:"+ryztHistoryEntity.getStartDate()+"????????????:"+ryztHistoryEntity.getEndDate()+";";
            }
        }
        return noDuty;
    }



    private String converToName(String ryzt,String remark){
        String str = "";
        if(StringUtil.isNull(remark)){
            if("4".equals(ryzt)){
                str ="??????";
            }else if("5".equals(ryzt)){
                str ="??????";
            }else if("6".equals(ryzt)){
                str ="??????";
            }
        }else{
            if("4".equals(ryzt)){
                str ="??????,"+remark;
            }else if("5".equals(ryzt)){
                str ="??????,"+remark;
            }else if("6".equals(ryzt)){
                str ="??????,"+remark;
            }

        }
        return str;
    }



    @Override
	@Transactional
	public AjaxResult importFile(MultipartFile file,  HttpServletRequest request)  {
		//????????????????????????
		// ????????????
		ExcelReader reader = null;
		String path = null;
		try {
				UserBean user = AuthSystemFacade.getLoginUserInfo();
				String filename = file.getOriginalFilename();
			    String[] strArray = filename.split("\\.");
		        int suffixIndex = strArray.length -1;
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "."+strArray[suffixIndex];
				 path = CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+fileName;
				// ???????????????
				file.transferTo(new File(path));
				//????????????
				 reader = ExcelUtil.getReader(FileUtil.file(path));
				//?????????Map???????????????????????????????????????Map??????key????????????value?????????????????????????????????
				List<Map<String,Object>> readAll = reader.readAll();
				//??????????????????
				//????????????????????????????????????????????????  1.???????????????????????????????????? 2.??????????????????
				//  1.???????????????????????????????????? 
				/*AjaxResult checkAjaxRes = checkDeptName(readAll, user.getCusNumber());
				if(checkAjaxRes.getCode()==RyglConstant.REE_CODE) {
					return checkAjaxRes;
				}*/
				//2.??????????????????
				/*for(Map<String,Object> ex:readAll) {
					RyglEntity ry = new RyglEntity();
					String policeBh =  ex.get("??????").toString();
					ry.setPoliceBh(policeBh);
					long selectCountByEntity = this.getDao().selectCountByEntity(ry);
					if(selectCountByEntity>0) {
						return AjaxResult.error("??????"+policeBh+"??????,???????????????");
					}
				}*/
				//????????????????????????
				List<RyglEntity> ryList = new ArrayList<RyglEntity>();
				for (int i = 0; i < readAll.size(); i++) {
					RyglEntity ryglEntity = new RyglEntity();
					Map<String, String> convetValue = convetValue(readAll.get(i).get("??????")==null?"":readAll.get(i).get("??????").toString(),readAll.get(i).get("????????????").toString(),readAll.get(i).get("??????").toString(),user.getCusNumber());
					ryglEntity.setDeptNumber(convetValue.get("deptNumber"));
					ryglEntity.setRyzt(convetValue.get("ryzt"));
					ryglEntity.setSex(convetValue.get("sex"));
					ryglEntity.setCusNumber(user.getCusNumber());
					ryglEntity.setCreateName(user.getUserId());
					ryglEntity.setCreateTime(DateUtils.getCurrentDate());
					ryglEntity.setJobLevel(readAll.get(i).get("??????")==null?"":readAll.get(i).get("??????").toString().trim());
					ryglEntity.setPosition(readAll.get(i).get("??????")==null?"":readAll.get(i).get("??????").toString());
					ryglEntity.setCsrq(readAll.get(i).get("????????????")==null?"":readAll.get(i).get("????????????").toString().trim());
					ryglEntity.setName(readAll.get(i).get("??????").toString());
					ryglEntity.setPoliceBh(readAll.get(i).get("??????")==null?"":readAll.get(i).get("??????").toString().trim());
					//????????????
					if(readAll.get(i).get("????????????")!=null&&readAll.get(i).get("????????????")!="") {
						String  birthDay1=readAll.get(i).get("????????????").toString();
						Date birthDay2 = DateUtils.parseDate(birthDay1);
						String age = getAgeByBirth(birthDay2);
						ryglEntity.setNl(age);
					}
                              ryglEntity.setPbOrder(i+1);
                              ryglEntity.setPbCount(0);
					ryglEntity.setJob(readAll.get(i).get("????????????")==null?"":readAll.get(i).get("????????????").toString().trim());
					ryList.add(ryglEntity);
				}
				this.getDao().insert(ryList);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.error(e.getMessage());
			}finally {
				reader.close();
				 //????????????Excel
				 FileUtil.del(new File(path));
			}
		
		return AjaxResult.success("??????????????????");
	}
	//??????
	public  static Map<String,String> convetValue(String deptNumber,String ryzt,String sex,String cusNumber)throws Exception {
		 List<OrgEntity> allChildrenOrgInfoByOrgKey = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
		 Map<String,String> map = new HashMap<String, String>();
		 if(StringUtil.isNull(deptNumber)){
                 map.put("deptNumber", "");
             }else {
                 for(OrgEntity or:allChildrenOrgInfoByOrgKey) {
                     if(or.getOrgName().equals(deptNumber)) {
                         map.put("deptNumber", or.getOrgKey());
                     }
                 }
             }

		 //????????????  1.??????2.?????? 3.??????4.??????5.?????? 6.??????
		 if("??????".equals(ryzt.trim())) {
			 map.put("ryzt", "1");
		 }else if("??????".equals(ryzt.trim())) {
			map.put("ryzt", "2");
		 }else if("??????".equals(ryzt.trim())) {
				map.put("ryzt", "3");
		 }else if("??????".equals(ryzt.trim())) {
				map.put("ryzt", "4");
		 }else if("??????".equals(ryzt.trim())) {
			 map.put("ryzt", "5");
		 }else{
                 map.put("ryzt", "6");
             }
		 
		 if("???".equals(sex.trim())) {
			 map.put("sex", "0");
		 }else {
			 map.put("sex", "1"); 
		 }
		return map;
	}
	
	/**
	 * ??????????????????????????????
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	 public static String getAgeByBirth(Date birthDay) throws Exception  {
	        int age = 0;
	        Calendar cal = Calendar.getInstance();
	        if (cal.before(birthDay)) { //?????????????????????????????????????????????
	            throw new IllegalArgumentException(
	                    "?????????????????????????????????????????????!");
	        }
	        int yearNow = cal.get(Calendar.YEAR);  //????????????
	        int monthNow = cal.get(Calendar.MONTH);  //????????????
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //????????????
	        cal.setTime(birthDay);
	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
	        age = yearNow - yearBirth;   //???????????????
	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                if (dayOfMonthNow < dayOfMonthBirth) age--;//??????????????????????????????????????????
	            } else {
	                age--;//??????????????????????????????????????????
	            }
	        }
	        String valueOf = String.valueOf(age);
	        return valueOf;
	    }
	 
	 /**
	  * ?????????????????????????????????????????????
	  * @param
	  * @return
	  */
	 public static AjaxResult checkDeptName(List<Map<String,Object>> excelData,String cusNumber) throws Exception {
		 boolean flag = false;
		 for(Map<String,Object> ed :excelData ) {
			 String deptName = ed.get("??????").toString();
			 List<OrgEntity> allChildrenOrgInfoByOrgKey = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
			 for(OrgEntity or:allChildrenOrgInfoByOrgKey) {
				 if(or.getOrgName().equals(deptName)) {
					 flag = true;
				 }
			 }
			 if(!flag) {
				return  AjaxResult.error(deptName+"?????????????????????,?????????,???????????????");
			 }
		 }
		
		 return AjaxResult.success();
	 }
	 /**
	  * ????????????
	  */
	@Override
	public AjaxResult writerByIds(String ids) {
		//???????????????
		String path =CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+"xczbrymb.xlsx";
		String copyPath =CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+IdUtil.simpleUUID()+".xlsx";
		ExcelWriter writer = null;
		try {
			 String[] strings = ids.split(",");
		        List<String> list = new ArrayList<String>();
		        for (String id:strings){
		            list.add(id);
		        }
		         List<RyglEntity> ryList = this.getDao().selecListtByIds(list);
		         List<List<String>> rows = new ArrayList<List<String>>();
		        // List<String> row1 = CollUtil.newArrayList("??????", "??????", "??????", "??????","????????????","??????","??????","??????","????????????");
		         //rows.add(row1);
		         for(RyglEntity  ry:ryList) {
		        	 List<String> row = new ArrayList<String>();
		        	 Map<String, String> mapValue = convetCode(ry.getSex(),ry.getCusNumber(),ry.getDeptNumber(),ry.getRyzt());
		        	 row.add(mapValue.get("cusNumber"));
		        	 row.add(mapValue.get("deptNumber"));
		        	 row.add(ry.getName());
		        	 row.add(ry.getPoliceBh());
		        	 row.add(ry.getCsrq());
		        	 row.add(mapValue.get("sex"));
		        	 row.add(ry.getJobLevel());
		        	 row.add(ry.getPosition());
		        	 row.add(mapValue.get("ryzt"));
		        	 rows.add(row);
		         }
		        FileUtil.copy(path, copyPath, false);
		         // ?????????????????????writer
		          writer = ExcelUtil.getWriter(copyPath);
		        //??????????????????????????????
		          writer.passCurrentRow();
		         // ???????????????????????????????????????????????????????????????
		         writer.write(rows, true);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}finally {
			writer.close();
		}
		
		return AjaxResult.success(copyPath);
	}
	 
	/**
	 * ???code ?????? ????????????
	 * @param sex
	 * @param cusNumber
	 * @param deptNumber
	 * @param ryzt
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> convetCode(String sex,String cusNumber,String deptNumber,String ryzt) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		if(sex.equals("0")) {
			map.put("sex","???");
		}else {
			map.put("sex","???");
		}
		if(cusNumber.equals("4300")) {
			map.put("cusNumber", "????????????????????????");
		}else {
			List<OrgEntity> allJyInfo = AuthSystemFacade.getAllJyInfo();
			for(OrgEntity or:allJyInfo) {
				if(cusNumber.equals(or.getOrgKey())) {
					map.put("cusNumber", or.getOrgName());
				}
			}
		}
		List<OrgEntity> allChildrenOrgInfoByOrgKey = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
		 for(OrgEntity or:allChildrenOrgInfoByOrgKey) {
			 if(or.getOrgKey().equals(deptNumber)) {
				 map.put("deptNumber", or.getOrgName());
			 }
		 }
		 //????????????  1.??????2.?????? 3.??????4.??????5.??????
		 if("1".equals(ryzt)) {
			 map.put("ryzt", "??????");
		 }else if("2".equals(ryzt)) {
			map.put("ryzt", "??????");
		 }else if("3".equals(ryzt)) {
				map.put("ryzt", "??????");
		 }else if("4".equals(ryzt)) {
				map.put("ryzt", "??????");
		 }else {
			 map.put("ryzt", "??????");
		 }
		return map;
	}
	/**
	 * ????????????id ?????????id ????????????????????????
	 */
	@Override
	public List<RyglEntity> selectList(RyglEntity ryglEntity) {
		List<RyglEntity> selectByEntity = this.getDao().selectListByJob(ryglEntity);
		return selectByEntity;
	}

	@Override
	public List<RyglEntity> checkIsZhz(RyglEntity ry) {
		List<RyglEntity> selectByEntity = this.getDao().selectByEntity(ry);
		return selectByEntity;
	}

    @Override
    public List<Map<String, Object>> getUserInfoByCusNumber(String cusNumber) {
        return this.getDao().getUserInfoByCusNumber(cusNumber);
    }

    @Override
    @Transactional
    public void updateById(RyglEntity ryglEntity) {
        this.getDao().updateById(ryglEntity);
    }

    @Override
    public RyglEntity getMaxPbOrder( ) {
        return this.getDao().getMaxPbOrder();
    }

    @Override
    public List<DutyCountDto> dutyCount(Map<String, Object> map) {
        //map.get("year");
       // map.get("cusNumber");
        List<DutyCountDto> result = new ArrayList<>();
        //orderName  year  cusNumber  type  list id
        RyglEntity ryglEntity = new RyglEntity();
        ryglEntity.setCusNumber(map.get("cusNumber").toString());
        List<RyglEntity> ryglEntities = this.getDao().selectByEntity(ryglEntity);
        for(RyglEntity rygl:ryglEntities){
            DutyCountDto dutyCountDto = new DutyCountDto();
            dutyCountDto.setCusNumber(rygl.getCusNumber());
            dutyCountDto.setId(rygl.getId());
            dutyCountDto.setName(rygl.getName());
            map.put("id",rygl.getId());
            Integer yearCount = this.getDao().dutyCount(map);
            //???????????????
            dutyCountDto.setYearCount(yearCount);
            //?????????
            //???????????????
            map.put("type","1");
            List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "1");
            List<String> list = new ArrayList<>();
            for(ZbRlwhEntity zbRl:zbRlwhEntities){
                list.add(zbRl.getDutyDate());
            }
            map.put("list",list);
            Integer hCount = this.getDao().dutyCount(map);
            dutyCountDto.setHolidaysCount(hCount);
            //?????????
            List<ZbRlwhEntity> workDays = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "0");
            List<String> list2 = new ArrayList<>();
            for(ZbRlwhEntity zbRl:workDays){
                list2.add(zbRl.getDutyDate());
            }
            map.put("list",list2);
            Integer wCount = this.getDao().dutyCount(map);
            dutyCountDto.setWorkDayCount(wCount);
            //??????
           // map.put("type","");
            map.remove("type");
            map.put("orderName", "??????");
            Integer zhCount = this.getDao().dutyCount(map);
            dutyCountDto.setMiddleCount(zhCount);
            //??????
            map.put("orderName", "??????");
            Integer wanCount = this.getDao().dutyCount(map);
           dutyCountDto.setNightCount(wanCount);
            map.remove("orderName");
            result.add(dutyCountDto);
        }
        return result;
    }


}

