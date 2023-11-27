import java.util.ArrayList;
import java.util.List;

class Solution {
    public void getRange(int start, int end, List<Integer> deck, int[] cards){
        for (int i = start; i < end; i++) {
            deck.add(i);
        }
    }

    public int solution(int coin, int[] cards) {
        int answer = 0;
        int index = 0;
        int n = cards.length;
        List<Integer> deck = new ArrayList<>();
        int limit = cards.length + 1;

        //init
        int first = n/3-1;
        index = first;
        getRange(0,first,deck,cards);







        return answer;
    }
}
