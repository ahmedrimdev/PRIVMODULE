package com.rimdev.rimpriv.entities;

import javax.persistence.Entity;

import java.io.Serializable;
import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "T_Group", catalog = "tarm_privilege", schema = "tarm_privilege")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "T_Group.findByGroupId", query = "SELECT d FROM T_Group d WHERE d.groupid = ?1"),
        @NamedQuery(name = "T_Group.findNotDele", query = "SELECT d FROM T_Group d WHERE d.deleted=0") })
public class T_Group implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    public Integer ptid;

    @Column(name = "groupid", length = 255)
    public String groupid;

    @Column(name = "groupname", length = 255)
    public String groupname;

    @Column(name = "grouppriority", length = 255)
    public String grouppriority;

    @Column(name = "groupstatus", length = 255)
    public String groupstatus;

    @JsonIgnore
    @XmlTransient
    @Column(name = "deleted", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    public Integer deleted = 0;

}
