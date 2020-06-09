/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Admin
 */
@Stateless
public class doctzBean implements doctzBeanLocal {

    @PersistenceContext(unitName = "doctzpu")
    EntityManager em;
    Pbkdf2PasswordHashImpl pb=new Pbkdf2PasswordHashImpl();
    //String pass=pb.generate(this.password.toCharArray());
    
    @Override
    public int addState(String stateName) {
        int status=0;
        StateTb s=new StateTb();
        s.setStateName(stateName);
        s.setIsActive(1);
        em.persist(s);
        status=1;
        return status;
    }

    @Override
    public int updateState(int stateId, String stateName) {
        int status=0;
        StateTb s =em.find(StateTb.class,stateId);
        s.setStateName(stateName);
        s.setIsActive(1);
        em.merge(s);
        status=1;
        return status;
    }

    @Override
    public int deleteState(int stateId) {
        int status=0;
        StateTb s =em.find(StateTb.class,stateId);
        s.setIsActive(0);
        em.merge(s);
        status=1;
        return status;
    }

    @Override
    public Collection<StateTb> getAllState() {
        return em.createNamedQuery("StateTb.findByIsActive").setParameter("isActive",1).getResultList();
    }

    @Override
    public int addCity(String cityName, int stateId) {
        int status=0;
        StateTb s=em.find(StateTb.class,stateId);
        Collection<CityTb> cities=s.getCityTbCollection();
        CityTb c=new CityTb();
        c.setCityName(cityName);
        c.setStateId(s);
        c.setIsActive(1);
        if(! cities.contains(c))
        {
            cities.add(c);
            s.setCityTbCollection(cities);
            em.persist(c);
            em.merge(s);
            status=1;
        }
        return status;
    }

    @Override
    public int updateCity(int cityId, String cityName, int stateId) {
        int status=0;
        
        StateTb s=em.find(StateTb.class,stateId);
        Collection<CityTb> cities=s.getCityTbCollection();
        CityTb c=em.find(CityTb.class,cityId);         
        
        if(cities.contains(c))
        {
            cities.remove(c);
            
            c.setCityName(cityName);
            c.setStateId(s);
            c.setIsActive(1);
            cities.add(c);
            s.setCityTbCollection(cities);
            em.merge(c);
            em.merge(s);
            status=1;
        }
        return status;
    }

    @Override
    public int addArea(String areaName, int cityId, int pincode) {
        int status=0;
        CityTb c=em.find(CityTb.class,cityId);
        Collection<AreaTb> areas=c.getAreaTbCollection();
        AreaTb a=new AreaTb();
        a.setAreaName(areaName);
        a.setCityId(c);
        a.setPincode(pincode);
        a.setIsActive(1);
        if(! areas.contains(a))
        {
            areas.add(a);
            c.setAreaTbCollection(areas);
            em.persist(a);
            em.merge(c);
            status=1;
        }
        return status;
    }

    @Override
    public int updateArea(int areaId, String areaName, int cityId, int pincode) {
        int status=0;
        CityTb c=em.find(CityTb.class,cityId);
        Collection<AreaTb> areas=c.getAreaTbCollection();
        AreaTb a=em.find(AreaTb.class,areaId);
       
        if(areas.contains(a))
        {
            areas.remove(a);
            
            a.setAreaName(areaName);
            a.setCityId(c);
            a.setPincode(pincode);
            //a.setIsActive(1);
            areas.add(a);
            c.setAreaTbCollection(areas);
            em.merge(a);
            em.merge(c);
            status=1;
        }
        return status;
    }

    @Override
    public int deleteArea(int areaId) {
        int status=0;
        AreaTb a =em.find(AreaTb.class,areaId);
        a.setIsActive(0);
        em.merge(a);
        status=1;
        return status;
    }

    @Override
    public Collection<AreaTb> getAllArea() {
       return em.createNamedQuery("AreaTb.findByIsActive").setParameter("isActive",1).getResultList();
    }

    @Override
    public int deleteCity(int cityId) {
        int status=0;
        CityTb c =em.find(CityTb.class,cityId);
        c.setIsActive(0);
        em.merge(c);
        status=1;
        return status;
    }

    @Override
    public Collection<CityTb> getAllCity() {
        return em.createNamedQuery("CityTb.findByIsActive").setParameter("isActive",1).getResultList();
    }

    @Override
    public int addUser(String userName, String password, String email, long contact) {
        Integer userid=0;
        UserTb u=new UserTb();
        u.setUserName(userName);
        u.setEmail(email);
        u.setPassword(password);
        u.setContact(contact);
        em.persist(u);
        em.flush();
        userid=u.getUserId();
        return userid;
    }
    
   
    @Override
    public int addUserGroup(int userId, int groupId) {
        int status=0;
        GroupTb g=em.find(GroupTb.class,groupId);
        UserTb u=em.find(UserTb.class,userId);
        Collection<UsergroupTb> users=g.getUsergroupTbCollection();
        Collection<UsergroupTb> groups=u.getUsergroupTbCollection();
        UsergroupTb ug=new UsergroupTb();
        ug.setUserId(u);
        ug.setGroupId(g);
        if(! users.contains(ug))
        {
            
            users.add(ug);
            groups.add(ug);
            
            
            g.setUsergroupTbCollection(users);
            u.setUsergroupTbCollection(groups);
            em.persist(ug);
            em.merge(g);
            status=1;
        }
        
        
        return status;
    }

