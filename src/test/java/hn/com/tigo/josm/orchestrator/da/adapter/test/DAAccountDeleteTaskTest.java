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

public class DAAccountDeleteTaskTest extends AbstractTest{

	public DAAccountDeleteTaskTest() throws Exception {
		super("java:global/DAAdapter/DAAccountDeleteTask!hn.com.tigo.josm.common.adapter.task.Task");
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
	
	@Test
	public void testErr()  {
		
		try {
			final TaskResponseType response = task.executeTask(buildTaskRequestActionErr());
			assertEquals(0, response.getResponseCode());
		} catch (AdapterException e) {
			//fail(e.getMessage());
		}
	}
	
	private TaskRequestType buildTaskRequestAction() {
		final ParameterArray parameterArray = new ParameterArray();
		
		ParameterType parameterTypeReq = new ParameterType();
		parameterTypeReq.setName(DAAdapterConstants.JSON);
		parameterTypeReq.setValue("{\r\n" + 
				"	\"deleteList\": [{\r\n" + 
				"			\"subscriber\": \"99909999\",\r\n" + 
				"			\"account\": \"123456\",\r\n" + 
				"			\"bindNumber\": \"6578\",\r\n" + 
				"			\"expirationDate\": \"202103\",\r\n" + 
				"			\"cycle\": \"05\",\r\n" + 
				"			\"bank\": \"Ficohsa\",\r\n" + 
				"			\"amount\": \"200.00\",\r\n" + 
				"			\"transactionDate\": \"20200303\",\r\n" + 
				"			\"error\": \"01\",\r\n" + 
				"			\"message\": \"Datos del Afiliado Invalidos.\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"subscriber\": \"99909999\",\r\n" + 
				"			\"account\": \"123456\",\r\n" + 
				"			\"bindNumber\": \"6578\",\r\n" + 
				"			\"expirationDate\": \"202103\",\r\n" + 
				"			\"cycle\": \"05\",\r\n" + 
				"			\"bank\": \"Ficohsa\",\r\n" + 
				"			\"amount\": \"200.00\",\r\n" + 
				"			\"transactionDate\": \"20200303\",\r\n" + 
				"			\"error\": \"01\",\r\n" + 
				"			\"message\": \"Datos del Afiliado Invalidos.\"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}");
		parameterArray.getParameter().add(parameterTypeReq);

		final TaskRequestType taskRequestType = new TaskRequestType();
		taskRequestType.setParameters(parameterArray);
		return taskRequestType;
	}
	
	private TaskRequestType buildTaskRequestActionErr() {
		final ParameterArray parameterArray = new ParameterArray();
		
		ParameterType parameterTypeReq = new ParameterType();
		parameterTypeReq.setName(DAAdapterConstants.JSON);
		parameterTypeReq.setValue("");
		parameterArray.getParameter().add(parameterTypeReq);

		final TaskRequestType taskRequestType = new TaskRequestType();
		taskRequestType.setParameters(parameterArray);
		return taskRequestType;
	}
}
