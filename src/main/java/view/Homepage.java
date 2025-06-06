package view;

import controller.CategoryController;
import controller.TransactionController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import main.FinalSistemaGestaoFinanceira;
import model.entity.Category;
import model.entity.Transaction;
import model.entity.User;
import view.models.TransactionTableModel;

public class Homepage extends javax.swing.JPanel {
    private FinalSistemaGestaoFinanceira app;
    private TransactionTableModel tableModel;
    private JTable table;
    private TransactionController trc = null;
    private boolean isLoading = false;


    public Homepage(FinalSistemaGestaoFinanceira app) {
        initComponents();
        this.app = app;
        txtGreetings.setText("Olá, " + app.getUser().getUsername());
        
        this.trc = new TransactionController();
        User u = app.getUser();
        
        setupTable();
        setupInitialCheckboxStates();
        fillCategoryComboBox();
        loadUserTransactions();
        setupDeleteSelectionSection();
        setupComboBoxEditors();
        
        Double amount = trc.calculateBalanceByUser(u);
        Integer NofT = trc.getTransactionsByUser(u).size();
        
        updateDashboardData();
    }
    
    private void loadUserTransactions() {
        User currentUser = app.getUser();
        if (currentUser != null) {
            trc.loadTransactionToTable(tableModel, currentUser);
        }
    }