    @Override
    public int authenticateUser(String email, long contact) {
        Integer userid=0;
        UserTb u=new UserTb();
        u.setEmail(email);
        u.setUserName("");
        u.setPassword("");
        u.setContact(contact);
        em.persist(u);
        em.flush();
        userid=u.getUserId();
        return userid;
    }

    @Override
    public int hospitalRegistration(String hospitalName, String address, int areaId, int cityId, int pincode, double latitude, double longitude,String maplink, Time openingTime, Time closingTime, String logo, String documents,String email,long contact) {
        int status=0;
        int userid=this.authenticateUser(email, contact);
        
        if(userid>0)
        {
            int i=this.addUserGroup(userid, 2);
            if(i==1)
            {
                AreaTb area=em.find(AreaTb.class,areaId);
                Collection<HospitalTb> areas=area.getHospitalTbCollection();
                
                CityTb c=em.find(CityTb.class,cityId);
                Collection<HospitalTb> cities=c.getHospitalTbCollection();
                
                UserTb u=em.find(UserTb.class,userid);
                Collection<HospitalTb> users=u.getHospitalTbCollection();
                HospitalTb h=new HospitalTb();
                h.setHospitalName(hospitalName);
                h.setAddress(address);
                h.setAreaId(area);
                h.setCityId(c);
                h.setClosingTime(closingTime);
                h.setDocuments("resources/img/hospitalDoc/"+documents);
                h.setIsActive(0);
                h.setLatitude(latitude);
                h.setLogo("resources/img/hospital/"+logo);
                h.setLongitude(longitude);
                h.setMaplink(maplink);
                h.setOpeningTime(openingTime);
                h.setPincode(pincode);
                h.setUserId(u);
                
                if(! users.contains(h))
                {
                    em.persist(h);
                    areas.add(h);
                    cities.add(h);
                    users.add(h);
                    
                    area.setHospitalTbCollection(areas);
                    c.setHospitalTbCollection(cities);
                    u.setHospitalTbCollection(users);
                    
                    em.merge(area);
                    em.merge(c);
                    em.merge(u);
                    
                    status=1;
                }
                
            }
        }
        
        return status;
    }
    
    
    @Override
    public int addSpecialization(String name, String description, String image) {
        int status=0;
       // SpecializationTb s=em.find(SpecializationTb.class,parentSpecializationId);
       // Collection<SpecializationTb> specilizations=s.getSpecializationTbCollection();
        SpecializationTb new_spe=new SpecializationTb();
        new_spe.setName(name);
       // new_spe.setParentSpecializationId(s);
        new_spe.setDescription(description);
        new_spe.setImage("resources/img/specialities/"+image);
        new_spe.setIsActive(1);
//        if(! specilizations.contains(new_spe))
//        {
//            specilizations.add(new_spe);
//            s.setSpecializationTbCollection(specilizations);
            em.persist(new_spe);
           // em.merge(s);
            status=1;
        //}
        return status;
    
    }

    @Override
    public int updateSpecialization(int specializaionId, String name, String description, String image) {
        int status=0;
//        
          SpecializationTb new_spe=em.find(SpecializationTb.class,specializaionId);
//        SpecializationTb s=em.find(SpecializationTb.class,parentSpecializationId);
     
            new_spe.setName(name);
           // new_spe.setParentSpecializationId(s);
            new_spe.setDescription(description);
            new_spe.setImage("resources/img/specialities/"+image);
          //  new_spe.setIsActive(1); 
            em.merge(new_spe);
           
            status=1;

        return status;
    
    }

    @Override
    public int deleteSpecialization(int specializaionId) {
        int status=0;
        SpecializationTb s =em.find(SpecializationTb.class,specializaionId);
        s.setIsActive(0);
        em.merge(s);
        status=1;
        return status;
    }

    @Override
    public Collection<SpecializationTb> getAllSpecialization() {
       return em.createNamedQuery("SpecializationTb.findByIsActive").getResultList();
    }
    
    @Override
    public SpecializationTb getSpecializationById(int specializaionId)
    {
        return em.find(SpecializationTb.class,specializaionId);
    }

    @Override
    public Collection<SpecializationTb> getSpecializationByParentId(int parentSpecializationId) {
        SpecializationTb s=em.find(SpecializationTb.class,parentSpecializationId);
        return em.createNamedQuery("SpecializationTb.findByParentSpecializationId").setParameter("parentSpecializationId",s).getResultList();
    }

    @Override
    public int doctorRegistration(String doctorName, int specializaionId, String experience, String gender, String certificates, String education, String email, long contact,String profile,String document) {
          int status=0;
          
        int userid=this.authenticateUser(email, contact);
        
        if(userid>0)
        {
            int i=this.addUserGroup(userid, 3);
            if(i==1)
            {
                SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
                Collection<DoctorTb> spes=s.getDoctorTbCollection();
                
                UserTb u=em.find(UserTb.class,userid);
                Collection<DoctorTb> users=u.getDoctorTbCollection();
                
                DoctorTb d=new DoctorTb();
                d.setDoctorName(doctorName);
                d.setCertificates(certificates);
                d.setEducation(education);
                d.setExperience(experience);
                d.setGender(gender);
                d.setProfile("resources/img/doctors/"+profile);
                d.setDocuments("resources/img/doctorDoc/"+document);
                d.setIsActive(0);
                d.setSpecializationId(s);
                d.setUserId(u);
                
                
                if(! users.contains(d))
                {
                    em.persist(d);
                    spes.add(d);
                    users.add(d);
                    
                    s.setDoctorTbCollection(spes);
                    u.setDoctorTbCollection(users);
                    
                    em.merge(s);
                    em.merge(u);
                    
                    status=1;
                }
                
            }
        }
        
        return status;
    }

