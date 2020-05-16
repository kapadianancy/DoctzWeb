/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "area_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaTb.findAll", query = "SELECT a FROM AreaTb a"),
    @NamedQuery(name = "AreaTb.findByAreaId", query = "SELECT a FROM AreaTb a WHERE a.areaId = :areaId"),
    @NamedQuery(name = "AreaTb.findByAreaName", query = "SELECT a FROM AreaTb a WHERE a.areaName = :areaName"),
    @NamedQuery(name = "AreaTb.findByPincode", query = "SELECT a FROM AreaTb a WHERE a.pincode = :pincode"),
    @NamedQuery(name = "AreaTb.findByLatitude", query = "SELECT a FROM AreaTb a WHERE a.latitude = :latitude"),
    @NamedQuery(name = "AreaTb.findByLongitude", query = "SELECT a FROM AreaTb a WHERE a.longitude = :longitude"),
    @NamedQuery(name = "AreaTb.findByIsActive", query = "SELECT a FROM AreaTb a WHERE a.isActive = :isActive")})
public class AreaTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "areaId")
    private Integer areaId;
    @Basic(optional = false)
    @Column(name = "areaName")
    private String areaName;
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
    @Column(name = "isActive")
    private int isActive;
    @JoinColumn(name = "cityId", referencedColumnName = "cityId")
    @ManyToOne(optional = false)
    private CityTb cityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId")
    private Collection<HospitalTb> hospitalTbCollection;

    public AreaTb() {
    }

    public AreaTb(Integer areaId) {
        this.areaId = areaId;
    }

    public AreaTb(Integer areaId, String areaName, int pincode, double latitude, double longitude, int isActive) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isActive = isActive;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public CityTb getCityId() {
        return cityId;
    }

    public void setCityId(CityTb cityId) {
        this.cityId = cityId;
    }

    @JsonbTransient
    public Collection<HospitalTb> getHospitalTbCollection() {
        return hospitalTbCollection;
    }

    public void setHospitalTbCollection(Collection<HospitalTb> hospitalTbCollection) {
        this.hospitalTbCollection = hospitalTbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaId != null ? areaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaTb)) {
            return false;
        }
        AreaTb other = (AreaTb) object;
        if ((this.areaId == null && other.areaId != null) || (this.areaId != null && !this.areaId.equals(other.areaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AreaTb[ areaId=" + areaId + " ]";
    }
    
}
