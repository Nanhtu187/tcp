package com.facenet.mina.GUI.client;
/*
 * Created at 16/02/2023:09:40:43
 */

import javax.swing.*;
import java.awt.*;

/**
 * @author hungdinh
 */

public class LoginPanel extends JPanel {
    private JButton btnLogin;
    private JTextField input;

    private JLabel label;

    public LoginPanel(JButton btnLogin, JTextField input, JLabel label) {
        this.setLayout(new GridLayout(3, 1));
        this.btnLogin = btnLogin;
        this.input = input;
        this.label = label;
        this.input.setBounds(0,0 ,100, 20);
//        this.input.setColumns(10);
//        input.set
        this.add(label);
        this.add(input);
        this.add(btnLogin);
    }
}
