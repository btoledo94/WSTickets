package com.miumg.wstickets.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author BYRON TOLEDO
 */
@Entity()
@Table(name = "USUARIO", schema = "TICKET")
public class Usuario implements java.io.Serializable {
 
@Id
@Column(name = "ID")
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_usuarioseq")
@SequenceGenerator(name = "id_usuarioseq", sequenceName = "id_usuarioseq")
    private Integer id;
    
    @Column(name = "CORREO", length = 25)
    private String correo;
    private String nombre;

    public Usuario(Integer id, String correo, String nombre) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
    }
   
    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

}
