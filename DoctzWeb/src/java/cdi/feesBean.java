/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myclient;
import client.mydoctor;
import client.myhospital;
import entity.*;
import entity.PatientTb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
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
@Named(value = "feesBean")
@RequestScoped
public class feesBean {

    @EJB doctzBeanLocal ejb;
    Response res;
    myclient c;
    myhospital h;
    Collection<FeesTb> allfees,allfeesofHos;
    GenericType<Collection<FeesTb>> gFees;
    
    private int feesId,hospitalId,specializationId,fees;
    String errorMsg="";
    String emailStr="";

        
    public feesBean() {
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";

          HttpSession session = request.getSession(false);
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          System.out.println("Token="+token);
            c = new myclient(token);
            h=new myhospital(token);
          
        }
        else
        {
          c=new myclient();
          h=new myhospital();
        }
         allfeesofHos=new ArrayList<FeesTb>();
         allfees=new ArrayList<FeesTb>();
         gFees=new GenericType<Collection<FeesTb>>(){};
         
           if(null != session.getAttribute("username"))
           {
                emailStr=session.getAttribute("username").toString();
           }
        else
        {
            System.out.println(session);
        }

    }

    public Collection<FeesTb> getAllfeesofHos() {
        HospitalTb hos=new HospitalTb();
        GenericType<Collection<FeesTb>> g=new GenericType<Collection<FeesTb>>(){};
        hos=ejb.getHospitalByEmail(emailStr);
        res=h.getFeesByHospital(Response.class,String.valueOf(hos.getHospitalId()));
        this.setAllfeesofHos(res.readEntity(g));  
        return allfeesofHos;
    }

    public void setAllfeesofHos(Collection<FeesTb> allfeesofHos) {
        this.allfeesofHos = allfeesofHos;
    }
    
    
    
    public Collection<FeesTb> getAllfees() {
        return allfees;
    }

    public void setAllfees(Collection<FeesTb> allfees) {
        this.allfees = allfees;
    }

    public int getFeesId() {
        return feesId;
    }

    public void setFeesId(int feesId) {
        this.feesId = feesId;
    }

    public int getHospitalId() {
        HospitalTb h=ejb.getHospitalByEmail(emailStr);
        hospitalId=h.getHospitalId();
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

     public String addFees()
    { 
       res=h.addFees(Response.class,String.valueOf(this.hospitalId),String.valueOf(this.specializationId),String.valueOf(this.fees));
       if(res.getStatus() > 0)
        {
            errorMsg="";  
        }
        else
        {
            errorMsg="Something went wrong";    
        }
         return "specialization.xhtml?faces-redirect=true";
    }
    
     public String delete(int id)
    {
        res=h.deleteFees(Response.class, String.valueOf(id));
        return "specialization.xhtml?faces-redirect=true";
    }
   
    public String edit(int id)
    {
        FeesTb f=ejb.getFeesByFeesId(id);
        this.feesId=f.getFeesId();
        this.hospitalId=f.getHospitalId().getHospitalId();
        this.specializationId=f.getSpecializationId().getSpecializationId();
        this.fees=f.getFees();
        return "editFees.xhtml";
    }
    
    public String update()
    {
      res=h.updateFees(Response.class,String.valueOf(this.feesId),String.valueOf(this.hospitalId),String.valueOf(this.specializationId),String.valueOf(this.fees));
        System.out.println("Res----->"+res);
      return "specialization.xhtml?faces-redirect=true";
    }
    
}
