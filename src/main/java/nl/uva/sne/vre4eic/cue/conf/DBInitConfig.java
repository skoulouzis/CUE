/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.conf;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author S. Koulouzis
 */
@Configuration
public class DBInitConfig {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            DatabaseMetaData meta = connection.getMetaData();
            System.err.println("The driver name is " + meta.getDriverName());
            System.err.println("getURL " + meta.getURL());
            statement.execute("DROP TABLE IF EXISTS UserLogin");
            statement.executeUpdate(
                    "CREATE TABLE UserLogin("
                    + "id INTEGER Primary key, "
                    + "userName varchar(30) not null,"
                    + "password varchar(30) not null,"
                    + "firstName varchar(30) not null, "
                    + "lastName varchar(30) not null,"
                    + "email varchar(100) not null,"
                    + "mobile varchar(30) not null)"
            );
            statement.executeUpdate(
                    "INSERT INTO UserLogin "
                    + "(userName,password,firstName,lastName,email,mobile) "
                    + "VALUES " + "('bharat0126','dbase123','Bharat','Verma',"
                    + " 'bharatverma2488@gmail.com','8861456151')"
            );
        } catch (SQLException ex) {
            Logger.getLogger(DBInitConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
