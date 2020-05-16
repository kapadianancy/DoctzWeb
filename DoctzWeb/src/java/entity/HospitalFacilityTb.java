/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "hospital_facility_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalFacilityTb.findAll", query = "SELECT h FROM HospitalFacilityTb h"),
    @NamedQuery(name = "HospitalFacilityTb.findByFacilityId", query = "SELECT h FROM HospitalFacilityTb h WHERE h.facilityId = :facilityId")})
public class HospitalFacilityTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "facilityId")
    private Integer facilityId;
    @JoinColumn(name = "hospitalId", referencedColumnName = "hospitalId")
    @ManyToOne(optional = false)
    private HospitalTb hospitalId;
    @JoinColumn(name = "specializationId", referencedColumnName = "specializationId")
    @ManyToOne(optional = false)
    private SpecializationTb specializationId;

    public HospitalFacilityTb() {
    }

    public HospitalFacilityTb(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public HospitalTb getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(HospitalTb hospitalId) {
        this.hospitalId = hospitalId;
    }

    public SpecializationTb getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(SpecializationTb specializationId) {
        this.specializationId = specializationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facilityId != null ? facilityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalFacilityTb)) {
            return false;
        }
        HospitalFacilityTb other = (HospitalFacilityTb) object;
        if ((this.facilityId == null && other.facilityId != null) || (this.facilityId != null && !this.facilityId.equals(other.facilityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HospitalFacilityTb[ facilityId=" + facilityId + " ]";
    }
    
}
