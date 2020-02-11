package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> sameNum = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sameNum.putIfAbsent(arr[i], new ArrayList<>());
            sameNum.get(arr[i]).add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        q.offer(0);
        visited[0] = true;
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int curr = q.poll();
                if (curr == arr.length - 1) {
                    return step;
                }
                List<Integer> next = sameNum.get(arr[curr]);
                next.add(curr - 1); next.add(curr + 1);
                for (int i = next.size() - 1; i >= 0; i--) {
                    int index = next.get(i);
                    if (index >= 0 && index < arr.length && !visited[index]) {
                        visited[index] = true;
                        q.offer(index);
                    }
                }
                next.clear();
            }
            step++;
        }
        return -1;
    }
}