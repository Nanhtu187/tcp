package com.facenet.mina.gui.client;

import javax.swing.*;
import java.awt.*;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class LoginPanel extends JPanel {
    private JButton btnLogin;
    private JTextField input;

    private JLabel label;

    /**
     * Init Panel of Login frame
     * @param btnLogin - Button Login
     * @param input - Input username
     * @param label
     */
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
