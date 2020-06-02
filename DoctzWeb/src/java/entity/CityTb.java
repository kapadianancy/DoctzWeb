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
@Table(name = "city_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CityTb.findAll", query = "SELECT c FROM CityTb c"),
    @NamedQuery(name = "CityTb.findByCityId", query = "SELECT c FROM CityTb c WHERE c.cityId = :cityId"),
    @NamedQuery(name = "CityTb.findByCityName", query = "SELECT c FROM CityTb c WHERE c.cityName = :cityName"),
    @NamedQuery(name = "CityTb.findByIsActive", query = "SELECT c FROM CityTb c WHERE c.isActive = :isActive")
})
public class CityTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cityId")
    private Integer cityId;
    @Basic(optional = false)
    @Column(name = "cityName")
    private String cityName;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId")
    private Collection<AreaTb> areaTbCollection;
    @JoinColumn(name = "stateId", referencedColumnName = "stateId")
    @ManyToOne(optional = false)
    private StateTb stateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId")
    private Collection<HospitalTb> hospitalTbCollection;

    public CityTb() {
    }

    public CityTb(Integer cityId) {
        this.cityId = cityId;
    }

    public CityTb(Integer cityId, String cityName, int isActive) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.isActive = isActive;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @JsonbTransient
    public Collection<AreaTb> getAreaTbCollection() {
        return areaTbCollection;
    }

    public void setAreaTbCollection(Collection<AreaTb> areaTbCollection) {
        this.areaTbCollection = areaTbCollection;
    }

    public StateTb getStateId() {
        return stateId;
    }

    public void setStateId(StateTb stateId) {
        this.stateId = stateId;
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
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityTb)) {
            return false;
        }
        CityTb other = (CityTb) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CityTb[ cityId=" + cityId + " ]";
    }
    
}
