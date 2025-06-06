package controller;

import model.dao.impl.TransactionDAOImpl;
import model.entity.Transaction;
import model.entity.TransactionType;
import model.entity.User;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.util.List;
import model.entity.Category;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import view.models.TransactionTableModel;

public class TransactionController {

    private final TransactionDAOImpl td;

    public TransactionController() {
        this.td = new TransactionDAOImpl();
    }
    
    private Date convertStringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data de formato inválido: dd/MM/yyyy", e);
        }
    }

    private Category getCategoryByName(String categoryStr, User user) {
        CategoryController ct = new CategoryController();
        return ct.getCategoryByName(categoryStr, user);
    }

    public void createTransaction(double amount, String dateStr, String description, TransactionType type, String categoryStr, User user) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }

        Date date = convertStringToDate(dateStr);

        Category category = new CategoryController().getCategoryByName(categoryStr, user);

        Transaction transaction = new Transaction(amount, date, description, type, category, user);
        td.save(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        if (transaction != null && transaction.getId() != null) {
            td.update(transaction);
        } else {
            throw new IllegalArgumentException("Transação ou ID nao podem ser null");
        }
    }

    public void deleteTransaction(Transaction transaction) {
        if (transaction != null && transaction.getId() != null) {
            td.delete(transaction);
        } else {
            throw new IllegalArgumentException("Transação ou ID nao podem ser null");
        }
    }

    public Transaction getTransactionById(Long id) {
        if (id != null) {
            return td.findById(id);
        } else {
            throw new IllegalArgumentException("ID nao pode ser null");
        }
    }

    public List<Transaction> getAllTransactions() {
        return td.findAll();
    }

    public List<Transaction> getTransactionsByUser(User user) {
        if (user != null) {
            return td.findByUser(user);
        } else {
            throw new IllegalArgumentException("usuário nao pode ser null");
        }
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        if (type != null) {
            return td.findByType(type);
        } else {
            throw new IllegalArgumentException("transação nao pode ser desse tipo");
        }
    }
    
    public List<Transaction> getTransactionsByCategory(Category category) {
        if (category != null) {
            return td.findByCategory(category.getId());
        } else {
            throw new IllegalArgumentException("categoria nao encontrada.");
        }
    }

    public double calculateBalanceByUser(User user) {
        List<Transaction> transactions = getTransactionsByUser(user);
        return transactions.stream()
                .mapToDouble(t -> t.getType() == TransactionType.RECEITA ? t.getAmount() : -t.getAmount())
                .sum();
    }
    
    public void loadTransactionToTable(TransactionTableModel tableModel, User user) {
        List<Transaction> transactions = getTransactionsByUser(user);
        tableModel.setTransactions(transactions);
    }
    
    public List<Transaction> filterTransactions(
        User user,
        String categoryStr,
        Boolean includeIncome,
        Boolean includeExpense,
        String startDateStr,
        String endDateStr
    ) {
        List<Transaction> transactions = getTransactionsByUser(user);

        if (categoryStr != null && !categoryStr.equals("Todos")) {
            Category category = getCategoryByName(categoryStr, user);
            transactions.removeIf(t -> !t.getCategory().getId().equals(category.getId()));
        }
        
        if ((startDateStr != null && !startDateStr.isEmpty()) || (endDateStr != null && !endDateStr.isEmpty())) {
            Date startDate = startDateStr != null && !startDateStr.isEmpty() ? convertStringToDate(startDateStr) : null;
            Date endDate = endDateStr != null && !endDateStr.isEmpty() ? convertStringToDate(endDateStr) : null;

            transactions.removeIf(t -> {
                Date transactionDate = t.getDate();
                if (startDate != null && transactionDate.before(startDate)) return true;
                if (endDate != null && transactionDate.after(endDate)) return true;
                return false;
            });
        }
        
        transactions.removeIf(t -> {
            if (t.getType() == TransactionType.RECEITA) { return !includeIncome; }
            else if (t.getType() == TransactionType.DESPESA) { return !includeExpense; }
            return false;
        });

        return transactions;
    }

    private boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date1).equals(sdf.format(date2));
    }
    
    public Category getCategoryWithHighestSales(User user) {
        List<Transaction> transactions = getTransactionsByUser(user);

        Map<Category, Double> categorySales = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.RECEITA) {
                Category category = t.getCategory();
                double currentTotal = categorySales.getOrDefault(category, 0.0);
                categorySales.put(category, currentTotal + t.getAmount());
            }
        }

        Category maxCategory = null;
        double maxTotal = Double.NEGATIVE_INFINITY;

        for (Map.Entry<Category, Double> entry : categorySales.entrySet()) {
            if (entry.getValue() > maxTotal) {
                maxTotal = entry.getValue();
                maxCategory = entry.getKey();
            }
        }

        return maxCategory;
    }


    public String getBestMonthAndYearOfSales(User user) {
        List<Transaction> transactions = getTransactionsByUser(user);
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");

        Map<String, Double> monthYearTotals = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.RECEITA) {
                String monthYear = monthYearFormat.format(t.getDate());
                monthYearTotals.put(monthYear, monthYearTotals.getOrDefault(monthYear, 0.0) + t.getAmount());
            }
        }

        String bestMonthYear = null;
        double maxTotal = Double.NEGATIVE_INFINITY;

        for (Map.Entry<String, Double> entry : monthYearTotals.entrySet()) {
            if (entry.getValue() > maxTotal) {
                maxTotal = entry.getValue();
                bestMonthYear = entry.getKey();
            }
        }
        
        return bestMonthYear;
    }
}