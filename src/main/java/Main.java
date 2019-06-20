import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

//        User user = new User();
//        user.setName("Taras");
//        user.setAge(27);
//        System.out.println(userDao.insertUser(user));

        List<User> allUsers = userDao.findAllUsers();
        allUsers.forEach(System.out::println);
        List<String> collect = allUsers.stream()
                .map(User::getName)
                .collect(Collectors.toList());
        System.out.println(userDao.getNameByUserId(6));
    }
}
