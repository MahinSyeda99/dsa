package interview_solarwinds;

import java.util.Map;

public interface ElectionStrategy {

    String findWinner(Map<String, Integer> mp);
}
