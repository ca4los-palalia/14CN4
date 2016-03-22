package com.cplsys.iacna.utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.springframework.stereotype.Repository;

import com.cplsys.iacna.utils.ifaces.IACNAIface;

@Repository
public class IACNAAbout extends JDialog implements IACNAIface {

	private static final long serialVersionUID = -4350059472036888918L;
	
	private JLabel icon;
	private JLabel legalInformation;
	private JLabel addresPublicInformation;
	private JLabel contactPublicInformation;
	private JLabel footerText;
	private JPanel globaContainer;
	private JPanel footerContainer;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	@Override
	public void initObjects() {
		icon = new JLabel(new ImageIcon(getClass().getResource("/media/LogoA+1.png")));
		legalInformation = new JLabel("<html><b>Sistema de pesaje de control de produccion</b><br>" +
				                   "Version: 1.0<br>" +
				                   "<b>(c) Copyright A+I® Automatizacion + Instrumentacion<br>" +
				                   "CPL Systems™ Software Development Company</b><br>" +
				                   "contributors and others 2005, 2012. <br> All rights reserved.</br>" +
				                   "Support: aisa_mx@xyahoo.com.mx</html>");
		addresPublicInformation = new JLabel("<html>43 Ote No 28, Despacho 105<br>" +
											 "<b>CP:</b> 72534" +
											 "<b>Col: </b> Huexotitla, Puebla, Pue.<br>" +
											 "<b>Tel/Fax: </b>(222) 5708380</html>");
		contactPublicInformation = new JLabel("<html><b>Nextel: <b/> 4449080<br>" +
													"<b>ID: <b/> 62*146756*2<br>" +
													"<b>Email: <b/> aisa_mx@yahoo.com.mx</html>");
		footerText = new JLabel("<html>Ingenieria Electrica - Automatizacion - Software - Instrumentacion</html>");
		globaContainer = new JPanel(new MigLayout("insets 0 0 0 0 "));
		footerContainer = new JPanel(new MigLayout("insets 0 0 0 0"));
	}
	
	@Override
	public void initProperties() {
		legalInformation.setFont(new Font("calibri", Font.PLAIN, 10));
		contactPublicInformation.setFont(new Font("calibri", Font.PLAIN, 10));
		addresPublicInformation.setFont(new Font("calibri", Font.PLAIN, 10));
		footerText.setFont(new Font("calibri", Font.PLAIN, 10));
		
		setSize(500, 215);
		setModal(true);
		setResizable(false);
		Dimension screenDimension =	Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)screenDimension.height / 2, (int)screenDimension.width / 5);
	}

	@Override
	public void initListeners() {

	}

	@Override
	public void createUI() {
		globaContainer.add(icon);
		JPanel infoPanel = new JPanel(new MigLayout("insets 0 0 0 0"));
		infoPanel.add(legalInformation, "wrap");
		infoPanel.add(addresPublicInformation, "wrap");
		infoPanel.add(contactPublicInformation, "wrap");
		globaContainer.add(infoPanel, "wrap");
		footerContainer.add(footerText);
		globaContainer.add(footerContainer);
		this.add(globaContainer);
	}

}
