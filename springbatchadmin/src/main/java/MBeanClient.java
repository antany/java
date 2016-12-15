

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.batch.core.launch.JobOperator;

public class MBeanClient {

	public static void main(String[] args) throws Exception {
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9010/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

		ObjectName mbeanName = new ObjectName("in.antany.springbatch:name=example");

		// Include spring-batch-core-library in your classpath to run thsi
		// program as standalone

		JobOperator jobOperator = JMX.newMBeanProxy(mbsc, mbeanName, JobOperator.class, true);

		for (long jobExecutionId : jobOperator.getRunningExecutions("helloWorld")) {
			jobOperator.stop(jobExecutionId);

			System.out.println("Killed -- " + jobExecutionId);

		}

		jmxc.close();

	}
}
