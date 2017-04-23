package com.octo.elab.pojo.reflection;

import java.util.List;

public class EvidenceNew {

	private List<AccessPair> evidenceType = null;
	private List<AccessPair> parentType = null;
	private List<AccessPair> parentEvidenceNumber = null;
	
	public List<AccessPair> getParentEvidenceNumber(){
		return parentEvidenceNumber;
	}
	public void setParentEvidenceNumber(List<AccessPair> parentEvidenceNumber) {
		this.parentEvidenceNumber = parentEvidenceNumber;
	}
	public List<AccessPair> getParentType() {
		return parentType;
	}
	public void setParentType(List<AccessPair> parentType) {
		this.parentType = parentType;
	}
	public List<AccessPair> getEvidenceType() {
		return evidenceType;
	}
	public void setEvidenceType(List<AccessPair> evidenceType) {
		this.evidenceType = evidenceType;
	}
	
}