package de.leipzig.htwk.readResources;

/**
 * @Autor Hendrik Sawade
 * List die Ressourcen ein
 */
public class ReadResources {

    public ReadResources() {
        // Class.getResourceAsStream
        Object resource = ReadResources.class.getResourceAsStream("/Lists");
        System.out.println("1: A.class /Lists=" + resource.getClass().getClasses().getClass().getName().toString());
    }




}
