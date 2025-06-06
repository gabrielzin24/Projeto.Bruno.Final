package main;

import javax.swing.*;

import view.Login;
import view.Homepage;
import view.RegisterUser;
import view.RegisterCategory;
import view.RegisterTransaction;

import model.entity.User;


public class FinalSistemaGestaoFinanceira {
    private JFrame frame;
    private User user;
     
    public FinalSistemaGestaoFinanceira() {        
        frame = new JFrame("Sistema de Gestão Financeira");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.showLogin();
        frame.setVisible(true);
    }

    public void showLogin() {
        Login loginPanel = new Login(this);
        frame.setContentPane(loginPanel);
        frame.pack();
    }

    public void showHomepage() {
        Homepage homepagePanel = new Homepage(this);
        homepagePanel.updateDashboardData();
        frame.setContentPane(homepagePanel);
        frame.pack();
    }
    
    public void showRegister() {
        RegisterUser homepagePanel = new RegisterUser(this);
        frame.setContentPane(homepagePanel);
        frame.pack();
    }
    
    public void showRegisterCategory(){
        RegisterCategory rpanel = new RegisterCategory(this);
        frame.setContentPane(rpanel);
        frame.pack();
    }
    
    public void showRegisterTransaction(){
        RegisterTransaction rtpanel = new RegisterTransaction(this);
        frame.setContentPane(rtpanel);
        frame.pack();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    // MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinalSistemaGestaoFinanceira::new);
    }
}