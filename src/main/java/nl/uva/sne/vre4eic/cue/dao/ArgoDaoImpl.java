package nl.uva.sne.vre4eic.cue.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import nl.uva.sne.vre4eic.cue.model.Argo;
import nl.uva.sne.vre4eic.cue.util.Consts;
import nl.uva.sne.vre4eic.cue.util.ArgoFactory;

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

}
