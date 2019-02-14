/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safecare.enitity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RE-2084
 */
@Entity
@Table(catalog = "sample", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentInfo.findAll", query = "SELECT s FROM StudentInfo s")
    , @NamedQuery(name = "StudentInfo.findById", query = "SELECT s FROM StudentInfo s WHERE s.id = :id")
    , @NamedQuery(name = "StudentInfo.findByName", query = "SELECT s FROM StudentInfo s WHERE s.name = :name")
    , @NamedQuery(name = "StudentInfo.findByEmail", query = "SELECT s FROM StudentInfo s WHERE s.email = :email")
    , @NamedQuery(name = "StudentInfo.findByPhone", query = "SELECT s FROM StudentInfo s WHERE s.phone = :phone")})
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, name = "id")
    private Integer id;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String email;
    @Column(length = 45)
    private String phone;

    public StudentInfo() {
    }

    public StudentInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentInfo)) {
            return false;
        }
        StudentInfo other = (StudentInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "safecare.enitity.StudentInfo[ id=" + id + " ]";
    }
    
}
