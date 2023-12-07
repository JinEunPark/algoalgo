package DFSstack;

import javax.xml.stream.events.StartDocument;
import java.util.*;

// https://school.programmers.co.kr/tryouts/71909/challenges
class Solution {

    public class Word {
        String w;
        Set<Integer> visited = new HashSet<>();

        public Word(String w, Set<Integer> visited) {
            this.w = w;
            this.visited.addAll(visited);
        }
    }

    public boolean check(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) count++;

        }
        return count == 1;
    }

    public int solution(String begin, String target, String[] words) {


        int answer = Integer.MAX_VALUE;
        int c = (int) Arrays.stream(words).filter(i -> i.equals(target)).count();
        if (c == 0) return 0;

        Stack<Word> st = new Stack<>();
        st.add(new Word(begin, new HashSet<>()));

        while (!st.isEmpty()) {
            Word cur = st.pop();
            if (cur.w.equals(target) && answer > cur.visited.size()) {
                answer = cur.visited.size();
                continue;
            }
            if (cur.visited.size() >= answer)
                continue;

            for (int i = 0; i < words.length; i++) {
                if (!(cur.visited.contains(i)) && check(cur.w, words[i])) {
                    Set<Integer> vis = new HashSet<>();
                    vis.addAll(cur.visited);
                    vis.add(i);
                    st.push(new Word(words[i], vis));
                }
            }

        }
        if (answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
}
