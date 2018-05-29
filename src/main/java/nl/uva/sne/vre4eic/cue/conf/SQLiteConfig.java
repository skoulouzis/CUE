/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.conf;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author S. Koulouzis
 */
@Configuration
public class SQLiteConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        URL resource = SQLiteConfig.class
                .getClassLoader().getResource("sql/mydb.db");
        
        dataSourceBuilder.url(String.format("jdbc:sqlite:%s", new File(resource.toURI()).getAbsolutePath()));
        return dataSourceBuilder.build();
    }
}
