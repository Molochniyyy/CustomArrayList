package pet.projects.customarraylist.list;

import java.util.*;

/**
 * Пользовательская реализация ArrayList.
 *
 * @param <T> тип элементов в списке
 */
public class CustomArrayListImpl<T> implements CustomArrayList<T> {
    /**
     * Начальная емкость по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private Object[] array;
    /**
     * Размер ArrayList (количество содержащихся в нем элементов).
     */
    private int size;

    /**
     * Конструктор для создания пустого списка с начальной емкостью 10.
     */
    public CustomArrayListImpl() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    @Override
    public boolean add(T element) {
        add(element, array, size);
        return true;
    }

    /**
     * Вставляет элемент на указанную позицию в списке.
     *
     * @param index   индекс, на который нужно вставить элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size: " + size);
        }
        int s = size;
        Object[] array = this.array;
        if (s == array.length) {
            array = grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size = s + 1;
    }

    private void add(T e, Object[] array, int s) {
        if (s == array.length)
            array = grow();
        array[s] = e;
        size = s + 1;
    }

    /**
     * Возвращает элемент на указанной позиции в списке.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size: " + size);
        }
        return (T) array[index];
    }

    /**
     * Удаляет элемент на указанной позиции в списке.
     *
     * @param index индекс элемента, который нужно удалить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    /**
     * Очищает все элементы из списка.
     */
    @Override
    public void clear() {
        final Object[] array = this.array;
        for (int to = size, i = size = 0; i < to; i++)
            array[i] = null;
    }

    /**
     * Сортирует элементы списка с использованием предоставленного компаратора.
     *
     * @param c компаратор, используемый для сортировки
     */
    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) array, 0, size, c);
    }

    /**
     * Возвращает длину списка
     *
     * @return длину списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Увеличивает емкость массива для списка.
     *
     * @param minCapacity минимальная требуемая емкость списка
     * @return новый массив с увеличенной емкостью
     */
    private Object[] grow(int minCapacity) {
        int oldCapacity = array.length;
        if (oldCapacity > 0 || array != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return array = Arrays.copyOf(array, newCapacity);
        } else {
            return array = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    /**
     * Увеличивает емкость списка.
     *
     * @return новый массив с увеличенной емкостью
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * Выполняет быструю сортировку списка с использованием заданного компаратора.
     *
     * @param c компаратор элементов списка
     */
    @Override
    public void quickSort(Comparator<? super T> c) {
        T[] array = (T[]) this.array;
        quickSort(array, 0, size - 1, c);
    }

    /**
     * Рекурсивный метод для быстрой сортировки массива с использованием компаратора.
     *
     * @param array массив, который требуется отсортировать
     * @param low   индекс начала подмассива
     * @param high  индекс конца подмассива
     * @param c     компаратор элементов массива
     */
    private void quickSort(T[] array, int low, int high, Comparator<? super T> c) {
        if (low < high) {
            int pi = partition(array, low, high, c);


            quickSort(array, low, pi - 1, c);
            quickSort(array, pi + 1, high, c);
        }
    }

    /**
     * Выполняет разделение массива для быстрой сортировки.
     *
     * @param array массив, который требуется отсортировать
     * @param low   индекс начала подмассива
     * @param high  индекс конца подмассива
     * @param c     компаратор элементов массива
     * @return индекс опорного элемента после разделения
     */
    private int partition(T[] array, int low, int high, Comparator<? super T> c) {
        T pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (c.compare(array[j], pivot) < 0) {
                i++;


                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }


        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;


        return i + 1;
    }

    private int newLength(int oldLength, int minGrowth, int prefGrowth) {
        int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            return oldLength + minGrowth;
        }
    }
}
