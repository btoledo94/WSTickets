/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miumg.wstickets.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author BYRON TOLEDO
 */
@Entity()
@Table
public class EncabezadoTikcets implements java.io.Serializable{
    @Id
@Column(name = "ID")
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    
    @Column(length = 25)
    private String Problema;
    
    @ManyToOne()
    @JoinColumn()
    private Usuario usuario;

    public EncabezadoTikcets() {
    }

    public EncabezadoTikcets(Integer id, String Problema) {
        this.id = id;
        this.Problema = Problema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblema() {
        return Problema;
    }

    public void setProblema(String Problema) {
        this.Problema = Problema;
    }
    
    
    
}
