package hn.com.tigo.josm.orchestrator.da.adapter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import hn.com.tigo.josm.common.adapter.dto.ParameterArray;
import hn.com.tigo.josm.common.adapter.dto.ParameterType;
import hn.com.tigo.josm.common.adapter.dto.TaskRequestType;
import hn.com.tigo.josm.common.adapter.dto.TaskResponseType;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.orchestrator.da.adapter.utils.DAAdapterConstants;

public class DAUpdateMPMockTaskTest extends AbstractTest{

	public DAUpdateMPMockTaskTest() throws Exception {
		super("java:global/DAAdapter/DAUpdateMPMockTask!hn.com.tigo.josm.common.adapter.task.Task");
	}
	@Test
	public void test()  {
		
		try {
			final TaskResponseType response = task.executeTask(buildTaskRequestAction());
			assertEquals(0, response.getResponseCode());
		} catch (AdapterException e) {
			fail(e.getMessage());
		}
	}
	
	private TaskRequestType buildTaskRequestAction() {
		final ParameterArray parameterArray = new ParameterArray();
		
		ParameterType parameterTypeReq = new ParameterType();
		parameterTypeReq.setName(DAAdapterConstants.JSON);
		parameterTypeReq.setValue("{\r\n" + 
				"                           \"id\":\"3eacd6ae-b100-43bb-82d6-eabceb55808a\",\r\n" + 
				"                           \"name\":\"PALMA PALMA PALMa\"\r\n" + 
				"                         }");
		parameterArray.getParameter().add(parameterTypeReq);

		final TaskRequestType taskRequestType = new TaskRequestType();
		taskRequestType.setParameters(parameterArray);
		return taskRequestType;
	}
}
