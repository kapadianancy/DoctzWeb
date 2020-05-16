package entity;

import entity.DoctorTb;
import entity.HospitalTb;
import entity.PatientTb;
import entity.UsergroupTb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T11:33:09")
@StaticMetamodel(UserTb.class)
public class UserTb_ { 

    public static volatile CollectionAttribute<UserTb, DoctorTb> doctorTbCollection;
    public static volatile CollectionAttribute<UserTb, HospitalTb> hospitalTbCollection;
    public static volatile SingularAttribute<UserTb, String> password;
    public static volatile SingularAttribute<UserTb, Long> contact;
    public static volatile CollectionAttribute<UserTb, PatientTb> patientTbCollection;
    public static volatile CollectionAttribute<UserTb, UsergroupTb> usergroupTbCollection;
    public static volatile SingularAttribute<UserTb, String> userName;
    public static volatile SingularAttribute<UserTb, Integer> userId;
    public static volatile SingularAttribute<UserTb, String> email;

}