    @Override
    public int patientRegistration(String patientName, String gender, String address, int age, String username, String password, String email, long contact) {
        int status=0;
       // System.out.println(password);
        String pass=pb.generate(password.toCharArray());
        //System.out.println("pass----"+pass);
        //String pass=password;
        int userid=this.addUser(username, pass, email, contact);
        
        if(userid>0)
        {
            int i=this.addUserGroup(userid, 4);
            if(i==1)
            {
                UserTb u=em.find(UserTb.class,userid);
                Collection<PatientTb> users=u.getPatientTbCollection();
                
                PatientTb p=new PatientTb();
                p.setAddress(address);
                p.setAge(age);
                p.setGender(gender);
                p.setIsActive(1);
                p.setPatientName(patientName);
                p.setUserId(u);
                if(! users.contains(p))
                {
                    em.persist(p);
                    users.add(p);
                    u.setPatientTbCollection(users); 
                    em.merge(u);
                    status=1;
                }
                
            }
        }
        
        return status;
    }

    @Override
    public Collection<PatientTb> getAllPatient() {
        return em.createNamedQuery("PatientTb.findAll").getResultList();
    }

    
    
    @Override
    public Collection<HospitalTb> getAllHospital() {
         return em.createNamedQuery("HospitalTb.findByIsActive").setParameter("isActive",1).getResultList();
   }

    @Override
    public int verifyHospital(int hospitalId,String username,String password) {
        int status=0;
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        String pass=pb.generate(password.toCharArray());
        
        UserTb u=h.getUserId();
        int userid=u.getUserId();
        UserTb user=em.find(UserTb.class,userid);
        user.setUserName(username);
        user.setPassword(pass);
        em.merge(user);
        h.setIsActive(1);
        em.merge(h);
        status=1;
        
        
        return status;
    }

    @Override
    public Collection<DoctorTb> getAllDoctor() {
        return em.createNamedQuery("DoctorTb.findByIsActive").setParameter("isActive",1).getResultList();
    }

    @Override
    public int verifyDoctor(int doctorId,String username,String password) {
        int status=0;
        DoctorTb d=em.find(DoctorTb.class,doctorId);
        String pass=pb.generate(password.toCharArray());
        
        UserTb u=d.getUserId();
        int userid=u.getUserId();
        UserTb user=em.find(UserTb.class,userid);
        user.setUserName(username);
        user.setPassword(pass);
        em.merge(user);
        d.setIsActive(1);
        em.merge(d);
        status=1;
        
        
        return status;
    }

    @Override
    public int addFees(int hospitalId, int specializaionId, int fees) {
        int status=0;
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        Collection<FeesTb> f1=h.getFeesTbCollection();
        
       SpecializationTb s=em.find(SpecializationTb.class,specializaionId);        
        Collection<FeesTb> f2=s.getFeesTbCollection();
        
        FeesTb f=new FeesTb();
       
        f.setFees(fees);
        f.setHospitalId(h);
        f.setSpecializationId(s);
        int flag=0;
        Collection<FeesTb> f3=em.createNamedQuery("FeesTb.findAll").getResultList();
        
        for(FeesTb finalfees:f3)
        {
           if(finalfees.getHospitalId()==f.getHospitalId() && finalfees.getSpecializationId()==f.getSpecializationId())
           {
                flag=1;
           }
        }
        if(flag==0)
        {
            em.persist(f);
            f1.add(f);
            f2.add(f);
            h.setFeesTbCollection(f2);
            s.setFeesTbCollection(f1);
            em.merge(h);   
            status=1;
        }
        return status;
    }

    @Override
    public int updateFees(int feesId, int hospitalId, int specializaionId, int fees) {
        int status=0;
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        Collection<FeesTb> f1=h.getFeesTbCollection();
        
        SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
        Collection<FeesTb> f2=s.getFeesTbCollection();
        
        FeesTb f=em.find(FeesTb.class,feesId);
       
        if(f1.contains(f) || f2.contains(f))
        {
            f1.remove(f);
            f2.remove(f);
            f.setSpecializationId(s);
            f.setFees(fees);
            f1.add(f);
            f2.add(f);
            
            em.merge(f);
            h.setFeesTbCollection(f2);
            s.setFeesTbCollection(f1);
            em.merge(h);
            em.merge(s);
            status=1;
        }
        return status;
    }

    @Override
    public FeesTb getFeesByFeesId(int fid) {
        FeesTb f=em.find(FeesTb.class, fid);
        return f;
    }

    
    
    @Override
    public Collection<FeesTb> getFeesByHospital(int hospitalId) {
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        return em.createNamedQuery("FeesTb.findFeesByHospital").setParameter("hospitalId",h).getResultList();
        
    }

    @Override
    public Collection<FeesTb> getFeesBySpeciality(int specializaionId) {
        SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
        return em.createNamedQuery("FeesTb.findFeesBySpeciality").setParameter("specializationId",s).getResultList();
    }

    @Override
    public Collection<FeesTb> getFeesBySpecialityandHospital(int specializaionId, int hospitalId) {
       HospitalTb h=em.find(HospitalTb.class,hospitalId);
       SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
       return em.createNamedQuery("FeesTb.findFeesBySpecialityAndHospital").setParameter("specializationId",s).setParameter("hospitalId", h).getResultList();
    }
    
    

    @Override
    public int deleteFees(int feesId) {
        int status=0;
        FeesTb f=em.find(FeesTb.class,feesId);
        em.remove(f);
        return 1;
                
    }

