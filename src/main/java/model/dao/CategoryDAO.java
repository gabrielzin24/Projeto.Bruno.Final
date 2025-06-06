package model.dao;

import model.entity.Category;
import java.util.List;
import model.entity.User;

public interface CategoryDAO {
    void save(Category category);
    void update(Category category);
    boolean delete(Category category, User user);
    
    Category findById(Long id);
    Category findByNameAndUserId(String name, Long userId);
    List<Category> findAllByUserId(Long userId);
}