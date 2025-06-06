package view;

import main.FinalSistemaGestaoFinanceira;

import controller.CategoryController;
import controller.TransactionController;
import java.util.List;
import model.entity.Category;
import model.entity.TransactionType;
import view.models.TransactionTableModel;

public class RegisterTransaction extends javax.swing.JPanel {
    private FinalSistemaGestaoFinanceira app;
    private TransactionController trc = null;

    public RegisterTransaction(FinalSistemaGestaoFinanceira app) {
        this.app = app;
        this.trc = new TransactionController();
        initComponents();
        fillCategoryComboBox();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtPanelTitle = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        checkboxIncome = new javax.swing.JRadioButton();
        checkboxExpense = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        comboboxCategory = new javax.swing.JComboBox<>();
        bntSubmit = new javax.swing.JButton();
        resultStr = new javax.swing.JLabel();

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtPanelTitle.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        txtPanelTitle.setText("Cadastrar nova transação");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel3.setText("Valor");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel4.setText("Data");

        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        jScrollPane1.setViewportView(textAreaDescription);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel5.setText("Descrição");

        checkboxIncome.setText("Receita");
        checkboxIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxIncomeActionPerformed(evt);
            }
        });

        checkboxExpense.setText("Despesa");
        checkboxExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxExpenseActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel6.setText("Tipo");

        comboboxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bntSubmit.setText("Salvar");
        bntSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSubmitActionPerformed(evt);
            }
        });

        resultStr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPanelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultStr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboboxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(checkboxExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkboxIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtData)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(279, 279, 279))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(417, 417, 417))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPanelTitle)
                    .addComponent(jButton1))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(txtAmount))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(checkboxExpense)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkboxIncome)
                        .addGap(18, 18, 18)
                        .addComponent(comboboxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(bntSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultStr, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        app.showHomepage();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bntSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSubmitActionPerformed
        try {
            double valor = Double.parseDouble(txtAmount.getText());
            String dataStr = txtData.getText().trim();
            String descricao = textAreaDescription.getText().trim();
            String categoria = (String) comboboxCategory.getSelectedItem();
            boolean isIncome = checkboxIncome.isSelected();
            boolean isExpense = checkboxExpense.isSelected();

            if (!isIncome && !isExpense) {
                return;
            }
            
            trc.createTransaction(
                valor,
                dataStr,
                descricao, 
                isIncome 
                    ? TransactionType.RECEITA 
                    : TransactionType.DESPESA,
                categoria,
                app.getUser()
            );
           
            resultStr.setText("Transação criada com sucesso.");
            this.cleanInputs();
        } catch (NumberFormatException e) {
            resultStr.setText("Valores inválidos.");
        } catch (Exception e) {
            resultStr.setText("Erro geral.");
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_bntSubmitActionPerformed

    private void checkboxExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxExpenseActionPerformed
        if (checkboxExpense.isSelected()) {
            checkboxIncome.setSelected(false);
            checkboxIncome.setEnabled(false);
        } else {
            checkboxIncome.setEnabled(true);
        }
    }//GEN-LAST:event_checkboxExpenseActionPerformed

    private void checkboxIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxIncomeActionPerformed
        if (checkboxIncome.isSelected()) {
            checkboxExpense.setSelected(false);
            checkboxExpense.setEnabled(false);
        } else {
            checkboxExpense.setEnabled(true);
        }
    }//GEN-LAST:event_checkboxIncomeActionPerformed
    
    private void cleanInputs(){
        txtAmount.setText("");
        txtData.setText("");
        textAreaDescription.setText("");
        checkboxIncome.setSelected(false);
        checkboxExpense.setSelected(false);
        comboboxCategory.setSelectedIndex(0);
    }
    
//    private void fillCategoryComboBox() {
//        try {
//            List<Category> categorias = new CategoryController().getAllCategoryByUser(app.getUser());
//
//            comboboxCategory.removeAllItems();
//
//            for (Category categoria : categorias) {
//                String name = categoria.getName();
//
//                if (!name.equals("Deleted")) {
//                    comboboxCategory.addItem(categoria.getName());
//                }
//            }
//        } catch (Exception e) {}
//    }
    
    
    private void fillCategoryComboBox() {
        try {
            List<Category> categorias = new CategoryController().getAllCategoryByUser(app.getUser());

            comboboxCategory.removeAllItems();
            boolean encontrouCategoria = false;

            for (Category categoria : categorias) {
                String name = categoria.getName();

                if (!name.equals("Deleted")) {
                    comboboxCategory.addItem(name);
                    encontrouCategoria = true;
                }
            }

            if (!encontrouCategoria) {
                bntSubmit.setEnabled(false);
                resultStr.setText("Nenhuma categoria disponível. Crie uma antes de registrar uma transação.");
            } else {
                bntSubmit.setEnabled(true);
                resultStr.setText("");
            }
        } catch (Exception e) {
            bntSubmit.setEnabled(false);
            resultStr.setText("Erro ao carregar categorias.");
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSubmit;
    private javax.swing.JRadioButton checkboxExpense;
    private javax.swing.JRadioButton checkboxIncome;
    private javax.swing.JComboBox<String> comboboxCategory;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel resultStr;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtData;
    private javax.swing.JLabel txtPanelTitle;
    // End of variables declaration//GEN-END:variables
}
