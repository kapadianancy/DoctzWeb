/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myclient;
import entity.*;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.Time;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@RequestScoped
public class loginBean {

    @EJB doctzBeanLocal ejb;
    
     @Inject
    SecurityContext sc;
     
     myclient c;
     Response res;
    private String username;
    private String password,message,color,npass,cpass;
    private AuthenticationStatus status;
    private Set<String> roles;
    private PatientTb patient;
    
          
   
    public loginBean() {
       
          
       
        patient=new PatientTb();
        
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNpass() {
        return npass;
    }

    public void setNpass(String npass) {
        this.npass = npass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public PatientTb getPatient() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
          
       // HttpSession session = request.getSession(true);
        patient=ejb.getPatientByEmail(request.getSession().getAttribute("username").toString());
        return patient;
    }

    public void setPatient(PatientTb patient) {
        this.patient = patient;
    }

    
    
    

     public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }
  
    public SecurityContext getSc() {
        return sc;
    }

    public void setSc(SecurityContext sc) {
        this.sc = sc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String checklogin(){
        //System.out.println("In Checking");
        try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
          
             Credential credential = new UsernamePasswordCredential(getUsername(), new Password(getPassword()));
                AuthenticationStatus status = sc.authenticate(request,response, withParams().credential(credential));
                   System.err.println(status);
                if(status.equals(SUCCESS)){
                    HttpSession session= request.getSession(true);
                    session.setAttribute("username",getUsername());
                    session.setAttribute("password",getPassword());
                    //System.out.println(sc.isCallerInRole("admin"));
                    if(sc.isCallerInRole("admin")){
                        return "admin/dashboard.xhtml?faces-redirect=true";
                    }
                    else if(sc.isCallerInRole("patient"))
                    {
                        
                        if(null != session.getAttribute("flag"))
                        {
                            if(session.getAttribute("flag").equals("1"))
                            { 
                                res=c.bookAppointment(Response.class, session.getAttribute("doctorId").toString(),this.getPatient().getPatientId().toString(), session.getAttribute("hospitalId").toString() , session.getAttribute("date").toString(),session.getAttribute("time").toString() );
                                return "bookingSuccess.xhtml?faces-redirect=true";
                            }
                        }
                        
                        return "index.xhtml";
                    }
                    else{
                        System.out.println("No Role Found");
                    }
                }
                else if(status.equals(SEND_FAILURE)){
                    message="Invalid User";
                    return "login.xhtml";
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
        
        
    }
    
     public String JWTlogin()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session= request.getSession(true);
        
        request.getSession().setAttribute("logged-group", ""); 
        
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status= sc.authenticate(request, response, withParams().credential(credential));
                                           
     
       if (status.equals(SEND_CONTINUE)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now. The control will now go into HttpAuthenticationMechanism
            context.responseComplete();
       } 
       
 //      else if (status.equals(SEND_FAILURE)) {
//            message = "Login Failed";
//            System.out.println(message);
//            addError(context, "Authentication failed");
//        }
         //  if(securityContext.isCallerInRole("Admin"))
         System.out.println("In bean");
         if(roles.contains("admin"))
           {
               System.out.println("In Admin");
               request.getSession().setAttribute("logged-group", "admin");
               request.getSession().setAttribute("username", this.username);
               request.getSession().setAttribute("password", this.password);
              return "/faces/admin/dashboard.xhtml?faces-redirect=true";
           }
        //   else if(securityContext.isCallerInRole("Supervisor"))
       else if(roles.contains("patient"))
        {
                 String token="";
               System.out.println("In Patient");
               request.getSession().setAttribute("logged-group", "patient");
               request.getSession().setAttribute("username", this.username);
               request.getSession().setAttribute("password", this.password);
               
                    if(null != session.getAttribute("token"))
                    {
                      token = request.getSession().getAttribute("token").toString();
                      c = new myclient(token); 
                    } 
               
                        if(null != request.getSession().getAttribute("flag"))
                        {
                            System.out.println("Flag------------"+session.getAttribute("flag"));
                            if(session.getAttribute("flag").toString().equals("1"))
                            { 
                                
                                res=c.bookAppointment(Response.class,session.getAttribute("doctorId").toString(),this.getPatient().getPatientId().toString(),session.getAttribute("hospitalId").toString(),session.getAttribute("date").toString(),session.getAttribute("time").toString());
                                ejb.decreaseTotalPatient(Integer.parseInt(session.getAttribute("doctorId").toString()),Integer.parseInt(session.getAttribute("hospitalId").toString()) , Date.valueOf(session.getAttribute("date").toString()),Time.valueOf(session.getAttribute("time").toString()));
                                return "/faces/bookingSuccess.xhtml?faces-redirect=true";
                            }
                        }
                        
                        return "/faces/index.xhtml?faces-redirect=true";
                        
   
        }
       else if(roles.contains("hospital"))
       {
            System.out.println("In Hospital");
            request.getSession().setAttribute("logged-group", "hospital");
            request.getSession().setAttribute("username", this.username);
            request.getSession().setAttribute("password", this.password);
           return "/faces/hospital/dashboard.xhtml?faces-redirect=true";
       }
       else if(roles.contains("doctor"))
       {
            System.out.println("In Doctor");
            request.getSession().setAttribute("logged-group", "doctor");
            request.getSession().setAttribute("username", this.username);
            request.getSession().setAttribute("password", this.password);
           return "/faces/doctor/dashboard.xhtml?faces-redirect=true";
       }
       else
       {
           return "/login.xhtml";
       }
        
       //} 
       
       
        }
        catch (Exception e)
        {
             message = "Invalid User !!!";
              e.printStackTrace();
        }
//        
      return "";
    }
    
    
     public String getLogin()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session= request.getSession(true);
        if(session.getAttribute("username")!=null)
        {
           return session.getAttribute("username").toString();
        }
        return "Login/Signup";
        
    }
    public String getLink()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session= request.getSession(true);
        if(session.getAttribute("username")!=null)
        {
//            PatientBean p=new PatientBean();
//            p.display();

           return "dashboard.xhtml";
        }
        return "faces/login.xhtml";
    }
    
    
    public void sendMail()
    {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        ejb.changePassword(this.username, saltStr);
        
        final String SMTP_HOST = "smtp.gmail.com";
        final String SMTP_PORT = "587";
        final String GMAIL_USERNAME = "nidhinancy0921@gmail.com";
        final String GMAIL_PASSWORD = "nidhi0921nancy";

        System.out.println("Process Started");

        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.host", SMTP_HOST);
        prop.setProperty("mail.smtp.user", GMAIL_USERNAME);
        prop.setProperty("mail.smtp.password", GMAIL_PASSWORD);
        prop.setProperty("mail.smtp.port", SMTP_PORT);
        prop.setProperty("mail.smtp.auth", "true");
        System.out.println("Props : " + prop);

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_USERNAME,
                        GMAIL_PASSWORD);
            }
        });

       
        MimeMessage message = new MimeMessage(session);
        try {
            System.out.println("before sending");
            message.setFrom(new InternetAddress(GMAIL_USERNAME));
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.username));
            message.setSubject("New Password");
            message.setContent("<h3>Your New Password For Doctz :"+saltStr+" </h3>"
            + "<br> <a href='http://localhost:8001/doctzApp-war/faces/login.xhtml'>Click link for login</a>", "text/html");
            //message.setText("<h1>Your New Password For Doctz : </h1>"+saltStr,"UTF-8","text/html");
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.username));
            Transport transport = session.getTransport("smtp");
            System.out.println("Got Transport" + transport);
            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
           //System.out.println("message Object : " + message);
            System.out.println("Email Sent Successfully");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
    
    public String forget_password()
    {
        PatientTb p=ejb.getPatientByEmail(this.username);
        //System.out.println(p);
        if(p.getPatientId() != null)
        {           
            sendMail();         
            this.message="Check Your Mail Please";
            this.color="green";
        }
        else
        {
            this.message="Invalid Email";  
            this.color="red";
        }
        return "faces/forget_password.xhtml";
    }
    
    
   public String changePassword()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session= request.getSession(true);
        if(!session.getAttribute("username").equals(this.username))
        {
            this.message="Enter Valid Email";  
            this.color="red";
        }
        else
        {
            PatientTb p=ejb.getPatientByEmail(this.username);
             //System.out.println(p);
             if(p.getPatientId() != null)
             {   
                 if(! this.npass.equals(this.cpass))
                 {
                    this.message="New Password and Confirm password must be same. ";
                    this.color="red";
                 }
                 else
                 {
                     ejb.changePassword(this.username, this.npass);
                     this.message="Successfully Changed";
                     this.color="Green";
                 }
             }
             else
             {
                 this.message="Enter Valid Email";  
                 this.color="red";
             }
        }
        return "faces/changePassword.xhtml";
    }
   
   public String changeDoctorPassword()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session= request.getSession(true);
        if(!session.getAttribute("username").equals(this.username))
        {
            this.message="Enter Valid Email";  
            this.color="red";
        }
        else
        {
            DoctorTb d=ejb.getDoctorByEmail(this.username);
             //System.out.println(p);
             if(d.getDoctorId()!= null)
             {   
                 if(! this.npass.equals(this.cpass))
                 {
                    this.message="New Password and Confirm password must be same. ";
                    this.color="red";
                 }
                 else
                 {
                     ejb.changePassword(this.username, this.npass);
                     this.message="Successfully Changed";
                     this.color="Green";
                 }
             }
             else
             {
                 this.message="Enter Valid Email";  
                 this.color="red";
             }
        }
        return "faces/doctor/changePassword.xhtml";
    }
   
    public String changeHospitalPassword()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session= request.getSession(true);
        if(!session.getAttribute("username").equals(this.username))
        {
            this.message="Enter Valid Email";  
            this.color="red";
        }
        else
        {
            HospitalTb h=ejb.getHospitalByEmail(this.username);
             //System.out.println(p);
             if(h.getHospitalId() != null)
             {   
                 if(! this.npass.equals(this.cpass))
                 {
                    this.message="New Password and Confirm password must be same. ";
                    this.color="red";
                 }
                 else
                 {
                     ejb.changePassword(this.username, this.npass);
                     this.message="Successfully Changed";
                     this.color="Green";
                 }
             }
             else
             {
                 this.message="Enter Valid Email";  
                 this.color="red";
             }
        }
        return "faces/hospital/changePassword.xhtml";
    }
   
   
   
   public String temp()
   {
       return "index.xhtml?faces-redirect=true";
   }

}
