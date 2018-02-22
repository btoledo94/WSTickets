/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miumg.wstickets.config;


import com.miumg.wstickets.InformacionAplicacion;
import org.slf4j.MDC;

/**
 * 
 * @author wilver
 */
public class LogsConfiguration {

    /**
     *
     */
    public final static String KEY = "aplicacion";

    private static String Discriminador() {
        return new StringBuilder().append(InformacionAplicacion.getOrganizacion()).
                append("/").
                append(InformacionAplicacion.getNombre()).
                append("/").
                append(InformacionAplicacion.getVersion()).toString();
    }

    /**
     *
     */
    public static void addKey() {
        MDC.put(KEY, Discriminador());
    }

    /**
     *
     */
    public static void removeKey() {
        MDC.remove(KEY);
    }
}
