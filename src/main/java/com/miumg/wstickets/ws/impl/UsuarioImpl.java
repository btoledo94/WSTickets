/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miumg.wstickets.ws.impl;

import com.miumg.wstickets.entities.Usuario;
import com.miumg.wstickets.ws.inte.UsuarioInt;
import com.miumg.wstickets.ws.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author BYRON TOLEDO
 */
@Component()
public class UsuarioImpl implements UsuarioInt{
    
    @Autowired()
    private UsuarioRepo usuarioRepo;
    
    @Override
    public ResponseEntity<Usuario> create(String correo) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Byron Toledo");
        usuario.setCorreo(correo);
        return new ResponseEntity(usuarioRepo.save(usuario), HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Usuario> findAll() throws Exception {
        return new ResponseEntity(usuarioRepo.findAll(), HttpStatus.OK);
    }
    
  }
