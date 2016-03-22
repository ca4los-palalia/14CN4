package com.cplsys.iacna.launcher;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import com.cplsys.iacna.launcher.*;

@Repository
public class IACNALauncher {

	public static void main(String[] args) {
		try {
			
			final JDialog dialog = new JDialog();
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {					
					dialog.getContentPane().add(new JLabel(
							new ImageIcon(IACNALauncher.class.getResource("/media/Splashcreen.png"))));
					dialog.setModal(true);
					dialog.setUndecorated(true);
					Dimension screenDimension =	Toolkit.getDefaultToolkit().getScreenSize();
					dialog.setLocation((int)screenDimension.height / 2, (int)screenDimension.width / 5);
					dialog.setSize(453, 273);
					dialog.setVisible(true);		
				}
			});
			
			
			ConfigurableApplicationContext beans = null;
			String[] contextPaths = new String[] { "spring/applicationContext.xml" };
			beans = new ClassPathXmlApplicationContext(contextPaths);
			dialog.dispose();
			
			String nameFileRunningApp ="IACNA.properties";
			File localFile = new File(nameFileRunningApp);
			if(!localFile.exists()) {
				createLogFile(nameFileRunningApp);
				Runnable application = beans.getBean(IACNA.class);
				try {
					if(localFile.delete()) {}
					SwingUtilities.invokeAndWait(application);
				} catch (InterruptedException e) {
					if(localFile.delete()) {}
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					if(localFile.delete()) {}
					e.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "SOLO ES POSIBLE EJECUTAR UNA\n " +
													"INSTANCIA DE LA APLICACION." 
													,"Abortar", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			
				
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, 
					"HA OCURRIDO UN ERROR EN LA CARGA\n" +
					"DEL CONTEXTO DE LA APLICACION:", 
					"ERROR DE CONTEXTO", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	private static void createLogFile(String nameFile) {
		FileWriter archivoConfig;
		FileInputStream fileConfig;
		Properties propiedades = new Properties();
		try {
			archivoConfig = new FileWriter(nameFile);
			fileConfig = new FileInputStream(nameFile);
			propiedades.load(fileConfig);
			propiedades.putAll(getConfig());
			propiedades.store(new FileOutputStream(nameFile), "Config");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"NO FUE POSIBLE CREAR LA BITACORA DE REGISTRO", "", 
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private static HashMap getConfig() {
		HashMap map = new HashMap();
		map.put("prueba", "www.google.com");
		return map;
	}
}
