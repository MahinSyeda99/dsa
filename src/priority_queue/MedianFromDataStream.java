package priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFromDataStream {
    //Maintain 2 priority queues for left and right halves of stream
    //left priority queue will be a max heap, to easily get the max, which will be the median if odd, or mid value to calculate for even case
    private PriorityQueue<Integer> leftMaxPq;
    //right priority queue will be a min heap, to easily get the min, mid + 1 value to calculate for even case
    private PriorityQueue<Integer> rightMinPq;

    public MedianFromDataStream() {
        this.leftMaxPq = new PriorityQueue<>(Collections.reverseOrder());
        this.rightMinPq = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //If num is less than leftHalf max, then it belongs to left half.
        if (leftMaxPq.isEmpty() || num <= leftMaxPq.peek()) {
            leftMaxPq.add(num);
        } else {
            //If value is greater than leftHalf peek, then it belongs to right half
            rightMinPq.add(num);
        }

        //Re-order if leftmax size is less than rightmin size
        if (leftMaxPq.size() < rightMinPq.size()) {
            leftMaxPq.add(rightMinPq.poll());
        } else if (leftMaxPq.size() > rightMinPq.size() + 1) {
            //Re-order if leftmax size is more than rightmin size by 2
            rightMinPq.add(leftMaxPq.poll());
        }
    }

    public double findMedian() {
        if (leftMaxPq.size() == rightMinPq.size()) {
            if (leftMaxPq.isEmpty()) {
                return 0;
            }
            return ((double) leftMaxPq.peek() + (double) rightMinPq.peek()) / 2.0;
        } else {
            return leftMaxPq.peek();
        }
    }

    public static void main(String[] args) {
        MedianFromDataStream medianFromDataStream = new MedianFromDataStream();
        medianFromDataStream.addNum(1);
        medianFromDataStream.addNum(2);
        //Expected output: 1.5
        System.out.println(medianFromDataStream.findMedian());
        medianFromDataStream.addNum(3);
        //Expected output: 2
        System.out.println(medianFromDataStream.findMedian());
    }
}
