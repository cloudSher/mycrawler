package com.sher.mycrawler.admin.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.AntPathMatcher;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/15.
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    @Autowired
    private Environment env;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.sher.mycrawler.domain");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if(resolver!=null){
            resolver.setPathMatcher(new AntPathMatcher("com.sher.mycrawler.mapper.*"));
        }


        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }



    /**
     * 创建数据源
     * @param
     * @return
     */
    @Bean
    public DataSource dataSource(){
        Properties properties = new Properties();
        properties.setProperty("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        properties.setProperty("url", env.getProperty("spring.datasource.url"));
        properties.setProperty("username", env.getProperty("spring.datasource.username"));
        properties.setProperty("password", env.getProperty("spring.datasource.password"));
        try {
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            return null;
        }
    }
}
