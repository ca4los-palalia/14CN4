package com.cplsys.iacna.utils.model;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class IACNATreeSelectionListener implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		JTree tree = (JTree) e.getSource();
	    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
	        .getLastSelectedPathComponent();
	    String selectedNodeName = selectedNode.toString();
	    if (selectedNode.isLeaf()) {
	      System.out.println(selectedNodeName);
	      System.err.println("carlos");
	    }
	}

}
