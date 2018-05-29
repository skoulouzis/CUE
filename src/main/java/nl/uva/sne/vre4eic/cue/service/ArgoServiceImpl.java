package nl.uva.sne.vre4eic.cue.service;

import java.sql.Date;
import java.util.List;
import nl.uva.sne.vre4eic.cue.model.Argo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nl.uva.sne.vre4eic.cue.dao.ArgoDAO;

@Service
public class ArgoServiceImpl implements ArgoService {

    @Autowired
    private ArgoDAO argoDao;

    @Override
    public List<Argo> getAllArgo() {
        List<Argo> persons = argoDao.getAllArgo();
        return persons;
    }

    @Override
    public void insertArgo(Argo argo) {
        argoDao.insertArgo(argo);
    }

    @Override
    public List<Argo> getArgoDataForBoundingBoxAndDate(Double geospatial_lat_min, Double geospatial_lat_max, Double geospatial_lon_min, Double geospatial_lon_max, Date sqlStartDate, Date sqlEndDate) {
        return argoDao.getArgoDataForBoundingBoxAndDate(geospatial_lat_min, geospatial_lat_max, geospatial_lon_min, geospatial_lon_max, sqlStartDate, sqlEndDate);
    }

    @Override
    public List<Argo> getArgoDataForBoundingBox(Double geospatial_lat_min, Double geospatial_lat_max, Double geospatial_lon_min, Double geospatial_lon_max) {
        return argoDao.getArgoDataForBoundingBox(geospatial_lat_min, geospatial_lat_max, geospatial_lon_min, geospatial_lon_max);
    }

}
