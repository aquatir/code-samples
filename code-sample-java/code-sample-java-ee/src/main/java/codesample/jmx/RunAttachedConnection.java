package codesample.jmx;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

class RunAttachedConnection {
    public static void main(String args[]) throws IOException, InterruptedException {


        /* I have taken my idea pid for test purposes. You should put your JVM pid here */
        int myPid = 3703;
        String attachUrl = AttachAgentToRemoteJvm.attachAgent(myPid);

        JMXServiceURL jmxUrl = new JMXServiceURL(attachUrl);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxUrl);
        MBeanServerConnection jmxBeanServerConnection = jmxConnector.getMBeanServerConnection();

        MemoryMXBean memoryMBean = ManagementFactory.newPlatformMXBeanProxy(jmxBeanServerConnection,
                ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);

        Thread.sleep(3000);
        MemoryUsage heap = memoryMBean.getHeapMemoryUsage();

        System.out.println("Process: " + myPid + "\n"
                + "init heap: " + heap.getInit() + "\n"
                + "committed: " + heap.getCommitted() + "\n"
                + "max: " + heap.getMax());
    }
}
