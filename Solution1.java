import java.util.*;

class Solution1 {
    Map<String, Integer> results = new HashMap<>();

    public void makeRecord(Map<String, Integer> fMap, String[][] giftArray, int[][] record, Map<String, int[]> gIndex){
        for(int i = 0; i < giftArray.length; i++){
            gIndex.get(giftArray[i][0])[0]++;
            gIndex.get(giftArray[i][1])[1]++;
            int t =  fMap.get(giftArray[i][0]);
            int r = fMap.get(giftArray[i][1]);
            record[t][r]++;
        }

        for(String fname: gIndex.keySet()){
            int[] k = gIndex.get(fname);
            k[2] = k[0] - k[1];
        }
    }

    public void calculate(Map<String, Integer> fMap, String[][] giftArray, int[][] record, Map<String, int[]> gIndex, String[] friends ){
        Arrays.stream(friends).forEach(i -> results.putIfAbsent(i,0));

        for(int i = 0; i < friends.length; i++){
            for(int j = i+1; j < friends.length; j++){
                if(i != j) {
                    int x = 0;
                    int y = 0;
                    String f1 = friends[i];
                    String f2 = friends[j];
                    int if1 = fMap.get(f1);
                    int if2 = fMap.get(f2);
                    int f1t = record[if1][if2];// 1->2
                    int f2t = record[if2][if1];//2->1

                    if (f1t > f2t) {
                        x++;
                    } else if (f2t > f1t) {
                        y++;
                    } else {
                        int xg = gIndex.get(f1)[2];
                        int yg = gIndex.get(f2)[2];
                        if (xg > yg) {
                            x++;
                        } else if (yg > xg) {
                            y++;
                        }
                    }
                    results.put(f1,results.get(f1) + x);
                    results.put(f2,results.get(f2) + y);
                }
            }
        }
    }
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        Map<String, Integer> fMap = new HashMap<>();
        Map<String, int[]> gIndex = new HashMap<>();
        String[][] giftArray = new String[gifts.length][2];

        for(int i = 0; i < friends.length; i++){
            fMap.putIfAbsent(friends[i],i);
            gIndex.put(friends[i], new int[3]);
        }

        StringTokenizer st;
        for(int i = 0; i< gifts.length; i++){
            st = new StringTokenizer(gifts[i]);
            giftArray[i][0] = st.nextToken();
            giftArray[i][1] = st.nextToken();
        }

        int [][] record = new int[friends.length][friends.length];
        makeRecord(fMap,giftArray,record, gIndex);
        calculate(fMap,giftArray,record,gIndex,friends);

        for(Map.Entry<String,Integer> en: results.entrySet()){
            int v = en.getValue();
            if(answer < v){
                answer = v;
            }
        }


        return answer;
    }
}