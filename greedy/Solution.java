package greedy;

import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] student = new int[n];
        Arrays.fill(student,1);
        for(int i: lost){
            student[i] = 0;
        }
        for(int i: reserve){
            student[i] +=1;
        }

        for (int i = 1; i < student.length; i++) {
            if(student[i] == -1 && student[i-1] == 2){
               student[i] = 1;
               student[i-1] = 1;
            }

            if(student[i] == 2 && student[i - 1] == -1){
                student[i] = student[i-1] = 1;
            }
        }

        return (int)Arrays.stream(student).filter(i -> i >=1).count();
    }
}
