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

public class DACreateMPTaskTest extends AbstractTest{

	public DACreateMPTaskTest() throws Exception {
		super("java:global/DAAdapter/DACreateMPTask!hn.com.tigo.josm.common.adapter.task.Task");
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
				"                       \"name\":\"PALMA FORTIN HERIBERTO KERIM\",\r\n" + 
				"                       \"noCard\":\"123456789\",\r\n" + 
				"                       \"typeCard\":\"algo\",\r\n" + 
				"                       \"modalityCard\":\"algo\",\r\n" + 
				"                       \"year\":\"2022\",\r\n" + 
				"                       \"month\":10,\r\n" + 
				"                       \"issuingBank\":\"Bac\",\r\n" + 
				"                       \"processorBank\":\"Bac\",\r\n" + 
				"                       \"groupPayment\":1,\r\n" + 
				"                       \"cycle\":\"05\",\r\n" + 
				"                       \"status\":1222,\r\n" + 
				"                       \"acctCode\":\"123456\",\r\n" + 
				"                       \"subscriberId\":\"99000000\"\r\n" + 
				"                         }");
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
