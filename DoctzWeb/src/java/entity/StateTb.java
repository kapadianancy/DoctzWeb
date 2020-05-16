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
@Table(name = "state_tb")
//////@XmlRootElement
@NamedQueries({
    
    @NamedQuery(name = "StateTb.findAll", query = "SELECT s FROM StateTb s"),
    @NamedQuery(name = "StateTb.findByStateId", query = "SELECT s FROM StateTb s WHERE s.stateId = :stateId"),
    @NamedQuery(name = "StateTb.findByStateName", query = "SELECT s FROM StateTb s WHERE s.stateName = :stateName"),
    @NamedQuery(name = "StateTb.findByIsActive", query = "SELECT s FROM StateTb s WHERE s.isActive = :isActive")})
public class StateTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stateId")
    private Integer stateId;
    @Basic(optional = false)
    @Column(name = "stateName")
    private String stateName;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateId")
    private Collection<CityTb> cityTbCollection;

    public StateTb() {
    }

    public StateTb(Integer stateId) {
        this.stateId = stateId;
    }

    public StateTb(Integer stateId, String stateName, int isActive) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.isActive = isActive;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @JsonbTransient
    public Collection<CityTb> getCityTbCollection() {
        return cityTbCollection;
    }

    public void setCityTbCollection(Collection<CityTb> cityTbCollection) {
        this.cityTbCollection = cityTbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StateTb)) {
            return false;
        }
        StateTb other = (StateTb) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StateTb[ stateId=" + stateId + " ]";
    }
    
}
