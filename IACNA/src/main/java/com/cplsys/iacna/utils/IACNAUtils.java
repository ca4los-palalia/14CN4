package com.cplsys.iacna.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.table.DefaultTableModel;

public class IACNAUtils {

	public static Dimension getScreenSizeForApp() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
	
	public static DefaultTableModel proccessExcelSheet(String locationPath) {
		File file = new File(locationPath);
		if (file != null && file.exists()) {
			
		}
		return null;
	}
}
