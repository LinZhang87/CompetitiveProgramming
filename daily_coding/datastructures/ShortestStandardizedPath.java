package datastructures;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @author lzhang
 * @since 7/6/19
 */

/*
This problem was asked by Quora.

Given an absolute pathname that may have . or .. as part of it, return the shortest standardized path.

For example, given "/usr/bin/../bin/./scripts/../", return "/usr/bin/".
 */
public class ShortestStandardizedPath {
    public static String shortestFilePath(String path) {
        ArrayDeque<Character> dq = new ArrayDeque<>();
        int i = 0, count = 0;
        while(i < path.length()) {
            if(path.charAt(i) != '.') {
                if(count > 0) {
                    dq.pollLast();
                    if(count == 2) {
                        while(dq.size() > 0 && dq.peekLast() != '/') {
                            dq.pollLast();
                        }
                        if(dq.size() > 0) {
                            dq.pollLast();
                        }
                    }
                }
                count = 0;
                dq.addLast(path.charAt(i));
            }
            else {
                count++;
            }
            i++;
        }
        if(count > 0) {
            dq.pollLast();
            if(count == 2) {
                while(dq.size() > 0 && dq.peekLast() != '/') {
                    dq.pollLast();
                }
                if(dq.size() > 0) {
                    dq.pollLast();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(dq.size() > 0) {
            sb.append(dq.pollFirst());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static String shortestFilePath2(String path) {
        //leading empty string is not discarded; only trailing empty strings are discarded
        String[] dirs = path.split("/");
        ArrayDeque<String> dq = new ArrayDeque<>();

        for(int i = 0; i < dirs.length; i++) {
            //make sure when there are more .. than allowed, do nothing as the path is already at root
            if(dirs[i].equals("..") && dq.size() > 1) {
                dq.pollLast();
            }
            else if(!dirs[i].equals(".")) {
                dq.addLast(dirs[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(dq.size() > 0) {
            sb.append(dq.pollFirst());
            sb.append("/");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(shortestFilePath("/usr/bin/../bin/./scripts/../"));
        System.out.println(shortestFilePath("/usr/bin/../bin/./scripts/.."));
        System.out.println(shortestFilePath("/usr/bin/../.."));
        System.out.println(shortestFilePath("/usr/bin/../../"));
        System.out.println(shortestFilePath("/usr/././bin/../../"));

        System.out.println(shortestFilePath2("/usr/bin/../bin/./scripts/../"));
        System.out.println(shortestFilePath2("/usr/bin/../bin/./scripts/.."));
        System.out.println(shortestFilePath2("/usr/bin/../.."));
        System.out.println(shortestFilePath2("/usr/bin/../../"));
        System.out.println(shortestFilePath2("/usr/././bin/../../"));
    }
}
