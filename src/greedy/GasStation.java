package greedy;

import java.io.*;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GasStation {

    //O(n) greedy
    public int canCompleteCircuitGreedy(int[] gas, int[] cost) {
        // Sum of difference between gas and cost at each station
        int total_surplus = 0;
        //Sum of difference between gas and cost at stations from the index where we can travel around to form circuit
        int surplus = 0;
        //Index
        int index = 0;
        for(int i = 0; i < gas.length; i++) {
            total_surplus = total_surplus + gas[i] - cost[i];
            surplus = surplus + gas[i] - cost[i];
            //If surplus from index has become negative, then we need to find new index, which will be just the next index
            if (surplus < 0) {
                surplus = 0;
                index = i + 1;
            }
        }
        //The solution exists only if total_surplus >= 0, i.e sum of difference between gas and cost is >= 0
        return total_surplus >= 0 ? index : -1;
    }

    //O(n^2) Brute Force
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0;
        int j = -1;
        while(i < gas.length) {
            if(j == -1) {
                if(gas[i] - cost[i] >= 0) {
                    j = i;
                    int k = j;
                    int remGas = 0;
                    while(k < gas.length) {
                        if(remGas + gas[k] - cost[k] >= 0) {
                            remGas = remGas + gas[k] - cost[k];
                            k++;
                        } else {
                            j = -1;
                            break;
                        }
                    }
                    if(k >= gas.length) {
                        k = 0;
                        while(k <= j) {
                            if(k == j) {
                                return j;
                            }
                            if(remGas + gas[k] - cost[k] >= 0) {
                                remGas = remGas + gas[k] - cost[k];
                                k++;
                            } else {
                                j = -1;
                                break;
                            }
                        }
                    }
                }
            }
            i++;
        }
        return j;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/msyeda/Documents/dsa/src/greedy/gas.txt"));
        List<Integer> gasStations = Stream.of(bufferedReader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        bufferedReader = new BufferedReader(new FileReader("/Users/msyeda/Documents/dsa/src/greedy/cost.txt"));
        List<Integer> cost = Stream.of(bufferedReader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());

        GasStation gasStation = new GasStation();
        Instant instant = Instant.now();
        System.out.println(gasStation.canCompleteCircuit(gasStations.stream().mapToInt(Integer::intValue).toArray(), cost.stream().mapToInt(Integer::intValue).toArray()));
        System.out.println(Instant.now().toEpochMilli() - instant.toEpochMilli());

        instant = Instant.now();
        System.out.println(gasStation.canCompleteCircuitGreedy(gasStations.stream().mapToInt(Integer::intValue).toArray(), cost.stream().mapToInt(Integer::intValue).toArray()));
        System.out.println(Instant.now().toEpochMilli() - instant.toEpochMilli());
    }
}
