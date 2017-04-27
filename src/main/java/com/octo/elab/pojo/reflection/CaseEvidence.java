package com.octo.elab.pojo.reflection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class will be used for all id-val mapping for /access API.
 * 
 * @author Sumit Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseEvidence {

	List<AccessPair> containers;

	List<AccessPair> packages;

	List<AccessPair> items;

	public List<AccessPair> getContainers() {
		return containers;
	}

	public void setContainers(List<AccessPair> containers) {
		this.containers = containers;
	}

	public List<AccessPair> getPackages() {
		return packages;
	}

	public void setPackages(List<AccessPair> packages) {
		this.packages = packages;
	}

	public List<AccessPair> getItems() {
		return items;
	}

	public void setItems(List<AccessPair> items) {
		this.items = items;
	}

}