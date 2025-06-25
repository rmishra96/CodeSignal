package com.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class EncryptAndDecryptString {

    private Map<Character, String> map = new HashMap<>();
    private Map<String, Integer> cnt = new HashMap<String, Integer>();

    public EncryptAndDecryptString(char[] keys, String[] values, String[] dictionary) {
        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], values[i]);
        }
        for (String w : dictionary) {
            w = encrypt(w);
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
    }



    public String encrypt(String words){
        StringBuilder sb = new StringBuilder();
        for(char c: words.toCharArray()){
            if(!map.containsKey(c)){
                return "";
            }
            sb.append(map.get(c));
        }
        return sb.toString();
    }
    public int decrypt(String words){
        return cnt.getOrDefault(words,0);
    }

    public static void main(String[] args) {
//        EncryptAndDecryptString obj = new EncryptAndDecryptString([],[],[]);
//        String param1 = obj.encrypt("Encrypter");
//        int param2 = obj.decrypt(", \"encrypt\"");
//        System.out.println(param1 + "***********" + param2);
    }
}
