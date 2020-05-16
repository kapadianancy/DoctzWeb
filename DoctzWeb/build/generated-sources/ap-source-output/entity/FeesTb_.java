package entity;

import entity.HospitalTb;
import entity.SpecializationTb;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T11:33:09")
@StaticMetamodel(FeesTb.class)
public class FeesTb_ { 

    public static volatile SingularAttribute<FeesTb, Integer> fees;
    public static volatile SingularAttribute<FeesTb, Integer> feesId;
    public static volatile SingularAttribute<FeesTb, HospitalTb> hospitalId;
    public static volatile SingularAttribute<FeesTb, SpecializationTb> specializationId;

}