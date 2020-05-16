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
import javax.persistence.Lob;
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
@Table(name = "specialization_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpecializationTb.findAll", query = "SELECT s FROM SpecializationTb s"),
    @NamedQuery(name = "SpecializationTb.findBySpecializationId", query = "SELECT s FROM SpecializationTb s WHERE s.specializationId = :specializationId"),
    @NamedQuery(name = "SpecializationTb.findByName", query = "SELECT s FROM SpecializationTb s WHERE s.name = :name"),
    @NamedQuery(name = "SpecializationTb.findByDescription", query = "SELECT s FROM SpecializationTb s WHERE s.description = :description"),
    @NamedQuery(name = "SpecializationTb.findByIsActive", query = "SELECT s FROM SpecializationTb s WHERE s.isActive =1")})


public class SpecializationTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "specializationId")
    private Integer specializationId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Lob
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specializationId")
    private Collection<HospitalFacilityTb> hospitalFacilityTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specializationId")
    private Collection<DoctorTb> doctorTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specializationId")
    private Collection<FeesTb> feesTbCollection;
  //  @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentSpecializationId")
   // private Collection<SpecializationTb> specializationTbCollection;
    //@JoinColumn(name = "parentSpecializationId", referencedColumnName = "specializationId")
    //@ManyToOne(optional = false)
    //private SpecializationTb parentSpecializationId;

    public SpecializationTb() {
    }

    public SpecializationTb(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public SpecializationTb(Integer specializationId, String name, String description, String image, int isActive) {
        this.specializationId = specializationId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.isActive = isActive;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    public Collection<DoctorTb> getDoctorTbCollection() {
        return doctorTbCollection;
    }

    public void setDoctorTbCollection(Collection<DoctorTb> doctorTbCollection) {
        this.doctorTbCollection = doctorTbCollection;
    }

    @JsonbTransient
    public Collection<FeesTb> getFeesTbCollection() {
        return feesTbCollection;
    }

    public void setFeesTbCollection(Collection<FeesTb> feesTbCollection) {
        this.feesTbCollection = feesTbCollection;
    }

//    @JsonbTransient
//    public Collection<SpecializationTb> getSpecializationTbCollection() {
//        return specializationTbCollection;
//    }

//    public void setSpecializationTbCollection(Collection<SpecializationTb> specializationTbCollection) {
//        this.specializationTbCollection = specializationTbCollection;
//    }

//    public SpecializationTb getParentSpecializationId() {
//        return parentSpecializationId;
//    }

//    public void setParentSpecializationId(SpecializationTb parentSpecializationId) {
//        this.parentSpecializationId = parentSpecializationId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specializationId != null ? specializationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecializationTb)) {
            return false;
        }
        SpecializationTb other = (SpecializationTb) object;
        if ((this.specializationId == null && other.specializationId != null) || (this.specializationId != null && !this.specializationId.equals(other.specializationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SpecializationTb[ specializationId=" + specializationId + " ]";
    }
    
}
