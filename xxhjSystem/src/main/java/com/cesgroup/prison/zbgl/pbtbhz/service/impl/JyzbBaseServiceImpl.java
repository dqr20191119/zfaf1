package com.cesgroup.prison.zbgl.pbtbhz.service.impl;

import java.beans.Transient;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.poi.excel.ExcelReader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.zbgl.pbtbhz.dao.JyzbBaseMapper;
import com.cesgroup.prison.zbgl.pbtbhz.dao.JyzbDetailMapper;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbBaseEntity;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbDetailEntity;
import com.cesgroup.prison.zbgl.pbtbhz.service.JyzbBaseService;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.multipart.MultipartFile;

/**
* @author lihong
* @date 创建时间：2020年5月25日 下午4:45:58
* 类说明:
*/
@Service
public class JyzbBaseServiceImpl extends BaseDaoService<JyzbBaseEntity, String, JyzbBaseMapper> implements JyzbBaseService {
	
	@Resource
	private JyzbDetailMapper jyzbDetailMapper;

	@Override
	public Page<JyzbBaseEntity> findList(JyzbBaseEntity jyzbBaseEntity, PageRequest pageRequest) {
		return this.getDao().findList(jyzbBaseEntity, pageRequest);
	}

	@Override
	@Transactional
	public void deleteById(String id) throws Exception {
		this.getDao().delete(id);
		jyzbDetailMapper.deleteByYwId(id);
	}

	@Override
	@Transactional
	public void updateById(JyzbBaseEntity jyzbBaseEntity) {
		this.getDao().updateById(jyzbBaseEntity);
	}

