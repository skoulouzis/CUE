package nl.uva.sne.vre4eic.cue.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import nl.uva.sne.vre4eic.cue.model.Argo;
import nl.uva.sne.vre4eic.cue.util.Consts;
import nl.uva.sne.vre4eic.cue.util.ArgoFactory;
import static nl.uva.sne.vre4eic.cue.util.Consts.CSV_SPLIT;
import static nl.uva.sne.vre4eic.cue.util.Consts.DATE_FORMAT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ArgoDaoImpl implements ArgoDAO {

    @Autowired
    private DataSource dataSource;

    @Transactional(readOnly = true)
    @Override
    public List<Argo> getAllArgo() {
        String sql = "SELECT * FROM " + Consts.TABLE_NAME;
        return query(sql);
    }

    @Transactional(readOnly = false)
    @Override
    public void insertArgo(Argo employee) {
    }

    @Override
    public List<Argo> getArgoDataForBoundingBoxAndDate(
            Double geospatial_lat_min,
            Double geospatial_lat_max,
            Double geospatial_lon_min,
            Double geospatial_lon_max,
            Date sqlStartDate,
            Date sqlEndDate) {

        String sql = "SELECT * FROM " + Consts.TABLE_NAME + " WHERE "
                + "latitude BETWEEN ?  AND ? "
                + "AND longitude BETWEEN ? AND ? "
                + "AND station_date BETWEEN ? AND ? ";
        Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.FINE, "Query: {0}", sql);
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, geospatial_lat_min);
            ps.setDouble(2, geospatial_lat_max);
            ps.setDouble(3, geospatial_lon_min);
            ps.setDouble(4, geospatial_lon_max);
            ps.setDate(5, sqlEndDate);
            ps.setDate(6, sqlStartDate);
            ResultSet result = ps.executeQuery();
            List<Argo> argoData = new ArrayList<>();
            while (result.next()) {
                Argo p = ArgoFactory.build(result);
                argoData.add(p);
            }
            return argoData;
        } catch (SQLException ex) {
            Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Argo> getArgoDataForBoundingBox(Double geospatial_lat_min, Double geospatial_lat_max, Double geospatial_lon_min, Double geospatial_lon_max) {
        String sql = "SELECT * FROM " + Consts.TABLE_NAME + " WHERE "
                + "latitude BETWEEN ?  AND ? "
                + "AND longitude BETWEEN ?  AND ? ";
        Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.FINE, "Query: {0}", sql);

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, geospatial_lat_min);
            ps.setDouble(2, geospatial_lat_max);
            ps.setDouble(3, geospatial_lon_min);
            ps.setDouble(4, geospatial_lon_max);
            ResultSet result = ps.executeQuery();
            List<Argo> argoData = new ArrayList<>();
            while (result.next()) {
                Argo p = ArgoFactory.build(result);
                argoData.add(p);
            }
            return argoData;
        } catch (SQLException ex) {
            Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private List<Argo> query(String sql) {
        Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.FINE, "Query: {0}", sql);
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            List<Argo> persons = new ArrayList<>();
            while (result.next()) {
                Argo p = ArgoFactory.build(result);
                persons.add(p);
            }
            return persons;
        } catch (SQLException ex) {
            Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insertCSVFile(File csvFile) {
        try (Connection conn = dataSource.getConnection()) {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            Statement statment = conn.createStatement();
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                if (lineNum > 0) {
                    String[] argo = line.split(CSV_SPLIT);
                    SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_FORMAT);
                    java.util.Date date = datetimeFormatter.parse(argo[2]);
                    if (argo.length == 12) {
                        statment.addBatch(
                                "INSERT INTO  " + Consts.TABLE_NAME
                                + " ("
                                + "station_id,"
                                + "platform_code,"
                                + "station_date,"
                                + "latitude,"
                                + "longitude,"
                                + "measure_type,"
                                + "parameter_code,"
                                + "parameter_value,"
                                + "parameter_qc,"
                                + "z_code,"
                                + "z_value,"
                                + "z_qc"
                                + ") "
                                + "VALUES "
                                + "('"
                                + argo[0] + "','"
                                + argo[1] + "','"
                                + new Timestamp(date.getTime()) + "','"
                                + argo[3] + "','"
                                + argo[4] + "','"
                                + argo[5] + "','"
                                + argo[6] + "','"
                                + argo[7] + "','"
                                + argo[8] + "','"
                                + argo[9] + "','"
                                + argo[10] + "','"
                                + argo[11] + "')");
                    }

                }

                lineNum++;

            }

            statment.executeBatch();
        } catch (SQLException | IOException | ParseException ex) {
            Logger.getLogger(ArgoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            csvFile.delete();
        }
    }

}
