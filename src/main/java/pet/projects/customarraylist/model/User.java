package pet.projects.customarraylist.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
@Data
@AllArgsConstructor
public class User {
    String name;
    int id;
    String email;

    public static Comparator<User> idComparator = Comparator.comparing(user -> user.id);
}
