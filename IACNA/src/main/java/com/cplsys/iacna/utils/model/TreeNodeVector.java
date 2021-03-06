package com.cplsys.iacna.utils.model;

import java.util.Vector;

public class TreeNodeVector<E> extends Vector<E> {

	private static final long serialVersionUID = 1L;

	String name;

	  public TreeNodeVector(String name) {
	    this.name = name;
	  }

	  public TreeNodeVector(String name, E elements[]) {
	    this.name = name;
	    for (int i = 0, n = elements.length; i < n; i++) {
	      add(elements[i]);
	    }
	  }

	  public String toString() {
	    return name;
	  }
}
