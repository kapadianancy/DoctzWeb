/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "hospital_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalTb.findAll", query = "SELECT h FROM HospitalTb h"),
    @NamedQuery(name = "HospitalTb.findByHospitalId", query = "SELECT h FROM HospitalTb h WHERE h.hospitalId = :hospitalId"),
    @NamedQuery(name = "HospitalTb.findByHospitalName", query = "SELECT h FROM HospitalTb h WHERE h.hospitalName = :hospitalName"),
    @NamedQuery(name = "HospitalTb.findByPincode", query = "SELECT h FROM HospitalTb h WHERE h.pincode = :pincode"),
    @NamedQuery(name = "HospitalTb.findByLatitude", query = "SELECT h FROM HospitalTb h WHERE h.latitude = :latitude"),
    @NamedQuery(name = "HospitalTb.findByLongitude", query = "SELECT h FROM HospitalTb h WHERE h.longitude = :longitude"),
    @NamedQuery(name = "HospitalTb.findByOpeningTime", query = "SELECT h FROM HospitalTb h WHERE h.openingTime = :openingTime"),
    @NamedQuery(name = "HospitalTb.findByClosingTime", query = "SELECT h FROM HospitalTb h WHERE h.closingTime = :closingTime"),
    @NamedQuery(name = "HospitalTb.getHospitalByCity", query = "SELECT h FROM HospitalTb h WHERE h.cityId.cityName = :cityName and h.isActive = 1"),
    @NamedQuery(name = "HospitalTb.getHospitalByArea", query = "SELECT h FROM HospitalTb h WHERE h.areaId.areaName = :areaName and h.isActive = 1"),
    @NamedQuery(name = "HospitalTb.getHospitalBystate", query = "SELECT h FROM HospitalTb h WHERE h.cityId.stateId.stateName = :stateName and h.isActive = 1"),
    @NamedQuery(name = "HospitalTb.getTotalHospitals", query = "SELECT count(h.hospitalId) FROM HospitalTb h WHERE h.isActive = 1"),
    @NamedQuery(name = "HospitalTb.NearMeHospital", query = "SELECT h FROM HospitalTb h WHERE h.isActive = 1 and h.latitude >= :currentlati and h.latitude <= :newlati and h.longitude >= :currentlongi and h.longitude <= :newlongi"),       
    @NamedQuery(name = "HospitalTb.findByIsActive", query = "SELECT h FROM HospitalTb h WHERE h.isActive = :isActive")})
public class HospitalTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hospitalId")
    private Integer hospitalId;
    @Basic(optional = false)
    @Column(name = "hospitalName")
    private String hospitalName;
    @Basic(optional = false)
    @Lob
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "pincode")
    private int pincode;
    @Basic(optional = false)
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @Column(name = "openingTime")
    @Temporal(TemporalType.TIME)
    private Date openingTime;
    @Basic(optional = false)
    @Column(name = "closingTime")
    @Temporal(TemporalType.TIME)
    private Date closingTime;
    @Basic(optional = false)
    @Lob
    @Column(name = "logo")
    private String logo;
    @Basic(optional = false)
    @Lob
    @Column(name = "documents")
    private String documents;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalId")
    private Collection<HospitalFacilityTb> hospitalFacilityTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalId")
    private Collection<DoctorScheduleTb> doctorScheduleTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalId")
    private Collection<ReviewTb> reviewTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalId")
    private Collection<FeesTb> feesTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalId")
    private Collection<AppointmentTb> appointmentTbCollection;
    @JoinColumn(name = "areaId", referencedColumnName = "areaId")
    @ManyToOne(optional = false)
    private AreaTb areaId;
    @JoinColumn(name = "cityId", referencedColumnName = "cityId")
    @ManyToOne(optional = false)
    private CityTb cityId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private UserTb userId;

    public HospitalTb() {
    }

    public HospitalTb(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public HospitalTb(Integer hospitalId, String hospitalName, String address, int pincode, double latitude, double longitude, Date openingTime, Date closingTime, String logo, String documents, int isActive) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.address = address;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.logo = logo;
        this.documents = documents;
        this.isActive = isActive;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @JsonbTransient
    public Collection<HospitalFacilityTb> getHospitalFacilityTbCollection() {
        return hospitalFacilityTbCollection;
    }

    public void setHospitalFacilityTbCollection(Collection<HospitalFacilityTb> hospitalFacilityTbCollection) {
        this.hospitalFacilityTbCollection = hospitalFacilityTbCollection;
    }

    @JsonbTransient
    public Collection<DoctorScheduleTb> getDoctorScheduleTbCollection() {
        return doctorScheduleTbCollection;
    }

    public void setDoctorScheduleTbCollection(Collection<DoctorScheduleTb> doctorScheduleTbCollection) {
        this.doctorScheduleTbCollection = doctorScheduleTbCollection;
    }

    @JsonbTransient
    public Collection<ReviewTb> getReviewTbCollection() {
        return reviewTbCollection;
    }

    public void setReviewTbCollection(Collection<ReviewTb> reviewTbCollection) {
        this.reviewTbCollection = reviewTbCollection;
    }

    @JsonbTransient
    public Collection<FeesTb> getFeesTbCollection() {
        return feesTbCollection;
    }

    public void setFeesTbCollection(Collection<FeesTb> feesTbCollection) {
        this.feesTbCollection = feesTbCollection;
    }

    @JsonbTransient
    public Collection<AppointmentTb> getAppointmentTbCollection() {
        return appointmentTbCollection;
    }

    public void setAppointmentTbCollection(Collection<AppointmentTb> appointmentTbCollection) {
        this.appointmentTbCollection = appointmentTbCollection;
    }

    public AreaTb getAreaId() {
        return areaId;
    }

    public void setAreaId(AreaTb areaId) {
        this.areaId = areaId;
    }

    public CityTb getCityId() {
        return cityId;
    }

    public void setCityId(CityTb cityId) {
        this.cityId = cityId;
    }

    public UserTb getUserId() {
        return userId;
    }

    public void setUserId(UserTb userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hospitalId != null ? hospitalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalTb)) {
            return false;
        }
        HospitalTb other = (HospitalTb) object;
        if ((this.hospitalId == null && other.hospitalId != null) || (this.hospitalId != null && !this.hospitalId.equals(other.hospitalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HospitalTb[ hospitalId=" + hospitalId + " ]";
    }
    
}
