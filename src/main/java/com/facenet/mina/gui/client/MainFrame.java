package com.facenet.mina.gui.client;

import com.facenet.mina.service.ClientService;
import com.facenet.mina.entity.Message;
import com.facenet.mina.service.impl.ClientServiceImpl;
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

    /**
     * Init Main frame
     * @param clientService
     * @throws HeadlessException
     */
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

    /**
     * Using when click login button
     * @param name
     */
    public void login(String name) {
        clientService.login(name);
    }

    /**
     * Change From Login Frame to Chat Frame
     */
    public void changeFrameChat() {
        this.remove(loginPanel);
        this.setTitle(((ClientServiceImpl) clientService).getUsername());
        this.setContentPane(chatPanel);
        this.repaint();
        this.t2.requestFocus();
        this.revalidate();
    }

    /**
     * Handling incoming messages
     * @param message
     */
    public void msgReceived(Message message) {
        if (message != null) {
            if (message.isVisible()) {
                t1.append(message.toString() + "\n");
                t2.setText("");
            }

            if (message.equals(Message.MESSAGE_REJECT)) {
                JOptionPane.showMessageDialog(this,
                        message.getContent() + "\n Try again!",
                        "Alert",JOptionPane.WARNING_MESSAGE);
            }
            if (message.equals(Message.LOGIN_SUCCESS)) {
                clientService.sendRequestGetRoom();
                changeFrameChat();
            }
        }

    }

    /**
     * Close Client and
     */
    public void close() {
        System.exit(0);
    }

}
