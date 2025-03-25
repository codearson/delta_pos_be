//package com.pos_main.Config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import javax.sql.DataSource;
//
///**
// * Title: DatabaseConfig.java. Company: www.codearson.com | Copyright: Copyright
// * (C) 2025.
// * 
// * @author Lathusan Thurairajah.
// * @date Mar 25, 2025.
// * @version 1.0
// **/
//
//@Configuration
//public class DatabaseConfig {
//
//	@Bean(name = "mainDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource.main")
//	public DataSource mainDataSource() {
//		return new DriverManagerDataSource();
//	}
//
//	@Bean(name = "mirrorDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource.mirror")
//	public DataSource mirrorDataSource() {
//		return new DriverManagerDataSource();
//	}
//
//	@Bean(name = "dataSource")
//	public DataSource failoverDataSource() {
//		return new FailoverRoutingDataSource(mainDataSource(), mirrorDataSource());
//	}
//}
