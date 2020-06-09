/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myclient;
import client.mydoctor;
import entity.DoctorAttachmentTb;
import entity.DoctorTb;
import entity.PatientTb;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
@Named(value = "attachmentBean")
@RequestScoped
public class attachmentBean {

    @EJB doctzBeanLocal ejb;
    Response res;
    mydoctor d;
    myclient c;
    private int aid;
    private int pid;
    private int did;
    private String attachment;
    private Collection<DoctorAttachmentTb> dall;
    private Collection<DoctorAttachmentTb> pall;
    private GenericType<Collection<DoctorAttachmentTb>> g;
    private String email;
    private Part uploadedAttachment;
    
    private String folder = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\web\\resources\\img\\docAttachment\\";
     
 // private String folder = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\DoctzWeb\\web\\resources\\img\\docAttachment\\";

    
    public attachmentBean() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";

        HttpSession session = request.getSession(false);
        email=session.getAttribute("username").toString();
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          System.out.println("Token="+token);
          d=new mydoctor(token);
          c=new myclient(token);
        }
        else
        {
         d=new mydoctor();
         c=new myclient();
        }
        dall=new ArrayList<DoctorAttachmentTb>();
        pall=new ArrayList<DoctorAttachmentTb>();
        g=new GenericType<Collection<DoctorAttachmentTb>>(){};
    }

    public Collection<DoctorAttachmentTb> getDall() {
        DoctorTb doc=ejb.getDoctorByEmail(this.email);
        res=d.viewAttachmentOfDoctor(Response.class, String.valueOf(doc.getDoctorId()));
        dall=res.readEntity(g);
        return dall;
    }

    public void setDall(Collection<DoctorAttachmentTb> dall) {
        this.dall = dall;
    }

    public Collection<DoctorAttachmentTb> getPall() {
        PatientTb p=ejb.getPatientByEmail(this.email);
        pall=ejb.getPatientAttachment(p.getPatientId());
        return pall;
    }

    public void setPall(Collection<DoctorAttachmentTb> pall) {
        this.pall = pall;
    }

    
    
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Part getUploadedAttachment() {
        return uploadedAttachment;
    }

    public void setUploadedAttachment(Part uploadedAttachment) {
        this.uploadedAttachment = uploadedAttachment;
    }
    
     public void uploadDocument()
    {
        try (InputStream input = this.uploadedAttachment.getInputStream())
        {
            String fileName = this.uploadedAttachment.getSubmittedFileName();
            Files.copy(input, new File(folder, fileName).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String addAttachment()
    {
        DoctorTb doc=ejb.getDoctorByEmail(this.email);
        this.uploadDocument();
        
        res=d.addAttachment(Response.class,String.valueOf(doc.getDoctorId()) ,String.valueOf(this.pid), this.uploadedAttachment.getSubmittedFileName());
        return "patientAttachment.xhtml";
    }
    
}
