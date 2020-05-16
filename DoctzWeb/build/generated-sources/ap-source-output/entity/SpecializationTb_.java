package entity;

import entity.DoctorTb;
import entity.FeesTb;
import entity.HospitalFacilityTb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T11:33:09")
@StaticMetamodel(SpecializationTb.class)
public class SpecializationTb_ { 

    public static volatile SingularAttribute<SpecializationTb, String> image;
    public static volatile CollectionAttribute<SpecializationTb, DoctorTb> doctorTbCollection;
    public static volatile CollectionAttribute<SpecializationTb, FeesTb> feesTbCollection;
    public static volatile CollectionAttribute<SpecializationTb, HospitalFacilityTb> hospitalFacilityTbCollection;
    public static volatile SingularAttribute<SpecializationTb, String> name;
    public static volatile SingularAttribute<SpecializationTb, String> description;
    public static volatile SingularAttribute<SpecializationTb, Integer> specializationId;
    public static volatile SingularAttribute<SpecializationTb, Integer> isActive;

}