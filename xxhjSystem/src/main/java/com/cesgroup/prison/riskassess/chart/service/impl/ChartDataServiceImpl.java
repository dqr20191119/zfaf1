package com.cesgroup.prison.riskassess.chart.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.riskassess.chart.dao.ChartDataDao;
import com.cesgroup.prison.riskassess.chart.dto.MapDto;
import com.cesgroup.prison.riskassess.chart.entity.ChartData;
import com.cesgroup.prison.riskassess.chart.service.ChartDataService;
import com.cesgroup.prison.riskassess.fxpg.dao.ChartFxpgZfDao;
import com.cesgroup.prison.riskassess.fxpg.entity.ChartFxpgZf;
import com.cesgroup.prison.riskassess.fxzx.dao.ChartFxzxGxzfDao;
import com.cesgroup.prison.riskassess.fxzx.entity.ChartFxzxGxzf;

/**
 * Description: 图表展示业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("chartDataService")
public class ChartDataServiceImpl extends BaseDaoService<ChartData, String, ChartDataDao> implements ChartDataService {
	/**
	 * desc: 风险评估-总分Dao
	 */
	@Autowired
	ChartFxpgZfDao fxpgZfDao;
	/**
	 * desc: 风险指向-各项总分Dao
	 */
	@Autowired
	ChartFxzxGxzfDao fxzxGxzfDao;
	
	/**
	 * Description: 根据用户角色，查询该角色用户可看到的风险分布数据
	 * @param userRole
	 * @return
	 */
	@Override
	public List<MapDto> queryFxfbByUserRole(String userRole) {
		List<MapDto> resultList = new ArrayList<MapDto>();
		if(Util.notNull(userRole)) {
			// desc: 根据用户角色查询角色可见的风险分布数据
			ChartData chartData = this.getDao().findFxfbByUserRole(userRole);
			if(chartData != null) {
				String[] names = chartData.getNames() != null ? chartData.getNames().split(",") : null;
				String[] counts = chartData.getCounts() != null ? chartData.getCounts().split(",") : null;
				
				if(names != null && names.length > 0) {
					for(int i=0; i<names.length; i++) {
						MapDto fxfbDto = new MapDto();
						fxfbDto.setName(names[i]);
						fxfbDto.setValue(counts != null && counts.length >= (i + 1) ? Util.string2Int(counts[i]) : 0);
						resultList.add(fxfbDto);
					}
				}
			}
			// desc: 根据value大小对list进行排序，从大到小
			Collections.sort(resultList, new Comparator<MapDto>() {
	            /*
	             * int compare(Person p1, Person p2) 返回一个基本类型的整型，
	             * 返回正数表示：p1 小于p2，
	             * 返回0 表示：p1和p2相等，
	             * 返回负数表示：p1大于p2
	             */
	            public int compare(MapDto p1, MapDto p2) {
	                // 按照MapDto的值进行降序排列
	                if(p1.getValue() > p2.getValue()) {
	                    return -1;
	                }
	                if(p1.getValue() == p2.getValue()) {
	                    return 0;
	                }
	                return 1;
	            }
	        });
		}
		
		return resultList;
	}
	/**
	 * Description: 根据用户角色，查询该角色用户可看到的风险趋势数据
	 * @param userRole
	 * @return
	 */
	@Override
	public List<MapDto> queryFxqsByUserRole(String userRole) {
		List<MapDto> resultList = new ArrayList<MapDto>();
		if(Util.notNull(userRole)) {
			// desc: 根据用户角色查询角色可见的风险趋势数据
			ChartData chartData = this.getDao().findFxqsByUserRole(userRole);
			if(chartData != null) {
				String[] names = chartData.getNames() != null ? chartData.getNames().split(",") : null;
				String[] counts = chartData.getCounts() != null ? chartData.getCounts().split(",") : null;
				
				if(names != null && names.length > 0) {
					for(int i=0; i<names.length; i++) {
						MapDto fxqsDto = new MapDto();
						fxqsDto.setName(names[i]);
						fxqsDto.setValue(counts != null && counts.length >= (i + 1) ? Util.string2Int(counts[i]) : 0);
						resultList.add(fxqsDto);
					}
				}
			}
		}
		
		return resultList;
	}
	/**
	 * Description: 根据风险评估种类与用户角色，查询该角色用户可看到的风险评估数据
	 * @param category
	 * @param userRole
	 * @return
	 */
	@Override
	public Map<String, Object> queryFxpgByCategoryAndUserRole(String category, String userRole) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Integer score = 200;
		List<MapDto> fxpgList = new ArrayList<MapDto>();
		if(Util.notNull(category) && Util.notNull(userRole)) {
			// desc: 根据风险评估种类与用户角色，查询角色可见的风险评估数据
			ChartData chartData = this.getDao().findFxpgByCategoryAndUserRole(category, userRole);
			if(chartData != null) {
				String[] names = chartData.getNames() != null ? chartData.getNames().split(",") : null;
				String[] counts = chartData.getCounts() != null ? chartData.getCounts().split(",") : null;
				
				if(names != null && names.length > 0) {
					for(int i=0; i<names.length; i++) {
						MapDto fxpgDto = new MapDto();
						fxpgDto.setName(names[i]);
						fxpgDto.setValue(counts != null && counts.length >= (i + 1) ? Util.string2Int(counts[i]) : 0);
						fxpgList.add(fxpgDto);
					}
				}
			}
			// desc: 根据风险评估种类，查询该种类的预设的总分值
			ChartFxpgZf fxpgZf = this.fxpgZfDao.findByCategory(category);
			score = fxpgZf != null && fxpgZf.getScore() != null ? fxpgZf.getScore() : score;
		}
		resultMap.put("score", score);
		resultMap.put("fxpgList", fxpgList);
		return resultMap;
	}
	/**
	 * Description: 根据风险评估种类与用户角色，查询该角色用户可看到的风险指向数据
	 * @param category
	 * @param userRole
	 * @return
	 */
	@Override
	public List<MapDto> queryFxzxByCategoryAndUserRole(String category, String userRole) {
		List<MapDto> fxzxList = new ArrayList<MapDto>();
		if(Util.notNull(userRole)) {
			// desc: 根据风险评估种类与用户角色，查询角色可见的风险指向数据
			ChartData chartData = this.getDao().findFxzxByCategoryAndUserRole(category, userRole);
			if(chartData != null) {
				String[] names = chartData.getNames() != null ? chartData.getNames().split(",") : null;
				String[] counts = chartData.getCounts() != null ? chartData.getCounts().split(",") : null;
				
				if(names != null && names.length > 0) {
					for(int i=0; i<names.length; i++) {
						MapDto fxqsDto = new MapDto();
						fxqsDto.setName(names[i]);
						fxqsDto.setValue(counts != null && counts.length >= (i + 1) ? Util.string2Int(counts[i]) : 0);
						
						// desc: 根据风险指向名称与风险评估分类，查询对应的风险指向总分值
						ChartFxzxGxzf temp = this.fxzxGxzfDao.findByNameAndCategory(names[i], category);
						Integer score = temp != null && temp.getScore() != null ? temp.getScore() : fxqsDto.getValue();
						fxqsDto.setScore(score);
						
						fxzxList.add(fxqsDto);
					}
				}
			}
		}
		
		return fxzxList;
	}
	/**
	 * Description: 根据用户角色，查询该角色用户可看到的风险概况数据
	 * @param userRole
	 * @return
	 */
	@Override
	public List<MapDto> queryFxgkByUserRole(String userRole) {
		List<MapDto> fxzxList = new ArrayList<MapDto>();
		if(Util.notNull(userRole)) {
			// desc: 根据用户角色，查询角色可见的风险概况数据
			ChartData chartData = this.getDao().findFxgkByUserRole(userRole);
			if(chartData != null) {
				String[] names = chartData.getNames() != null ? chartData.getNames().split(",") : null;
				String[] counts = chartData.getCounts() != null ? chartData.getCounts().split(",") : null;
				
				if(names != null && names.length > 0) {
					for(int i=0; i<names.length; i++) {
						MapDto fxgkDto = new MapDto();
						fxgkDto.setName(names[i]);
						fxgkDto.setValue(counts != null && counts.length >= (i + 1) ? Util.string2Int(counts[i]) : 0);

						// desc: 根据风险评估种类，查询该种类的预设的总分值
						ChartFxpgZf fxpgZf = this.fxpgZfDao.findByCategory(fxgkDto.getName());
						Integer score = fxpgZf != null && fxpgZf.getScore() != null ? fxpgZf.getScore() : fxgkDto.getValue();
						fxgkDto.setScore(score);
						
						fxzxList.add(fxgkDto);
					}
				}
			}
		}
		
		return fxzxList;
	}
}
