package com.kljx.workflow;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;

public class WorkflowFactory
{

	@Value("${develop.isDebug}")
	private boolean isDebug;
	private static final Map<String, WorkInfo> workInfoMap = new HashMap();

	private static final Map<String, CheckerRole> checkerRoleMap = new HashMap();

	@PostConstruct
	private void loadWorkInfos() {
		
		InputStream inputstream = null;
		try {
			inputstream = getClass().getResourceAsStream("/work-flow-config.xml");
			Document document = new SAXReader().read(inputstream);
			Element workInfos = document.getRootElement();

			for (Iterator iter = workInfos.elementIterator(); iter.hasNext(); ) {
				Element workInfoElement = (Element)iter.next();
				WorkInfo workInfo = createWorkInfo(workInfoElement);
				if (this.isDebug) System.out.println(workInfo.toString());
				workInfoMap.put(workInfo.getId(), workInfo);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			try {
				if (inputstream != null)
					inputstream.close();
			}
			catch (Exception localException)
			{
			}
		}
		finally
		{
			try
			{
				if (inputstream != null)
					inputstream.close();
			}
			catch (Exception localException1)
			{
			}
		}
		Iterator iter;
		try {
			inputstream = getClass().getResourceAsStream("/checker-info-config.xml");
			Document document = new SAXReader().read(inputstream);
			Element workInfos = document.getRootElement();

			for (iter = workInfos.elementIterator(); iter.hasNext(); ) {
				Element checkersElement = (Element)iter.next();
				CheckerRole checkerRole = createCheckerRole(checkersElement);
				if (this.isDebug) System.out.println(checkerRole.toString());
				checkerRoleMap.put(checkerRole.getMapKey(), checkerRole);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			try {
				if (inputstream != null)
					inputstream.close();
			} catch (Exception localException3) {
			}
		}
		finally {
			try
			{
				if (inputstream != null)
					inputstream.close();
			}
			catch (Exception localException4)
			{
			}
		}
		for (CheckerRole info : checkerRoleMap.values())
			System.out.println(info);
	}

	public WorkInfo getWorkInfo(String id)
	{
		return (WorkInfo)workInfoMap.get(id);
	}

	public Collection<CheckerRole> getCollectionOfCheckerRole()
	{
		return checkerRoleMap.values();
	}

	private CheckerRole createCheckerRole(Element checkersElement) {
		CheckerRole checkerRole = new CheckerRole();
		String mapKey = checkersElement.attributeValue("mapKey");
		String roleName = checkersElement.attributeValue("roleName");
		String refModule = checkersElement.attributeValue("refModule");
		Iterator iterator = checkersElement.elementIterator();
		List accountOfchecker = new ArrayList();
		while (iterator.hasNext())
		{
			Element element = (Element)iterator.next();
			String _account = element.attributeValue("account");
			String _name = element.attributeValue("name");
			CheckUserInfo info = new CheckUserInfo();
			info.setAccount(_account);
			info.setName(_name);
			accountOfchecker.add(info);
		}
		checkerRole.setMapKey(mapKey);
		checkerRole.setRoleName(roleName);
		checkerRole.setRefModule(refModule);
		checkerRole.setRefCheckUserInfo((CheckUserInfo[])accountOfchecker.toArray(new CheckUserInfo[accountOfchecker.size()]));
		return checkerRole;
	}

	private WorkInfo createWorkInfo(Element stepElementCollection)
	{
		WorkInfo workInfo = new WorkInfo();
		String id = stepElementCollection.attributeValue("id");
		workInfo.setId(id);
		List stepList = new ArrayList();
		Iterator iterator = stepElementCollection.elementIterator();
		while (iterator.hasNext())
		{
			Element element = (Element)iterator.next();
			String _id = element.attributeValue("id");
			String _next_step_id = element.attributeValue("nextNode");
			NextStep nextStepObj = new NextStep();
			nextStepObj.setId(_id);
			nextStepObj.setNextStepId(_next_step_id);
			Iterator valuesIterator = element.elementIterator();
			while (valuesIterator.hasNext())
			{
				String roleDesc = ((Element)valuesIterator.next()).getText();
				nextStepObj.getListOfRoleDesc().add(StringUtils.trim(roleDesc));
			}
			stepList.add(nextStepObj);
		}
		workInfo.layoutWorkflow(stepList);
		return workInfo;
	}
}