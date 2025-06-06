package view.models;

import java.text.ParseException;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.entity.Transaction;
import java.text.SimpleDateFormat;
import model.dao.impl.CategoryDAOImpl;
import model.dao.impl.TransactionDAOImpl;
import model.entity.Category;
import model.entity.TransactionType;


public class TransactionTableModel extends AbstractTableModel {
    private List<Transaction> transactions;
    private final String[] columnNames = {"ID", "Valor", "Data", "Descrição", "Tipo", "Categoria"};

    public TransactionTableModel(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction getTransactionAt(int rowIndex){
        return transactions.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
        return transactions != null ? transactions.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction transaction = transactions.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return switch (columnIndex) {
            case 0 -> transaction.getId();
            case 1 -> transaction.getAmount();
            case 2 -> sdf.format(transaction.getDate());
            case 3 -> transaction.getDescription();
            case 4 -> transaction.getType() != null ? transaction.getType().toString() : null;
            case 5 -> transaction.getCategory() != null ? transaction.getCategory().getName() : null;
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) { return false; }
        Object lastColValue = getValueAt(rowIndex, getColumnCount() - 1);
        return !(lastColValue instanceof String && lastColValue.equals("Deleted"));
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Transaction transaction = transactions.get(rowIndex);
        TransactionDAOImpl dao = new TransactionDAOImpl();

        switch (columnIndex) {
            case 1 -> transaction.setAmount(Double.parseDouble(aValue.toString()));
            case 2 -> {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    transaction.setDate(sdf.parse(aValue.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            case 3 -> transaction.setDescription(aValue.toString());
            case 4 -> transaction.setType(TransactionType.valueOf(aValue.toString()));
            case 5 -> {
                CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
                Category category = categoryDAO.findByNameAndUserId(aValue.toString(), transaction.getUser().getId());
                if (category != null) {
                    transaction.setCategory(category);
                }
            }
        }

        dao.update(transaction);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        fireTableDataChanged();
    }
}
