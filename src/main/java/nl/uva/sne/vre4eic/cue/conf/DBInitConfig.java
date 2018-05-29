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
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import nl.uva.sne.vre4eic.cue.util.Consts;
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
//            statement.executeUpdate("DROP TABLE IF EXISTS " + Consts.TABLE_NAME);
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS "
                    + Consts.TABLE_NAME
                    + " ("
                    + "id INTEGER Primary key, "
                    + "station_id INTEGER not null,"
                    + "platform_code INTEGER not null,"
                    + "station_date DATETIME not null,"
                    + "latitude DOUBLE not null,"
                    + "longitude DOUBLE not null, "
                    + "measure_type INTEGER not null,"
                    + "parameter_code INTEGER not null,"
                    + "parameter_value DOUBLE not null,"
                    + "parameter_qc INTEGER not null,"
                    + "z_code INTEGER not null,"
                    + "z_value DOUBLE not null,"
                    + "z_qc INTEGER not null,"
                    + "z_level DOUBLE"
                    + ")"
            );
//            statement.executeUpdate(
//                    "INSERT INTO  " + Consts.TABLE_NAME
//                    + " ("
//                    + "station_id,"
//                    + "platform_code,"
//                    + "station_date,"
//                    + "latitude,"
//                    + "longitude,"
//                    + "measure_type,"
//                    + "parameter_code,"
//                    + "parameter_value,"
//                    + "parameter_qc,"
//                    + "z_code,"
//                    + "z_value,"
//                    + "z_qc,"
//                    + "z_level"
//                    + ") "
//                    + "VALUES "
//                    + "('2451065','21850','" + new Timestamp(System.currentTimeMillis()) + "','37.847','138.536','1','35','11.569','1','28','58.5','1','')"
//            );
        } catch (SQLException ex) {
            Logger.getLogger(DBInitConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
