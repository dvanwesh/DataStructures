package com.string;


import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSubString {

    public static void main(String[] args) {
        String[] ar = new String[]{"0","abc"};
        Arrays.sort(ar);
        System.out.println("apple".compareTo("bannana"));
    }
    public int execute(){
        try {
            System.out.println("running try");
            throw new Exception("abc");
        } catch (Exception e){
            System.out.println("running catch");

        }
        return 123;
    }
    private int diff(String s){
        String start = "123";
        return (int) IntStream.range(0, s.length()).filter(i->s.charAt(i)!=start.charAt(i)).count();
    }

    private int[] diff(){
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 2, 3};
        Set<Integer> ar1 = new HashSet(Arrays.asList(nums1));
        return Arrays.stream(nums2).filter(n->ar1.contains(n)).toArray();
    }
}
