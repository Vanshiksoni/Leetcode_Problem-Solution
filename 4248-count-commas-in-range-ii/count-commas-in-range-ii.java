class Solution {
    public long countCommas(long n) {
        long start = 1000;
        long commas = 1;
        long result = 0;

        while (start <= n) {

            long end = start * 1000 - 1;

            if (end > n) {
                end = n;
            }

            long count = end - start + 1;

            result += count * commas;

            start *= 1000;
            commas++;
        }

        return result;
    }
}