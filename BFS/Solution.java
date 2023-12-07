package BFS;

import java.util.*;

class Solution {
    public static class Point {
        int x;
        int y;
        int dept;

        public Point(int x, int y, int dept) {
            this.x = x;
            this.y = y;
            this.dept = dept;
        }

        public String toString(){
            return this.x + " " + this.y;
        }
    }

    public boolean check(int[][] maps, int x, int y) {
        if (0 <= x && x < maps.length && 0 <= y && y < maps[0].length) {
            if (maps[x][y] == 1 ) return true;
        }
        return false;
    }

    public int solution(int[][] maps) {

        int answer =  -1;


        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Point> q = new LinkedList<>();
        Point start = new Point(0, 0, 1);
        q.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if(cur.x == maps.length -1 && cur.y == maps[0].length -1){
                return cur.dept;
            }
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(check(maps,nx,ny)){
                    Point np = new Point(nx,ny, cur.dept+1);
                    maps[nx][ny] = 0;
                    q.add(np);
                }
            }

        }
        return answer;
    }
}
