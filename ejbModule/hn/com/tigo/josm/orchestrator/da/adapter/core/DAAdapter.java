package hn.com.tigo.josm.orchestrator.da.adapter.core;

import hn.com.tigo.josm.common.adapter.AbstractAdapter;
import hn.com.tigo.josm.common.adapter.config.ConnectionConfig;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.orchestrator.da.driver.core.DADriver;
import hn.com.tigo.josm.orchestrator.da.driver.util.DAConstantsDriver;

import static javax.ejb.ConcurrencyManagementType.BEAN;

import java.util.Map;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

/**
 * DAAdapter This class gets the configuration from the configuration file.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-16-2022 06:16:25 AM 2022
 */
@Singleton
@Startup
@DependsOn("MonitoringManager")
@ConcurrencyManagement(BEAN)
public class DAAdapter extends AbstractAdapter<DADriver> {

	/** Attribute that determine a Constant of LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(DAAdapter.class);

	/**
	 * Instantiates a new DA adapter.
	 */
	public DAAdapter() {
		super();
		LOGGER.info("Init DAAdapter singleton");
	}

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.AbstractAdapter#createDriver()
	 */
	@Override
	protected DADriver createDriver() throws AdapterException {
		final Map<String, ConnectionConfig> connection = this.getConfigurationType().getDriverConfig().getConnections();
		return new DADriver(connection.get(DAConstantsDriver.ENDPOINT).getParameters().get(DAConstantsDriver.URL));
	}

}
