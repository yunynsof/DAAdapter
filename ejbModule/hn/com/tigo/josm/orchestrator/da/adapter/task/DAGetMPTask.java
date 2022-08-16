package hn.com.tigo.josm.orchestrator.da.adapter.task;

import hn.com.tigo.josm.common.adapter.AbstractAdapter;
import hn.com.tigo.josm.common.adapter.AdapterValidationType;
import hn.com.tigo.josm.common.adapter.dto.TaskRequestType;
import hn.com.tigo.josm.common.adapter.dto.TaskResponseType;
import hn.com.tigo.josm.common.adapter.task.AbstractTask;
import hn.com.tigo.josm.common.adapter.task.Task;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.common.exceptions.enumerators.AdapterErrorCode;
import hn.com.tigo.josm.orchestrator.da.adapter.core.DAAdapter;
import hn.com.tigo.josm.orchestrator.da.adapter.utils.DAAdapterConstants;
import hn.com.tigo.josm.orchestrator.da.driver.core.DADriver;
import hn.com.tigo.josm.orchestrator.da.driver.model.GetMPInputModel;
import hn.com.tigo.josm.orchestrator.da.driver.operations.GetMPOperation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * DAGetMPTask This class is referenced to the DAGetMP task.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-16-2022 06:29:45 AM 2022
 */
@WebService
@Stateless(mappedName = "DAGetMPTask")
public class DAGetMPTask extends AbstractTask<GetMPOperation, DADriver> implements Task {

	
	/** Attribute that determine a Constant of LOGGER. */
	private static final transient Logger LOGGER = Logger.getLogger(DAGetMPTask.class);

	/** Attribute that determine dAAdapter. */
	@EJB
	private DAAdapter dAAdapter;

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#getSingletonAdapter()
	 */
	@Override
	protected AbstractAdapter<DADriver> getSingletonAdapter() {
		return dAAdapter;
	}

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#executeDriverTask(java.lang.Object)
	 */
	@Override
	protected TaskResponseType executeDriverTask(final DADriver driver) throws AdapterException {
		return driver.executeDriver(request);
	}

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#createRequest(hn.com.tigo.josm.common.adapter.dto.TaskRequestType)
	 */
	@Override
	protected GetMPOperation createRequest(final TaskRequestType taskType) throws AdapterException {

		GetMPInputModel inputModel = new GetMPInputModel();
		final String json = DAAdapter.getParameterValue(DAAdapterConstants.JSON, taskType);
		printParameterValue(DAAdapterConstants.JSON, json);
		validateParameter(DAAdapterConstants.JSON, json, AdapterValidationType.ALL, true);

		try {
			Gson gson = new Gson();
			inputModel = gson.fromJson(json, GetMPInputModel.class);

			if (inputModel.getAcctCode() != null) {
				validateParameter(DAAdapterConstants.PARAMETER_ACCTCODE, inputModel.getAcctCode(),
						AdapterValidationType.ALL, false);
			} else if (inputModel.getPrimaryIdentity() != null) {
				validateParameter(DAAdapterConstants.PARAMETER_PRIMARYIDENTITY, inputModel.getPrimaryIdentity(),
						AdapterValidationType.ALL, false);
			} else {
				validateParameter(
						DAAdapterConstants.PARAMETER_ACCTCODE + " / " + DAAdapterConstants.PARAMETER_PRIMARYIDENTITY,
						null, AdapterValidationType.ALL, false);
			}

		} catch (NullPointerException | JsonSyntaxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new AdapterException(AdapterErrorCode.PARAMETERS_ERROR, DAAdapterConstants.CORRECT_JSON_STRUCTURE, e);
		}
		return new GetMPOperation(inputModel);
	}

}
