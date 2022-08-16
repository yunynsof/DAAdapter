package hn.com.tigo.josm.orchestrator.da.adapter.task;

import javax.ejb.Stateless;
import javax.jws.WebService;

import hn.com.tigo.josm.common.adapter.config.MockTaskConfig;
import hn.com.tigo.josm.common.adapter.dto.TaskResponseType;
import hn.com.tigo.josm.common.adapter.task.Task;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.orchestrator.da.driver.core.DADriver;

/**
 * DAGetMPMockTask This class is a mock type for the DAGetMP task.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-16-2022 06:33:43 AM 2022
 */
@WebService
@Stateless(mappedName = "DAGetMPMockTask")
public class DAGetMPMockTask extends DAGetMPTask implements Task {

	
	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.orchestrator.da.adapter.task.DAGetMPTask#executeDriverTask(hn.com.tigo.josm.orchestrator.da.driver.core.DADriver)
	 */
	@Override
	protected TaskResponseType executeDriverTask(final DADriver driver) throws AdapterException {
		final MockTaskConfig mockConfig = findMockTaskConfig(DAGetMPMockTask.class.getSimpleName());
		return ResponseMock.responseMock(mockConfig);
	}

}
