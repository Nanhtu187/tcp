package com.facenet.mina.GUI.client;

import javax.swing.*;
import java.awt.*;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class ChatPanel extends JPanel{

    private JLabel l1;

    private JTextArea t1;

    private JScrollPane pane;

    private JLabel l2;

    private TextField t2;

    private JButton b1;


    /**
     * Init Panel of Chat Room
     * @param l1
     * @param t1
     * @param pane
     * @param l2
     * @param t2
     * @param b1
     */
    public ChatPanel(JLabel l1, JTextArea t1, JScrollPane pane, JLabel l2, TextField t2, JButton b1) {
        Panel panel1 = new Panel();
        Panel panel2 = new Panel();
        this.l1 = l1;
        this.t1 = t1;
        this.pane = pane;
        this.l2 = l2;
        this.t2 = t2;
        this.b1 = b1;

        t1.setWrapStyleWord(true);
        t1.setEditable(false);
        t1.setLineWrap(true);
        panel1.setLayout(new GridBagLayout());
        panel2.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        panel1.add(l1, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        panel1.add(pane, gc);

        GridBagConstraints gc2 = new GridBagConstraints();
        gc2.fill = GridBagConstraints.HORIZONTAL;
        gc2.weightx = 1;
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.ipadx = 10;
        panel2.add(l2, gc2);

        gc2.gridx = 1;
        gc2.gridy = 0;
        panel2.add(t2, gc2);

        gc2.gridx = 1;
        gc2.gridy = 1;
        panel2.add(b1, gc2);
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
    }
}
