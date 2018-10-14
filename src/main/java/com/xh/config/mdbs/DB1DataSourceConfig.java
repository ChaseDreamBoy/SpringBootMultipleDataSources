package com.xh.config.mdbs;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author xiaohe
 * @version V1.0.0
 * @Description: Configuration注解 -- 使类似于配置文件
 *      MapperScan注解 -- 扫描Mapper接口交给容器管理
 *          basePackages - 数据源对应dao层的包，并把包中的接口交给容器管理（指定基包）
 *          sqlSessionFactoryRef - 使用的哪一个SqlSessionFactory,对应下面生成的SqlSessionFactory
 * @date: 2018-10-14 16:10
 */
@Configuration
@MapperScan(basePackages = DB1DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1DataSourceConfig {

    // 每一个数据源对应dao层一个包，精确到那个包的目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.xh.dao.db1";
    // 接口对应的xml文件
    private static final String MAPPER_LOCATION = "classpath:com/xh/mapper/*.xml";
    // 有的项目把xml文件放到src/main/resources下，就把MAPPER_LOCATION换为下面的
//    private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${jdbc1.url}")
    private String url;

    @Value("${jdbc1.username}")
    private String user;

    @Value("${jdbc1.password}")
    private String password;

    @Value("${jdbc1.driverClassName}")
    private String driverClass;

    // 创建数据源
    @Bean(name = "db1DataSource")
    public DataSource db1DataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    // 配置事务管理器
    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager db1TransactionManager() {
        return new DataSourceTransactionManager(db1DataSource());
    }

    // 根据数据源创建SqlSessionFactory
    // @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 指定数据源(这个必须有，否则报错)
        sessionFactory.setDataSource(db1DataSource);
        // 指定*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DB1DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
