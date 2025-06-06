package controller;

import model.dao.impl.UserDAOImpl;
import model.entity.User;

public class UserAuthController {
    private String mensagemErro = "";

    public User login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            mensagemErro = "Preencha todos os campos.";
            return null;
        }

        UserDAOImpl dao = new UserDAOImpl();
        User user = dao.findByEmail(email);

        if (user == null) {
            mensagemErro = "Usuário não encontrado.";
            return null;
        }

        if (!user.getPassword().equals(password)) {
            mensagemErro = "Senha inválida.";
            return null;
        }

        return user;
    }
    
    public boolean register(String username, String email, String password) {
        mensagemErro = "";

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            mensagemErro = "Todos os campos são obrigatórios.";
            return false;
        }

        UserDAOImpl dao = new UserDAOImpl();
        if (dao.findByEmail(email) != null) {
            mensagemErro = "Já existe um usuário com este e-mail.";
            return false;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        
        // TODO: hash das senha
        newUser.setPassword(password);

        dao.save(newUser);
        return true;
    }
    
    public String getMensagemErro() {
        return mensagemErro;
    }
}