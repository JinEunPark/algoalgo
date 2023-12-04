package implement;

import java.util.*;
//구현 문제
class Solution3 {
    public int calculate(char h, char put, Map<Character, List<Integer>> keypad) {
        List<Integer> putP = keypad.get(put);
        List<Integer> hP = keypad.get(h);

        return Math.abs(putP.get(0) - hP.get(0)) + Math.abs(putP.get(1) - hP.get(1));
    }


    public void touch(Set<Character> setL, Set<Character> setR, Set<Character> setCenter,
                      String hand, StringBuilder sb, int[] numbers, Map<Character, List<Integer>> keypad) {
        char r = '#', l = '*';
        for (int i = 0; i < numbers.length; i++) {
            char p = String.valueOf(numbers[i]).charAt(0);

            char pr = r;
            char pl = l;
            if (setL.contains(p)) {
                l = p;
                sb.append("L");
            } else if (setR.contains(p)) {
                r = p;
                sb.append("R");
            } else if (setCenter.contains(p)) {
                int rdis = calculate(r, p, keypad);
                int ldis = calculate(l, p, keypad);

                if (rdis > ldis) {
                    l = p;
                    sb.append("L");
                } else if (rdis < ldis) {
                    sb.append("R");
                    r = p;
                } else if (rdis == ldis) {
                    if (hand.equals("right")) {
                        sb.append("R");
                        r = p;
                    } else {
                        l = p;
                        sb.append("L");
                    }
                }

            }

        }
    }


    public String solution(int[] numbers, String hand) {
        String pad = "123456789*0#";
        Set<Character> setL = new HashSet<>() {{
            add('1');
            add('4');
            add('7');
            add('*');
        }};
        Set<Character> setR = new HashSet<>() {{
            add('3');
            add('6');
            add('9');
            add('#');
        }};
        Set<Character> setCenter = new HashSet<>() {{
            add('2');
            add('5');
            add('8');
            add('0');
        }};


        Map<Character, List<Integer>> keypad = new HashMap<>();
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                char num = pad.charAt(index);
                List<Integer> location = new ArrayList<>();
                location.add(i);
                location.add(j);
                keypad.put(num, location);
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        touch(setL, setR, setCenter, hand, sb, numbers, keypad);
        String answer = sb.toString();
        return answer;
    }

}