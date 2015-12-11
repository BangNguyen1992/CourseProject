/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ddahuy
 */
@Entity
@Table(name = "TAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByTagid", query = "SELECT t FROM Tag t WHERE t.tagid = :tagid"),
    @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name")})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TAGID")
    private Integer tagid;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "tagCollection")
    private Collection<Image> imageCollection;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private Users id;
    @JoinColumn(name = "IMGID", referencedColumnName = "IMGID")
    @ManyToOne
    private Image imgid;

    public Tag() {
    }

    public Tag(String name, Users id, Image imgid) {
        this.name = name;
        this.id = id;
        this.imgid = imgid;
    }
    
    public Tag(Integer tagid) {
        this.tagid = tagid;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    public Users getId() {
        return id;
    }

    public void setId(Users id) {
        this.id = id;
    }

    public Image getImgid() {
        return imgid;
    }

    public void setImgid(Image imgid) {
        this.imgid = imgid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagid != null ? tagid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tagid == null && other.tagid != null) || (this.tagid != null && !this.tagid.equals(other.tagid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Tag[ tagid=" + tagid + " ]";
    }
    
}
