package tree;

import java.util.*;
//자료구조 트라이
class Solution {
    class Node {
        String value;
        Map<Integer, Integer> deptCount = new HashMap<>();
        Map<String, Node> stringNodeMap = new HashMap<>();

        public Node(String v) {
            this.value = v;
        }

        public String toString() {
            return value +" + " + deptCount.toString();
        }
    }

    public void insertCountMap(Node n, int length) {
        n.deptCount.putIfAbsent(length, 0);
        n.deptCount.put(length, (n.deptCount.get(length) + 1));
    }

    public Node insertStringMap(Node pNode, String word, int len) {
        int index = word.length() - len;

        if (len != 0 ) {
            String key = Character.toString(word.charAt(index));

            if(!pNode.stringNodeMap.containsKey(key)){
                Node node = new Node(key);
                pNode.stringNodeMap.put(key, node);
                return node;
            }else{
                return pNode.stringNodeMap.get(key);
            }

        } else {
            return null;
        }
    }

    private void makeTri(String[] words, Node head) {

        for (int i = 0; i < words.length; i++) {

            int offset = 0;
            String word = words[i];
            int l = word.length();
            Node pNode = head;
            while ((l - offset) >= 0) {
                int len = l - offset;
                insertCountMap(pNode, len);
                pNode = insertStringMap(pNode, word, len);
                offset++;
            }
        }
    }

    public int find(Node head, String query) {
        String[] q = query.split("");
        int count = (int) query.chars().filter(i -> (char) i == '?').count();
        Node node = head;
        int index = 0;
        while (!q[index].equals("?")) {
            if (node.stringNodeMap.containsKey(q[index])) {
                node = node.stringNodeMap.get(q[index]);
                index++;
            } else {
                return 0;
            }
        }


        if(node.deptCount.get(count) != null ){
            return node.deptCount.get(count);
        }else{
            return 0;
        }
    }

    public int[] solution(String[] words, String[] queries) {
        Node head = new Node("");
        Node tail = new Node("");

        makeTri(words, head);

        String[] wordsreverse = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            StringBuilder stringBuilder = new StringBuilder(words[i]);
            stringBuilder.reverse();
            wordsreverse[i] = stringBuilder.toString();
        }

        makeTri(wordsreverse, tail);
        List<Integer> result = new ArrayList<>();

        Arrays.stream(queries).forEach(i -> {
            if (i.charAt(0) == '?') {
                StringBuilder sb = new StringBuilder(i);
                String r = sb.reverse().toString();
                result.add(find(tail, r));
            } else {
                result.add(find(head, i));
            }
        });

        return result.stream().mapToInt(i -> i).toArray();
    }


}
