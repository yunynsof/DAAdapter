package hn.com.tigo.josm.orchestrator.da.adapter.test;


import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ DAGetMPTaskTest.class,
	            DAGetMPMockTaskTest.class,
	            DACreateMPTaskTest.class,
	            DACreateMPMockTaskTest.class,
	            DACreateIncluAndExcluTaskTest.class,
	            DACreateIncluAndExcluMockTaskTest.class,
	            DAUpdateMPTaskTest.class,
	            DAUpdateMPMockTaskTest.class,
	            DAAccountDeleteTaskTest.class,
	            DAAccountDeleteMockTaskTest.class
	            })

public class AllTest {

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		EjbContainerContext.INSTANCE.close();
	}
}
