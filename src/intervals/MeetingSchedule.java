package intervals;

import java.util.*;

/**
 * https://neetcode.io/problems/meeting-schedule-ii
 */
public class MeetingSchedule {

    //TODO - Need to check this accuracy and which cases it might fail
//    public int minMeetingRooms(List<Interval> intervals) {
//        //Sort based on end times
//        Collections.sort(intervals, new Comparator<Interval>(){
//            @Override
//            public int compare(Interval a, Interval b) {
//                return a.end - b.end;
//            }
//        });
//        //Initialize count with total number of intervals
//        int count = intervals.size();
//        //Index to store the lowest end time current occupied meeting interval
//        int index = 0;
//        for(int i = 1; i < intervals.size(); i++) {
//            //If current intervals start time is greater than lowest current occupied meeting interval,
//            //then one meeting room is now available. So, the current meeting can occupy that room. So, decrease the count value
//            if(intervals.get(i).start >= intervals.get(index).end) {
//                count--;
//                index++;
//            }
//        }
//        return count;
//    }

    public int minMeetingRooms(List<Interval> intervals) {
        //Sort based on start times
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        //Create a priority queue which will keep min heap of end times
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });
        for(Interval interval : intervals) {
            //If the current meeting's start time is equal or greater than the end time of peek of pq, then that meeting room is available, so remove it from queue
            if(!pq.isEmpty() && pq.peek().end <= interval.start) {
                pq.poll();
            }
            //Add current interval to queue
            pq.add(interval);
        }
        return pq.size();
    }

    public static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        MeetingSchedule meetingSchedule = new MeetingSchedule();
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0, 40));
        intervals.add(new Interval(5, 10));
        intervals.add(new Interval(15, 20));
        //O/p should be 2
        System.out.println(meetingSchedule.minMeetingRooms(intervals));
    }
}
