package com.cplsys.iacna.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.annotation.PostConstruct;
import javax.sound.midi.SysexMessage;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.utils.ifaces.IACNAIface;

/**
 * @author CPL
 */

@Repository
public class SheetWindow extends JDialog implements IACNAIface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9160299798345723730L;

	private JButton aceptarButton;
    private JButton cancelButton;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JLabel labelFormulas;
    private JList listaFormulas;
	
	private XSSFWorkbook workbook;
	private int sheetCount;
	public int indexSheet;
	private int sizeList;
	private boolean cancel;
	private DefaultListModel model;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new java.awt.CardLayout());
        jPanel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        jScrollPane1.setViewportView(listaFormulas);
        aceptarButton.setFont(new Font("Tahoma", 0, 10));
        cancelButton.setFont(new Font("Tahoma", 0, 10));
	}

	@Override
	public void initObjects() {
		jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        listaFormulas = new JList();
        labelFormulas = new JLabel("Formulas:");
        aceptarButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");
        model = new DefaultListModel();
		
	}


	@Override
	public void initListeners() {
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performLogin();				
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanAllComponents();
				setCancel(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	cleanAllComponents();
            	setCancel(true);
            }
        });
	}


	@Override
	public void createUI() {
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(aceptarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelFormulas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFormulas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelButton))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
		
	}
	
	/*
    public SheetWindow(int sheetSize, XSSFWorkbook wb) {
    	initComponents();
        
        workbook=wb;
        sheetCount=sheetSize;
        
        
        listaFormulas.setModel(model);
        
        int index=0;
        for (int i = 0; i < sheetCount; i++) {
        	model.add(i, workbook.getSheetName(i));
        	if(workbook.getSheetName(i).equalsIgnoreCase("Formula"))
                index=i;
        }
        
        sizeList=listaFormulas.getModel().getSize();
    }
    
    */

    private void performLogin() {    	
        if(listaFormulas.getModel().getSize()==0)
        	setIndexSheet(0);
        else setIndexSheet(listaFormulas.getSelectedIndex());
        setCancel(false);
        dispose();        
    }
    
    public void loadSheets(int sheetSize, XSSFWorkbook wb) {
    	
    	model.clear();
    	//removeElementsList();
    	
    	workbook=wb;
        sheetCount=sheetSize;
        
        int index=0;
        for (int i = 0; i < sheetCount; i++) {
        	model.add(i, workbook.getSheetName(i));
        	if(workbook.getSheetName(i).equalsIgnoreCase("Formula"))
                index=i;
        }
        listaFormulas.setModel(model);
        sizeList=listaFormulas.getModel().getSize();
    }
 
    private void cleanAllComponents() {

    	Component componentes[] = getContentPane().getComponents(); 
        for(int i=0; i<componentes.length;i++)
        {
            String clase = componentes[i].getClass().getName();

            if(clase.equals("javax.swing.JTextField"))
            {
                JTextField tmp = (JTextField)componentes[i];
                tmp.setText("");
            }

            if(clase.equals("javax.swing.JPasswordField"))
            {
                JPasswordField tmp = (JPasswordField)componentes[i];
                tmp.setText("");
            }

            if(clase.equals("javax.swing.JComboBox"))
            {
                JComboBox tmp = (JComboBox)componentes[i];
                tmp.setSelectedIndex(0);
            }

            if(clase.equals("javax.swing.JCheckBox"))
            {
                JCheckBox tmp = (JCheckBox)componentes[i];
                tmp.setSelected(false);
            }
            if(clase.equals("javax.swing.JTable"))
            {
                JTable tmp = (JTable)componentes[i];
                tmp.setModel(new DefaultTableModel());
            } 
        }
        dispose();
    
    }
    

    public int getIndexSheet() {
		return indexSheet;
	}

	public void setIndexSheet(int indexSheet) {
		this.indexSheet = indexSheet;
	}

	public javax.swing.JList getListaFormulas() {
		return listaFormulas;
	}

	public void setListaFormulas(javax.swing.JList listaFormulas) {
		this.listaFormulas = listaFormulas;
	}
	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}


	
}
