package LeetCode_Easy_Problems;

import java.util.ArrayList;
import java.util.List;

class Pascals_Triangle_II {
    private List<Integer> getRow(int targetRow) {

        List<Integer> list = new ArrayList<>();

        long prevNum = 1;

        list.add(1);

        for (int i = 1; i <= targetRow; i++) {
            long nextNum = prevNum * (targetRow - i + 1) / i;
            list.add((int) nextNum);
            prevNum = nextNum;
        }

        return list;

    }
}