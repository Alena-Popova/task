
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;

@ThreadSafe
public class DinamicList<E> implements Iterable<E> {

    private int capacity = 10;

    @GuardedBy("monitor")
    private Object[] container = new Object[capacity];
    @GuardedBy("monitor")
    private Integer index = 0;
    @GuardedBy("monitor")
    private Integer modCount = 0;


    public synchronized boolean add(E value) {
        if (index >= capacity) {
            capacity = (capacity * 3) / 2 + 1;
            container = Arrays.copyOf(container, capacity);
        }
        container[index++] = value;
        modCount++;
        return true;
    }

    public synchronized E get(int sIndex) {
        return (E) container[sIndex];
    }

    protected synchronized int getLength() {
        return index;
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized Object[] getCopy() {

        return container;
    }

    public Iterator<E> iterator() {
        return new synchItr();
    }


    private class synchItr implements Iterator<E> {
        /**
         * count of modificetion;
         */
        private int expectedModCount;
        /**
         * start of iteration
         */
        private int count = 0;

        /**
         * new container
         */
        private Object[] tempContaner;

        /**
         * copy data to new contener
         */
        public synchItr() {
            tempContaner = Arrays.copyOf(getCopy(), getCapacity());
            expectedModCount = modCount;

        }

        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return count < capacity;
        }

        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (count >= tempContaner.length) {
                throw new NoSuchElementException();
            }
            return (E) tempContaner[count++];

        }

        public void remove() {
            if (count > tempContaner.length || count < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            tempContaner[--count] = null;
            container = Arrays.copyOf(tempContaner, getCapacity());
            expectedModCount++;
            modCount++;
            index--;
        }
    }

}