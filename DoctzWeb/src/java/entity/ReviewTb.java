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
@Table(name = "review_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReviewTb.findAll", query = "SELECT r FROM ReviewTb r"),
    @NamedQuery(name = "ReviewTb.findByReviewId", query = "SELECT r FROM ReviewTb r WHERE r.reviewId = :reviewId"),
    @NamedQuery(name = "ReviewTb.findByReview", query = "SELECT r FROM ReviewTb r WHERE r.review = :review")})
public class ReviewTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reviewId")
    private Integer reviewId;
    @Basic(optional = false)
    @Column(name = "review")
    private String review;
    @JoinColumn(name = "hospitalId", referencedColumnName = "hospitalId")
    @ManyToOne(optional = false)
    private HospitalTb hospitalId;
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    @ManyToOne(optional = false)
    private PatientTb patientId;
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
    @ManyToOne(optional = false)
    private DoctorTb doctorId;

    public ReviewTb() {
    }

    public ReviewTb(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public ReviewTb(Integer reviewId, String review) {
        this.reviewId = reviewId;
        this.review = review;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public HospitalTb getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(HospitalTb hospitalId) {
        this.hospitalId = hospitalId;
    }

    public PatientTb getPatientId() {
        return patientId;
    }

    public void setPatientId(PatientTb patientId) {
        this.patientId = patientId;
    }

    public DoctorTb getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorTb doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewTb)) {
            return false;
        }
        ReviewTb other = (ReviewTb) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReviewTb[ reviewId=" + reviewId + " ]";
    }
    
}
