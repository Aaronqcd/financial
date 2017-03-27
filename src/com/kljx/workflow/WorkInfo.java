package com.kljx.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class WorkInfo
{
	private String id;
	private List<NextStep> listOfStep = new ArrayList();

	public NextStep findNextStep(String roleDesc)
	{
		for (NextStep step : this.listOfStep)
		{
			if (CollectionUtils.contains(step.getListOfRoleDesc().iterator(), StringUtils.trim(roleDesc)))
				return step;
		}
		return null;
	}

	public Map<String, Integer> findOrderWorkflow(String[] roles)
	{
		Map oretMap = new HashMap();
		List sortListNextStep = new ArrayList();
		for (String role : roles)
		{
			NextStep nextstep = findNextStep(role);
			if (nextstep != null) {
				sortListNextStep.add(nextstep);
			}

		}

		sort(sortListNextStep);
		for (int i = 0; i < sortListNextStep.size(); i++)
		{
			NextStep step = (NextStep)sortListNextStep.get(i);
			Iterator roleIterator = step.getListOfRoleDesc().iterator();
			while (roleIterator.hasNext())
			{
				String role = roleIterator.next().toString();
				oretMap.put(role, Integer.valueOf(i));
			}
		}
		return oretMap;
	}

	private static void sort(List<NextStep> list)
	{
		Collections.sort(list, new Comparator<NextStep>()
		{
			public int compare(NextStep arg0, NextStep arg1) {
				if (arg0.getOrder() > arg1.getOrder()) return 1;
				if (arg0.getOrder() < arg1.getOrder()) return -1;

				return 0;
			}
		});
	}

	public void layoutWorkflow(List<NextStep> list) {
		NextStep tailStep = null;

		for (int i = list.size() - 1; i >= 0; i--)
		{
			NextStep nstep = (NextStep)list.get(i);
			if (StringUtils.isEmpty(nstep.getNextStepId()))
			{
				tailStep = nstep;
				list.remove(i);
				break;
			}
		}
		if (tailStep != null)
		{
			this.listOfStep.add(tailStep);
			String prveId = tailStep.getId();
			recursionLayout(prveId, this.listOfStep, list);
			Collections.reverse(this.listOfStep);
			for (int i = 0; i < this.listOfStep.size(); i++)
			{
				NextStep step = (NextStep)this.listOfStep.get(i);
				step.setOrder(i);
			}
		} else {
			throw new IllegalArgumentException("非法的参数配置,未检测到工作流的结束节点");
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (NextStep nstep : this.listOfStep)
		{
			buffer.append(nstep.getId() + " :[" + nstep.getListOfRoleDesc() + "]--- > ");
		}
		return buffer.toString();
	}

	private void recursionLayout(String currentStepId, List<NextStep> workflowList, List<NextStep> list) {
		if (list.isEmpty()) return;

		for (int i = list.size() - 1; i >= 0; i--)
		{
			NextStep nstep = (NextStep)list.get(i);
			if (nstep.getNextStepId().equalsIgnoreCase(currentStepId))
			{
				list.remove(i);
				workflowList.add(nstep);
				recursionLayout(nstep.getId(), workflowList, list);
				break;
			}
		}
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
}