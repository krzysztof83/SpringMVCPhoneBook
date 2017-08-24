package phonebook.services;


import phonebook.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}