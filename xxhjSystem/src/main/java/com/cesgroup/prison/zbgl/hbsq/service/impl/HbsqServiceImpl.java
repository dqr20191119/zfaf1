package com.cesgroup.prison.zbgl.hbsq.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.AuthSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.zbgl.hbsq.dao.HbsqMapper;
import com.cesgroup.prison.zbgl.hbsq.dto.ZbrxxDto;
import com.cesgroup.prison.zbgl.hbsq.entity.HbsqEntity;
import com.cesgroup.prison.zbgl.hbsq.service.HbsqService;
import com.cesgroup.prison.zbgl.rygl.dao.RyglMapper;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.zbbp.dao.ZbbpMapper;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午11:32:00
* 类说明:
*/
@Service
public class HbsqServiceImpl extends BaseDaoService<HbsqEntity, String, HbsqMapper> implements HbsqService {
	@Resource
	private RyglMapper ryglMapper;
	@Resource
	private ZbbpMapper zbbpMapper;
	@Override
	public Page<HbsqEntity> findList(HbsqEntity hbsqEntity, PageRequest pageRequest) {
		return this.getDao().findList(hbsqEntity, pageRequest);
	}

	@Override
	@Transactional
	public void updateById(HbsqEntity hbsqEntity) {
		this.getDao().updateById(hbsqEntity);
	}

	@Override
	@Transactional
	public void tyHbsq(String id) {
		//查询换班申请信息
		HbsqEntity hbsq = this.getDao().selectOne(id);
          UserBean user = AuthSystemFacade.getLoginUserInfo();
          RyglEntity tdrxx =new RyglEntity();
          RyglEntity hbrxx =new RyglEntity();
         if(EUserLevel.PROV.equals(user.getUserLevel())) {//省局
              tdrxx = ryglMapper.selectOne(hbsq.getTdr());
              hbrxx = ryglMapper.selectOne(hbsq.getHbr());
         }else{
             try {
                 String tdrName= AuthSystemFacade.getUserInfoByUserId(hbsq.getTdr()).getUserName();
                 tdrxx.setName(tdrName);
                String hbrName = AuthSystemFacade.getUserInfoByUserId(hbsq.getHbr()).getUserName();
                 hbrxx.setName(hbrName);
             }catch (Exception e){
                 e.printStackTrace();
             }

         }

		hbsq.setHbrName(hbrxx.getName());
		hbsq.setTdrName(tdrxx.getName());
		
		ZbbpEntity zbbp = new ZbbpEntity();
	   //查询换班人的值排班表id
		zbbp.setDbdCusNumber(hbsq.getCusNumber());
		zbbp.setDbdDutyDate(DateUtils.parseDate(hbsq.getDutyDate()));
		zbbp.setDbdStaffId(hbsq.getHbr());
		List<ZbbpEntity> hbrzbxx = zbbpMapper.selectByEntity(zbbp);
		String hbzbxxId = "";
		if(hbrzbxx.size()>0) {
			hbzbxxId = hbrzbxx.get(0).getId();
		}
		
		ZbbpEntity zbbp2 = new ZbbpEntity();
		//查询替代人值排班信息
		zbbp2.setDbdCusNumber(hbsq.getCusNumber());
		zbbp2.setDbdDutyDate(DateUtils.parseDate(hbsq.getHbDate()));
		zbbp2.setDbdStaffId(hbsq.getTdr());
		
		List<ZbbpEntity> tdrzbxx = zbbpMapper.selectByEntity(zbbp2);
		String tdrzbxxId = "";
		if(tdrzbxx.size()>0) {
			tdrzbxxId = tdrzbxx.get(0).getId();
		}
		
		//进行换班 换班人值班信息的改为替代人的值班信息
		ZbbpEntity zbbp3 = new ZbbpEntity();
		zbbp3.setId(hbzbxxId);
		zbbp3.setDbdStaffId(hbsq.getTdr());
		zbbp3.setDbdStaffName(hbsq.getTdrName());
		this.getDao().updateZbrById(zbbp3);
		
		//替代人的值班信息改为换班人的值班信息
		ZbbpEntity zbbp4 = new ZbbpEntity();
		zbbp4.setId(tdrzbxxId);
		zbbp4.setDbdStaffId(hbsq.getHbr());
		zbbp4.setDbdStaffName(hbsq.getHbrName());
		this.getDao().updateZbrById(zbbp4);
		
	}

	@Override
	public Integer checkZbrIsZbbp(ZbrxxDto zbrxxDto) {
	Integer count =	this.getDao().checkZbrIsZbbp(zbrxxDto);
		return count;
	}

    @Override
    public AjaxResult checkIsZhz(String dutyDate) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        List<Map<String, Object>> mapList = this.getDao().checkIsZhz(dutyDate, user.getCusNumber(),user.getRealName());
        if(mapList.size() >0){
           return AjaxResult.success();
        }else{
            return  AjaxResult.error();
        }
    }

}
