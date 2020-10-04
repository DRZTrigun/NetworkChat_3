package ru.geekbrains.core;

import ru.geekbrains.data.DBConnection;
import ru.geekbrains.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AuthController {

    HashMap<String, User> users = new HashMap<>();

    public void init(){
        for (User user:receiveUsers()) {
            users.put(user.getLogin(), user);
        }
    }

    public String getNickname (String login, String password){
        User user = users.get(login);
        if(user != null && user.isPasswordCorrect(password)){
            return user.getNickname();
        }
        return null;
    }

    private ArrayList<User> receiveUsers() {
        ArrayList<User> userArr = new ArrayList<>();
        userArr.add(new User("admin", "admin", "sysroot"));
        userArr.add(new User("user1", "user1", "user1- st"));
        return userArr;
    }

    public User findByLAndP(String login, String password){
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE login = ? AND password = ?");

        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet rs =  statement.executeQuery();
        if(rs.next()){
            return new User(
            rs.getString("login"),
            rs.getString("password"),
            rs.getString("nickname")
                    );
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

