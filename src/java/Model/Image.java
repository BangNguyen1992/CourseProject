/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ddahuy
 */
@Entity
@Table(name = "IMAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
//    @NamedQuery(name=  "Image.getGallery", query="SELECT i FROM Image i GROUP BY i.imgid ORDER BY i.imgid DESC LIMIT 6"),
    @NamedQuery(name = "Image.findByImgid", query = "SELECT i FROM Image i WHERE i.imgid = :imgid"),
    @NamedQuery(name = "Image.findByPath", query = "SELECT i FROM Image i WHERE i.path LIKE :path"),
    @NamedQuery(name = "Image.findByFilesize", query = "SELECT i FROM Image i WHERE i.filesize = :filesize"),
    @NamedQuery(name = "Image.findByUploaddate", query = "SELECT i FROM Image i WHERE i.uploaddate = :uploaddate"),
    @NamedQuery(name = "Image.findByDescription", query = "SELECT i FROM Image i WHERE i.description = :description"),
    @NamedQuery(name = "Image.findByTitleView", query="SELECT i FROM Image i WHERE i.path LIKE ?1 ORDER BY i.imgid ASC")})

    //@NamedQuery(name = "Image.findByTitle", query = "SELECT i FROM Image i GROUP BY imgid ORDER BY imgid DESC LIMIT 6 WHERE i.title = :title")})
    
    public class Image implements Serializable {
    @OneToMany(mappedBy = "imgid")
    private Collection<Tag> tagCollection1;
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IMGID")
    private Integer imgid;
    
    @Size(max = 254)
    @Column(name = "PATH")
    private String path;
    
    @Column(name = "FILESIZE")
    private Integer filesize;
    
    @Column(name = "UPLOADDATE")
    @Temporal(TemporalType.DATE)
    private Date uploaddate;
    
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Size(max = 200)
    @Column(name = "TITLE")
    private String title;
    
    @JoinTable(name = "TAGS", joinColumns = {
        @JoinColumn(name = "IMG", referencedColumnName = "IMGID")}, inverseJoinColumns = {
        @JoinColumn(name = "TAG", referencedColumnName = "TAGID")})
    
    @ManyToMany
    private Collection<Tag> tagCollection;
    
    @JoinColumn(name = "OWNER", referencedColumnName = "ID")
    @ManyToOne
    private Users owner;
    
    @OneToMany(mappedBy = "img")
    private Collection<Rate> rateCollection;
    
    @OneToMany(mappedBy = "img")
    private Collection<Comment> commentCollection;

    public Image() {
    }

    public Image(String path, String description, Date uploaddate, int filesize){
        this.description = description;
        this.path = path;
        this.uploaddate = uploaddate;
        this.filesize = filesize;
        //this.owner = owner;
    }
    
    public Image(Integer imgid) {
        this.imgid = imgid;
    }

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Date getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @XmlTransient
    public Collection<Rate> getRateCollection() {
        return rateCollection;
    }

    public void setRateCollection(Collection<Rate> rateCollection) {
        this.rateCollection = rateCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgid != null ? imgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imgid == null && other.imgid != null) || (this.imgid != null && !this.imgid.equals(other.imgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Image[ imgid=" + imgid + " ]";
    }

    @XmlTransient
    public Collection<Tag> getTagCollection1() {
        return tagCollection1;
    }

    public void setTagCollection1(Collection<Tag> tagCollection1) {
        this.tagCollection1 = tagCollection1;
    }
    
}
