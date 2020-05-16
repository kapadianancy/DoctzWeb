package entity;

import entity.DoctorTb;
import entity.HospitalTb;
import entity.PatientTb;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T11:33:09")
@StaticMetamodel(ReviewTb.class)
public class ReviewTb_ { 

    public static volatile SingularAttribute<ReviewTb, HospitalTb> hospitalId;
    public static volatile SingularAttribute<ReviewTb, PatientTb> patientId;
    public static volatile SingularAttribute<ReviewTb, DoctorTb> doctorId;
    public static volatile SingularAttribute<ReviewTb, String> review;
    public static volatile SingularAttribute<ReviewTb, Integer> reviewId;

}