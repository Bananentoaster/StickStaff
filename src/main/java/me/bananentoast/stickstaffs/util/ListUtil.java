package me.bananentoast.stickstaffs.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static List<String> getShorterList(List<String> list, String preString) {
        List<String> shorterList = new ArrayList<>();
        for (String str : list) {
            if (str.toLowerCase().startsWith(preString.toLowerCase()))
                shorterList.add(str);
        }
        return shorterList;
    }

}
