package com.cplsys.iacna.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.cplsys.iacna.utils.ifaces.IACNAIface;

public class ModifierIpDatabase extends JFrame {

	private static final long serialVersionUID = 6432555845580687991L;

	private static JRadioButton clientRadioButton;
	private static JRadioButton serverRadioButton;
	private static ButtonGroup groupRadioButtons;
	private static JTextField ipTextField;
	private static JButton aceptarButton;
	
	public static void main(String[] args) {
		
		init();
		
		new ModifierIpDatabase().add(clientRadioButton);
		new ModifierIpDatabase().add(serverRadioButton);
		new ModifierIpDatabase().add(ipTextField);
		new ModifierIpDatabase().add(aceptarButton);
		//copia ("D:/hibernateeXAMPLE.cfg.xml", "D:/Rebuild.cfg.xml");
		
		//System.err.println(this.getClass().getResource("/media/Base.png"));
		
	}

	
	
	
	public static void copia (String ficheroOriginal, String ficheroCopia) {
		BufferedReader bufferedReader;
		FileWriter fichero = null;
        PrintWriter pw = null;
        int i = 1;		
		String anterior="";
		String lineaNueva = "";
		String ip = "10.178.128.46";
		
		
		try {
			bufferedReader = new BufferedReader(new FileReader(ficheroOriginal));
			fichero = new FileWriter(ficheroCopia);
			pw = new PrintWriter(fichero);
			
			//Buscar si existe una palabra
			String line = "";
			while((line = bufferedReader.readLine())!=null) {
				
				lineaNueva = line;
				if(i == 12) {
					lineaNueva = "";
					for (int j = 0; j < line.length(); j++) {
						
						if ( line.substring(j, (j+1)).equals("l") &&
								
								line.substring((j+1), (j+2)).equals("o")
								) {
							lineaNueva += ip;
						}
						
						else if (j < 61) {
							lineaNueva += line.substring(j, (j+1));
						}
						if(j>=70) {
							lineaNueva += line.substring(j, (j+1));
						}
					}
					
                }
				
				pw.println(lineaNueva);
				
                i++;
			}
			bufferedReader.close();
			fichero.close();

			deleteFile(ficheroOriginal);
			renameFile (ficheroOriginal, ficheroCopia);
			
			System.err.println("Terminado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//-------------------
		
	}
	
	private static void renameFile(String ficheroOriginal, String ficheroCopia) {
		
		File oldfile =new File(ficheroCopia);
		File file2 = new File(ficheroOriginal);

		oldfile.renameTo(file2);
 
	}
	
	private static void deleteFile(String ficheroOriginal) {
		String fileName = ficheroOriginal;
		
		// A File object to represent the filename
	    File f = new File(fileName);
	    f.setWritable(true);

	    // Make sure the file or directory exists and isn't write protected
	    if (!f.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + fileName);

	    if (!f.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + fileName);

	    // If it is a directory, make sure it is empty
	    if (f.isDirectory()) {
	      String[] files = f.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException(
	            "Delete: directory not empty: " + fileName);
	    }

	    // Attempt to delete it
	    boolean success = f.delete();

	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
	}


	public static void init() {
		initObjects();
		initProperties();
		createUI();
		initListeners();
	}

	public static void initProperties() {
		groupRadioButtons.add(clientRadioButton);
		groupRadioButtons.add(serverRadioButton);
		ipTextField.setColumns(15);
		
	}


	public static void initObjects() {
		clientRadioButton = new JRadioButton("Cliente");
		serverRadioButton = new JRadioButton("Server");
		groupRadioButtons = new ButtonGroup();
		ipTextField = new JTextField();
		aceptarButton = new JButton("Aceptar");
		
	}



	public static void initListeners() {
		// TODO Auto-generated method stub
		
	}

	public static void createUI() {
		
		
	}

}
