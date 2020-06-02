/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import entity.HospitalTb;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import servlets.test;


/**
 *
 * @author Admin
 */
@Named(value = "hospitalBean")
@RequestScoped
public class hospitalBean  {
    
    @EJB doctzBeanLocal ejb;
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    myclient c;
    Response res;
    
    GenericType<Collection<HospitalTb>> ghos;
    Collection<HospitalTb> allhos;
    
    
        
    private int id;
    private String name;
    private String address;
    private int aid;
    private int cid;
    private int pin;
    private double lati;
    private double longi;
    private String otime;
    private String ctime;
    private String logo;
    private String doc;
    private int uid;
    private int isActive;
    private String maplink;
    private String username;
    private String password;
    private String email;
    private long contact;
    private Part uploadedLogo,uploadedDocument;
    String area="";
    String spec="";
       
     
    // private String folder = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\web\\resources\\img\\hospital\\";
    // private String folderDoc = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\web\\resources\\img\\hospitalDoc\\";
    
    private String folder = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\DoctzWeb\\web\\resources\\img\\hospital\\";
    private String folderDoc = "C:\\Users\\Admin\\Desktop\\doctzWeb-git\\DoctzWeb\\DoctzWeb\\web\\resources\\img\\hospitalDoc\\";

   
    
    public hospitalBean() {
        
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
         
        
        ghos=new GenericType<Collection<HospitalTb>>(){};
        allhos=new ArrayList<HospitalTb>(); 
        
    }


    public Collection<HospitalTb> getAllhos() {
        area=params.get("area");
        spec=params.get("spec");
//        System.out.println("area-----------"+area);
//        System.out.println("spec-----------"+spec);
        if(area != null && spec.equals("null"))
        {  
            res=c.getHospitalByArea(Response.class, area);
            allhos=res.readEntity(ghos);
          
        }
        else if(area != null && spec != null)
        {
            if(area.equals("all") && spec.equals("all"))
            {
                //System.err.println("allhos--------");
                res=c.getAllHospital(Response.class);
                allhos=res.readEntity(ghos);
            }
            else
            {
            allhos=ejb.getHospitalByAreaAndSpecializationName(area, spec);
            }
        }
       
        return allhos;
    }

    public String getMaplink() {
        return maplink;
    }

    public void setMaplink(String maplink) {
        this.maplink = maplink;
    }
    
    

    public void setAllhos(Collection<HospitalTb> allhos) {
        this.allhos = allhos;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Part getUploadedLogo() {
        return uploadedLogo;
    }

    public void setUploadedLogo(Part uploadedLogo) {
        this.uploadedLogo = uploadedLogo;
    }
    
     

    public Part getUploadedDocument() {
        return uploadedDocument;
    }

    public void setUploadedDocument(Part uploadedDocument) {
        this.uploadedDocument = uploadedDocument;
    }

    
    public void uploadLogo()
    {
        try (InputStream input = this.uploadedLogo.getInputStream())
        {
            String fileName = this.uploadedLogo.getSubmittedFileName();
            Files.copy(input, new File(folder, fileName).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public void uploadDocument()
    {
        try (InputStream input = this.uploadedDocument.getInputStream())
        {
            String fileName = this.uploadedDocument.getSubmittedFileName();
            Files.copy(input, new File(folderDoc, fileName).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    
    public void getHospitalByArea(String area)
    {
        //System.out.println("area------------"+area);
         res=c.getHospitalByArea(Response.class, area);
        // System.out.println("res------------"+res);
         this.setAllhos(res.readEntity(ghos));
         
    }
    public void getHospitalBySpec(int sid)
    {
        //System.out.println("sid------------"+sid);
        res=c.getHospitalBySpecialization(Response.class, String.valueOf(sid));
        this.setAllhos(res.readEntity(ghos));
        
    }
    
    public String hospitalRegistration() throws ParseException
    {
        this.uploadLogo();
        this.uploadDocument();
        
        SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
        java.sql.Time t1=null;
        java.sql.Time t2=null;
          
            try {
                t1=new Time(ft.parse(this.otime).getTime());
                t2=new Time(ft.parse(this.ctime).getTime());
               
            } catch (ParseException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }    
          
     //   System.out.println("cdi.hospitalBean.hospitalRegistration()---------- "+this.otime+" "+this.ctime);
        int i=ejb.hospitalRegistration(this.name, this.address, this.aid, this.cid, this.pin, this.lati, this.longi, this.maplink,t1, t2, this.uploadedLogo.getSubmittedFileName(), this.uploadedDocument.getSubmittedFileName(), this.email, this.contact);
        if(i==1)
        {
            return "login.xhtml";
        }
        else
        {
            return "hospitalSignup.xhtml";
        }

    }
    
}
