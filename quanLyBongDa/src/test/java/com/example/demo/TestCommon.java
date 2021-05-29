package com.example.demo;

import java.util.List;

public class TestCommon {

    public static boolean equalList(List l1, List l2) {
        if (l1 == l2) return true;
        if (l1 == null || l2 == null) return false;
        if (l1.equals(l2)) return true;

        if (l1.size() != l2.size()) return false;
        for (int i=0; i<l1.size(); i++) {
            Object o1 = l1.get(i);
            Object o2 = l2.get(i);

            if (o1 != o2 && (o1 == null || o2 == null)) return false;
            if (o1 != null && o2 != null && !l1.equals(l2)) return false;
        }

        return true;
    }

}
