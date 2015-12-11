/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
 * @author ddahuy
 */
@Entity
@Table(name = "RATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r"),
    @NamedQuery(name = "Rate.findByGrade", query = "SELECT r FROM Rate r WHERE r.grade = :grade"),
    @NamedQuery(name = "Rate.findByRateid", query = "SELECT r FROM Rate r WHERE r.rateid = :rateid")})
public class Rate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "GRADE")
    private Integer grade;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RATEID")
    private Integer rateid;
    @JoinColumn(name = "RATER", referencedColumnName = "ID")
    @ManyToOne
    private Users rater;
    @JoinColumn(name = "IMG", referencedColumnName = "IMGID")
    @ManyToOne
    private Image img;

    public Rate() {
    }

    public Rate(Integer rateid) {
        this.rateid = rateid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getRateid() {
        return rateid;
    }

    public void setRateid(Integer rateid) {
        this.rateid = rateid;
    }

    public Users getRater() {
        return rater;
    }

    public void setRater(Users rater) {
        this.rater = rater;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateid != null ? rateid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.rateid == null && other.rateid != null) || (this.rateid != null && !this.rateid.equals(other.rateid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Rate[ rateid=" + rateid + " ]";
    }
    
}
