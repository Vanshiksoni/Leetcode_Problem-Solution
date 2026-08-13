import java.util.*;

class Solution {

    // Represents a run [start, end] having one character
    static class Run {
        int start, end;

        Run(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int length() {
            return end - start + 1;
        }
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();
        int k = queryIndices.length;

        char[] str = s.toCharArray();

        // Runs sorted by their starting index
        TreeMap<Integer, Run> runs = new TreeMap<>();

        // Multiset of run lengths
        TreeMap<Integer, Integer> lengths = new TreeMap<>();

        // Build initial runs
        int start = 0;

        for (int i = 1; i <= n; i++) {

            if (i == n || str[i] != str[start]) {
                Run run = new Run(start, i - 1);
                runs.put(start, run);
                addLength(run.length(), lengths);

                start = i;
            }
        }

        int[] answer = new int[k];

        for (int q = 0; q < k; q++) {

            int index = queryIndices[q];
            char newChar = queryCharacters.charAt(q);

            // No actual change
            if (str[index] == newChar) {
                answer[q] = lengths.lastKey();
                continue;
            }

            /*
             * Find the run containing index.
             */
            Map.Entry<Integer, Run> entry = runs.floorEntry(index);
            Run oldRun = entry.getValue();

            removeRun(oldRun, runs, lengths);

            /*
             * Split the old run into:
             *
             * [oldStart ... index-1]
             * [index ... index]
             * [index+1 ... oldEnd]
             */
            if (oldRun.start <= index - 1) {
                addRun(
                    new Run(oldRun.start, index - 1),
                    runs,
                    lengths
                );
            }

            if (index + 1 <= oldRun.end) {
                addRun(
                    new Run(index + 1, oldRun.end),
                    runs,
                    lengths
                );
            }

            str[index] = newChar;

            /*
             * Insert the new single-character run.
             */
            Run newRun = new Run(index, index);

            /*
             * Merge with left run if same character.
             */
            Map.Entry<Integer, Run> leftEntry = runs.lowerEntry(index);

            if (leftEntry != null) {
                Run left = leftEntry.getValue();

                if (str[left.start] == newChar &&
                    left.end + 1 == index) {

                    removeRun(left, runs, lengths);

                    newRun.start = left.start;
                }
            }

            /*
             * Merge with right run if same character.
             */
            Map.Entry<Integer, Run> rightEntry = runs.higherEntry(index);

            if (rightEntry != null) {
                Run right = rightEntry.getValue();

                if (str[right.start] == newChar &&
                    index + 1 == right.start) {

                    removeRun(right, runs, lengths);

                    newRun.end = right.end;
                }
            }

            addRun(newRun, runs, lengths);

            answer[q] = lengths.lastKey();
        }

        return answer;
    }

    private void addRun(
            Run run,
            TreeMap<Integer, Run> runs,
            TreeMap<Integer, Integer> lengths) {

        runs.put(run.start, run);
        addLength(run.length(), lengths);
    }

    private void removeRun(
            Run run,
            TreeMap<Integer, Run> runs,
            TreeMap<Integer, Integer> lengths) {

        runs.remove(run.start);
        removeLength(run.length(), lengths);
    }

    private void addLength(
            int length,
            TreeMap<Integer, Integer> lengths) {

        lengths.put(
            length,
            lengths.getOrDefault(length, 0) + 1
        );
    }

    private void removeLength(
            int length,
            TreeMap<Integer, Integer> lengths) {

        int count = lengths.get(length);

        if (count == 1) {
            lengths.remove(length);
        } else {
            lengths.put(length, count - 1);
        }
    }
}