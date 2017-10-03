package learn_to_code.jmx;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * In order for this to work you should add
 * tools-linux (Or other OS specific jar) to classPath. See pom.xml for details
 */
public class AttachAgentToRemoteJvm {

    /** Magic class name for JMX connection */
    private static final String LOCAL_CONNECTOR_ADDRESS_PROP = "com.sun.management.jmxremote.localConnectorAddress";

    /**
     * @param pid pid of JVM process
     * @return Jmx Agent Address String, which can be used for creating jmx connection <br>
     *     for example: {@code JMXServiceURL jmxUrl = new JMXServiceURL(jxmAgentAddress);}
     */
    public static String attachAgent(int pid) {

        String returnedJmxAgentAddress = "";

        VirtualMachine vm;
        String pidAsString = String.valueOf(pid);
        try {
            vm = VirtualMachine.attach(pidAsString);
            String home = vm.getSystemProperties().getProperty("java.home");

            /*
             * Normally JMX agent is in ${java.home}/jre/lib/management-agent.jar but might
             * be in ${java.home}/lib in build environments.
             */
            String agent = home + File.separator + "jre" + File.separator
                    + "lib" + File.separator + "management-agent.jar";
            File f = new File(agent);
            if (!f.exists()) {
                agent = home + File.separator + "lib" + File.separator
                        + "management-agent.jar";
                f = new File(agent);
            }

            agent = f.getCanonicalPath();
            vm.loadAgent(agent, "com.sun.management.jmxremote");

            /* get the connector address */
            Properties agentProps = vm.getAgentProperties();

            returnedJmxAgentAddress = (String) agentProps
                    .get(LOCAL_CONNECTOR_ADDRESS_PROP);
            vm.detach();

        } catch (IOException | AttachNotSupportedException | AgentLoadException | AgentInitializationException e) {
            e.printStackTrace();
        }

        return returnedJmxAgentAddress;
    }
}
