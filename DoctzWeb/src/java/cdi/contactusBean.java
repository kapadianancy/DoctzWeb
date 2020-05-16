/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
@Named(value = "contactusBean")
@RequestScoped
public class contactusBean {

    private String name,email,subject,message;
    
    public contactusBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String sendMessage()
    {
        
        
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

        System.out.println("Got Session : " + session);

        MimeMessage message = new MimeMessage(session);
        try {
            System.out.println("before sending");
            message.setFrom(new InternetAddress(GMAIL_USERNAME));
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(GMAIL_USERNAME));
            message.setSubject(this.subject);
            message.setText(this.email+" : "+this.message);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(GMAIL_USERNAME));
            Transport transport = session.getTransport("smtp");
            System.out.println("Got Transport" + transport);
            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
           //System.out.println("message Object : " + message);
          //  System.out.println("Email Sent Successfully");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return "contact.xhtml";
    }
    
}
