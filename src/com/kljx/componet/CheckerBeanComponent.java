package com.kljx.componet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.wsdl.Constants;
import com.kljx.context.SpringContext;
import com.kljx.context.WebUtils;
import com.kljx.pojo.User;
import com.kljx.workflow.CheckUserInfo;
import com.kljx.workflow.CheckerRole;
import com.kljx.workflow.WorkInfo;
import com.kljx.workflow.WorkflowFactory;

@Component
public class CheckerBeanComponent {

	@Autowired
	private WorkflowFactory workflowFactory;

	

}