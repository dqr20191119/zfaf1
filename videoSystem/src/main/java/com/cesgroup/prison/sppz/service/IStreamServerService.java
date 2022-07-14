package com.cesgroup.prison.sppz.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.sppz.entity.StreamServer;

public interface IStreamServerService {
	public Page<Map<String,String>> searchStreamServer(StreamServer streamServer_param,PageRequest pageRequest);
	public List<Map<String,Object>> simpleStreamServerList(String ssiCusNumber);
	public void deleteByIds(List<String> id);
}
