import java.util.*;

class Solution {

    private int index;

    public List<String> braceExpansionII(String expression) {
        index = 0;

        Set<String> result = parseExpression(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles concatenation
    private Set<String> parseExpression(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length() && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> part;

            if (s.charAt(index) == '{') {
                index++; // skip {

                part = parseUnion(s);

                index++; // skip }
            } else {
                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(index)));
                index++;
            }

            result = concatenate(result, part);
        }

        return result;
    }

    // Handles comma-separated union
    private Set<String> parseUnion(String s) {
        Set<String> result = new HashSet<>();

        while (true) {
            Set<String> part = parseExpression(s);
            result.addAll(part);

            if (index < s.length() && s.charAt(index) == ',') {
                index++; // skip comma
            } else {
                break;
            }
        }

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}