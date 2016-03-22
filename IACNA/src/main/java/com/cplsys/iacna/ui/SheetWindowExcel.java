package com.cplsys.iacna.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.utils.ifaces.IACNAIface;
@Repository
public class SheetWindowExcel extends JDialog implements IACNAIface {
	private JButton aceptarButton;
    private JButton cancelButton;
    private JPanel container;
	private JScrollPane jScrollPane1;
    private JLabel labelFormulas;
    private JList listaFormulas;
	
	private XSSFWorkbook workbook;
	private int sheetCount;
	public int indexSheet;
	private int sizeList;
	private boolean cancel;
	private DefaultListModel model;
	
	private static final long serialVersionUID = 8526204865058470495L;

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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setSize(300, 200);
		setResizable(false);
        
        //jScrollPane1.setViewportView(listaFormulas);
		listaFormulas.setSize(100,500);
        aceptarButton.setFont(new Font("Tahoma", 0, 10));
        cancelButton.setFont(new Font("Tahoma", 0, 10));
		
	}

	@Override
	public void initObjects() {
		container = new JPanel(new MigLayout("insets 5 10 0 0"));
		
		listaFormulas = new JList();
		jScrollPane1 = new JScrollPane(listaFormulas);
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
		
		container.add(labelFormulas);
		container.add(aceptarButton);
		container.add(cancelButton, "wrap");
		container.add(jScrollPane1);
		
		getContentPane().add(container);
		
	}

	/**---------------------------**/
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
	
	private void performLogin() {    	
        if(listaFormulas.getModel().getSize()==0)
        	setIndexSheet(0);
        else setIndexSheet(listaFormulas.getSelectedIndex());
        setCancel(false);
        dispose();        
    }

}
