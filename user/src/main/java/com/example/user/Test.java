package com.example.user;

import java.util.HashMap;

/**
 * @ClassName Test
 * @Author yanyu
 * @Date 2024/11/19 10:23
 * @Version 1.0
 * @Description TODO
 **/
public class Test {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(map.put("1","a"));
        System.out.println(map.put("2","b"));
        System.out.println(map.put("1","A"));
    }
}
