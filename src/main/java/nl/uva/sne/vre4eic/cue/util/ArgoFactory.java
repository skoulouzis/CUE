/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.util;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import nl.uva.sne.vre4eic.cue.model.Argo;
import static nl.uva.sne.vre4eic.cue.util.Consts.DATE_FORMAT;

/**
 *
 * @author S. Koulouzis
 */
public class ArgoFactory {

    public static Argo build(ResultSet result) throws SQLException {
        Argo p = new Argo();
        p.setId(result.getInt("id"));
        p.setStation_date(new Date(result.getTimestamp("station_date").getTime()) );
        p.setStation_id(result.getInt("station_id"));
        p.setPlatform_code(result.getInt("platform_code"));
        p.setLatitude(result.getDouble("latitude"));
        p.setLongitude(result.getDouble("longitude"));
        p.setMeasure_type(result.getInt("measure_type"));
        p.setParameter_code(result.getInt("parameter_code"));
        p.setParameter_value(result.getDouble("parameter_value"));
        p.setParameter_qc(result.getInt("parameter_qc"));
        p.setZ_code(result.getInt("z_code"));
        p.setZ_value(result.getDouble("z_value"));
        p.setZ_qc(result.getInt("z_qc"));
        p.setZ_level(result.getDouble("z_level"));

        return p;
    }

}
