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
@Table(name = "T_Role", catalog = "tarm_privilege", schema = "tarm_privilege")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "T_Role.findNotDele", query = "SELECT d FROM T_Role d WHERE d.deleted=0"),
        @NamedQuery(name = "T_Role.findByRoleId", query = "SELECT d FROM T_Role d WHERE d.roleid= ?1"),
        @NamedQuery(name = "T_Role.findByRoledeviceidNotNull", query = "SELECT d FROM T_Role d WHERE d.roledeviceid IN (:roledeviceids) AND d.roleservicecode =:roleservicecode"),
        @NamedQuery(name = "T_Role.findByRoledeviceidNull", query = "SELECT d FROM T_Role d WHERE d.roledeviceid IN (:roledeviceids) AND d.roleservicecode =:roleservicecode"),
        @NamedQuery(name = "T_Role.findByRoledevicetypeNotNull", query = "SELECT d FROM T_Role d WHERE d.roledevicetype IN (:roledevicetypes) AND d.roleservicecode = :roleservicecode  AND d.roledeviceid =:roledeviceid"),
        @NamedQuery(name = "T_Role.findByRoleuseridNotNull", query = "SELECT d FROM T_Role d WHERE d.roleuserid IN (:roleuserids) AND d.roleservicecode =:roleservicecode  AND d.roledeviceid =:roledeviceid AND d.roledevicetype=:roledevicetype"),
        @NamedQuery(name = "T_Role.findByRoleusertypeNotNull", query = "SELECT d FROM T_Role d WHERE d.roleusertype IN (:roleusertypes) AND d.roleservicecode =:roleservicecode  AND d.roledeviceid =:roledeviceid AND d.roledevicetype=:roledevicetype AND d.roleuserid=:roleuserid"),
        @NamedQuery(name = "T_Role.findByRolepagenumNotNull", query = "SELECT d FROM T_Role d WHERE d.rolepagenum IN (:rolepagenums) AND d.roleservicecode =:roleservicecode  AND d.roledeviceid =:roledeviceid AND d.roledevicetype=:roledevicetype AND d.roleuserid=:roleuserid AND d.roleusertype=:roleusertype") })

public class T_Role implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    public Integer ptid;

    @Column(name = "roleid", length = 255)
    public String roleid;

    @Column(name = "roleservicecode", length = 255)
    public String roleservicecode;

    @Column(name = "roledeviceid", length = 255)
    public String roledeviceid;

    @Column(name = "roledevicetype", length = 255)
    public String roledevicetype;

    @Column(name = "roleuserid", length = 255)
    public String roleuserid;

    @Column(name = "roleusertype", length = 255)
    public String roleusertype;

    @Column(name = "rolepagenum", length = 255)
    public String rolepagenum;

    @Column(name = "groupid", length = 255)
    public String groupid;

    @JsonIgnore
    @XmlTransient
    @Column(name = "deleted", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    public Integer deleted = 0;

}
