package hn.com.tigo.josm.orchestrator.da.adapter.task;

import org.apache.log4j.Logger;

import hn.com.tigo.josm.common.adapter.config.MockTaskConfig;
import hn.com.tigo.josm.common.adapter.dto.TaskResponseType;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.common.exceptions.enumerators.AdapterErrorCode;

/**
 * ResponseMock This class responsible of configuration the mockTask ResponseMock.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-16-2022 06:31:08 AM 2022
 */
public final class ResponseMock {
	
	/** The Constant LOGGER. */
	private static final transient Logger LOGGER = Logger.getLogger(ResponseMock.class);
	

	/**
	 * Response Mock for all Tasks.
	 * 
	 * @param mockConfig
	 *			Configuration needed for the mock response.
	 * @return TaskResponseType
	 * 			Response given by the executed task.
	 * @throws AdapterException 
	 * 			Thrown by InterruptedException: If any thread has interrupted the current thread.
	 */
	public static TaskResponseType responseMock(final MockTaskConfig mockConfig) throws AdapterException {
		TaskResponseType response = null;	
		try {
			if (mockConfig != null) {
				LOGGER.info("Executing mock task with delay of: " + mockConfig.getTaskDelay());
				final long delay = mockConfig.getTaskDelay();
				Thread.sleep(delay);
			}
			response = new TaskResponseType();
			response.setResponseCode(0);
			response.setResponseDescription("SUCCESS");
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new AdapterException(AdapterErrorCode.INTERNAL_EXECUTOR_ERROR, ex.getMessage(), ex);
		}
		return response;
	}

}
