package com.kljx.workflow;

import java.util.ArrayList;
import java.util.List;

public class NextStep
{
	private int order = -1;
	private String id;
	private List<String> listOfRoleDesc = new ArrayList();
	private String nextStepId;

	public String getId()
	{
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getListOfRoleDesc() {
		return this.listOfRoleDesc;
	}
	public void setListOfRoleDesc(List<String> listOfRoleDesc) {
		this.listOfRoleDesc = listOfRoleDesc;
	}

	public String getNextStepId() {
		return this.nextStepId;
	}
	public void setNextStepId(String nextStepId) {
		this.nextStepId = nextStepId;
	}
	public int getOrder() {
		return this.order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}