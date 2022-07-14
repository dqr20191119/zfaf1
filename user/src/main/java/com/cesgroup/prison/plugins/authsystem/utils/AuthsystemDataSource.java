package com.cesgroup.prison.plugins.authsystem.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import ces.sdk.sdk.db.SdkDataSource;

@Component("authsystemPluginFitDS")
public class AuthsystemDataSource implements SdkDataSource {
	
	@Resource(name="authsystemDataSource")
	private DataSource dataSource;
	
	@Override
	public Connection getConnection() throws SQLException {
		
		return dataSource.getConnection();
	}
}
