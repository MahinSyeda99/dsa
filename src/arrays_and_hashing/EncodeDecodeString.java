package arrays_and_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeDecodeString {

    public String encode(List<String> strs) {
        if (strs.isEmpty()) {
            return "\\f";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (str.isEmpty()) {
                sb.append("\\t").append("\n");
            } else {
                sb.append(str).append("\n");
            }
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        if (str.equals("\\f")) {
            return new ArrayList<String>();
        }
        String[] strs = str.split("\n");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("\\t")) {
                strs[i] = "";
            }
        }
        return Arrays.asList(strs);
    }
}
