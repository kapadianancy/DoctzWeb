/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "usergroup_tb")
////@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsergroupTb.findAll", query = "SELECT u FROM UsergroupTb u"),
    @NamedQuery(name = "UsergroupTb.findByUsergroupId", query = "SELECT u FROM UsergroupTb u WHERE u.usergroupId = :usergroupId")})
public class UsergroupTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usergroupId")
    private Integer usergroupId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private UserTb userId;
    @JoinColumn(name = "groupId", referencedColumnName = "groupId")
    @ManyToOne(optional = false)
    private GroupTb groupId;

    public UsergroupTb() {
    }

    public UsergroupTb(Integer usergroupId) {
        this.usergroupId = usergroupId;
    }

    public Integer getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Integer usergroupId) {
        this.usergroupId = usergroupId;
    }

    public UserTb getUserId() {
        return userId;
    }

    public void setUserId(UserTb userId) {
        this.userId = userId;
    }

    public GroupTb getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupTb groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usergroupId != null ? usergroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsergroupTb)) {
            return false;
        }
        UsergroupTb other = (UsergroupTb) object;
        if ((this.usergroupId == null && other.usergroupId != null) || (this.usergroupId != null && !this.usergroupId.equals(other.usergroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UsergroupTb[ usergroupId=" + usergroupId + " ]";
    }
    
}
