package com.cplsys.iacna.utils.render;

import java.awt.Component;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

import org.jdesktop.swingx.renderer.DefaultTreeRenderer;

import sun.swing.DefaultLookup;

import com.cplsys.iacna.utils.model.FileTreeModel;

@SuppressWarnings("serial")
public class TreeCellRenderer extends DefaultTreeCellRenderer {
	
	private static final long serialVersionUID = 8174237882554984968L;
	
	//private ImageIcon rendererIcon;
	private ImageIcon iconoRoot;
	private ImageIcon iconoAbierto;
	private ImageIcon iconoCerrado;
	private ImageIcon iconoFocus;
	private ImageIcon iconoSelect;
	
	public void setRendererIcon(ImageIcon root, ImageIcon hoja, ImageIcon closed, ImageIcon focu, ImageIcon sel){
        //this.rendererIcon = myIcon;
        
		iconoRoot = root;
    	iconoAbierto = hoja;
    	iconoCerrado = closed;
    	
    	iconoFocus = focu;
    	iconoSelect = sel;
	};
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		Component ret = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		JLabel label = (JLabel) ret ;
		
		
		if (leaf) {
			label.setIcon(iconoCerrado);
		}
		else if (expanded) {
			label.setIcon( iconoRoot );	
		}
		else if(hasFocus) {
			label.setIcon( iconoFocus );	
		}
		else if(selected) {
			label.setIcon( iconoSelect );	
		}
		else {
			label.setIcon(iconoAbierto);
		}
		label.setText(new File(value.toString()).getName().toString());
		return this;
    }
}
