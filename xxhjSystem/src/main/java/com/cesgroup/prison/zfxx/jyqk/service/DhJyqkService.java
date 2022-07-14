package com.cesgroup.prison.zfxx.jyqk.service;

public interface DhJyqkService {

	/**
	 * 各监区就医情况
	 * @param jyId
	 * @param cjpc
	 */
	public void synchroDhJyqk(String jyId, String cjpc);
	
}
