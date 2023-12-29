package A1000PLAN.设计题;

import java.util.Iterator;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/4
 **/
public class P284窥视迭代器 {
    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;

        private Integer next;

        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            next = iterator.next();
        }

        public Integer peek() {
            return next;
        }

        @Override
        public Integer next() {
            Integer res = next;
            next = iterator.hasNext() ? iterator.next() : null;
            return res;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
