import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution2 {
    List<int[]> com  = new ArrayList<>();
    public void combination(int[] input, int[] combination, int count, int start) {
        if (count == combination.length) {
            com.add(combination.clone());
            return;
        }

        for (int i = start; i < input.length; i++) {
            combination[count] = input[i];
            combination(input, combination, count + 1, i + 1);
        }
    }

    public void dfs(List<List<Integer>> d1, int d, int count, int v, List<Integer> result){
        if(count == d1.size()){
            result.add(v);
            return;
        }

        for (int i = 0; i < 6; i++) {
            dfs(d1,d+1,count+1,v+d1.get(d).get(i),result);
        }
    }
    public List<Integer> cal(List<List<Integer>> d1){
        List<Integer> re1 = new ArrayList<>();
        dfs(d1,0,0,0,re1);
        return re1;
    }
    public int[] calculate(List<int[]> com, int[][] dice, int[] result){

        for(int i = 0; i < com.size()/2; i++){
            int j = com.size() -1 - i;
            List<List<Integer>> l1 = new ArrayList<>();
            List<List<Integer>> l2 = new ArrayList<>();

            for(int k = 0; k < com.get(0).length; k++){
                int d = com.get(i)[k];
                int d2 = com.get(j)[k];
                List<Integer> ll1 = new ArrayList<>(Arrays.stream(dice[d]).boxed().collect(Collectors.toList()));
                List<Integer> ll2 = new ArrayList<>(Arrays.stream(dice[d2]).boxed().collect(Collectors.toList()));
                l1.add(ll1);
                l2.add(ll2);
            }

            List<Integer> result1 = cal(l1);
            List<Integer> result2 = cal(l2);

            int x = 0;
            int y = 0;

            for(int k = 0; k < result1.size(); k++){
                for(int l = 0; l < result1.size(); l++) {
                    if(result1.get(k) > result2.get(l)){
                        x++;
                    }else if(result1.get(k) < result2.get(l)){
                        y++;
                    }
                }
            }

            result[i] = x;
            result[j] = y;

        }

        return result;
    }
    public int[] solution(int[][] dice) {

        int[] answer = {};
        int[] range = new int[dice.length];

        for(int i = 0; i < dice.length; i++){
            range[i] = i;
        }
        combination(range, new int[dice.length/2],0,0);
        int[] result = new int[com.size()];

        calculate(com,dice,result);

        int max = 0;
        int index = 0;
        for(int i = 0; i < result.length; i++){
            if(max < result[i]){
                max = result[i];
                index = i;
            }
        }
        answer = com.get(index);
        for (int i = 0; i < answer.length; i++) {
            answer[i] +=1;
        }

        return answer;
    }
}