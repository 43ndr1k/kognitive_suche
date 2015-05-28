package de.cpdfjarrtm.pdfborx.gui.data;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Exportpath {

	/**
	 * getpath (String typ)
	 * 
	 * @param typ String with the name of the data-typ
	 * <br> there are only two Options: 
	 * <br>- "bib" for .bib
	 * <br>- anything else use shows in the filechosser pdf-files
	 * 
	 * @return path of the directory where the File will create<br>
	 * <br>
	 * <b>Example:</b>
	 * <br> getpath ("bib");
	 * <br>Filechooser shows only Files with the postfix .bib and returns the selected directorie
	 * 
	*/
	
	public String getpath(String typ){
		
		JFileChooser chooser = new JFileChooser();
		FileFilter filter;
		if (typ.compareTo("bib")==0){
			filter = new FileNameExtensionFilter("Bibtex-Dateien", "bib");
		} else {
			filter = new FileNameExtensionFilter("PDF-Dateien", "pdf");
		}
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		chooser.showSaveDialog(null);
		try{
			return chooser.getSelectedFile().getAbsolutePath();
		} catch (NullPointerException e) {
			return null;
		}
	}
}
