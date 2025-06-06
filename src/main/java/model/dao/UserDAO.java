package model.dao;

import model.entity.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    void update(User user);
    void delete(User user);
    User findById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}