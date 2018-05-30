package nl.uva.sne.vre4eic.cue.service;

import java.sql.Date;
import java.util.List;
import nl.uva.sne.vre4eic.cue.model.Argo;
import org.springframework.web.multipart.MultipartFile;

public interface ArgoService {

    public List<Argo> getAllArgo();

    public void insertArgo(Argo argo);

    public List<Argo> getArgoDataForBoundingBoxAndDate(Double geospatial_lat_min, Double geospatial_lat_max, Double geospatial_lon_min, Double geospatial_lon_max, Date sqlStartDate, Date sqlEndDate);

    public List<Argo> getArgoDataForBoundingBox(Double geospatial_lat_min, Double geospatial_lat_max, Double geospatial_lon_min, Double geospatial_lon_max);

    public void insertCSVFile(MultipartFile file);
}
