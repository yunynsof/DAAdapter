package hn.com.tigo.josm.orchestrator.da.adapter.task;

import java.text.Normalizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
import hn.com.tigo.josm.orchestrator.da.driver.model.CreateMPInputModel;
import hn.com.tigo.josm.orchestrator.da.driver.operations.CreateMPOperation;

/**
 * DACreateMPTask This class is referenced to the DACreateMP task.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-16-2022 06:29:12 AM 2022
 */
@WebService
@Stateless(mappedName = "DACreateMPTask")
public class DACreateMPTask extends AbstractTask<CreateMPOperation, DADriver> implements Task {

	/** Attribute that determine a Constant of LOGGER. */
	private static final transient Logger LOGGER = Logger.getLogger(DAGetMPTask.class);

	/** Attribute that determine dAAdapter. */
	@EJB
	private DAAdapter dAAdapter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#getSingletonAdapter()
	 */
	@Override
	protected AbstractAdapter<DADriver> getSingletonAdapter() {
		return dAAdapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hn.com.tigo.josm.common.adapter.task.AbstractTask#executeDriverTask(java.lang
	 * .Object)
	 */
	@Override
	protected TaskResponseType executeDriverTask(final DADriver driver) throws AdapterException {
		return driver.executeDriver(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hn.com.tigo.josm.common.adapter.task.AbstractTask#createRequest(hn.com.tigo.
	 * josm.common.adapter.dto.TaskRequestType)
	 */
	@Override
	protected CreateMPOperation createRequest(final TaskRequestType taskType) throws AdapterException {

		CreateMPInputModel inputModel = new CreateMPInputModel();
		String json = DAAdapter.getParameterValue(DAAdapterConstants.JSON, taskType);
		printParameterValue(DAAdapterConstants.JSON, json);
		validateParameter(DAAdapterConstants.JSON, json, AdapterValidationType.ALL, true);

		try {
			json = cleanAccents(json);
			Gson gson = new Gson();
			inputModel = gson.fromJson(json, CreateMPInputModel.class);

		} catch (NullPointerException | JsonSyntaxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new AdapterException(AdapterErrorCode.PARAMETERS_ERROR, DAAdapterConstants.CORRECT_JSON_STRUCTURE, e);
		}
		return new CreateMPOperation(inputModel);
	}

	/**
	 * Clean accents.
	 *
	 * @param json the json
	 * @return the string
	 */
	private static String cleanAccents(String json) {

		json = json.replaceAll("[\\#\\$\\%\\^\\&\\*\\/\\-\\_\\*\\+\\#\\\\\'\\<\\>\\|\\°\\!\\¨]", "");
		String cadenaNormalize = Normalizer.normalize(json, Normalizer.Form.NFD);
		return cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

	}
}
