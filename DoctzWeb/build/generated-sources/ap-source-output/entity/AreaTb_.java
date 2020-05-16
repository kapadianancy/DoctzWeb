package entity;

import entity.CityTb;
import entity.HospitalTb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T11:33:09")
@StaticMetamodel(AreaTb.class)
public class AreaTb_ { 

    public static volatile SingularAttribute<AreaTb, Integer> pincode;
    public static volatile CollectionAttribute<AreaTb, HospitalTb> hospitalTbCollection;
    public static volatile SingularAttribute<AreaTb, Integer> areaId;
    public static volatile SingularAttribute<AreaTb, String> areaName;
    public static volatile SingularAttribute<AreaTb, Double> latitude;
    public static volatile SingularAttribute<AreaTb, CityTb> cityId;
    public static volatile SingularAttribute<AreaTb, Integer> isActive;
    public static volatile SingularAttribute<AreaTb, Double> longitude;

}