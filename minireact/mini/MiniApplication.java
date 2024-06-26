package com.react.mini;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.react.mini.controller","com.react.mini.service"})
@MapperScan(value={"com.react.mini.mapper"})
public class MiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniApplication.class, args);
	}

	@Autowired
	    private ApplicationContext applicationContext;

		
		@Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	        sqlSessionFactoryBean.setDataSource(dataSource);
	        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappersql/*.xml"));
	        return sqlSessionFactoryBean.getObject();
	    }

	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }

}
