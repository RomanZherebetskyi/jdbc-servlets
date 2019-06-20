import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connection;

    UserDao() {
        connection = Database.getConnection();
    }

    public int insertUser(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO User (name, age) VALUES (?, ?) ");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt(2));
                user.setId(resultSet.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public String getNameByUserId(int id) {
        String name = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM User WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }
}
