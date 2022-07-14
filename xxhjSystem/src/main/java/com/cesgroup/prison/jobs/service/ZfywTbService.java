package com.cesgroup.prison.jobs.service;

import java.util.List;
import java.util.Map;

public interface ZfywTbService {

	/**
	 * 同步罪犯业务数据
	 * @param jyList 监狱列表
	 */
	void synchroZfyw(List<Map<String, Object>> jyList);
	
	/**
	 * 同步计分考核
	 * @param jyList 监狱列表
	 */
	void synchroJfkh(List<Map<String, Object>> jyList);
	
	/**
	 * 全量同步罪犯照片
	 */
	void synchroZfzp();

	void zfJbxx(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfSy(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfLj(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfShgx(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfLbc(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfRzfp(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfTg(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfZdzf(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfJzzy(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfJhzs(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfJyzf(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfLjtq(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfJzzySj(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfZyjwzx(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfPhoto(String cjpc);

	void incZfPhoto(String time, String cjpc);

	void zfXfbdJx(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfXfbdJs(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfYnjcxx(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfJfkhRkh(String time, String cjpc, List<Map<String, Object>> jyList);

	void zfJfkhYhz(String time, String cjpc, List<Map<String, Object>> jyList);

	void synchroDhJyqk(String cjpc, List<String> jyList);

	void synchroDhMbqk(String cjpc, List<String> jyList);

	void synchroDhJyqs(String cjpc, List<String> jyList);

	void synchroDzwp(String cjpc, List<String> jyList);

	
}
