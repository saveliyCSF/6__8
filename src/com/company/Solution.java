package com.company;

import java.util.*;

public class Solution{
    public ArrayList<Integer> solution(int[] arr){
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        addAllToMap(map, arr);

        int tmp_size = Integer.MIN_VALUE, tmp = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) { // работает за время O(n log(n))
            if(map.get(arr[i]).size() >= tmp_size && arr[i] > tmp){
                System.out.println(arr[i] + " " + map.get(arr[i]).size());
                tmp_size = map.get(arr[i]).size();
                tmp = arr[i];
            }
        }
        return map.get(tmp);
    }

    private void addAllToMap(Map<Integer, ArrayList<Integer>> map, int[] arr){ // работает за время  O(log(n))
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
    }
}
