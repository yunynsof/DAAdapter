package hn.com.tigo.josm.orchestrator.da.adapter.test;

import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.Logger;

import hn.com.tigo.josm.common.adapter.task.Task;
import hn.com.tigo.josm.orchestrator.da.adapter.core.DAAdapter;


public class AbstractTest {

	/** Attribute that determine container. */
	protected static EJBContainer container;

	protected static DAAdapter daAdapter;

	/** Attribute that determine task. */
	protected Task task;

	/** Attribute that determine a Constant of LOGGER. */
	protected static final transient Logger LOGGER = Logger.getLogger(AbstractTest.class);

	/**
	 * Instantiates a new abstract test.
	 *
	 * @param jndi the jndi
	 * @throws Exception the exception
	 */
	public AbstractTest(final String jndi) throws Exception {
		container = EjbContainerContext.INSTANCE.getContainer();
		daAdapter = (DAAdapter) container.getContext().lookup(
				"java:global/DAAdapter/DAAdapter!hn.com.tigo.josm.orchestrator.da.adapter.core.DAAdapter");
		task = (Task) container.getContext().lookup(jndi);
	}

}
