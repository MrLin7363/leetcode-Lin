package hw.基础学习.集合;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapTest {
    public static void main(String[] args) {
        Map<Key,String> map=new HashMap<>();
        Key key=new Key(10,20);
        map.put(key,"first");
        System.out.println(map.get(key)); // first
        key.setFf(4);
        System.out.println(map.get(key)); // null
        key.setFf(10);
        System.out.println(map.get(key)); // first
    }

    static class Key {
        private int ff;
        private int ss;

        public Key(int ff, int ss) {
            this.ff = ff;
            this.ss = ss;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Key key = (Key) o;
            return ff == key.ff && ss == key.ss;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ff, ss);
        }

        public int getFf() {
            return ff;
        }

        public void setFf(int ff) {
            this.ff = ff;
        }

        public int getSs() {
            return ss;
        }

        public void setSs(int ss) {
            this.ss = ss;
        }
    }
}
