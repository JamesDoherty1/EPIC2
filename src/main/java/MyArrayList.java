import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyArrayList<T> implements Iterable<T> {
        private static final int INITIAL_CAPACITY = 10;
        private Object[] array;
        private int size;

        public MyArrayList() {
            array = new Object[INITIAL_CAPACITY];
            size = 0;
        }

        public void add(T element) {
            ensureCapacity();
            array[size++] = element;
        }

        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            return (T) array[index];
        }

        public int size() {
            return size;
        }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Shift elements to the left to remove the element at the specified index
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null; // Set the last element to null
    }

        private void ensureCapacity() {
            if (size == array.length) {
                int newCapacity = array.length * 2;
                array = Arrays.copyOf(array, newCapacity);
            }
        }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[currentIndex++];
            }
        };
    }
    }
