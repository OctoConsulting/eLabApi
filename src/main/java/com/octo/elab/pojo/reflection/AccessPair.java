package com.octo.elab.pojo.reflection;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class will be used for all id-val mapping for /access API.
 * 
 * @author Sumit Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessPair {

	int id;

	String val;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	// Constructor
	public AccessPair() {

	}

	public AccessPair(int id) {
		super();
		this.id = id;
	}

	public AccessPair(int id, String val) {
		super();
		this.id = id;
		this.val = val;
	}

}