/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import entity.ReviewTb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
@Named(value = "reviewBean")
@RequestScoped
public class reviewBean {
    
    @EJB doctzBeanLocal ejb;
 
    private int rid,pid,hid,did;
    private String review;
    
    Collection<ReviewTb> all;
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    public reviewBean() {
        all=new ArrayList<ReviewTb>();
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Collection<ReviewTb> getAll() {
        int did=Integer.parseInt(params.get("did"));
        all=ejb.getReviewByDoctorId(did);
        return all;
    }

    public void setAll(Collection<ReviewTb> all) {
        
        this.all = all;
    }
    
    
    
}
