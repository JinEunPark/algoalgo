package DFSstack;

import java.util.*;

class Solution {
//스택을 이용한 DFS
    public static class Calculation{
        int index;
        int value;
        public Calculation(int index, int value){
            this.index = index;
            this.value = value;
        }
    }
    public int solution(int[] numbers, int target){

        int answer = 0;
        int index = 0;
        Stack<Calculation> st = new Stack<>();
        st.push(new Calculation(0, 0));
        while(!st.isEmpty()){
            Calculation cur = st.pop();
            if(cur.index == numbers.length ) {
                if (cur.value == target) answer++;
                continue;
            }
            st.push(new Calculation(cur.index+1, cur.value + numbers[cur.index ]));
            st.push(new Calculation(cur.index+1, cur.value - numbers[cur.index]));
        }
        return answer;
    }
}
