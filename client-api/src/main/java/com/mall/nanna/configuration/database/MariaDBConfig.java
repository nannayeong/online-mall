package com.mall.nanna.configuration.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * mariaDB 설정
 * @author EDA
 * @since 2022-12-12
 */
@Configuration
public class MariaDBConfig {

    @Bean(value = "mariaDBDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.mariadb.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
