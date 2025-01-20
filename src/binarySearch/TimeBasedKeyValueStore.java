package binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeBasedKeyValueStore {

    private HashMap<String, List<Pair<String, Integer>>> mp;

    public TimeBasedKeyValueStore() {
        mp = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (mp.containsKey(key)) {
            mp.get(key).add(new Pair<String, Integer>(value, timestamp));
        } else {
            List<Pair<String, Integer>> l = new ArrayList<>();
            l.add(new Pair<String, Integer>(value, timestamp));
            mp.put(key, l);
        }
    }

    public String get(String key, int timestamp) {
        if (!mp.containsKey(key)) {
            return "";
        } else {
            List<Pair<String, Integer>> list = mp.get(key);
            String ans = "";
            int low = 0;
            int high = list.size() - 1;
            //Why binary search? Because its given, when set is called, timestamp is always greater than prev timestamp set.
            //So ascending order of timestamp values
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid).getValue() == timestamp) {
                    return list.get(mid).getKey();
                } else if (list.get(mid).getValue() < timestamp) {
                    //We found value <= timestamp, but we value for larget timestamp_prev
                    ans = list.get(mid).getKey();
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return ans;
        }
    }

    class Pair<T, V> {
        T key;
        V value;

        Pair(T first, V second) {
            this.key = first;
            this.value = second;
        }

        public T getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }
}
