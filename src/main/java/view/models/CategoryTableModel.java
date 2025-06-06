package view.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.dao.impl.CategoryDAOImpl;
import model.dao.impl.TransactionDAOImpl;
import model.entity.Category;
import model.entity.Transaction;
import model.entity.TransactionType;

public class CategoryTableModel extends AbstractTableModel {

    private List<Category> categories;
    private String[] columnNames = {"ID", "Nome", "User ID", "Receitas", "Despesas"};

    public CategoryTableModel(List<Category> categories) {
        this.categories = categories;
    }

    public Category getCategoryAt(int rowIndex) {
        return categories.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
        return categories != null ? categories.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category category = categories.get(rowIndex);
        
        return switch (columnIndex) {
            case 0 -> category.getId();
            case 1 -> category.getName();
            case 2 -> category.getUser() != null ? category.getUser().getId() : null;

            case 3 -> new TransactionDAOImpl()
                .findByCategory(category.getId())
                .stream()
                .filter(i -> i.getType() == TransactionType.RECEITA)
                .mapToDouble(Transaction::getAmount)
                .sum();
                
            case 4 -> new TransactionDAOImpl()
                .findByCategory(category.getId())
                .stream()
                .filter(i -> i.getType() == TransactionType.DESPESA)
                .mapToDouble(Transaction::getAmount)
                .sum();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 1; // nome da categoria (único editável)
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Category cat = categories.get(rowIndex);
        
        switch (columnIndex){
            case 1: {
                cat.setName(aValue.toString());
                new CategoryDAOImpl().update(cat);
                break;
            }
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        fireTableDataChanged();
    }
}