package com.cesgroup.prison.aqfxyp.fxcj.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.aqfxyp.fxcj.common.FxcjConstant;
import com.cesgroup.prison.aqfxyp.fxcj.dao.FxcjMapper;
import com.cesgroup.prison.aqfxyp.fxcj.entity.Fxcj;
import com.cesgroup.prison.aqfxyp.fxcj.service.FxcjService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.rwgl.entity.RwxfEntity;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.wwjg.fxdgl.dao.FxdglMapper;
import com.cesgroup.prison.wwjg.fxdgl.entity.FxdglEntity;
import com.cesgroup.prison.wwjg.sjfwgl.dao.SjfwglMapper;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;
import com.cesgroup.prison.wwjg.wwjgwh.dao.WwjgwhMapper;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;
import com.sun.xml.internal.ws.api.server.SDDocumentFilter;

/**
 * Description: 风险采集业务操作类接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月15日
 */
@Service(value = "fxcjService")
public class FxcjServiceImpl extends BaseDaoService<Fxcj, String, FxcjMapper> implements FxcjService {
	/**
	 * 五维架构维护Dao
	 */
	@Autowired
	private WwjgwhMapper wwjgwhMapper;
	/**
	 * 数据范围管理Dao
	 */
	@Autowired
	private SjfwglMapper sjfwglMapper;
	/**
	 * 风险点管理Dao
	 */
	@Autowired
	private FxdglMapper fxdglMapper;

	@Override
	public List<TreeDto> initFxysTree() throws BusinessLayerException {
		try {
			List<TreeDto> treeList = this.packageFxysTreeData();
			return treeList;
		} catch (Exception e) {
			throw new BusinessLayerException("初始化风险要素树形结构发生异常，Exception info is: " + e.getMessage());
		}
	}
	
	@Override
	public Page<Fxcj> findList(Fxcj fxcj, PageRequest pageRequest) {
		UserBean user=AuthSystemFacade.getLoginUserInfo();
		String jyId=user.getCusNumber();
		fxcj.setJyId(jyId);
		Page<Fxcj> pageList = this.getDao().findList(fxcj, pageRequest);
		return pageList;
	}
	@Override 
	public Page<Fxcj> findList(String jQName, String wwName,String bz,String date, PageRequest pageRequest) {
		UserBean user=AuthSystemFacade.getLoginUserInfo();
		String jyId=user.getCusNumber();
		Page<Fxcj> pageList = this.getDao().findListNew(jQName,wwName, bz,date,jyId,pageRequest);
		return pageList;
	}
	@Override
	@Transactional
	public String saveOrUpdate(Fxcj fxcj) throws BusinessLayerException {
		String id = fxcj.getId();
		try {
			// Description: id为空则新增，id不为空则更新
			if(id != null && !"".equals(id)) {
				this.getDao().update(fxcj);
			} else {
				String uuid = ((UUID.randomUUID()).toString()).replace("-", "");
				id = uuid;
				fxcj.setId(uuid);
				this.getDao().insert(fxcj);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("新增或保存风险采集数据发生异常，Exception info is:" + e);
		}
		return id;
	}

	@Override
	@Transactional
	public void deleteByIds(String ids) throws BusinessLayerException {
		String[] idArr = ids.split(",");
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			
			if(idArr != null && idArr.length > 0) {
				for(String id : idArr) {
					Fxcj fxcj = this.getDao().selectOne(id);
					if(fxcj != null) {
						// Description: 逻辑删除，将数据状态置为无效
						fxcj.setScflg(CommonConstant.STATUS_INVALID_CODE);
						fxcj.setcGxr(user.getUserName());
						fxcj.setcGxrId(user.getUserId());
						fxcj.setcGxRq(new Date());
						
						this.getDao().update(fxcj);
					}
				}
			}
		} catch (Exception e) {
			throw new BusinessLayerException("逻辑删除风险采集数据发生异常，Exception info is:" + e);
		}
	}

	/**
	 * Description: 封装安全风险评估要素树形结构
	 * @return
	 */
	private List<TreeDto> packageFxysTreeData() {
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		
		// 根节点设置
		TreeDto root = new TreeDto();
        root.setId(TreeDto.ROOT_ID);
        root.setCode(TreeDto.ROOT_CODE);
        root.setName("风险要素");
        root.setOpen(true);
        treeList.add(root);
		
		// 查询有效的五维架构
		List<WwjgwhEntity> wwjgList = this.queryValidWwjgList();
		
		// 循环五维架构，查询五维架构下的有效的数据范围
		List<SjfwglEntity> sjfwList = this.queryValidSjfwListInWwjgList(wwjgList);
		
		// 循环数据范围，查询数据范围下的有效的安全风险点
		List<FxdglEntity> fxdList = this.queryValidFxdListInSjfwList(sjfwList);
		
		// 将五维架构转化为TreeDto，并存入treeList
		if(wwjgList != null && wwjgList.size() > 0) {
			for(WwjgwhEntity wwjg : wwjgList) {
				TreeDto treeNode = new TreeDto();
				treeNode.setId(FxcjConstant.FxysTypePrefix.WWJG + wwjg.getId());
				treeNode.setCode(FxcjConstant.FxysTypePrefix.WWJG + wwjg.getCode());
				treeNode.setName(wwjg.getName());
				treeNode.setpId(root.getId());
				treeNode.setOpen(true);
				treeList.add(treeNode);
			}
		}
		// 将数据范围转化为TreeDto，并存入treeList
		if(sjfwList != null && sjfwList.size() > 0) {
			for(SjfwglEntity sjfw : sjfwList) {
				TreeDto treeNode = new TreeDto();
				treeNode.setId(FxcjConstant.FxysTypePrefix.SJFW + sjfw.getId());
				treeNode.setCode(FxcjConstant.FxysTypePrefix.SJFW + sjfw.getCode());
				treeNode.setName(sjfw.getName());
				treeNode.setpId(FxcjConstant.FxysTypePrefix.WWJG + sjfw.getWwjgId());
				treeNode.setOpen(true);
				treeList.add(treeNode);
			}
		}
		// 将风险点转化为TreeDto，并存入treeList
		if(fxdList != null && fxdList.size() > 0) {
			for(FxdglEntity fxd : fxdList) {
				TreeDto treeNode = new TreeDto();
				treeNode.setId(FxcjConstant.FxysTypePrefix.FXD + fxd.getId());
				treeNode.setCode(FxcjConstant.FxysTypePrefix.FXD + fxd.getCode());
				treeNode.setName(fxd.getName());
				treeNode.setpId(FxcjConstant.FxysTypePrefix.SJFW + fxd.getSjfwId());
				treeNode.setOpen(true);
				treeList.add(treeNode);
			}
		}
		return treeList;
	}
	
