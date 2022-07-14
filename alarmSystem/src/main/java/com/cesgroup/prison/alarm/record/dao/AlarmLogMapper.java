package com.cesgroup.prison.alarm.record.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntityLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**      
* @projectName：prison   
* @ClassName：AlarmMapper   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月4日 下午2:30:24   
* @version        
*/
public interface AlarmLogMapper extends BaseDao<AlarmRecordEntityLog, String> {

}
