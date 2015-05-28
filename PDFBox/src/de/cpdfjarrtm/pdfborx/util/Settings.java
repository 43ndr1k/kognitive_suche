package de.cpdfjarrtm.pdfborx.util;

import java.nio.file.FileSystems;

/**
 * Static settings helper.
 * 
 * @author  Philipp Kleinhenz
 */
public final class Settings {
    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    
    public static final String indexDirectory = "./index";
    public static final String dataDirectoryDe = "/de";
    public static final String dataDirectoryEn = "/en";
    
    public static final String germanStopList = dataDirectoryDe+"/stoplist_de.txt";
    public static final String englishStopList = dataDirectoryEn+"/stoplist_en.txt";
    
    public static final String germanDic = dataDirectoryDe+"/de_DE_frami.dic";
    public static final String englishDic = dataDirectoryEn+"/en_US.dic";
    
    public static final String germanAff = dataDirectoryDe+"/de_DE_frami.aff";
    public static final String englishAff = dataDirectoryEn+"/en_US.aff";
    
    private Settings() {}
    
}
