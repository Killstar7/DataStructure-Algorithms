package Miscellaneous.LC_1622_Fancy_Sequence;

import java.util.ArrayList;

public class Fancy_Sequence {
    public static void main(String[] args) {
        Fancy obj = new Fancy();
        obj.append(2);
        obj.addAll(3);
        obj.append(7);
        obj.multAll(2);
        int idx = obj.getIndex(0);
        System.out.println(idx);
        obj.addAll(3);
        obj.append(10);
        obj.multAll(2);
        idx = obj.getIndex(0);
        System.out.println(idx);
        idx = obj.getIndex(1);
        System.out.println(idx);
        idx = obj.getIndex(2);
        System.out.println(idx);


    }

    static class Fancy {
        ArrayList<Long> list;
        int MOD = (int) (Math.pow(10, 9) + 7);

        public Fancy() {
            list = new ArrayList<>();
        }

        public void append(int val) {

            list.add((long) val);
        }

        public void addAll(int inc) {
            for (int i = 0; i < list.size(); i++) {

                list.set(i, (list.get(i) + inc) % MOD);
            }

        }

        public void multAll(int m) {
            for (int i = 0; i < list.size(); i++) {

                list.set(i, (list.get(i) * m) % MOD);
            }

        }

        public int getIndex(int idx) {
            if (idx >= list.size()) return -1;
            return (int) (list.get(idx) % MOD);

        }
    }
}
