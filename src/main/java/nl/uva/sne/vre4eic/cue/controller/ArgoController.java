/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.vre4eic.cue.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import nl.uva.sne.vre4eic.cue.model.Argo;
import nl.uva.sne.vre4eic.cue.util.WriteCsvToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nl.uva.sne.vre4eic.cue.service.ArgoService;
import static nl.uva.sne.vre4eic.cue.util.Consts.DATE_FORMAT;

/**
 *
 * @author S. Koulouzis
 */
@RestController
public class ArgoController {

    @Autowired
    private ArgoService service;

    @RequestMapping(value = "/argo", produces = "text/csv", method = RequestMethod.GET)
    public void getArgoData(@RequestParam(required = false) Map<String, String> requestParams,
            HttpServletResponse response) {
        try {
            List<Argo> argo;
            if (requestParams.containsKey("geospatial_lat_min")
                    && requestParams.containsKey("geospatial_lat_max")
                    && requestParams.containsKey("geospatial_lon_min")
                    && requestParams.containsKey("geospatial_lon_max")
                    && requestParams.containsKey("time_coverage_start")
                    && requestParams.containsKey("time_coverage_end")) {
                Double geospatial_lat_min = Double.valueOf(requestParams.get("geospatial_lat_min"));
                Double geospatial_lat_max = Double.valueOf(requestParams.get("geospatial_lat_max"));
                Double geospatial_lon_min = Double.valueOf(requestParams.get("geospatial_lon_min"));
                Double geospatial_lon_max = Double.valueOf(requestParams.get("geospatial_lon_max"));
                Date time_coverage_start = new SimpleDateFormat(DATE_FORMAT).parse(requestParams.get("time_coverage_start"));
                Date time_coverage_end = new SimpleDateFormat(DATE_FORMAT).parse(requestParams.get("time_coverage_end"));

                java.sql.Date sqlStartDate = new java.sql.Date(time_coverage_start.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date(time_coverage_end.getTime());
                argo = service.getArgoDataForBoundingBoxAndDate(geospatial_lat_min, geospatial_lat_max, geospatial_lon_min, geospatial_lon_max, sqlStartDate, sqlEndDate);
            } else if (requestParams.containsKey("geospatial_lat_min")
                    && requestParams.containsKey("geospatial_lat_max")
                    && requestParams.containsKey("geospatial_lon_min")
                    && requestParams.containsKey("geospatial_lon_max")) {

                Double geospatial_lat_min = Double.valueOf(requestParams.get("geospatial_lat_min"));
                Double geospatial_lat_max = Double.valueOf(requestParams.get("geospatial_lat_max"));
                Double geospatial_lon_min = Double.valueOf(requestParams.get("geospatial_lon_min"));
                Double geospatial_lon_max = Double.valueOf(requestParams.get("geospatial_lon_max"));

                argo = service.getArgoDataForBoundingBox(geospatial_lat_min, geospatial_lat_max, geospatial_lon_min, geospatial_lon_max);
            } else {
                argo = service.getAllArgo();
            }

            WriteCsvToResponse.writeArgo(response.getWriter(), argo);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | ParseException ex) {
            Logger.getLogger(ArgoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
