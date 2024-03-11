package pet.projects.customarraylist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pet.projects.customarraylist.list.CustomArrayList;
import pet.projects.customarraylist.list.CustomArrayListImpl;
import pet.projects.customarraylist.model.User;

import java.util.Random;

@SpringBootTest
class CustomArrayListApplicationTests {

    @Test
    void addTest() {
        Random random = new Random();
        CustomArrayList<Integer> numbers = new CustomArrayListImpl<>();
        Assertions.assertEquals(numbers.size(), 0);
        for (int i = 0; i < 50; i++) {
            numbers.add(random.nextInt() % 100);
        }
        Assertions.assertEquals(50, numbers.size());
    }

    @Test
    void addByIndexTest() {
        User user = new User("Mike", 0, "mike@mail.ru");
        User user1 = new User("Ashlie", 1, "ashlie@mail.ru");
        User user2 = new User("Nikola", 2, "nikola@mail.ru");
        User user3 = new User("Alex", 3, "alex@mail.ru");

        CustomArrayList<User> users = new CustomArrayListImpl<>();
        users.add(user);
        users.add(user3);
        users.add(user1);
        users.add(user2);

        Assertions.assertEquals(users.size(), 4);

        User user4 = new User("Max", 4, "max@mail.ru");
        users.add(2, user4);

        Assertions.assertEquals(5, users.size());
        Assertions.assertEquals(user4, users.get(2));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> users.add(23432, user));
    }

    @Test
    void clearTest() {
        Random random = new Random();
        CustomArrayList<Integer> numbers = new CustomArrayListImpl<>();
        Assertions.assertEquals(numbers.size(), 0);
        for (int i = 0; i < 50; i++) {
            numbers.add(random.nextInt() % 100);
        }
        Assertions.assertEquals(50, numbers.size());

        numbers.clear();

        Assertions.assertEquals(0, numbers.size());
    }

    @Test
    void sortTest() {
        CustomArrayList<Integer> numbers = new CustomArrayListImpl<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(-12);
        numbers.add(-4);
        numbers.add(8);
        numbers.add(100);
        numbers.add(0);
        numbers.add(45);

        CustomArrayList<Integer> numbers1 = new CustomArrayListImpl<>();
        numbers1.add(-12);
        numbers1.add(-4);
        numbers1.add(0);
        numbers1.add(2);
        numbers1.add(5);
        numbers1.add(8);
        numbers1.add(45);
        numbers1.add(100);

        numbers.sort(Integer::compareTo);

        Assertions.assertEquals(numbers1.get(0), numbers.get(0));
    }

    @Test
    void getTest() {
        CustomArrayList<String> stringCustomArrayList = new CustomArrayListImpl<>();
        String test = "test";
        stringCustomArrayList.add(test);

        Assertions.assertEquals(test, stringCustomArrayList.get(0));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> stringCustomArrayList.get(23432));
    }

    @Test
    void removeTest() {
        Random random = new Random();
        CustomArrayList<Integer> numbers = new CustomArrayListImpl<>();
        for (int i = 0; i < 50; i++) {
            numbers.add(random.nextInt() % 100);
        }
        Assertions.assertEquals(50, numbers.size());

        numbers.remove(4);

        Assertions.assertEquals(49, numbers.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> numbers.remove(23432));
    }

    @Test
    void hugeSizeTest() {
        Random random = new Random();
        CustomArrayList<Integer> numbers = new CustomArrayListImpl<>();

        Assertions.assertThrows(OutOfMemoryError.class, () -> {
            for (int i = 0; i < Integer.MAX_VALUE - 8; i++) {
                numbers.add(random.nextInt() % 10);
            }
        });
    }

    @Test
    void quickSortTest() {
        User user = new User("Mike", 0, "mike@mail.ru");
        User user1 = new User("Ashlie", 1, "ashlie@mail.ru");
        User user2 = new User("Nikola", 2, "nikola@mail.ru");
        User user3 = new User("Alex", 3, "alex@mail.ru");

        CustomArrayList<User> users = new CustomArrayListImpl<>();
        users.add(user);
        users.add(user3);
        users.add(user1);
        users.add(user2);

        int[] rightIds = new int[]{0, 1, 2, 3};
        int[] ids = new int[4];
        for (int i = 0; i < users.size(); i++) {
            ids[i] = users.get(i).getId();
        }

        Assertions.assertNotEquals(ids, rightIds);

        users.quickSort(User.idComparator);

        for (int i = 0; i < users.size(); i++) {
            ids[i] = users.get(i).getId();
        }

        Assertions.assertArrayEquals(ids, rightIds);


    }

}
