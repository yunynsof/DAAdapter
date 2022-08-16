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

public class DACreateIncluAndExcluMockTaskTest extends AbstractTest{

	public DACreateIncluAndExcluMockTaskTest() throws Exception {
		super("java:global/DAAdapter/DACreateIncluAndExcluMockTask!hn.com.tigo.josm.common.adapter.task.Task");
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
				"                       \"numpla\":\"123456789\",\r\n" + 
				"                       \"type_tran\":\"algo\",\r\n" + 
				"                       \"anexo\":\"algo\",\r\n" + 
				"                       \"noCard\":\"111111111\",\r\n" + 
				"                       \"cardIsueDate\":10,\r\n" + 
				"                       \"expDate\":10,\r\n" + 
				"                       \"status\":\"Bac\",\r\n" + 
				"                       \"cycle\":\"02\"\r\n" + 
				"                         }");
		parameterArray.getParameter().add(parameterTypeReq);

		final TaskRequestType taskRequestType = new TaskRequestType();
		taskRequestType.setParameters(parameterArray);
		return taskRequestType;
	}
}
