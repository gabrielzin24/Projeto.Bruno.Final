package view;

import view.models.CategoryTableModel;
import controller.CategoryController;
import model.entity.User;
import javax.swing.*;
import java.util.ArrayList;
import main.FinalSistemaGestaoFinanceira;
import model.entity.Category;

public class RegisterCategory extends javax.swing.JPanel {
    private CategoryTableModel tableModel;
    private FinalSistemaGestaoFinanceira app;
    private CategoryController ct = null;
    private boolean byIncome = false;
    private boolean byExpense = false;
    private JTable table;

    public RegisterCategory(FinalSistemaGestaoFinanceira app) {
        this.app = app;
        ct = new CategoryController();
        
        this.initComponents();
        this.setupTable();
        this.loadCategories();
        this.setupDeleteSelectionSection();
    }
    
    private void setupDeleteSelectionSection(){
        btnRemoveCategory.setVisible(false);
        txtDeletedthiscategory.setVisible(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean rowSelected = table.getSelectedRow() != -1;
                btnRemoveCategory.setVisible(rowSelected);
                txtDeletedthiscategory.setVisible(rowSelected);
            }
        });
    }

    private void setupTable() {
        tableModel = new CategoryTableModel(new ArrayList<>());
        table = new JTable(tableModel);
        scrollPaneCategoryTables.setViewportView(table);
    }

    private void loadCategories() {
        User currentUser = app.getUser(); 
        if (currentUser != null) {
            ct.loadCategoriesToTable(tableModel, currentUser);
        }
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPanelTitle = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        checkboxVendas = new javax.swing.JCheckBox();
        checkboxGastos = new javax.swing.JCheckBox();
        txtLabelFiltrar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNewCategoryName = new javax.swing.JTextField();
        scrollPaneCategoryTables = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        resultStr = new javax.swing.JLabel();
        btnRemoveCategory = new javax.swing.JButton();
        txtDeletedthiscategory = new javax.swing.JLabel();

        txtPanelTitle.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        txtPanelTitle.setText("Categorias");

        btnSubmit.setText("Salvar");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        checkboxVendas.setText("Vendas");
        checkboxVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxVendasActionPerformed(evt);
            }
        });

        checkboxGastos.setText("Gastos");
        checkboxGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxGastosActionPerformed(evt);
            }
        });

        txtLabelFiltrar.setText("Filtrar por:");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabel3.setText("Adicionar nova categoria");

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnRemoveCategory.setText("Apagar");
        btnRemoveCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveCategoryActionPerformed(evt);
            }
        });

        txtDeletedthiscategory.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        txtDeletedthiscategory.setText("Apagar categoria selecionada?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPanelTitle)
                                .addGap(323, 323, 323))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtLabelFiltrar)
                                .addGap(18, 18, 18)
                                .addComponent(checkboxVendas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkboxGastos)))
                        .addGap(423, 423, 423)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneCategoryTables, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtNewCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resultStr)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDeletedthiscategory)
                            .addComponent(btnRemoveCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPanelTitle)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneCategoryTables, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLabelFiltrar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(checkboxVendas)
                                .addComponent(checkboxGastos)))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNewCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(resultStr)
                        .addGap(41, 41, 41)
                        .addComponent(txtDeletedthiscategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String categoryName = txtNewCategoryName.getText().trim();

        if (categoryName.isEmpty()) { return; }
        
        int res = ct.registerCategory(categoryName, app.getUser());
        
        if (res == 1){
            resultStr.setText("Verifique os inputs.");
            return;
        }
        
        if (res == 2){
            resultStr.setText("Categoria já existente.");
            return;
        }
        
        ct.loadCategoriesToTable(tableModel, app.getUser());
        
        txtNewCategoryName.setText("");
        resultStr.setText("Nova categoria adicionada.");
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void checkboxVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxVendasActionPerformed
        this.byIncome = checkboxVendas.isSelected();
        ct.loadCategoriesToTable(tableModel, app.getUser(), byIncome, byExpense);
    }//GEN-LAST:event_checkboxVendasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        app.showHomepage();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkboxGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxGastosActionPerformed
        this.byExpense = checkboxGastos.isSelected();
        ct.loadCategoriesToTable(tableModel, app.getUser(), byIncome, byExpense);
    }//GEN-LAST:event_checkboxGastosActionPerformed
    
    private void btnRemoveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCategoryActionPerformed
        int[] selectedRows = table.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos uma categoria para remover.");
            return;
        }

        String plural = (selectedRows.length == 1 ? " categoria" : " categorias");
        
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover " + selectedRows.length + plural + "?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            User u = app.getUser();
            for (int rowIndex : selectedRows) {
                Category c = tableModel.getCategoryAt(rowIndex);
                ct.deleteCategory(c, u);
            }

            loadCategories();
            resultStr.setText("Sucesso ao apagar " + plural);
        }
    }//GEN-LAST:event_btnRemoveCategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoveCategory;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JCheckBox checkboxGastos;
    private javax.swing.JCheckBox checkboxVendas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel resultStr;
    private javax.swing.JScrollPane scrollPaneCategoryTables;
    private javax.swing.JLabel txtDeletedthiscategory;
    private javax.swing.JLabel txtLabelFiltrar;
    private javax.swing.JTextField txtNewCategoryName;
    private javax.swing.JLabel txtPanelTitle;
    // End of variables declaration//GEN-END:variables
}
