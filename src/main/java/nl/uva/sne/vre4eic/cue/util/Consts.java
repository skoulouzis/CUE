/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.util;

/**
 *
 * @author S. Koulouzis
 */
public class Consts {

    public static final String TABLE_NAME = "ARGO_DATA";

    public static final String[] COLUMS = new String[]{"station_id", "platform_code",
        "station_date", "latitude", "longitude", "measure_type", "parameter_code",
        "parameter_value", "parameter_qc", "z_code", "z_value", "z_qc", "z_level"};

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String CSV_SPLIT = ",";

}
