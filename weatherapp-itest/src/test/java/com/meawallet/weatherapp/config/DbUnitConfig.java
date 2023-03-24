package com.meawallet.weatherapp.config;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@Configuration
@Profile("itest")
public class DbUnitConfig {
    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        var databaseConfigBean = new DatabaseConfigBean();
        databaseConfigBean.setDatatypeFactory(new MySqlDataTypeFactory());
        databaseConfigBean.setAllowEmptyFields(true);
        databaseConfigBean.setCaseSensitiveTableNames(false);
        databaseConfigBean.setQualifiedTableNames(true);
        return databaseConfigBean;
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(DataSource dataSource, DatabaseConfigBean databaseConfigBean) {
        var factoryBean = new DatabaseDataSourceConnectionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setDatabaseConfig(databaseConfigBean);
        return factoryBean;
    }

    @Bean
    public DataSource dbUnitDataSource(@Value("jdbc:mariadb://localhost:3306/weatherapp_db") String url,
                                       @Value("weatherapp_user") String username,
                                       @Value("m34walletIsC00l") String password,
                                       @Value("org.mariadb.jdbc.Driver") String driver) {
        return DataSourceBuilder.create()
                                .driverClassName(driver)
                                .url(url)
                                .username(username)
                                .password(password)
                                .build();
    }
}