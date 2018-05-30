package nl.uva.sne.vre4eic.cue.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import nl.uva.sne.vre4eic.cue.util.Consts;
import static nl.uva.sne.vre4eic.cue.util.Consts.DATE_FORMAT;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = Consts.TABLE_NAME)
public class Argo implements Serializable {

    /**
     * @return the z_level
     */
    public Double getZ_level() {
        return z_level;
    }

    /**
     * @param z_level the z_level to set
     */
    public void setZ_level(Double z_level) {
        this.z_level = z_level;
    }

    /**
     * @return the station_id
     */
    public Integer getStation_id() {
        return station_id;
    }

    /**
     * @param station_id the station_id to set
     */
    public void setStation_id(Integer station_id) {
        this.station_id = station_id;
    }

    /**
     * @return the platform_code
     */
    public Integer getPlatform_code() {
        return platform_code;
    }

    /**
     * @param platform_code the platform_code to set
     */
    public void setPlatform_code(Integer platform_code) {
        this.platform_code = platform_code;
    }

    /**
     * @return the station_date
     */
    @DateTimeFormat(pattern = DATE_FORMAT)
    public Date getStation_date() {
        return station_date;
    }

    /**
     * @param station_date the station_date to set
     */
    public void setStation_date(Date station_date) {
        this.station_date = station_date;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the measure_type
     */
    public Integer getMeasure_type() {
        return measure_type;
    }

    /**
     * @param measure_type the measure_type to set
     */
    public void setMeasure_type(Integer measure_type) {
        this.measure_type = measure_type;
    }

    /**
     * @return the parameter_code
     */
    public Integer getParameter_code() {
        return parameter_code;
    }

    /**
     * @param parameter_code the parameter_code to set
     */
    public void setParameter_code(Integer parameter_code) {
        this.parameter_code = parameter_code;
    }

    /**
     * @return the parameter_value
     */
    public Double getParameter_value() {
        return parameter_value;
    }

    /**
     * @param parameter_value the parameter_value to set
     */
    public void setParameter_value(Double parameter_value) {
        this.parameter_value = parameter_value;
    }

    /**
     * @return the parameter_qc
     */
    public Integer getParameter_qc() {
        return parameter_qc;
    }

    /**
     * @param parameter_qc the parameter_qc to set
     */
    public void setParameter_qc(Integer parameter_qc) {
        this.parameter_qc = parameter_qc;
    }

    /**
     * @return the z_code
     */
    public Integer getZ_code() {
        return z_code;
    }

    /**
     * @param z_code the z_code to set
     */
    public void setZ_code(Integer z_code) {
        this.z_code = z_code;
    }

    /**
     * @return the z_value
     */
    public Double getZ_value() {
        return z_value;
    }

    /**
     * @param z_value the z_value to set
     */
    public void setZ_value(Double z_value) {
        this.z_value = z_value;
    }

    /**
     * @return the z_qc
     */
    public Integer getZ_qc() {
        return z_qc;
    }

    /**
     * @param z_qc the z_qc to set
     */
    public void setZ_qc(Integer z_qc) {
        this.z_qc = z_qc;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "station_id")
    private Integer station_id;

    @Column(name = "platform_code")
    private Integer platform_code;

    @Column(name = "station_date")
    @DateTimeFormat(pattern = DATE_FORMAT)
    private Date station_date;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "measure_type")
    private Integer measure_type;

    @Column(name = "parameter_code")
    private Integer parameter_code;

    @Column(name = "parameter_value")
    private Double parameter_value;

    @Column(name = "parameter_qc")
    private Integer parameter_qc;

    @Column(name = "z_code")
    private Integer z_code;

    @Column(name = "z_value")
    private Double z_value;

    @Column(name = "z_qc")
    private Integer z_qc;

    @Column(name = "z_level")
    private Double z_level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