	/**
	 * Description: 查询有效的安全风险五维架构
	 * @return
	 */
	private List<WwjgwhEntity> queryValidWwjgList() {
		return this.wwjgwhMapper.findByScflg(CommonConstant.STATUS_VALID_CODE);
	}
	
	/**
	 * Description: 查询五维架构list下，有效的数据范围list
	 * @param wwjgList
	 * @return
	 */
	private List<SjfwglEntity> queryValidSjfwListInWwjgList(List<WwjgwhEntity> wwjgList) {
		List<SjfwglEntity> sjfwList = new ArrayList<SjfwglEntity>();
		if(wwjgList != null && wwjgList.size() > 0) {
			for(WwjgwhEntity wwjg : wwjgList) {
				List<SjfwglEntity> tempList = this.sjfwglMapper.findByWwjgIdAndScflg(wwjg.getId(), CommonConstant.STATUS_VALID_CODE);
				sjfwList.addAll(tempList);
			}
		}
		return sjfwList;
	}

	/**
	 * Description: 查询数据范围list下，有效的风险点list
	 * @param sjfwList
	 * @return
	 */
	private List<FxdglEntity> queryValidFxdListInSjfwList(List<SjfwglEntity> sjfwList) {
		List<FxdglEntity> fxdList = new ArrayList<FxdglEntity>();
		if(fxdList != null && fxdList.size() > 0) {
			for(SjfwglEntity sjfw : sjfwList) {
				List<FxdglEntity> tempList = this.fxdglMapper.findBySjfwIdAndScflg(sjfw.getId(), CommonConstant.STATUS_VALID_CODE);
				fxdList.addAll(tempList);
			}
		}
		return fxdList;
	}

	@Override
	public WwjgwhEntity getwwjg(String id) {
		// TODO Auto-generated method stub
		return wwjgwhMapper.getById(id);
	}

	@Override
	public SjfwglEntity getsjfw(String id) {
		// TODO Auto-generated method stub
		return sjfwglMapper.getById(id);
	}

	@Override
	public List getWg(String leve,String parent) {
		UserBean user=AuthSystemFacade.getLoginUserInfo();
		if(!Util.isNull(parent)){
			parent = "."+parent;
		}else{

			parent="."+user.getCusNumber();
		}
		
		Map map = new HashMap();
		map.put("groupkey", "4.13.6");
		map.put("parentId", "4.13.6"+parent);
		map.put("codeKeyClass",user.getCusNumber());
		List<Map> list = this.getDao().getAllwG(map);
		List<Map<String, Object>> mapS1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapS2 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapS3 = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map mapjg = list.get(i);
			String codeKey = (String) mapjg.get("CODE_KEY");
			String name = (String) mapjg.get("NAME");
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("value",codeKey );
			map2.put("text",name);
			int codelength = codeKey.length();
			//maps.add(map);
			if(codelength==6){
				mapS1.add(map2);
			}
			if(codelength==8){
				mapS2.add(map2);		
			}
			if(codelength==10){
				mapS3.add(map2);
			}
		}
		List<Map<String, Object>> mapS = new ArrayList<Map<String, Object>>();
		if(leve.equals("1")){
			mapS = mapS1;
		}else if(leve.equals("2")){
			mapS = mapS2;		
		}else if(leve.equals("3")){
			mapS = mapS3;
		}
		return mapS;
	}

	@Override
	public Fxcj getById(String id) {
		// TODO Auto-generated method stub
		Fxcj Fxcj = this.getDao().getById(id);
		
		return Fxcj;
	}

	@Override
	@Transactional
	public void insterDb(Fxcj fxcj) {
		// TODO Auto-generated method stub
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			Map map = new HashMap();
			String uuid = ((UUID.randomUUID()).toString()).replace("-", "");
			map.put("id", uuid);
			map.put("prisonId", user.getOrgCode());
			map.put("departId", user.getDprtmntCode());
			map.put("departName", user.getDprtmntName());
			map.put("startTime",  sdf.format(new Date()));
			if(fxcj.getJhJjsj()==null){
				map.put("endTime", "");
			}else{
				map.put("endTime", sdf.format(fxcj.getJhJjsj()));
			}
			map.put("createTime",new Date() );
			map.put("createUserId", user.getUserId());
			map.put("dataType", 5);
			map.put("state", 0);
			map.put("isTimeout",0 );
			map.put("title","风险处理" );
			map.put("finishDate",null );
			map.put("mark",null );
			map.put("finishUserName", null);
			map.put("finishUserId",null );
			map.put("fxcjId", fxcj.getId());
			this.getDao().insertDb(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
