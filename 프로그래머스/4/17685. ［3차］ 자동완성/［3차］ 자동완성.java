import java.util.*;
class Solution {
    public int solution(String[] words) {
        Arrays.sort(words);
        int count = 0;
        for (int i = 0; i < words.length; ++i) {
            int len = compare(words, i);
            if (len == words[i].length()) count += len;
            else count += (len + 1);
        }
        return count;
    }
    private int compare(String[] words, int i) {
        int len = 0;
        if (i > 0) {
            len = prefix(words[i - 1], words[i]);
        }
        if (i < words.length - 1) {
            len = Math.max(len, prefix(words[i], words[i + 1]));
        }
        return len;
    }
    private int prefix(String s1, String s2) {
        int len = 0;
        for (int i = 0; i < Math.min(s1.length(), s2.length()); ++i) {
            if (s1.charAt(i) == s2.charAt(i)) len++;
            else break;
        }
        return len;
    }
}