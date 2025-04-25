package com.example.batch.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfiguration {

	private final DataSource dataSource;
	private final ApplicationContext applicationContext;

	public BatchConfig(DataSource dataSource, ApplicationContext applicationContext) {
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
    }

    // @Bean
	// public SqlSessionFactory sqlSessionFactory() throws Exception {
	// 	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	// 	sqlSessionFactoryBean.setDataSource(dataSource);
	// 	sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));

	// 	return sqlSessionFactoryBean.getObject();
	// }
}
