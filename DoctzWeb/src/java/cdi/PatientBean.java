/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import client.mydoctor;
import client.myhospital;
import entity.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;


/**
 *
 * @author ADMIN
 */
@Named(value = "patientBean")
@RequestScoped
public class PatientBean {

    @EJB doctzBeanLocal ejb;
    Response res;
    myclient c;
    mydoctor d;
    myhospital h;
    Collection<PatientTb> allpatient;
    GenericType<Collection<PatientTb>> gpatient;
    Collection<PatientTb> docPatient,hosPatient;
    
    private int patientid,userid,age;
    private String patientname,gender,address,username,password,email;
    private long contact;
    private int isActive;
    private String errorMsg="";
    
    private PatientTb currentUser=new PatientTb();  
    private PatientTb p=new PatientTb();  
    
    GenericType<PatientTb> gp;
    String emailStr="";
      
    public PatientBean() {
         
        // c=new myclient();
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";

          HttpSession session = request.getSession(false);
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          System.out.println("Token="+token);
        
//            String token1 = request.getHeader("Authorization").substring("Bearer ".length());
//            System.out.println("Token="+token1);
           // a = new myadmin(token);
            c = new myclient(token);
            d=new mydoctor(token);
            h=new myhospital(token);
          
        }
        else
        {
          c=new myclient();
          d=new mydoctor();
          h=new myhospital();
         // a=new myadmin();
        }
         
        docPatient=new ArrayList<PatientTb>();
        hosPatient=new ArrayList<PatientTb>();
         allpatient=new ArrayList<PatientTb>();
         gpatient=new GenericType<Collection<PatientTb>>(){};
         
         gp=new GenericType<PatientTb>(){};
         
           if(null != session.getAttribute("username"))
           {
               System.out.println(session);
                emailStr=session.getAttribute("username").toString();
           }
            else
            {
                System.out.println(session);
            }

           //System.err.println(emailStr);

    }

    public Collection<PatientTb> getDocPatient() {
        DoctorTb doc=new DoctorTb();
        GenericType<Collection<PatientTb>> g=new GenericType<Collection<PatientTb>>(){};
        doc=ejb.getDoctorByEmail(emailStr);
        res=d.getPatientOfDoctor(Response.class,String.valueOf(doc.getDoctorId()));
        this.setDocPatient(res.readEntity(g));
        return docPatient;
    }

    public void setDocPatient(Collection<PatientTb> docPatient) {
        this.docPatient = docPatient;
    }

    public Collection<PatientTb> getHosPatient() {
        HospitalTb hos=new HospitalTb();
        GenericType<Collection<PatientTb>> g=new GenericType<Collection<PatientTb>>(){};
        hos=ejb.getHospitalByEmail(emailStr);
        res=h.getPatientOfHospital(Response.class,String.valueOf(hos.getHospitalId()));
        this.setHosPatient(res.readEntity(g));
        return hosPatient;
    }

    public void setHosPatient(Collection<PatientTb> hosPatient) {
        this.hosPatient = hosPatient;
    }

   
    

    public Collection<PatientTb> getAllpatient() {
          allpatient=ejb.getAllPatient();
          return allpatient;
    }

    public void setAllpatient(Collection<PatientTb> allpatient) {
        this.allpatient = allpatient;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
     public PatientTb getCurrentUser() 
    {
        currentUser=ejb.getPatientByEmail(emailStr);
        return currentUser;
    }

    public void setCurrentUser(PatientTb currentUser) {
        this.currentUser = currentUser;
    }
    
    public String addPatient()
    {
    
        res=c.patientRegistration(Response.class, this.patientname, this.gender, this.address, String.valueOf(this.age), this.username,this.password , this.email, String.valueOf(this.contact));
        if(res.getStatus() > 0)
        {
            return "index.xhtml";
        }
        return "signup.xhtml";
        
    }
    
    
    public String display()
    {
        this.p=this.getCurrentUser();
        this.patientname=this.p.getPatientName();
        this.username=this.p.getUserId().getUserName();
        this.contact=this.p.getUserId().getContact();
        this.email=this.p.getUserId().getEmail();
        this.gender=this.p.getGender();
        this.age=this.p.getAge();
        this.address=this.p.getAddress();
        this.patientid=this.p.getPatientId();
        this.userid=this.p.getUserId().getUserId();
        return "/userProfile.xhtml";
    }
    
        public String editProfile()
    {
        //PatientTb p=this.getCurrentUser();
//        System.err.println("id:"+this.getPatientid());
//        System.err.println("name:"+this.getPatientname());
//        System.err.println("username:"+this.getUsername());
//        System.err.println("email:"+this.getEmail());
//        System.err.println("id:"+this.getUserid());
        
   //     System.out.println(this.currentUser.getPatientName());
        res=c.editPatientProfile(Response.class, String.valueOf(this.getPatientid()), this.getPatientname(), this.getGender(), this.getAddress(), String.valueOf(this.getAge()), this.getUsername(),this.getEmail(),String.valueOf(this.getContact()), String.valueOf(this.getUserid()));
     //   System.out.println("res:"+res);
        if(res.getStatus() > 0)
        {
            this.errorMsg="";
            
            return "userProfile.xhtml";
        }
        else
        {
            this.errorMsg="Could not edit your Profile please try again";
            return "userProfile.xhtml";
        
    
        }
      

//        this.setCurrentUser(ejb.getPatientByEmail(str));
//        this.setPatientname(currentUser.getPatientName());

    }
    
   
}
