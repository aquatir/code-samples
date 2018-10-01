package codesample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewStuff {
    public static void main(String[] args) {

        /* New constructors for immutable collections */
        Map<String, String> immutableMap = Map.of("foo", "bar", "bar", "baz");
        Set<Integer> immutableSet = Set.of(1,2,3);
        List<Integer> immutableList = List.of(1,2,3);

        /* get current JVM pid */
        System.out.println("Current process pid " + ProcessHandle.current().pid());

        // List all processes
        String allProcesses = ProcessHandle.allProcesses()
                .map(processHandle -> String.valueOf(processHandle.pid()))
                .collect(Collectors.joining(", "));
        System.out.println("All processes pids: " + allProcesses);

        // kill every process!
        //ProcessHandle.allProcesses().forEach(ProcessHandle::destroyForcibly);

        /* Try-with-resources without fresh resource requirement */
        CustromAutoclosable autoclosable = new CustromAutoclosable();
        try (autoclosable) {
            // Now we can auto-close resource with try-with-resources after it was created
            // This allows passing autoclosable resources to other methods
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> map = Map.of("foo", "foo-value");
        List<String> keys = List.of("foo", "bar");

        List<String> values1 = keys.stream()
                .flatMap(key -> {
                    String temp = map.get(key);
                    return temp != null ? Stream.of(temp) : Stream.empty();
                })
                .collect(Collectors.toList());

        // or
        List<String> values2 = keys.stream()
                .filter(map::containsKey)
                .map(map::get)
                .collect(Collectors.toList());

        // now
        List<String> values3 = keys.stream()
                .flatMap(key -> Stream.ofNullable(map.get(key)))
                .collect(Collectors.toList());



    }

    private static class CustromAutoclosable implements AutoCloseable {
        @Override
        public void close() throws Exception {

        }
    }

    private static Optional<String> try_get_one() {
        return Optional.ofNullable("possible_null");
    }
    private static Optional<String> try_get_two() {
        return Optional.of("possible_null_againt");
    }
}