	@Override
	public List<JyzbDetailEntity> getJyzbDetailByYwID(JyzbBaseEntity jyzbBaseEntity) {
		List<JyzbDetailEntity> jyzbList = new ArrayList<JyzbDetailEntity>();
		if(jyzbBaseEntity.getId()!=null && !"".equals(jyzbBaseEntity.getId())) {
			//修改  进行id查询
			JyzbDetailEntity jyzb = new JyzbDetailEntity();
			jyzb.setYwId(jyzbBaseEntity.getId());
			jyzbList = jyzbDetailMapper.selectByEntity(jyzb);
		}else {
			//新增  初始化
			Map<String, String> ycAndYm = getYcAndYm(jyzbBaseEntity.getZbYf());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String  fisrtDay = ycAndYm.get("fisrtDay");
			String  lastDay = ycAndYm.get("lastDay");
			try {
				String difDay = CommonUtil.getDateDiff(sdf.parse(fisrtDay), sdf.parse(lastDay));
				Integer days =Integer.valueOf(difDay);
				for (int i = 0; i < days+1; i++) {
					JyzbDetailEntity jyzb = new JyzbDetailEntity();
					jyzb.setDutyDate(sdf.format(CommonUtil.addDate(sdf.parse(fisrtDay), i)));
					jyzbList.add(jyzb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return jyzbList;
	}
	
	public static Map<String,String> getYcAndYm(String date) {
		Map<String,String> map = new HashMap<String, String>();
		
		Calendar cale = Calendar.getInstance();
		SimpleDateFormat fommatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			cale.setTime(fommatter.parse(date));
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH,1);
			map.put("fisrtDay", fommatter.format(cale.getTime()));
			cale.add(Calendar.MONTH, 1);
			cale.set(Calendar.DAY_OF_MONTH,0);
			map.put("lastDay", fommatter.format(cale.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	@Transactional
	public void saveOrUpdate(JyzbBaseEntity jyzbBaseEntity) {
		//JSONArray parseArray = JSONUtil.parseArray(jyzbBaseEntity.getJyzbDetails());
		//parseArray.to
		ObjectMapper objectMapper = new ObjectMapper();
		List<JyzbDetailEntity> jyzbDetails = new  ArrayList<JyzbDetailEntity>();
		try {
			  jyzbDetails= objectMapper.readValue(jyzbBaseEntity.getJyzbDetails(), new TypeReference<List<JyzbDetailEntity>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
			if("1".equals(jyzbBaseEntity.getParam())) {
				//修改  先删除之前的
				jyzbDetailMapper.deleteByYwId(jyzbBaseEntity.getId());
				this.getDao().updateById(jyzbBaseEntity);
				for(JyzbDetailEntity jyzbDetail:jyzbDetails) {
					jyzbDetail.setYwId(jyzbBaseEntity.getId());
					jyzbDetail.setCjrId(jyzbBaseEntity.getCjrId());
					jyzbDetail.setCjrName(jyzbBaseEntity.getCjrName());
					jyzbDetail.setCjrq(jyzbBaseEntity.getCjrq());
					jyzbDetail.setCusNumber(jyzbBaseEntity.getCusNumber());
					jyzbDetailMapper.insert(jyzbDetail);
				}
			}else if("2".equals(jyzbBaseEntity.getParam())) {
				//新增
				this.getDao().insert(jyzbBaseEntity);
				for(JyzbDetailEntity jyzbDetail:jyzbDetails) {
					jyzbDetail.setYwId(jyzbBaseEntity.getId());
					jyzbDetail.setCjrId(jyzbBaseEntity.getCjrId());
					jyzbDetail.setCjrName(jyzbBaseEntity.getCjrName());
					jyzbDetail.setCjrq(jyzbBaseEntity.getCjrq());
					jyzbDetail.setCusNumber(jyzbBaseEntity.getCusNumber());
					jyzbDetailMapper.insert(jyzbDetail);
				}
			}
			
			
	}

	@Override
	public AjaxResult checkJyzbByzbYf(JyzbBaseEntity jyzbBaseEntity) {
		List<JyzbBaseEntity> selectByEntity = this.getDao().selectByEntity(jyzbBaseEntity);
		String flag = "true";
		if(selectByEntity.size()<=0) {
			flag = "false";
		}
		return AjaxResult.success(flag);
	}

	@Override
	@Transactional
	public Page<JyzbDetailEntity> getZbDetailByDay(String dutyDate,String zbYf,PageRequest pageRequest) {
		JyzbBaseEntity jyzb = new JyzbBaseEntity();
		if("".equals(zbYf)) {
			String zbYfNow = dutyDate.substring(0,dutyDate.length()-3);
			jyzb.setZbYf(zbYfNow);
		}else {
			jyzb.setZbYf(zbYf);
		}
		jyzb.setZt("2");
		this.getDao().updateZtByZbYf(jyzb);//更新已上报的该月份值班表为审批状态
		JyzbDetailEntity jyzbDetail = new JyzbDetailEntity();
		if(!"".equals(dutyDate)) {
			jyzbDetail.setDutyDate(dutyDate);
		}
		if(!"".equals(zbYf)) {
			jyzbDetail.setZbYf(zbYf);
		}
		Page<JyzbDetailEntity> jyzbDetails = jyzbDetailMapper.getByDutyDate(jyzbDetail,pageRequest);
		return jyzbDetails;
	}
	/**
	 * 写出指定天的值班汇总的数据 或指定月的汇总数据
	 */
	@Override
	public AjaxResult writerByDutyDate(String dutyDate,String zbYf) {
		//源文件路径
				String path =CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+"jyzbb.xlsx";
				String copyPath =CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+IdUtil.simpleUUID()+".xlsx";
				ExcelWriter   writer = null;
				try {
					JyzbDetailEntity jyzbDetail = new JyzbDetailEntity();
					if(!"".equals(dutyDate)) {
						jyzbDetail.setDutyDate(dutyDate);
					}
					if(!"".equals(zbYf)) {
						jyzbDetail.setZbYf(zbYf);
					}
					List<JyzbDetailEntity> zbDatas = jyzbDetailMapper.getByDutyDateOrZbyf(jyzbDetail);
					 List<List<String>> rows = new ArrayList<List<String>>();
				   //   List<String> row1 = CollUtil.newArrayList("序号","监狱","值班日期","指挥长","值班长","值班人员","值班电话");
				     //    rows.add(row1);
				       for (int i = 0; i < zbDatas.size(); i++) {
				    	   List<String> row = new ArrayList<String>();
				    	   row.add(String.valueOf(i+1));
				    	   row.add(convetCode(zbDatas.get(i).getCusNumber()).get("cusNumber"));
				    	   row.add(zbDatas.get(i).getDutyDate());
				    	   row.add(zbDatas.get(i).getZhz());
				    	   row.add(zbDatas.get(i).getZbz());
				    	   row.add(zbDatas.get(i).getZby());
				    	   row.add(zbDatas.get(i).getZbDh());
				    	   rows.add(row);
					}
				        FileUtil.copy(path, copyPath, false);
				         // 通过工具类创建writer
				          writer = ExcelUtil.getWriter(copyPath);
				          writer.passCurrentRow();
				         // 一次性写出内容，使用默认样式
				         writer.write(rows,true);
				         writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.error(e.getMessage());
				}finally {
					writer.close();
				}
				
				return AjaxResult.success(copyPath);
	}

    @Override
    @Transactional
    public AjaxResult importFile(MultipartFile file, HttpServletRequest request) {
        //保存文件到到磁盘
        // 文件路径
        String cusNumber = request.getParameter("cusNumber");
        String zbyf = request.getParameter("zbyf");
        ExcelReader reader = null;
        String path = null;
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try {

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
            //日期	指挥长	值班电话	值班长U	值班人员
            JyzbBaseEntity jyzbBaseEntity = new JyzbBaseEntity();
            jyzbBaseEntity.setZbYf(zbyf);
            if(EUserLevel.PROV.equals(user.getUserLevel())){
                jyzbBaseEntity.setZt("2");
            }else{
                jyzbBaseEntity.setZt("0");
            }
            jyzbBaseEntity.setCusNumber(cusNumber);
            jyzbBaseEntity.setCjrId(user.getUserId());
            jyzbBaseEntity.setCjrName(user.getUserName());
            jyzbBaseEntity.setCjrq(Util.getCurrentDate());
            jyzbBaseEntity.setZbDh(readAll.get(0).get("值班电话")==null?"":readAll.get(0).get("值班电话").toString());
            this.getDao().insert(jyzbBaseEntity);
            //插入明细
            String startDate = zbyf+"-01";
            for (int i = 0; i < readAll.size(); i++) {
               String date  = sdf.format(Util.addDate(sdf.parse(startDate),i));
                JyzbDetailEntity jyzbDetailEntity = new JyzbDetailEntity();
                jyzbDetailEntity.setDutyDate(date);
                jyzbDetailEntity.setYwId(jyzbBaseEntity.getId());
                jyzbDetailEntity.setCjrId(user.getUserId());
                jyzbDetailEntity.setCjrName(user.getUserName());
                jyzbDetailEntity.setCjrq(Util.getCurrentDate());
                jyzbDetailEntity.setCusNumber(cusNumber);
                jyzbDetailEntity.setZhz(readAll.get(i).get("指挥长")==null?"":readAll.get(i).get("指挥长").toString());
                jyzbDetailEntity.setZbz(readAll.get(i).get("值班长")==null?"":readAll.get(i).get("值班长").toString());
                jyzbDetailEntity.setZby(readAll.get(i).get("值班人员")==null?"":readAll.get(i).get("值班人员").toString());
                jyzbDetailMapper.insert(jyzbDetailEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("导入失败");
        }finally {
            reader.close();
            //删除磁盘Excel
            FileUtil.del(new File(path));
        }

        return AjaxResult.success("文件导入成功");
    }


    /**
	 * 将code 转为 中文含义
	 * @param cusNumber
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> convetCode(String cusNumber) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
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
		return map;
	}

	
	
	
}