    @Override
    public int addReview(int patientId, int doctorId, int hospitalId, String review) {
        int status=0;
        System.err.println(patientId+" "+doctorId+" "+hospitalId+" "+review);
        
        PatientTb p=em.find(PatientTb.class,patientId);
        Collection<ReviewTb> r2=p.getReviewTbCollection();
        
        ReviewTb r=new ReviewTb();
       
        r.setPatientId(p);
        r.setReview(review);
        
        if(doctorId==0)
        {
            HospitalTb h=em.find(HospitalTb.class,hospitalId);
            Collection<ReviewTb> r1=h.getReviewTbCollection();
             r.setHospitalId(h);
             r.setDoctorId(null);
             r1.add(r);
             h.setReviewTbCollection(r2);
             p.setReviewTbCollection(r1);
             em.merge(h); 
        }
    
        else
        {
            DoctorTb d=em.find(DoctorTb.class,doctorId);
            Collection<ReviewTb> r3=d.getReviewTbCollection();
             r.setDoctorId(d);
             r.setHospitalId(null);
             r3.add(r);
             
             d.setReviewTbCollection(r2);
             p.setReviewTbCollection(r3);
             em.merge(d);
        }
        em.persist(r);
        r2.add(r);
        status=1;
        return status;
   
    }

    @Override
    public Collection<ReviewTb> getAllReview() {
          return em.createNamedQuery("ReviewTb.findAll").getResultList();
  
    }

    @Override
    public int bookAppointment(int doctorId, int patientId, int hospitalId, Date date, Time time) 
    {
        int status=0;
        PatientTb p=em.find(PatientTb.class,patientId);
        Collection<AppointmentTb> pa=p.getAppointmentTbCollection();
        
        DoctorTb d=em.find(DoctorTb.class,doctorId);
        Collection<AppointmentTb> da=d.getAppointmentTbCollection();
        
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        Collection<AppointmentTb> ha=p.getAppointmentTbCollection();
        
        AppointmentTb a=new AppointmentTb();
        a.setDoctorId(d);
        a.setPatientId(p);
        a.setHospitalId(h);
        a.setDate(date);
        a.setTime(time);
       
        a.setInvoice(null);
        a.setStatus("pending");
        a.setIsActive(1);
        
        em.persist(a);
        pa.add(a);
        da.add(a);
        ha.add(a);
        
        p.setAppointmentTbCollection(pa);
        d.setAppointmentTbCollection(da);
        h.setAppointmentTbCollection(ha);
//        em.merge(pa);
//       em.merge(da);
//        em.merge(ha);
       
        status=1;
    return status;
    
    }

    @Override
    public int cancelAppointment(int appointmentId) {
        int status=0;
        AppointmentTb a =em.find(AppointmentTb.class,appointmentId);
        //a.setIsActive(0);
        a.setStatus("cancel");
        em.merge(a);
        status=1;
        return status;
    }

    @Override
    public Collection<AppointmentTb> getAllDoctorAppointment(int hospitalId, int doctorId) {
        DoctorTb d=em.find(DoctorTb.class,doctorId);
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        return em.createNamedQuery("AppointmentTb.findByDoctorId").setParameter("doctorId",d).
                setParameter("hospitalId",h).getResultList();
    }

    @Override
    public Collection<AppointmentTb> getAllAppointment() {
        return em.createNamedQuery("AppointmentTb.findAll").getResultList();
    }

    @Override
    public Collection<AppointmentTb> getAllPatientAppointment(int patientId) {
        PatientTb p=em.find(PatientTb.class,patientId);
        return em.createNamedQuery("AppointmentTb.findByPatientId").setParameter("patientId",p).getResultList();
        
        
    }

    @Override
    public Collection<PatientTb> getPatientOfDoctor(int doctorId) {
        DoctorTb d=em.find(DoctorTb.class,doctorId);
        
        return em.createNamedQuery("AppointmentTb.findPatientOfDoctor").setParameter("doctorId",d).getResultList();
    }

    @Override
    public Collection<PatientTb> getPatientOfHospital(int hospitalId) {
       
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        return em.createNamedQuery("AppointmentTb.findPatientOfHospital").setParameter("hospitalId",h).getResultList();
    }

    @Override
    public Collection<HospitalTb> getHospitalByArea(String areaName) {
       
        return em.createNamedQuery("HospitalTb.getHospitalByArea").setParameter("areaName",areaName).getResultList();
   
        
    }

    @Override
    public Collection<HospitalTb> getHospitalByAreaAndSpecializationName(String areaName, String specializationName) {
      
        Collection<HospitalTb> hosByArea=em.createNamedQuery("HospitalTb.getHospitalByArea").setParameter("areaName",areaName).getResultList(); 
        Collection<HospitalTb> hospitals=new ArrayList<HospitalTb>();
        Collection<FeesTb> fees=em.createNamedQuery("FeesTb.findFeesBySpecializationName").setParameter("specializationName",specializationName).getResultList(); 
        
        for(FeesTb f:fees)
        {
            for(HospitalTb h:hosByArea)
            {
                if(f.getHospitalId().getHospitalId()==h.getHospitalId())
                {
                    hospitals.add(h);
                }
               
            }
           
        }
        return hospitals;  
    }
    
    

