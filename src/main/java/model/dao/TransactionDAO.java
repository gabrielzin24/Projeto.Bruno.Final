package model.dao;

import model.entity.Transaction;
import model.entity.User;
import model.entity.TransactionType;
import java.util.Date;
import java.util.List;

public interface TransactionDAO {
    void save(Transaction transaction);
    void update(Transaction transaction);
    void delete(Transaction transaction);

    Transaction findById(Long id);
    List<Transaction> findAll();
    
    List<Transaction> findByUser(User user);
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByCategory(Long catId);
}
