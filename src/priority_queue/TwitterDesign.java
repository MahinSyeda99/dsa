package priority_queue;

import java.util.*;

/**
 * https://leetcode.com/problems/design-twitter/
 */
public class TwitterDesign {
    Map<Integer, PriorityQueue<Pair<Integer, Integer>>> tweets;
    Map<Integer, Set<Integer>> followers;
    int timestamp = 0;

    public TwitterDesign() {
        this.tweets = new HashMap<>();
        this.followers = new HashMap<>();
    }

    //O(1)
    public void postTweet(int userId, int tweetId) {
        if(tweets.containsKey(userId)) {
            tweets.get(userId).add(new Pair<Integer, Integer>(tweetId, timestamp++));
        } else {
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
                    if(a.getSecond() > b.getSecond()) {
                        return -1;
                    } else if(a.getSecond() < b.getSecond()) {
                        return 1;
                    }
                    return 0;
                }
            });
            pq.add(new Pair<Integer, Integer>(tweetId, timestamp++));
            tweets.put(userId, pq);
        }
    }

    //O(n)
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Pair<Integer, Integer>> pq;
        if(tweets.containsKey(userId)) {
            pq = new PriorityQueue<>(tweets.get(userId));
        } else {
            pq = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
                    if(a.getSecond() > b.getSecond()) {
                        return -1;
                    } else if(a.getSecond() < b.getSecond()) {
                        return 1;
                    }
                    return 0;
                }
            });
        }
        if(followers.containsKey(userId)) {
            for(Integer followee: followers.get(userId)) {
                if (tweets.containsKey(followee)) {
                    pq.addAll(tweets.get(followee));
                }
            }
        }
        int count = 0;
        List<Integer> result = new ArrayList<>();
        while(pq.size() != 0 && count < 10) {
            result.add(pq.poll().getFirst());
            count++;
        }
        return result;
    }

    //O(1)
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (followers.containsKey(followerId)) {
            followers.get(followerId).add(followeeId);
        } else {
            followers.put(followerId, new HashSet<Integer>(List.of(followeeId)));
        }
    }

    //O(1)
    public void unfollow(int followerId, int followeeId) {
        if(followers.containsKey(followerId)) {
            if(followerId == followeeId) {
                return;
            }
            followers.get(followerId).remove(followeeId);
        }
    }

    class Pair<T, V> {
        T first;
        V second;

        Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return this.first;
        }

        public V getSecond() {
            return this.second;
        }
    }

    public static void main(String[] args) {
        TwitterDesign obj = new TwitterDesign();
//        obj.postTweet(1, 5);
//        List<Integer> param_1 = obj.getNewsFeed(1);
//        System.out.println(param_1);
//        obj.follow(1, 2);
//        obj.postTweet(2, 6);
//        List<Integer> param_2 = obj.getNewsFeed(1);
//        System.out.println(param_2);
//        obj.unfollow(1, 2);
//        List<Integer> param_3 = obj.getNewsFeed(1);
//        System.out.println(param_3);
        obj.postTweet(1, 100);
        obj.follow(1, 1);
        List<Integer> param_1 = obj.getNewsFeed(1);
        System.out.println(param_1);
    }
}
