package com.ssm.common.autoConfig;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
@Configuration
public class SpringBootMybitisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis.xml"));
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:/mapper/*.xml");
            sqlSessionFactoryBean.setMapperLocations(resources);
            sqlSessionFactoryBean.setTypeAliasesPackage("com.ssm.entities");
            return sqlSessionFactoryBean;
        } catch (Exception e) {
            return null;
        }

    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.ssm");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
//    }


}
