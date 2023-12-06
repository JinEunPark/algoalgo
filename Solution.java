import java.util.*;

//네트워크 연결
public class Solution {
    Set<Integer> s = new HashSet<>();

    public int getParent(int[] parent, int p) {
        while (parent[p] != -1) {
            p = parent[p];
        }
        return p;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers.length; j++) {
                if (computers[i][j] == 1 && getParent(parent,i) != getParent(parent,j) && i != j) {
                    parent[getParent(parent, j)] = getParent(parent, i);
                    computers[i][j] = 0;
                    computers[j][i] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int p = i;
            p = getParent(parent, p);
            s.add(p);
        }

        return s.size();
    }
}




