package de.leipzig.htwk.readResources;

/**
 * @Autor Hendrik Sawade
 * List die Ressourcen ein
 */
public class ReadResources {

    public ReadResources() {
        // Class.getResourceAsStream
        Object resource = ReadResources.class.getResourceAsStream("/");
        Class a[] = resource.getClass().getClasses();
        System.out.println(a.length);

        //System.out.println("1: A.class /Lists=" + resource.getClass().getClasses());
    }




}