    private void setupDeleteSelectionSection() {
        btndeletetransaction.setVisible(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                boolean rowSelected = selectedRow != -1;
                btndeletetransaction.setVisible(rowSelected);
            }
        });
    }
    
    private void setupTable() {
        tableModel = new TransactionTableModel(new ArrayList<>());
        table = new JTable(tableModel);
        scrollPaneTransactionTables.setViewportView(table);
        
        table.getModel().addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged
            (javax.swing.event.TableModelEvent e
                ) {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                    updateDashboardData();
                }
            }
        });
    }
    
    private void setupInitialCheckboxStates() {
        checkboxFilterByIncome.setSelected(true);
        checkboxFilterByExpense.setSelected(true);
    }
    
    private void fillCategoryComboBox() {
        try {
            List<Category> categorias = new CategoryController().getAllCategoryByUser(app.getUser());
            
            comboboxTransactionFilterByCategory.removeAllItems();
            comboboxTransactionFilterByCategory.addItem("Todos");

            for (Category categoria : categorias) {
                String name = categoria.getName();

                if (!name.equals("Deleted")) {
                    comboboxTransactionFilterByCategory.addItem(categoria.getName());
                }
            }
        } catch (Exception e) {}
    }

    private void setupComboBoxEditors() {
        String[] tipos = {"DESPESA", "RECEITA"};
        JComboBox<String> tipoComboBox = new JComboBox<>(tipos);
        TableColumn tipoColuna = table.getColumnModel().getColumn(4);
        tipoColuna.setCellEditor(new DefaultCellEditor(tipoComboBox));

        User currentUser = app.getUser();
        if (currentUser != null) {
            String[] categorias = new CategoryController()
                    .getAllCategoryByUser(currentUser)
                    .stream()
                    .map(Category::getName)
                    .toArray(String[]::new);
            JComboBox<String> categoriaComboBox = new JComboBox<>(categorias);
            TableColumn categoriaColuna = table.getColumnModel().getColumn(5);
            categoriaColuna.setCellEditor(new DefaultCellEditor(categoriaComboBox));
        }
    }
    
    public void updateDashboardData() {
        if (isLoading) { return; }

        isLoading = true;

        User u = app.getUser();

        Double amount = trc.calculateBalanceByUser(u);
        Integer NofT = trc.getTransactionsByUser(u).size();

        txtSaldoAtual.setText("Saldo atual:  R$ " + amount.toString());
        isLoading = false;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        txtGreetings = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtIntro = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        txtSaldoAtual = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        btnFilterSubmit = new javax.swing.JButton();
        checkboxFilterByIncome = new javax.swing.JCheckBox();
        checkboxFilterByExpense = new javax.swing.JCheckBox();
        comboboxTransactionFilterByCategory = new javax.swing.JComboBox<>();
        dataBeginFilter = new javax.swing.JTextField();
        dataEndFilter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        scrollPaneTransactionTables = new javax.swing.JScrollPane();
        btndeletetransaction = new javax.swing.JButton();

        txtGreetings.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        txtGreetings.setText("Apresentaçao");

        btnLogout.setText("Sair");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jButton1.setText("Categorias");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Transações");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtIntro.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        txtIntro.setText("Sistema de Gestão Financeira");

        panel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSaldoAtual.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtSaldoAtual.setText("Saldo atual:");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtSaldoAtual)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(txtSaldoAtual)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        panel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnFilterSubmit.setText("Filtrar");
        btnFilterSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterSubmitActionPerformed(evt);
            }
        });

        checkboxFilterByIncome.setText("Receita");
        checkboxFilterByIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxFilterByIncomeActionPerformed(evt);
            }
        });

        checkboxFilterByExpense.setText("Despesa");

        comboboxTransactionFilterByCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dataBeginFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBeginFilterActionPerformed(evt);
            }
        });

        jLabel1.setText("Entre as datas");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataBeginFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataEndFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addComponent(checkboxFilterByIncome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkboxFilterByExpense)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFilterSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboboxTransactionFilterByCategory, 0, 136, Short.MAX_VALUE))
                        .addGap(15, 15, 15))))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkboxFilterByIncome)
                    .addComponent(checkboxFilterByExpense)
                    .addComponent(comboboxTransactionFilterByCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(dataBeginFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataEndFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFilterSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        btndeletetransaction.setText("Apagar");
        btndeletetransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletetransactionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtGreetings)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtIntro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(btndeletetransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneTransactionTables, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtIntro))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogout)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGreetings)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btndeletetransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPaneTransactionTables, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        app.showLogin();
        app.setUser(null);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        app.showRegisterCategory();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        app.showRegisterTransaction();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnFilterSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterSubmitActionPerformed
        String categoriaSelecionada = (String) comboboxTransactionFilterByCategory.getSelectedItem();
        boolean filtrarIncome = checkboxFilterByIncome.isSelected();
        boolean filtrarExpense = checkboxFilterByExpense.isSelected();
        String dataStart = dataBeginFilter.getText().trim();
        String dataEnd = dataEndFilter.getText().trim();

        User user = app.getUser();

        List<Transaction> transacoesFiltradas = trc.filterTransactions(
            user,
            categoriaSelecionada,
            filtrarIncome,
            filtrarExpense,
            dataStart.isEmpty() ? null : dataStart,
            dataEnd.isEmpty() ? null : dataEnd
        );

        tableModel.setTransactions(transacoesFiltradas);
    }//GEN-LAST:event_btnFilterSubmitActionPerformed

    
    private void dataBeginFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBeginFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataBeginFilterActionPerformed

    private void btndeletetransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletetransactionActionPerformed
        int[] selectedRows = table.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos uma transação para remover.");
            return;
        }

        String plural = (selectedRows.length == 1 ? " transação" : " transações");

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja remover " + selectedRows.length + plural + "?",
            "Confirmar Remoção",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            for (int rowIndex : selectedRows) {
                Transaction tx = tableModel.getTransactionAt(rowIndex);
                trc.deleteTransaction(tx);
            }

            loadUserTransactions();
            updateDashboardData();
        }
    }//GEN-LAST:event_btndeletetransactionActionPerformed

    private void checkboxFilterByIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxFilterByIncomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxFilterByIncomeActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilterSubmit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btndeletetransaction;
    private javax.swing.JCheckBox checkboxFilterByExpense;
    private javax.swing.JCheckBox checkboxFilterByIncome;
    private javax.swing.JComboBox<String> comboboxTransactionFilterByCategory;
    private javax.swing.JTextField dataBeginFilter;
    private javax.swing.JTextField dataEndFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel3;
    private javax.swing.JScrollPane scrollPaneTransactionTables;
    private javax.swing.JLabel txtGreetings;
    private javax.swing.JLabel txtIntro;
    private javax.swing.JLabel txtSaldoAtual;
    // End of variables declaration//GEN-END:variables
}
