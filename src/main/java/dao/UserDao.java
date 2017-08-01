package dao;

import bean.User;

public interface UserDao {

    public User getUser(User user);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int UserId);

}
