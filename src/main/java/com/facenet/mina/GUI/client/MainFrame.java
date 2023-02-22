package com.facenet.mina.GUI.client;

import com.facenet.mina.service.ClientService;
import com.facenet.mina.Entity.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class MainFrame extends JFrame {
    private JButton btnLogin;

    private JLabel labelLogin;

    private JTextField inputLogin;

    private LoginPanel loginPanel;

    private JPanel panel1;

    private JPanel panel2;

    private JLabel l1;

    private JTextArea t1;

    private JScrollPane pane;

    private JLabel l2;

    private TextField t2;

    private JButton b1;

    private ChatPanel chatPanel;

    private ClientService clientService;

    public MainFrame(ClientService clientService) throws HeadlessException {
        this.btnLogin = new JButton("Login");
        this.labelLogin = new JLabel("Please fill your name");
        this.inputLogin = new JTextField();
        this.t1 = new JTextArea(10, 10);
        this.l1 = new JLabel("Chat log");
        this.pane = new JScrollPane(t1);
        this.l2 = new JLabel("Message");
        this.t2 = new TextField(10);
        this.b1 = new JButton("Send");
        this.clientService = clientService;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!inputLogin.getText().equals("")) {
                    login(inputLogin.getText());
                }
            }
        });

        inputLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!inputLogin.getText().equals("")) {
                        login(inputLogin.getText());

                    }
                }
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!t2.getText().equals("")) {
                    clientService.sendMessage(t2.getText().trim());
                    t2.setText("");
                }
            }
        });

        t2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!t2.getText().equals("")) {
                        clientService.sendMessage(t2.getText().trim());
                        t2.setText("");
                    }
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientService.logout();
                e.getWindow().dispose();
            }
        });

        this.loginPanel = new LoginPanel(btnLogin, inputLogin, labelLogin);
        this.chatPanel = new ChatPanel(l1, t1, pane, l2, t2, b1);

        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public void login(String name) {

        clientService.login(name);
        this.remove(loginPanel);
        this.setTitle(name);
        this.setContentPane(chatPanel);
        this.repaint();
        this.t2.requestFocus();
        this.revalidate();
    }

    public void msgReceive(Message message) {
        t1.append(message.toString() + "\n");
        t2.setText("");
    }
}
