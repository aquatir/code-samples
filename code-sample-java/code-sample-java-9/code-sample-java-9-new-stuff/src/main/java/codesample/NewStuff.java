package codesample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NewStuff {
    public static void main(String[] args) {

        /* New constructors for immutable collections */
        Map<String, String> immutableMap = Map.of("ivan", "narkoman");
        Set<Integer> immutableSet = Set.of(1,2,3);
        List<Integer> immutableList = List.of(1,2,3);

        /* New simplified ProcessHandle */
        System.out.println("Current process pid " + ProcessHandle.current().pid());

        String allProcesses = ProcessHandle.allProcesses()
                .map(processHandle -> String.valueOf(processHandle.pid()))
                .collect(Collectors.joining(", "));
        System.out.println("All processes pids: " + allProcesses);

        /* Try-with-resources without fresh resource requirement */
        CustromAutoclosable autoclosable = new CustromAutoclosable();
        try (autoclosable) {
            // Now we can auto-close resource with try-with-resources after it was created
            // This allows passing autoclosable resources to other methods
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Diamond operator on anonymous classes (Would not compile on java8*/
        List<String> list = new ArrayList<>(){};

        /* Reactive streams */
        
    }

    private static class CustromAutoclosable implements AutoCloseable {
        @Override
        public void close() throws Exception {

        }
    }

}
