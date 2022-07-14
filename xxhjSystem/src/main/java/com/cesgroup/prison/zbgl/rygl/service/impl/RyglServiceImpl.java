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
            //查询状态历史表记录 不参与值班
            RyztHistoryEntity ryztHistoryEntity = new RyztHistoryEntity();
            ryztHistoryEntity.setZbryId(ryglEntity1.getId());
            ryztHistoryEntity.setYear(ryglEntity1.getYear());
            List<RyztHistoryEntity> ryztHistoryEntities = ryztHistoryMapper.selectByZbryId(ryztHistoryEntity);
            if(ryztHistoryEntities.size() >0){
                ryglEntity1.setNoDuty(appendNoduty(ryztHistoryEntities));
            }
            map.put("id",ryglEntity1.getId());
            Integer yearCount = this.getDao().dutyCount(map);
            //全年值班数
            ryglEntity1.setYearCount(yearCount);
            //节假日
            //查询节假日
            map.put("type","1");
            List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "1");
            List<String> list = new ArrayList<>();
            for(ZbRlwhEntity zbRl:zbRlwhEntities){
                list.add(zbRl.getDutyDate());
            }
            map.put("list",list);
            Integer hCount = this.getDao().dutyCount(map);
            ryglEntity1.setHolidaysCount(hCount);
            //工作日
            List<ZbRlwhEntity> workDays = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "0");
            List<String> list2 = new ArrayList<>();
            for(ZbRlwhEntity zbRl:workDays){
                list2.add(zbRl.getDutyDate());
            }
            map.put("list",list2);
            Integer wCount = this.getDao().dutyCount(map);
            ryglEntity1.setWorkDayCount(wCount);

            //法定节假日
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

            //中班
            // map.put("type","");
            map.remove("type");
            map.put("orderName", "中班");
            Integer zhCount = this.getDao().dutyCount(map);
            ryglEntity1.setMiddleCount(zhCount);
            //晚班
            map.put("orderName", "晚班");
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
     * 根据id 更新标记表
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
        //获取下个月初和月末
       Date startDate = (Date)nextMonths.get("startDate");
       Date enDate =(Date)nextMonths.get("endDate");
       RyglEntity ryglEntity = new RyglEntity();
       ryglEntity.setStartDate(sdf.format(startDate));
       ryglEntity.setEndDate(sdf.format(enDate));
       String str ="";
       List<RyglEntity> ryglEntities = this.getDao().remindMessageByRyzy(ryglEntity);
        if(ryglEntities.size() >0){
            for(RyglEntity ry:ryglEntities){
                str +=ry.getName()+":"+converToName(ry.getRyzt(),ry.getRemark())+"结束时间:"+ry.getEndDate()+";";
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
        //进行后移
        this.getDao().updateBypbOrder(r);
        return pbOrder;
    }

    @Override
    @Transactional
    public void afterMove(RyglEntity ryglEntity) {
        //进行后移
        this.getDao().updateBypbOrder(ryglEntity);
    }


    private String appendNoduty(List<RyztHistoryEntity> list){
        String noDuty = "";
        if(list !=null && list.size() >0){
            for(RyztHistoryEntity ryztHistoryEntity:list){
                noDuty +=converToName(ryztHistoryEntity.getRyzt(),ryztHistoryEntity.getRemark())+"开始时间:"+ryztHistoryEntity.getStartDate()+"结束时间:"+ryztHistoryEntity.getEndDate()+";";
            }
        }
        return noDuty;
    }



    private String converToName(String ryzt,String remark){
        String str = "";
        if(StringUtil.isNull(remark)){
            if("4".equals(ryzt)){
                str ="培训";
            }else if("5".equals(ryzt)){
                str ="病假";
            }else if("6".equals(ryzt)){
                str ="其他";
            }
        }else{
            if("4".equals(ryzt)){
                str ="培训,"+remark;
            }else if("5".equals(ryzt)){
                str ="病假,"+remark;
            }else if("6".equals(ryzt)){
                str ="其他,"+remark;
            }

        }
        return str;
    }



    @Override
	@Transactional
	public AjaxResult importFile(MultipartFile file,  HttpServletRequest request)  {
		//保存文件到到磁盘
		// 文件路径
		ExcelReader reader = null;
		String path = null;
		try {
				UserBean user = AuthSystemFacade.getLoginUserInfo();
				String filename = file.getOriginalFilename();
			    String[] strArray = filename.split("\\.");
		        int suffixIndex = strArray.length -1;
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "."+strArray[suffixIndex];
				 path = CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+fileName;
				// 保存到磁盘
				file.transferTo(new File(path));
				//读取数据
				 reader = ExcelUtil.getReader(FileUtil.file(path));
				//读取为Map列表，默认第一行为标题行，Map中的key为标题，value为标题对应的单元格值。
				List<Map<String,Object>> readAll = reader.readAll();
				//插入到数据库
				//插入数据库之前先要对数据进行核对  1.部门要在系统平台中查的到 2.警号不能重复
				//  1.部门要在系统平台中查的到 
				/*AjaxResult checkAjaxRes = checkDeptName(readAll, user.getCusNumber());
				if(checkAjaxRes.getCode()==RyglConstant.REE_CODE) {
					return checkAjaxRes;
				}*/
				//2.警号不能重复
				/*for(Map<String,Object> ex:readAll) {
					RyglEntity ry = new RyglEntity();
					String policeBh =  ex.get("警号").toString();
					ry.setPoliceBh(policeBh);
					long selectCountByEntity = this.getDao().selectCountByEntity(ry);
					if(selectCountByEntity>0) {
						return AjaxResult.error("警号"+policeBh+"重复,请重新导入");
					}
				}*/
				//表示可以插入数据
				List<RyglEntity> ryList = new ArrayList<RyglEntity>();
				for (int i = 0; i < readAll.size(); i++) {
					RyglEntity ryglEntity = new RyglEntity();
					Map<String, String> convetValue = convetValue(readAll.get(i).get("部门")==null?"":readAll.get(i).get("部门").toString(),readAll.get(i).get("人员状态").toString(),readAll.get(i).get("性别").toString(),user.getCusNumber());
					ryglEntity.setDeptNumber(convetValue.get("deptNumber"));
					ryglEntity.setRyzt(convetValue.get("ryzt"));
					ryglEntity.setSex(convetValue.get("sex"));
					ryglEntity.setCusNumber(user.getCusNumber());
					ryglEntity.setCreateName(user.getUserId());
					ryglEntity.setCreateTime(DateUtils.getCurrentDate());
					ryglEntity.setJobLevel(readAll.get(i).get("职级")==null?"":readAll.get(i).get("职级").toString().trim());
					ryglEntity.setPosition(readAll.get(i).get("职务")==null?"":readAll.get(i).get("职务").toString());
					ryglEntity.setCsrq(readAll.get(i).get("出生日期")==null?"":readAll.get(i).get("出生日期").toString().trim());
					ryglEntity.setName(readAll.get(i).get("姓名").toString());
					ryglEntity.setPoliceBh(readAll.get(i).get("警号")==null?"":readAll.get(i).get("警号").toString().trim());
					//计算年龄
					if(readAll.get(i).get("出生日期")!=null&&readAll.get(i).get("出生日期")!="") {
						String  birthDay1=readAll.get(i).get("出生日期").toString();
						Date birthDay2 = DateUtils.parseDate(birthDay1);
						String age = getAgeByBirth(birthDay2);
						ryglEntity.setNl(age);
					}
                              ryglEntity.setPbOrder(i+1);
                              ryglEntity.setPbCount(0);
					ryglEntity.setJob(readAll.get(i).get("值班岗位")==null?"":readAll.get(i).get("值班岗位").toString().trim());
					ryList.add(ryglEntity);
				}
				this.getDao().insert(ryList);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.error(e.getMessage());
			}finally {
				reader.close();
				 //删除磁盘Excel
				 FileUtil.del(new File(path));
			}
		
		return AjaxResult.success("数据导入成功");
	}
	//转码
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

		 //人员状态  1.在编2.借调 3.退休4.培训5.病假 6.其他
		 if("在编".equals(ryzt.trim())) {
			 map.put("ryzt", "1");
		 }else if("借调".equals(ryzt.trim())) {
			map.put("ryzt", "2");
		 }else if("退休".equals(ryzt.trim())) {
				map.put("ryzt", "3");
		 }else if("培训".equals(ryzt.trim())) {
				map.put("ryzt", "4");
		 }else if("病假".equals(ryzt.trim())) {
			 map.put("ryzt", "5");
		 }else{
                 map.put("ryzt", "6");
             }
		 
		 if("男".equals(sex.trim())) {
			 map.put("sex", "0");
		 }else {
			 map.put("sex", "1"); 
		 }
		return map;
	}
	
	/**
	 * 根据出生日期计算年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	 public static String getAgeByBirth(Date birthDay) throws Exception  {
	        int age = 0;
	        Calendar cal = Calendar.getInstance();
	        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
	            throw new IllegalArgumentException(
	                    "出生日期晚于当前时间，无法计算!");
	        }
	        int yearNow = cal.get(Calendar.YEAR);  //当前年份
	        int monthNow = cal.get(Calendar.MONTH);  //当前月份
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
	        cal.setTime(birthDay);
	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
	        age = yearNow - yearBirth;   //计算整岁数
	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
	            } else {
	                age--;//当前月份在生日之前，年龄减一
	            }
	        }
	        String valueOf = String.valueOf(age);
	        return valueOf;
	    }
	 
	 /**
	  * 检查部门数据的在系统中是否都有
	  * @param
	  * @return
	  */
	 public static AjaxResult checkDeptName(List<Map<String,Object>> excelData,String cusNumber) throws Exception {
		 boolean flag = false;
		 for(Map<String,Object> ed :excelData ) {
			 String deptName = ed.get("部门").toString();
			 List<OrgEntity> allChildrenOrgInfoByOrgKey = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
			 for(OrgEntity or:allChildrenOrgInfoByOrgKey) {
				 if(or.getOrgName().equals(deptName)) {
					 flag = true;
				 }
			 }
			 if(!flag) {
				return  AjaxResult.error(deptName+"在系统中未维护,修改后,请重新导入");
			 }
		 }
		
		 return AjaxResult.success();
	 }
	 /**
	  * 导出数据
	  */
	@Override
	public AjaxResult writerByIds(String ids) {
		//源文件路径
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
		        // List<String> row1 = CollUtil.newArrayList("单位", "部门", "姓名", "警号","出生日期","性别","职级","职务","人员状态");
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
		         // 通过工具类创建writer
		          writer = ExcelUtil.getWriter(copyPath);
		        //跳过当前行，既第一行
		          writer.passCurrentRow();
		         // 一次性写出内容，使用默认样式，强制输出标题
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
	 * 将code 转为 中文含义
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
			map.put("sex","男");
		}else {
			map.put("sex","女");
		}
		if(cusNumber.equals("4300")) {
			map.put("cusNumber", "湖南省监狱管理局");
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
		 //人员状态  1.在编2.借调 3.退休4.培训5.病假
		 if("1".equals(ryzt)) {
			 map.put("ryzt", "在编");
		 }else if("2".equals(ryzt)) {
			map.put("ryzt", "借调");
		 }else if("3".equals(ryzt)) {
				map.put("ryzt", "退休");
		 }else if("4".equals(ryzt)) {
				map.put("ryzt", "培训");
		 }else {
			 map.put("ryzt", "病假");
		 }
		return map;
	}
	/**
	 * 通过监狱id 和部门id 查询值班人员信息
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
            //全年值班数
            dutyCountDto.setYearCount(yearCount);
            //节假日
            //查询节假日
            map.put("type","1");
            List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "1");
            List<String> list = new ArrayList<>();
            for(ZbRlwhEntity zbRl:zbRlwhEntities){
                list.add(zbRl.getDutyDate());
            }
            map.put("list",list);
            Integer hCount = this.getDao().dutyCount(map);
            dutyCountDto.setHolidaysCount(hCount);
            //工作日
            List<ZbRlwhEntity> workDays = zbRlwhMapper.selectDateByYear(map.get("year").toString(), "0");
            List<String> list2 = new ArrayList<>();
            for(ZbRlwhEntity zbRl:workDays){
                list2.add(zbRl.getDutyDate());
            }
            map.put("list",list2);
            Integer wCount = this.getDao().dutyCount(map);
            dutyCountDto.setWorkDayCount(wCount);
            //中班
           // map.put("type","");
            map.remove("type");
            map.put("orderName", "中班");
            Integer zhCount = this.getDao().dutyCount(map);
            dutyCountDto.setMiddleCount(zhCount);
            //晚班
            map.put("orderName", "晚班");
            Integer wanCount = this.getDao().dutyCount(map);
           dutyCountDto.setNightCount(wanCount);
            map.remove("orderName");
            result.add(dutyCountDto);
        }
        return result;
    }


}

