package com.miumg.wstickets;

/**
 * 
 * @author wilver
 */
public class InformacionAplicacion {

    private final static String ORG = InformacionAplicacion.class.getPackage().getImplementationVendor();
    private final static String PROJECT = InformacionAplicacion.class.getPackage().getImplementationTitle();
    private final static String VERSION = InformacionAplicacion.class.getPackage().getImplementationVersion();

    /**
     * @return the Organizacion
     */
    public static String getOrganizacion() {

        return ORG;
    }

    /**
     * @return the Nombre
     */
    public static String getNombre() {
        return PROJECT;
    }

    /**
     * @return the Version
     */
    public static String getVersion() {
        return VERSION;
    }
}