    @Override
    public Collection<DoctorTb> getDoctorByArea(String areaName) {
        Collection<HospitalTb> hos=this.getHospitalByArea(areaName);
        
        Collection<DoctorTb> docs=new ArrayList<>();
        
        Collection<DoctorTb> d=new ArrayList<>() ;
        for(HospitalTb h:hos)
        {
            d=em.createNamedQuery("DoctorScheduleTb.findDoctorByHospital").setParameter("hospitalId",h).getResultList();
            for(DoctorTb d1:d)
            {
                docs.add(d1);
            }
        }
        
        return docs;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<HospitalTb> getHospitalBySpecialization(int specializaionId) {
        SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
        return em.createNamedQuery("FeesTb.findHospitalBySpecialization").setParameter("specializationId",s).getResultList();
    }

    @Override
    public Collection<DoctorTb> getDoctorOfHospital(int hospitalId) {
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        
        Collection<DoctorTb> docs=em.createNamedQuery("DoctorScheduleTb.findDoctorByHospital").setParameter("hospitalId",h).getResultList();
         
        return docs;
    }

    @Override
    public Collection<DoctorTb> getDoctorBySpecialization(int specializaionId) {
        SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
        return em.createNamedQuery("DoctorTb.findBySpecialization").setParameter("specializationId",s).getResultList();
    }

    @Override
    public Collection<DoctorTb> getDoctorBySpecializationName(String specializationName) {
         return em.createNamedQuery("DoctorTb.findBySpecializationName").setParameter("specializationName",specializationName).getResultList();
      
    }

    @Override
    public Collection<DoctorTb> getDoctorByGender(String gender) {
        return em.createNamedQuery("DoctorTb.findByGender").setParameter("gender",gender).getResultList();
    }
    
    @Override
    public Collection<HospitalTb> getHospitalByName(String hospitalName) {
        return em.createNamedQuery("HospitalTb.findByHospitalName").setParameter("hospitalName",hospitalName).getResultList();
    }

    @Override
    public Collection<DoctorTb> getDoctorByName(String doctorName) {
        return em.createNamedQuery("DoctorTb.findByDoctorName").setParameter("doctorName",doctorName).getResultList();
    }

    @Override
    public Collection<HospitalTb> getHospitalByFeesAndSpecialization(int specializaionId, int fromFee, int toFee) {
        SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
        return em.createNamedQuery("FeesTb.findHospitalByFeesAndSpecialization").
                setParameter("specializationId",s)
                .setParameter("fromFee",fromFee).setParameter("toFee",toFee).getResultList();
        
    }
    
     @Override
    public Collection<HospitalTb> emergency(int specializaionId) {
        Collection<FeesTb> fees=em.createNamedQuery("FeesTb.findFeesBySpeciality").setParameter("specializationId", specializaionId).getResultList();
        Collection<HospitalTb> hospitals=new ArrayList<HospitalTb>();
        
        for(FeesTb f:fees)
        {
            hospitals.add(f.getHospitalId());
        }
        return hospitals;
    }

    @Override
    public Collection<HospitalTb> getHospitalByCity(String cityName) {
       return em.createNamedQuery("HospitalTb.getHospitalByCity").setParameter("cityName",cityName).getResultList();   
    }

    @Override
    public Collection<HospitalTb> getHospitalByState(String stateName) {
        return em.createNamedQuery("HospitalTb.getHospitalBystate").setParameter("stateName", stateName).getResultList();
      }

    @Override
    public Collection<DoctorTb> getDoctorByExperience() {
        return em.createNamedQuery("DoctorTb.getDoctorByExperience").getResultList();
    }

    @Override
    public Collection<DoctorTb> getDoctorByExperienceAndSpec(String spec) {
        return em.createNamedQuery("DoctorTb.getDoctorByExperienceAndSpec").setParameter("spec",spec).getResultList();
    }
    
    

    @Override
    public Collection<HospitalTb> getHospitalByLowToHighFees(String spcializationName) {
        Collection<HospitalTb> hospitals=new ArrayList<HospitalTb>();
        Collection<FeesTb> fees=em.createNamedQuery("FeesTb.getHospitalByLowToHighFees").setParameter("spcializationName",spcializationName).getResultList();
        for(FeesTb f:fees)
        {
            hospitals.add(f.getHospitalId());
        }
        return hospitals;
    }

    @Override
    public Collection<HospitalTb> getHospitalByHighToLowFees(String spcializationName) {
        Collection<HospitalTb> hospitals=new ArrayList<HospitalTb>();
        Collection<FeesTb> fees=em.createNamedQuery("FeesTb.getHospitalByHighToLowFees").setParameter("spcializationName",spcializationName).getResultList();
        for(FeesTb f:fees)
        {
            hospitals.add(f.getHospitalId());
        }
        return hospitals;
    }

    @Override
    public Collection<HospitalTb> getHospitalByFees(String name, int fromFees, int toFees) {
        Collection<HospitalTb> hospitals=new ArrayList<HospitalTb>();
        Collection<FeesTb> fees=em.createNamedQuery("FeesTb.getHospitalByFees").setParameter("spcializationName",name).setParameter("from", fromFees).setParameter("to", toFees).getResultList();
        for(FeesTb f:fees)
        {
            hospitals.add(f.getHospitalId());
        }
        return hospitals;
    }

    @Override
    public Collection<DoctorTb> getDoctorByAvailability(Date date) {
       // Collection<DoctorScheduleTb> ds=em.createNamedQuery("DoctorScheduleTb.findByDate").setParameter("date",date).getResultList();
        Collection<DoctorTb> d=em.createNamedQuery("DoctorScheduleTb.findByDate").setParameter("date",date).getResultList();
//        for(DoctorScheduleTb d1:ds)
//        {
//            d.add(d1.getDoctorId());
//        }
        
        return d;


    }    

    @Override
    public Collection<DoctorTb> getDoctorByAvailabilityOfBooking() {
        return em.createNamedQuery("DoctorScheduleTb.findByAvailabilityOfBooking").getResultList();
    }

    @Override
    public Collection<DoctorTb> getDoctorByAvailabilityOfBookingAndSpec(String spec) {
       return em.createNamedQuery("DoctorScheduleTb.findByAvailabilityOfBookingAndSpec").setParameter("spec",spec).getResultList();
    }
    
    

    @Override
    public int editHospitalProfile(int hospitalId,String hospitalName, String address, int areaId, int cityId, int pincode,double latitude,double longitude,Time openingTime, Time closingTime, String logo, String email, long contact, String username,int userId) {
        int status=0;
        HospitalTb h=em.find(HospitalTb.class,hospitalId);
        CityTb c=em.find(CityTb.class,cityId);
        Collection<HospitalTb> cityHospitals=c.getHospitalTbCollection();
        
        AreaTb a=em.find(AreaTb.class,areaId);
        Collection<HospitalTb> areaHospitals=a.getHospitalTbCollection();
        
        
        UserTb u=em.find(UserTb.class,userId);
        Collection<HospitalTb> userHospitals=u.getHospitalTbCollection();
        u.setContact(contact);
        u.setEmail(email);
        u.setUserName(username);
        
        if(cityHospitals.contains(h) && areaHospitals.contains(h) && userHospitals.contains(h))
        {
            cityHospitals.remove(h);
            areaHospitals.remove(h);
            userHospitals.remove(h);
        }
            
            h.setAddress(address);
            h.setAreaId(a);
            h.setCityId(c);
            h.setClosingTime(closingTime);
            h.setHospitalName(hospitalName);
            h.setLogo("resources/img/hospital/"+logo);
            h.setOpeningTime(openingTime);
            h.setPincode(pincode);
            h.setLatitude(latitude);
            h.setLongitude(longitude);
            h.setUserId(u);
            
            
            em.merge(h);
            cityHospitals.add(h);
            areaHospitals.add(h);
            userHospitals.add(h);
            
            c.setHospitalTbCollection(cityHospitals);
            a.setHospitalTbCollection(areaHospitals);
            u.setHospitalTbCollection(userHospitals);
            
            em.merge(c);
            em.merge(a);
            em.merge(u);
            status=1;
      
        return status;
    }

    @Override
    public int editDoctorProfile(int doctorId, String doctorName, int specializaionId, String experience, String gender, String certificates, String education, String email, long contact, String username, int userId,String profile) {
        int status=0;
        DoctorTb d=em.find(DoctorTb.class,doctorId);
        
        SpecializationTb s=em.find(SpecializationTb.class,specializaionId);
        Collection<DoctorTb> doctors=s.getDoctorTbCollection();
        
       
        UserTb u=em.find(UserTb.class,userId);
        Collection<DoctorTb> userDoctor=u.getDoctorTbCollection();
        u.setContact(contact);
        u.setEmail(email);
        u.setUserName(username);
        
        if(doctors.contains(d) && userDoctor.contains(d))
        {
           doctors.remove(d);
           userDoctor.remove(d);
           
        }
            
            d.setCertificates(certificates);
            d.setDoctorName(doctorName);
            d.setEducation(education);
            d.setExperience(experience);
            d.setGender(gender);
            d.setProfile("resources/img/doctors/"+profile);
            d.setSpecializationId(s);
            d.setUserId(u);
            
            em.merge(d);
            userDoctor.add(d);
            doctors.add(d);
            
            u.setDoctorTbCollection(userDoctor);
            s.setDoctorTbCollection(doctors);
            
            em.merge(u);
            em.merge(s);
            
            status=1;
      
        return status;
    }

    @Override
    public int editPatientProfile(int patientId, String patientName, String gender, String address, int age, String username, String email, long contact, int userId) {
        int status=0;
        PatientTb p=em.find(PatientTb.class,patientId);
      
        UserTb u=em.find(UserTb.class,userId);
        Collection<PatientTb> userPatients=u.getPatientTbCollection();
        u.setContact(contact);
        u.setEmail(email);
        u.setUserName(username);
        
        if(userPatients.contains(p))
        {
           userPatients.remove(p);
           
        }
           p.setAddress(address);
           p.setAge(age);
           p.setGender(gender);
           p.setPatientName(patientName);
            
            em.merge(p);
            userPatients.add(p);
            
            
            u.setPatientTbCollection(userPatients);
            em.merge(u);
            
            status=1;
      
        return status;
    }
    
  // ------------------------------------add schedule-----------------------------------------------  

    @Override
    public long addSchedule(int hospitalId, int doctorId, Date date, Time fromTime, Time toTime, int numberOfPatients) {
        int status=0;
        List<Long> temp=em.createNamedQuery("DoctorScheduleTb.findCount").getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        
        List<Long> temp1=em.createNamedQuery("DoctorScheduleTb.verifySchedule").setParameter("did",doctorId).
                setParameter("d",date).setParameter("f1",fromTime).setParameter("t1",toTime).getResultList();
        long result=0;
        
        for(long j:temp1)
        {
            result=j;
        }
        
        return count;
        //return status;
        //return result;
    }
    
    
    //--------------------------Doctor Attachment------------------------------------------------------------------------------------------

    @Override
    public int addAttachment(int doctorId, int patientId, String attachment) {
    
        int status=0;
        DoctorTb d=em.find(DoctorTb.class,doctorId);
        Collection<DoctorAttachmentTb> a1=d.getDoctorAttachmentTbCollection();
        
        PatientTb p=em.find(PatientTb.class,patientId);        
        Collection<DoctorAttachmentTb> a2=p.getDoctorAttachmentTbCollection();
        
        DoctorAttachmentTb a=new DoctorAttachmentTb();
       
        a.setAttachment("resources/img/docAttachment/"+attachment);
        a.setDoctorId(d);
        a.setPatientId(p);
        
       
            em.persist(a);
            a1.add(a);
            a2.add(a);
            d.setDoctorAttachmentTbCollection(a2);
            p.setDoctorAttachmentTbCollection(a1);
            em.merge(d);   
            status=1;
        
        return status;
    
    }

    @Override
    public Collection<DoctorAttachmentTb> viewAttachmentOfDoctor(int doctorId) {
       return em.createNamedQuery("DoctorAttachmentTb.findByDoctorId").setParameter("doctorId", doctorId).getResultList();
    }

    @Override
    public Collection<DoctorAttachmentTb> viewAttachmentOfPatient(int patientId) {
       return em.createNamedQuery("DoctorAttachmentTb.findByPatientId").setParameter("patientId", patientId).getResultList();
    }

    
//----------------------Get Total Number of entity----------------------------------------------------------------------

    @Override
    public long getTotalDoctors() {
        List<Long> temp=em.createNamedQuery("DoctorTb.getTotalDoctors").getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalHospitals() {
        List<Long> temp=em.createNamedQuery("HospitalTb.getTotalHospitals").getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalAppointments() {
         List<Long> temp=em.createNamedQuery("AppointmentTb.getTotalAppointments").getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalPatients() {
         List<Long> temp=em.createNamedQuery("PatientTb.getTotalPatients").getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalHosDoctors(int hid) {
        List<Long> temp=em.createNamedQuery("DoctorScheduleTb.getTotalDoctorsByHospitalId").setParameter("hid", hid).getResultList();
        long count=0;
        int row=0;
        for(long i:temp)
        {
            count=i;
            row++;
        }
        return row;
    }

    @Override
    public long getTotalHosSpecializations(int hid) {
         List<Long> temp=em.createNamedQuery("FeesTb.getTotalSpecializationByHospitalId").setParameter("hid", hid).getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalHosAppointments(int hid) {
      List<Long> temp=em.createNamedQuery("AppointmentTb.getTotalAppointmentByHospitalId").setParameter("hid", hid).getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalHosPatients(int hid)
    {
        List<Long> temp=em.createNamedQuery("AppointmentTb.getTotalPatientByHospitalId").setParameter("hid", hid).getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalDocHospitals(int did) {
         List<Long> temp=em.createNamedQuery("DoctorScheduleTb.getTotalHospitalByDoctorId").setParameter("did", did).getResultList();
        long count=0;
        int row=0;
        for(long i:temp)
        {
            count=i;
            row++;
        }
        return row;
    }

    @Override
    public long getTotaldocAppointments(int did) {
        List<Long> temp=em.createNamedQuery("AppointmentTb.getTotalAppointmentByDoctorId").setParameter("did", did).getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public long getTotalDocPatients(int did) {
        List<Long> temp=em.createNamedQuery("AppointmentTb.getTotalPatientByDoctorId").setParameter("did", did).getResultList();
        long count=0;
        for(long i:temp)
        {
            count=i;
        }
        return count;   
    }
    
    
    
    
        @Override
        public PatientTb getPatientByEmail(String email) {
        
        Collection<PatientTb> ps=em.createNamedQuery("PatientTb.findByEmail").setParameter("email",email).getResultList();
        PatientTb p=new PatientTb();
        for(PatientTb x:ps)
        {
            p=x;
        }
     
        return p;
    }

    @Override
    public HospitalTb getHospitalByEmail(String email) {
       Collection<HospitalTb> ps=em.createNamedQuery("HospitalTb.findByEmail").setParameter("email",email).getResultList();
        HospitalTb h=new HospitalTb();
        for(HospitalTb x:ps)
        {
            h=x;
        }
     
        return h;
    }

    @Override
    public DoctorTb getDoctorByEmail(String email) {
       Collection<DoctorTb> ps=em.createNamedQuery("DoctorTb.findByEmail").setParameter("email",email).getResultList();
        DoctorTb d=new DoctorTb();
        for(DoctorTb x:ps)
        {
            d=x;
        }
     
        return d;
    }
        
        

    @Override
    public void changePassword(String email,String password) {
       UserTb finalUser=new UserTb();
       String pass=pb.generate(password.toCharArray());
       //String pass=password;
        Collection<UserTb> u =em.createNamedQuery("UserTb.findByEmail").setParameter("email", email).getResultList();
        for(UserTb user:u)
        {
            finalUser=user;
        }
        finalUser.setPassword(pass);
        em.merge(finalUser);
        
    }

    @Override
    public DoctorTb getDoctorById(int doctorId) {
        return em.find(DoctorTb.class,doctorId);
    }

    @Override
    public HospitalTb getHospitalById(int hospitalId) {
        return em.find(HospitalTb.class, hospitalId);
    }
    
    

    @Override
    public Collection<DoctorScheduleTb> getDoctorSchedule(int doctorId) {
        return em.createNamedQuery("DoctorScheduleTb.findByDoctorId").setParameter("doctorId",doctorId).getResultList();
    }

    @Override
    public Collection<ReviewTb> getReviewByDoctorId(int id) {
        return em.createNamedQuery("ReviewTb.findByDoctorId").setParameter("doctorId",id).getResultList();
    }

    @Override
    public Collection<ReviewTb> getReviewByHospitalId(int id) {
       return em.createNamedQuery("ReviewTb.findByHospitalId").setParameter("hospitalId",id).getResultList();
    }
    
    @Override
    public Collection<HospitalTb> getHospitalByDoctorId(int id) {
        return em.createNamedQuery("DoctorScheduleTb.findHospitalByDoctor").setParameter("doctorId",id).getResultList();
    }

    @Override
    public Collection<DoctorScheduleTb> getScheduleByHospitalAndDoctorId(int hid, int did) {
        return em.createNamedQuery("DoctorScheduleTb.findScheduleByHospitalAndDoctor").setParameter("hospitalId",hid).setParameter("doctorId",did).getResultList();
    }

    @Override
    public Collection<DoctorScheduleTb> getScheduleByDoctorAndDate(int did, Date date) {
        return em.createNamedQuery("DoctorScheduleTb.findScheduleByDoctorAndDate").setParameter("doctorId", did).setParameter("date", date).getResultList();
    }

    @Override
    public Collection<DoctorScheduleTb> getScheduleByDoctorAndHospitalAndDate(int did, int hid, Date date) {
       return em.createNamedQuery("DoctorScheduleTb.findScheduleByDoctorAndHospitalAndDate").setParameter("doctorId", did).setParameter("hospitalId", hid).setParameter("date", date).getResultList();
   
    }
    
    

    @Override
    public Collection<DoctorTb> getScheduleByHospital(int hid) {
        return em.createNamedQuery("DoctorScheduleTb.findScheduleByHospital").setParameter("hospitalId",hid).getResultList();
    }

    @Override
    public Integer getTotalPatientByScheduleId(int sid) {
        List<Integer> temp=em.createNamedQuery("DoctorScheduleTb.findTotalPatientByScheduleId").setParameter("scheduleId", sid).getResultList();
        Integer count=0;
        for(Integer i:temp)
        {
            count=i;
        }
        return count;
    }

    @Override
    public void decreaseTotalPatient(int did,int hid,Date date,Time time) {
      Collection<DoctorScheduleTb> ds=em.createNamedQuery("DoctorScheduleTb.findScheduleByDoctorAndHospitalAndDateAndTime").setParameter("doctorId", did).setParameter("hospitalId",hid).setParameter("date", date).setParameter("time", time).getResultList();
       DoctorScheduleTb d=new DoctorScheduleTb();
       for(DoctorScheduleTb d1:ds)
       {
           d=em.find(DoctorScheduleTb.class, d1.getScheduleId());
           d.setNumberOfPatient(d.getNumberOfPatient()-1);
       }

    }

    @Override
    public Collection<HospitalTb> nearMeHospital(double currentlati, double currentlongi, double newlati, double newlongi) {
        return em.createNamedQuery("HospitalTb.NearMeHospital").setParameter("currentlati",currentlati).setParameter("newlati",newlati).setParameter("currentlongi",currentlongi).setParameter("newlongi",newlongi).getResultList();
                
    }

    @Override
    public AppointmentTb getAppointmentById(int id) {
        AppointmentTb app=new AppointmentTb();
        Collection<AppointmentTb> all=em.createNamedQuery("AppointmentTb.findByAppointmentId").setParameter("appointmentId",id).getResultList();
        for(AppointmentTb a:all)
        {
            app=a;
        }
        return app;
    }

    @Override
    public Collection<HospitalTb> getInactiveHospital() {
        return em.createNamedQuery("HospitalTb.findByIsActive").setParameter("isActive",0).getResultList();
          }

    @Override
    public Collection<DoctorTb> getInactiveDoctor() {
        return em.createNamedQuery("DoctorTb.findByIsActive").setParameter("isActive",0).getResultList();
         }

    @Override
    public Collection<AppointmentTb> getAppointmentByDoctor(int did) {
        return em.createNamedQuery("AppointmentTb.findByDoctor").setParameter("doctorId",did).getResultList();
    }

    @Override
    public Collection<AppointmentTb> getAppointmentByHospital(int hid) {
        return em.createNamedQuery("AppointmentTb.findByHospitalId").setParameter("hospitalId",hid).getResultList();
    }

    @Override
    public Collection<AppointmentTb> getDoctorPendingAppoitment(int did) {
        return em.createNamedQuery("AppointmentTb.findPendingByDoctor").setParameter("doctorId",did).setParameter("status","pending").getResultList();
    }

    @Override
    public Collection<AppointmentTb> getHospitalPendingAppoitment(int hid) {
        return em.createNamedQuery("AppointmentTb.findPendingByHospital").setParameter("hospitalId",hid).setParameter("status","pending").getResultList();
    }

    
    @Override
    public void increaseTotalPatient(int did, int hid, Date date, Time time) {
    
       Collection<DoctorScheduleTb> ds=em.createNamedQuery("DoctorScheduleTb.findScheduleByDoctorAndHospitalAndDateAndTime").setParameter("doctorId", did).setParameter("hospitalId",hid).setParameter("date", date).setParameter("time", time).getResultList();
       DoctorScheduleTb d=new DoctorScheduleTb();
       for(DoctorScheduleTb d1:ds)
       {
           d=em.find(DoctorScheduleTb.class, d1.getScheduleId());
           d.setNumberOfPatient(d.getNumberOfPatient()+1);
       }
    }
    
    
    
    
}
