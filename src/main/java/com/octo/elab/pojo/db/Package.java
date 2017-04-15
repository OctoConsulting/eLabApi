package com.octo.elab.pojo.db;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Package {

	private String packageName;
	private Integer packageType;
	private List<Evidence> items;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public List<Evidence> getItems() {
		return items;
	}

	public void setItems(List<Evidence> items) {
		this.items = items;
	}

}