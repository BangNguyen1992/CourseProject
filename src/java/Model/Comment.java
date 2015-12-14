/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ddahuy
 */
@Entity
@Table(name = "COMMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCmtid", query = "SELECT c FROM Comment c WHERE c.cmtid = :cmtid"),
    @NamedQuery(name = "Comment.findByText", query = "SELECT c FROM Comment c WHERE c.text = :text"),
    @NamedQuery(name = "Comment.findByTimestampt", query = "SELECT c FROM Comment c WHERE c.timestampt = :timestampt")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CMTID")
    private Integer cmtid;
    @Size(max = 200)
    @Column(name = "TEXT")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMPT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampt;
    
    @JoinColumn(name = "WRITER", referencedColumnName = "ID")
    @ManyToOne
    private Users writer;
    
    @JoinColumn(name = "IMG", referencedColumnName = "IMGID")
    @ManyToOne
    private Image img;

     public Comment() {
        
    }

    
    public Comment(String text) {
        this.text = text;
    }

    public Comment(Users id, Image imgid, String text){
        this.writer = id;
        this.img = imgid;
        this.text = text;
        
    }
    
    public Comment(Integer cmtid) {
        this.cmtid = cmtid;
    }

    public Comment(Integer cmtid, Date timestampt) {
        this.cmtid = cmtid;
        this.timestampt = timestampt;
    }

    public Integer getCmtid() {
        return cmtid;
    }

    public void setCmtid(Integer cmtid) {
        this.cmtid = cmtid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestampt() {
        return timestampt;
    }

    public void setTimestampt(Date timestampt) {
        this.timestampt = timestampt;
    }

    public Users getWriter() {
        return writer;
    }

    public void setWriter(Users writer) {
        this.writer = writer;
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
        hash += (cmtid != null ? cmtid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.cmtid == null && other.cmtid != null) || (this.cmtid != null && !this.cmtid.equals(other.cmtid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Comment[ cmtid=" + cmtid + " ]";
    }
    
}
