package com.cesgroup.prison.sql.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.scrap.db.SequenceUtil;
import com.cesgroup.scrap.db.UpdateProcess;
import com.cesgroup.scrap.db.bean.SqlParamsBean;

@Service
public class UpdateService implements IUpdateService {
	private static final Logger logger = LoggerFactory.getLogger(UpdateService.class);
	@Resource
	UpdateProcess updateProcess;
	@Resource
	SequenceUtil sequenceUtil;
	
	
	public int update(String sqlid,List<Object> parasList) {
		int updateNum=updateProcess.process(sqlid, parasList);
		return updateNum;
	}
	@Override
	public int updateBlob(String sqlId, final List<Object> params) {
		try {
			return updateProcess.updateBlob(sqlId, params);
		} catch (Exception e) {
			logger.error("", e);
			return 0;
		}
	}

	@Override
	public void batchUpdate(List<SqlParamsBean> sqlParams){
		updateProcess.process(sqlParams);
	}

/*	public AjaxMessage batchUpdate(String args) {
		logger.debug("传进的参数===" + args);
		AjaxMessage ajaxMsg = new AjaxMessage();
		Map<String,String> seqMap=new HashMap<String,String>();
		try {
			if(!StringUtil.isNull(args)){
				JSONArray argsArray=JSONArray.parseArray(args);
				List<SqlParamsBean> sqlParamsList=new ArrayList<SqlParamsBean>();
				if(argsArray!=null){
					for(int i=0;i<argsArray.size();i++){
						JSONObject argsObject=argsArray.getJSONObject(i);
						String sqlId=argsObject.getString(IControllerConst.SQLID);
						String tableName=argsObject.getString(IControllerConst.TABLENAME);
						List<Object> parasList=new ArrayList<Object>();
						parasList=argsObject.getJSONArray(IControllerConst.PARAS);
						if(!StringUtil.isNull(tableName)){
							String columnName=argsObject.getString(IControllerConst.COLUMNNAME);
							String seqIndex=argsObject.getString(IControllerConst.SEQINDEX);
							String seq=sequenceUtil.getSequence(tableName, columnName);
							if(!StringUtil.isNull(seqIndex)){
								parasList.add(Integer.valueOf(seqIndex).intValue(), seq);
							}
							seqMap.put(tableName+columnName, seq);
						}
						String relationTableName=argsObject.getString(IControllerConst.RELATION_TABLENAME);
						if(!StringUtil.isNull(relationTableName)){
							String relationColumnName=argsObject.getString(IControllerConst.RELATION_COLUMNNAME);
							String relationSeqIndex=argsObject.getString(IControllerConst.RELATION_SEQINDEX);
							if(seqMap.containsKey(relationTableName+relationColumnName)){
								String relationSeq=seqMap.get(relationTableName+relationColumnName);
								if(!StringUtil.isNull(relationSeq)){
									parasList.add(Integer.valueOf(relationSeqIndex).intValue(), relationSeq);
								}
							}
						}
						SqlParamsBean sqlParamsBean=new SqlParamsBean();
						sqlParamsBean.setSqlId(sqlId);
						sqlParamsBean.setParasList(parasList);
						sqlParamsList.add(sqlParamsBean);
					}
				}
				batchUpdate(sqlParamsList);
				ajaxMsg.setSuccess(true);
				ajaxMsg.setMsg("更新成功");
			}else{
				ajaxMsg.setSuccess(false);
				ajaxMsg.setMsg("参数不能为空");
			}
		} catch (Exception e) {
			logger.error("", e);
			ajaxMsg.setSuccess(false);
			ajaxMsg.setMsg("出现异常:"+e.getMessage());
		}
		return ajaxMsg;
	}*/
	@Override
	public int updateClob(String sqlId, List<Object> params) {
		try {
			return updateProcess.updateClob(sqlId, params);
		} catch (Exception e) {
			logger.error("", e);
			return 0;
		}
		
	}
	
}
