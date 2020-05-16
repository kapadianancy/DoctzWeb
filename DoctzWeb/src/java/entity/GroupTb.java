/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "group_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupTb.findAll", query = "SELECT g FROM GroupTb g"),
    @NamedQuery(name = "GroupTb.findByGroupId", query = "SELECT g FROM GroupTb g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "GroupTb.findByGroupName", query = "SELECT g FROM GroupTb g WHERE g.groupName = :groupName")})
public class GroupTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupId")
    private Integer groupId;
    @Basic(optional = false)
    @Column(name = "groupName")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Collection<UsergroupTb> usergroupTbCollection;

    public GroupTb() {
    }

    public GroupTb(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupTb(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonbTransient
    public Collection<UsergroupTb> getUsergroupTbCollection() {
        return usergroupTbCollection;
    }

    public void setUsergroupTbCollection(Collection<UsergroupTb> usergroupTbCollection) {
        this.usergroupTbCollection = usergroupTbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupTb)) {
            return false;
        }
        GroupTb other = (GroupTb) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupTb[ groupId=" + groupId + " ]";
    }
    
}
