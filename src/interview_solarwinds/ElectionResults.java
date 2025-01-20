package interview_solarwinds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionResults {
    private List<String> votes;

    public ElectionResults(List<String> votes) {
        this.votes = votes;
    }

    public String findWinner(String strategy) {
        Map<String, Integer> frequencyCounts = new HashMap<>();
        for (String candidate : votes) {
            frequencyCounts.put(candidate, frequencyCounts.getOrDefault(candidate, 0) + 1);
        }
        ElectionStrategy electionStrategy;
        if (strategy.equals("LEXICOGRAPHICAL")) {
            electionStrategy = new LexicoGraphicalStrategy();
        } else {
            electionStrategy = new PopularityStrategy();
        }
        return electionStrategy.findWinner(frequencyCounts);
    }

    public static void main(String[] args) {
        List<String> votes = new ArrayList<>();
        votes.add("A");
        votes.add("A");
        votes.add("B");
        votes.add("B");
        votes.add("C");
        ElectionResults electionResults = new ElectionResults(votes);
        System.out.println("Winner for lexicographical strategy is: " + electionResults.findWinner("LEXICOGRAPHICAL"));
        System.out.println("Winner for popularity strategy is: " + electionResults.findWinner("POPULARITY"));
    }
}
