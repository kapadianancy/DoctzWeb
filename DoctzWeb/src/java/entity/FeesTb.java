/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.json.bind.annotation.JsonbTransient;
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
@Table(name = "fees_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeesTb.findAll", query = "SELECT f FROM FeesTb f"),
    @NamedQuery(name = "FeesTb.findByFeesId", query = "SELECT f FROM FeesTb f WHERE f.feesId = :feesId"),
    @NamedQuery(name = "FeesTb.findByFees", query = "SELECT f FROM FeesTb f WHERE f.fees = :fees"),
    @NamedQuery(name = "FeesTb.findFeesByHospital", query = "SELECT f FROM FeesTb f WHERE f.hospitalId = :hospitalId"),
    @NamedQuery(name = "FeesTb.findHospitalBySpecialization", query = "SELECT f.hospitalId FROM FeesTb f WHERE f.specializationId =:specializationId"),
    @NamedQuery(name = "FeesTb.findFeesBySpecializationName", query = "SELECT f FROM FeesTb f WHERE f.specializationId.name = :specializationName"),
    @NamedQuery(name = "FeesTb.findHospitalByFeesAndSpecialization", query = "SELECT f.hospitalId FROM FeesTb f WHERE f.specializationId =:specializationId and f.fees >= :fromFee and f.fees <= :toFee"),
    @NamedQuery(name = "FeesTb.getHospitalByLowToHighFees", query = "SELECT f FROM FeesTb f WHERE f.specializationId.name = :spcializationName ORDER BY f.fees"),
    @NamedQuery(name = "FeesTb.getHospitalByHighToLowFees", query = "SELECT f FROM FeesTb f WHERE f.specializationId.name = :spcializationName ORDER BY f.fees desc"),
    @NamedQuery(name = "FeesTb.getHospitalByFees", query = "SELECT f FROM FeesTb f WHERE f.specializationId.name = :spcializationName and f.fees between :from and :to "),
    @NamedQuery(name = "FeesTb.findFeesBySpecialityAndHospital", query = "SELECT f FROM FeesTb f WHERE f.specializationId = :specializationId and f.hospitalId = :hospitalId"),
    @NamedQuery(name = "FeesTb.findFeesBySpeciality", query = "SELECT f FROM FeesTb f WHERE f.specializationId = :specializationId")
        
    })
public class FeesTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feesId")
    private Integer feesId;
    @Basic(optional = false)
    @Column(name = "fees")
    private int fees;
    @JoinColumn(name = "hospitalId", referencedColumnName = "hospitalId")
    @ManyToOne(optional = false)
    private HospitalTb hospitalId;
    @JoinColumn(name = "specializationId", referencedColumnName = "specializationId")
    @ManyToOne(optional = false)
    private SpecializationTb specializationId;

    public FeesTb() {
    }

    public FeesTb(Integer feesId) {
        this.feesId = feesId;
    }

    public FeesTb(Integer feesId, int fees) {
        this.feesId = feesId;
        this.fees = fees;
    }

    public Integer getFeesId() {
        return feesId;
    }

    public void setFeesId(Integer feesId) {
        this.feesId = feesId;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
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
        hash += (feesId != null ? feesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeesTb)) {
            return false;
        }
        FeesTb other = (FeesTb) object;
        if ((this.feesId == null && other.feesId != null) || (this.feesId != null && !this.feesId.equals(other.feesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FeesTb[ feesId=" + feesId + " ]";
    }
    
}
