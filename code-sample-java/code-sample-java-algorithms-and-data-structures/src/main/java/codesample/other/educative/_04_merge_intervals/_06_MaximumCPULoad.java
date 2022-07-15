package codesample.other.educative._04_merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Job {
    int start;
    int end;
    int cpuLoad;

    public Job(int start, int end, int cpuLoad) {
        this.start = start;
        this.end = end;
        this.cpuLoad = cpuLoad;
    }
};

public class _06_MaximumCPULoad {
    public static int findMaxCPULoad(List<Job> jobs) {
        // CPU load increases when tasks are executed at the same time
        // we need to know how many are executed simultaneously.
        // 1. sort by start
        // 2. create priority queue on end
        // 3. for each job
        // 4.   remove all finished tasks
        // 5.   add new one
        // 6.   calculate current load
        // 7.   take max between current and max load knows before
        // optimization: store current load and either and - it on removal and + it on adding new one

        jobs.sort(Comparator.comparing(job -> job.start));
        var minHeap = new PriorityQueue<Job>(Comparator.comparing(job -> job.end));
        int currentLoad = 0;
        int maxLoad = 0;

        for (Job j: jobs) {
            while (!minHeap.isEmpty() && minHeap.peek().end <= j.start) {
                currentLoad -= minHeap.poll().cpuLoad;
            }
            minHeap.offer(j);
            currentLoad += j.cpuLoad;
            maxLoad = Math.max(maxLoad, currentLoad);
        }

        return maxLoad;
    }

    public static void main(String[] args) {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println("Maximum CPU load at any time: " + _06_MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println("Maximum CPU load at any time: " + _06_MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println("Maximum CPU load at any time: " + _06_MaximumCPULoad.findMaxCPULoad(input));
    }
}
