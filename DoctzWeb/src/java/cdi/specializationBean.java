/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import client.myadmin;
import client.myclient;
import entity.SpecializationTb;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Admin
 */
@Named(value = "specializationBean")
@RequestScoped
@DeclareRoles({"admin","patient","hospital","doctor"})
public class specializationBean {

    Response res;
    myclient c;
    myadmin a;
    Collection<SpecializationTb> allspec;
    GenericType<Collection<SpecializationTb>> gspec;
    GenericType<SpecializationTb> gs;
    
    private String errorMsg="";
    
    private int id,selectedId;
    private String name;
    private String des;
    private String image;
    private int isActive;
    private Part uploadedFile;
    private SpecializationTb spec;
    
  //  private String folder = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\web\\resources\\img\\specialities\\";
    
  private String folder = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\DoctzWeb\\web\\resources\\img\\specialities\\";
	
     public specializationBean() {
         
         
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
            a = new myadmin(token);
            c = new myclient(token);
          
        }
        else
        {
          c=new myclient();
          a=new myadmin();
        }
         
        // a=new myadmin();
         allspec=new ArrayList<SpecializationTb>();
         gspec=new GenericType<Collection<SpecializationTb>>(){};
         gs=new GenericType<SpecializationTb>(){};
    }
    

    public Collection<SpecializationTb> getAllspec() {
        res=c.getAllSpecialization(Response.class);
        allspec=res.readEntity(gspec);
        return allspec;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    
    
    
    public void setAllspec(Collection<SpecializationTb> allspec) {
        this.allspec = allspec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
   
    
    
    public void uploadImage()
    {
        try (InputStream input = this.uploadedFile.getInputStream())
        {
            String fileName = this.uploadedFile.getSubmittedFileName();
            Files.copy(input, new File(folder, fileName).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RolesAllowed("admin")
    public String addSpecilaization()
    {
        this.uploadImage();
       // System.out.println(this.name+" "+this.uploadedFile.getSubmittedFileName());
        res=a.addSpecialization(Response.class, this.name, this.des,this.uploadedFile.getSubmittedFileName());
        if(res.getStatus() > 0)
        {
            errorMsg="";
            return "dashboard.xhtml?faces-redirect=true";
        }
        else
        {
            errorMsg="Something went wrong";
            return "addSpecialities.xhtml";
        }
        
    }
            
    public String delete(int id)
    {
        res=a.deleteSpecialization(Response.class, String.valueOf(id));
        return "specialities.xhtml?faces-redirect=true";
    }
   
    public String edit(int id)
    {
           res=a.getSpecializationById(Response.class, String.valueOf(id));
           spec=(SpecializationTb)res.readEntity(gs);
           this.id=spec.getSpecializationId();
           this.name=spec.getName();
           this.des=spec.getDescription();
           this.image=spec.getImage();
           return "editSpecialities.xhtml";
    }
    
    public String update()
    {
        String path;
        if(uploadedFile == null)
        {
            String str=this.getImage();
            path=str.replace("resources/img/specialities/", "");
        }
        else
        {
            this.uploadImage();
            path=this.uploadedFile.getSubmittedFileName();
        }
        res=a.updateSpecialization(Response.class, String.valueOf(this.id), this.name, this.des, path);
        return "dashboard.xhtml?faces-redirect=true";
    }
}
