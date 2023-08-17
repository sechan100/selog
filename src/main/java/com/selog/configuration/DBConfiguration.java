package com.selog.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;
	

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	
	@Bean
	SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.addMapperLocations( applicationContext.getResources("classpath:/mapper/*.xml") );
		factoryBean.setTypeAliasesPackage("com.selog.dto");
		
		return factoryBean.getObject();
	}
	
	@Bean
	SqlSessionTemplate sqlSessionTemplate() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
















