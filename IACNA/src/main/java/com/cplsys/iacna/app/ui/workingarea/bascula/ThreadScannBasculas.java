package com.cplsys.iacna.app.ui.workingarea.bascula;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.services.BasculaService;

@Repository
public class ThreadScannBasculas extends Thread{
	
	@Autowired
	private BasculaService basculaService;
	@Autowired
	private BasculaWorkArea basculaWorkArea;
	private final int BASCULAS = 8; 
	String basculaEnOperacion = "";
	
	public void run() {
		
		int i = 0;
		while(true){
			basculaEnOperacion = String.valueOf(i+1);
			Bascula bascula = basculaService.getBasculaByName(basculaEnOperacion);
			
			
			if (!bascula.isBasculaAsignada()){//apagar
				if(basculaEnOperacion.equals("1")){
					if(basculaWorkArea.buttonBascula1.isSelected()) 
						basculaWorkArea.buttonBascula1.doClick();
				}
				
				if(basculaEnOperacion.equals("2")){
					if(basculaWorkArea.buttonBascula2.isSelected()) 
						basculaWorkArea.buttonBascula2.doClick();
				}
				if(basculaEnOperacion.equals("3")){
					if(basculaWorkArea.buttonBascula3.isSelected()) 
						basculaWorkArea.buttonBascula3.doClick();
				}
				if(basculaEnOperacion.equals("4")){
					if(basculaWorkArea.buttonBascula4.isSelected()) 
						basculaWorkArea.buttonBascula4.doClick();
				}
				if(basculaEnOperacion.equals("5")){
					if(basculaWorkArea.buttonBascula5.isSelected()) 
						basculaWorkArea.buttonBascula5.doClick();
				}
				if(basculaEnOperacion.equals("6")){
					if(basculaWorkArea.buttonBascula6.isSelected()) 
						basculaWorkArea.buttonBascula6.doClick();
				}
				if(basculaEnOperacion.equals("7")){
					if(basculaWorkArea.buttonBascula7.isSelected()) 
						basculaWorkArea.buttonBascula7.doClick();
				}
				if(basculaEnOperacion.equals("8")){
					if(basculaWorkArea.buttonBascula8.isSelected()) 
						basculaWorkArea.buttonBascula8.doClick();
				}
				if(basculaEnOperacion.equals("9")){
					if(basculaWorkArea.buttonBascula9.isSelected()) 
						basculaWorkArea.buttonBascula9.doClick();
				}
			}
			else { // encender
				if(basculaEnOperacion.equals("1")){
					if(!basculaWorkArea.buttonBascula1.isSelected()) 
						basculaWorkArea.buttonBascula1.doClick();
				}
				if(basculaEnOperacion.equals("2")){
					if(!basculaWorkArea.buttonBascula2.isSelected()) 
						basculaWorkArea.buttonBascula2.doClick();
				}
				if(basculaEnOperacion.equals("3")){
					if(!basculaWorkArea.buttonBascula3.isSelected()) 
						basculaWorkArea.buttonBascula3.doClick();
				}
				if(basculaEnOperacion.equals("4")){
					if(!basculaWorkArea.buttonBascula4.isSelected()) 
						basculaWorkArea.buttonBascula4.doClick();
				}
				if(basculaEnOperacion.equals("5")){
					if(!basculaWorkArea.buttonBascula5.isSelected()) 
						basculaWorkArea.buttonBascula5.doClick();
				}
				if(basculaEnOperacion.equals("6")){
					if(!basculaWorkArea.buttonBascula6.isSelected()) 
						basculaWorkArea.buttonBascula6.doClick();
				}
				if(basculaEnOperacion.equals("7")){
					if(!basculaWorkArea.buttonBascula7.isSelected()) 
						basculaWorkArea.buttonBascula7.doClick();
				}
				if(basculaEnOperacion.equals("8")){
					if(!basculaWorkArea.buttonBascula8.isSelected()) 
						basculaWorkArea.buttonBascula8.doClick();
				}
				if(basculaEnOperacion.equals("9")){
					if(!basculaWorkArea.buttonBascula9.isSelected()) 
						basculaWorkArea.buttonBascula9.doClick();
				}
			}
			
			i++;
			if(i > 8)
				i = 0;
		}//END while
		
	}
}
