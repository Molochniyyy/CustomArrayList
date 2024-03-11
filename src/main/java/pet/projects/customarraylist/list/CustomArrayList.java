package pet.projects.customarraylist.list;

import java.util.Comparator;

/**
 * Пользовательская реализация ArrayList.
 *
 * @param <T> тип элементов в списке
 */
public interface CustomArrayList<T> {
    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    boolean add(T element);

    /**
     * Вставляет элемент на указанную позицию в списке.
     *
     * @param index   индекс, на который нужно вставить элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */

    void add(int index, T element);

    /**
     * Возвращает элемент на указанной позиции в списке.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */

    T get(int index);

    /**
     * Удаляет элемент на указанной позиции в списке.
     *
     * @param index индекс элемента, который нужно удалить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    void remove(int index);

    /**
     * Очищает все элементы из списка.
     */
    void clear();

    /**
     * Сортирует элементы списка с использованием предоставленного компаратора.
     *
     * @param c компаратор, используемый для сортировки
     */
    void sort(Comparator<? super T> c);

    /**
     * Возвращает длину списка
     *
     * @return длину списка
     */
    int size();

    /**
     * Выполняет быструю сортировку списка с использованием заданного компаратора.
     *
     * @param c компаратор элементов списка
     */
    void quickSort(Comparator<? super T> c);

}
