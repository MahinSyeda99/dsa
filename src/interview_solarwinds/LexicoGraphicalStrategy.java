package interview_solarwinds;

import java.util.Map;

public class LexicoGraphicalStrategy implements ElectionStrategy{

    public LexicoGraphicalStrategy() {
    }

    @Override
    public String findWinner(Map<String, Integer> mp) {
        int max = 0;
        String winner = "";
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            if (entry.getValue() == max) {
                winner = findLexicoGraphicallySmallest(entry.getKey(), winner);
            } else if (entry.getValue() > max) {
                max = entry.getValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }

    private String findLexicoGraphicallySmallest(String currentMax, String winner) {
        int comparison = currentMax.compareTo(winner);
        if (comparison < 0) {
            return currentMax;
        } else {
            return winner;
        }
    }
}
