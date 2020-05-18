/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import entity.DoctorTb;
import entity.SpecializationTb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "doctorBean")
@RequestScoped
public class doctorBean {

    @EJB doctzBeanLocal ejb;
    myclient c;
    Response res;
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    GenericType<Collection<DoctorTb>> gdoc;
   
    private int id;
    private String name;
    private int sid;
    private String exp;
    private String gender;
    private int uid;
    private String certi;
    private String edu;
    private String profile;
    private int isActive;
    String spec;
   
    private Collection<DoctorTb> alldocs;
    private Collection<DoctorTb> searchDocs,serachGenderDocs;
    private String ajaxvalue="";

    public String getAjaxvalue() {
        return ajaxvalue;
    }

    public void setAjaxvalue(String ajaxvalue) {
        this.ajaxvalue = ajaxvalue;
    }
    
    

    public doctorBean() {
        //c=new myclient();
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
          
        }
        else
        {
          c=new myclient();
          //a=new myadmin();
        }
         
        
        gdoc=new GenericType<Collection<DoctorTb>>(){};
        alldocs=new ArrayList<DoctorTb>();
        searchDocs=new ArrayList<DoctorTb>();
        
       
       
    }
    public void ajax(String str)
    {
       ajaxvalue="hello "+str;
    }
     
    
    public Collection<DoctorTb> getSearchDocs() 
    {
         
       spec=params.get("spec");
       if(spec != null){
           
            if(spec.equals("all"))
            {
                 res=c.getAllDoctor(Response.class);
                searchDocs=res.readEntity(gdoc);

            }
            else
            {
                System.out.println(spec);
                searchDocs=ejb.getDoctorBySpecializationName(spec);
            }
       }
      
        return searchDocs;
    }

    public void setSearchDocs(Collection<DoctorTb> searchDocs) {
        this.searchDocs = searchDocs;
    }

    public Collection<DoctorTb> getSerachGenderDocs() {
        
        
        return serachGenderDocs;
    }

    public void setSerachGenderDocs(Collection<DoctorTb> serachGenderDocs) {
        this.serachGenderDocs = serachGenderDocs;
    }

    
    
    public void getDoctorByGender(String str)
    {
        
        this.serachGenderDocs=ejb.getDoctorByGender(str);
        this.setSearchDocs(serachGenderDocs);
       
        for(DoctorTb d:this.searchDocs)
        {
            System.out.println(d.getDoctorName()+" "+d.getGender());
        }
       
        

    }
    
    
     public Collection<DoctorTb> getAlldocs() {
    
        res=c.getAllDoctor(Response.class);
        alldocs=res.readEntity(gdoc);
         
        return alldocs;
    
    }

    public void setAlldocs(Collection<DoctorTb> alldocs) {
        this.alldocs = alldocs;
    }
    
 
    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCerti() {
        return certi;
    }

    public void setCerti(String certi) {
        this.certi = certi;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

   
}
