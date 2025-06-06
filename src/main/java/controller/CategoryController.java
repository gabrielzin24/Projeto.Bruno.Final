package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.dao.impl.CategoryDAOImpl;
import model.entity.Category;
import model.entity.TransactionType;
import model.entity.User;
import view.models.CategoryTableModel;

public class CategoryController {
    public int registerCategory(String categoryName, User user) {
        if (user == null || categoryName == null || categoryName.trim().isEmpty()) {
            return 1;
        }

        CategoryDAOImpl dao = new CategoryDAOImpl();
        Category existingCategory = dao.findByNameAndUserId(categoryName.trim(), user.getId());

        if (existingCategory != null) {
            System.out.println("Categoria já existe.");
            return 2;
        }

        Category newCategory = new Category(categoryName.trim(), user);
        try {
            dao.save(newCategory);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    
    public boolean deleteCategory(Category category, User user) {
        if (category == null || user == null) {
            return false;
        }
        
        return new CategoryDAOImpl().delete(category, user);
    }

    
    public void loadCategoriesToTable(CategoryTableModel tableModel, User user) {
        loadCategoriesToTable(tableModel, user, false, false);
    }

    public void loadCategoriesToTable(CategoryTableModel tableModel, User user, boolean byIncome, boolean byExpense) {
        List<Category> allCategories = new CategoryDAOImpl()
            .findAllByUserId(user.getId())
            .stream()
            .filter(c -> !"Deleted".equalsIgnoreCase(c.getName()))
            .collect(Collectors.toList());

        if (!byIncome && !byExpense) {
            tableModel.setCategories(allCategories);
            return;
        }

        List<Category> filtered = allCategories.stream()
            .filter(cat -> {
                boolean hasIncome = cat.getTransactions().stream()
                        .anyMatch(tx -> tx.getType() == TransactionType.RECEITA);
                boolean hasExpense = cat.getTransactions().stream()
                        .anyMatch(tx -> tx.getType() == TransactionType.DESPESA);
                return (byIncome && hasIncome) || (byExpense && hasExpense);
            })
            .toList();

        tableModel.setCategories(filtered);
    }
    
    public Category getCategoryByName(String name, User user){
        return new CategoryDAOImpl().findByNameAndUserId(name, user.getId());
    }
    
    public List<Category> getAllCategoryByUser(User user){
        return new CategoryDAOImpl().findAllByUserId(user.getId());
    }
}