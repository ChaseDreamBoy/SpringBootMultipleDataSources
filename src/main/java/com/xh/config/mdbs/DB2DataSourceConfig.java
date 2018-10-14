package com.xh.config.mdbs;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author xiaohe
 * @version V1.0.0
 * @Description:
 * @date: 2018-10-14 16:10
 * @Copyright:
 */
@Configuration
@MapperScan(basePackages = DB2DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DB2DataSourceConfig {

    static final String PACKAGE = "com.xh.dao.db2";
    private static final String MAPPER_LOCATION = "classpath:com/xh/mapper/*.xml";

    @Value("${jdbc2.url}")
    private String url;

    @Value("${jdbc2.username}")
    private String user;

    @Value("${jdbc2.password}")
    private String password;

    @Value("${jdbc2.driverClassName}")
    private String driverClass;

    @Bean(name = "db2DataSource")
    @Primary
    public DataSource db2DataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "db2TransactionManager")
    @Primary
    public DataSourceTransactionManager db2TransactionManager() {
        return new DataSourceTransactionManager(db2DataSource());
    }

    @Bean(name = "db2SqlSessionFactory")
    @Primary
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(db2DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DB2DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
