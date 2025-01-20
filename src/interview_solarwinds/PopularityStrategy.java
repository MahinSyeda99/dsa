package interview_solarwinds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PopularityStrategy implements ElectionStrategy{
    private List<String> popularity;

    public PopularityStrategy() {
        this.popularity = new ArrayList<>();
        popularity.add("C");
        popularity.add("B");
        popularity.add("A");
        popularity.add("D");
    }

    @Override
    public String findWinner(Map<String, Integer> mp) {
        int max = 0;
        String winner = "";
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            if (entry.getValue() == max) {
                winner = findPopularCandidate(entry.getKey(), winner);
            } else if (entry.getValue() > max) {
                max = entry.getValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }

    private String findPopularCandidate(String currentMax, String winner) {
        for (String popular : popularity) {
            if (popular.equals(currentMax)) {
                return currentMax;
            } else if (popular.equals(winner)) {
                return winner;
            }
        }
        return winner;
    }
}
