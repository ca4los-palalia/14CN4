package com.cplsys.iacna.app.ui.workingarea.configuration;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cplsys.iacna.app.ui.workingarea.IACNAWorkingAreaPanel;
import com.cplsys.iacna.app.ui.workingarea.material.MaterialWorkArea;
import com.cplsys.iacna.domain.OrigenDeDatos;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.OrigenDeDatosService;
import com.cplsys.iacna.services.PrivilegiosService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class ConfiguracionWorkArea extends JDialog implements IACNAIface,
		LogginListener {

	private static final long serialVersionUID = 2737509002245925739L;

	private JLabel title;
	private JLabel productosLocationPath;
	private JLabel productosPrepesados;	
	private JTextField productsField;
	private JTextField productosPrepesadosField;	
	private JButton accept;
	private JButton cancel;
	private JButton exploreProductos;
	private JButton exploreProductosPrepesados;	
	private JPanel titleContainer;
	private JPanel pathsContainer;
	private JPanel controlContainer;	
	private JButton ping;
	private JTextField ipHostField;	
	private InetAddress address;
	private boolean connectionWasTested;
	
	private boolean privilegio;
	
	@Autowired
	private OrigenDeDatosService origenDeDatosService;
	@Autowired 
	private MaterialWorkArea materialWorkArea;
	private OrigenDeDatos origenDeDatosSession; 
	@Autowired
	private IACNAWorkingAreaPanel iacnaWorkingArea;
	@Autowired
	private ISession sesionService;
	@Autowired
	private PrivilegiosService privilegiosService;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	@Override
	public void initProperties() {
		productsField.setEnabled(false);
		productosPrepesadosField.setEnabled(false);
		
		List<OrigenDeDatos> origenList =  origenDeDatosService.getAll();
		if (origenList != null && !origenList.isEmpty()) {
			for (OrigenDeDatos origenDeDatos : origenList) {
				productsField.setText(origenDeDatos.getLocationProductos());
				productosPrepesadosField.setText(origenDeDatos.getLocationProductosRed());
			}
		}

		this.setLayout(new MigLayout("insets 0 0 0 0"));
		setModal(true);
		setLocationRelativeTo(this);
		setSize(500, 230);
		setResizable(false);
		title.setFont(new Font("arial", Font.BOLD, 16));
		ping.setToolTipText("Haga click aqui para verificar la conectividad del host");
		ipHostField.setToolTipText("Ingrese la direccion ip del host que contiene el origen de datos");
		
		if(!productsField.equals(""))
			exploreProductos.setEnabled(false);
		if(!productosPrepesadosField.equals(""))
			exploreProductosPrepesados.setEnabled(false);
	}

	@Override
	public void initObjects() {
		title = new JLabel("Configuracion de Origenes de Datos");
		productosLocationPath = new JLabel("Productos: ");
		productosPrepesados = new JLabel("Productos [Prepesados]");

		productsField = new JTextField(40);
		productosPrepesadosField = new JTextField(40);

		accept = new JButton("Aceptar");
		cancel = new JButton("Cancelar");
		
		exploreProductos = new JButton("Explorar");
		exploreProductosPrepesados = new JButton("Explorar");
		
		titleContainer = new JPanel(new MigLayout("insets 10 120 0 0"));
		pathsContainer = new JPanel(new MigLayout("insets 5 0 0 0"));
		controlContainer = new JPanel(new MigLayout("insets 10 180 0 0")); ;
		
		ipHostField = new JTextField(30);
		
		ping = new JButton("Verificar");
	}

	@Override
	public void initListeners() {
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean connectionRefused = true;
				accept.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (!connectionWasTested) {
					if(performNetworkTestConnection()) {
						connectionRefused = false;
					} else {
						connectionRefused = true;
						JOptionPane.showMessageDialog(null, "Error, la direccion es inalcanzable, por favor\n" + 
															"verifique que el nombre del dominio es correcto.",
															"", JOptionPane.WARNING_MESSAGE);
					}
				} else {					
					if (origenDeDatosSession == null) {
						origenDeDatosSession = new OrigenDeDatos();
					}
					origenDeDatosSession.setDescripcion("Origen de datos administrador");
					origenDeDatosSession.setNombre("Default Configuration");
					origenDeDatosSession.setLocationProductos(productsField.getText());
					origenDeDatosSession.setLocationProductosPrepesados(productosPrepesadosField.getText());
					origenDeDatosSession.setHostName(address.getHostName());
					origenDeDatosSession.setHostIP(ipHostField.getText());
					origenDeDatosService.saveOrigenDeDatos(origenDeDatosSession);
					LogginListener login= (LogginListener)iacnaWorkingArea;
					login.sessionActivated();
					dispose();
				}

				accept.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				origenDeDatosSession.setDescripcion("Origen de datos administrador");
				origenDeDatosSession.setNombre("Local Configuration");
				origenDeDatosSession.setLocationProductos(productsField.getText());
				origenDeDatosSession.setLocationProductosPrepesados(productosPrepesadosField.getText());
				//origenDeDatosSession.setLocationProductosRed(getPathHost(productsField.getText()));
				origenDeDatosSession.setLocationProductosRed(productsField.getText());
				
				
				origenDeDatosService.saveOrigenDeDatos(origenDeDatosSession);
				LogginListener login= (LogginListener)iacnaWorkingArea;
				login.sessionActivated();
				dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		});
	
		exploreProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserProducts = new JFileChooser();
				chooserProducts.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int opc = chooserProducts.showOpenDialog(null);
				if (opc == JOptionPane.OK_OPTION) {
					if (chooserProducts.getSelectedFile().isDirectory()) {
						  
						productsField.setText(chooserProducts.getSelectedFile().getPath());	
					}
				}
			}
		});
		
		exploreProductosPrepesados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser exploreMaterial1 = new JFileChooser();
				exploreMaterial1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int opc = exploreMaterial1.showOpenDialog(null);
				if (opc == JOptionPane.OK_OPTION) {
					productosPrepesadosField.setText(exploreMaterial1.getSelectedFile().getPath());
				}
			}
		});
		
		ping.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performPing();
			}
		});
		
		ipHostField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performPing();
			}
		});

	}
	
	@Override
	public void createUI() {
		titleContainer.add(title);
		
		pathsContainer.add(new JLabel("Direccion IP del host: "));
		pathsContainer.add(ipHostField);
		pathsContainer.add(ping, "wrap");
		
		pathsContainer.add(productosLocationPath);
		pathsContainer.add(productsField);
		pathsContainer.add(exploreProductos, "wrap");
		
		pathsContainer.add(productosPrepesados);
		pathsContainer.add(productosPrepesadosField);
		pathsContainer.add(exploreProductosPrepesados, "wrap");
		
		controlContainer.add(accept);
		controlContainer.add(cancel);
		
		this.add(titleContainer, "wrap");
		this.add(buildSeparator(), "width 500:500:500, wrap");
		this.add(pathsContainer, "wrap");
		this.add(buildSeparator(), "width 500:500:500, wrap");
		this.add(controlContainer, "wrap");
	}

	@Override
	public void sessionActivated() {
		List<OrigenDeDatos> origenes = origenDeDatosService.getAll();
		for (OrigenDeDatos origenDeDatos : origenes) {
			origenDeDatosSession = origenDeDatos;
			productsField.setText(origenDeDatos.getLocationProductos());
			productosPrepesadosField.setText(origenDeDatos.getLocationProductosPrepesados());
			ipHostField.setText(origenDeDatos.getHostIP());
			break;
		}
		if (productsField.getText().isEmpty()) {
			exploreProductos.setEnabled(false);
			exploreProductosPrepesados.setEnabled(false);
		}
	}

	@Override
	public void sessionDeactivated() {

	}
	
	private JSeparator buildSeparator() {
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setForeground(Color.BLACK);		
		return separator;
	}
	
	private boolean performNetworkTestConnection() {
		try {
			address = InetAddress.getByName(ipHostField.getText());
			if (!address.getHostName().equals(ipHostField.getText())) {
				return true;
			}			
			return false;							
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception, "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private String getPathHost(String path) {
		
		InetAddress ip;
		String host="";
		
		//---- GET FOLDER NAME ---------
		String carpeta="";
		int count=0;
		int nslash=0;
		
		for (int i = 0; i < path.length(); i++) {
			String character=path.substring(i, (i+1));
			if(character.equals("\\")) {
				count++;
			}
		}
		for (int i = 0; i < path.length(); i++) {
			String character = path.substring(i, (i+1));
			if(character.equals("\\")) {
				nslash++;
				if(nslash == count) {
					carpeta = path.substring((i+1));
				}
			}
		}
		//---- END FLODER NAME ---------
		
		try {
			ip = InetAddress.getLocalHost();
			
			host="\\"+"\\"+ip.getHostName() +"\\"+carpeta;
			
		} catch (UnknownHostException e1) {
			
			return "";
		}
		return host;
	}
	
	public boolean isPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}

	private void performPing() {

		ping.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if (!ipHostField.getText().isEmpty()) {
			if (performNetworkTestConnection()) {
				exploreProductos.setEnabled(true);
				exploreProductosPrepesados.setEnabled(true);
				connectionWasTested = true;
				JOptionPane.showMessageDialog(null, "Conexion establecida satisfactoriamente ", "", 
						JOptionPane.INFORMATION_MESSAGE );
			}
		} else {
			exploreProductos.setEnabled(false);
			exploreProductosPrepesados.setEnabled(false);
			connectionWasTested = false;
			JOptionPane.showMessageDialog(null,  
					"Por favor especifique un nombre de dominio ", 
					"", JOptionPane.INFORMATION_MESSAGE);
		}
		ping.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}