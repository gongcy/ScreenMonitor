/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Serever;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ServerRes extends Thread{
    //    private JPanel jp = null;
    private Socket socket = null;
    private JFrame frame = null;
    private JPanel jp = new JPanel();
    private int count = 1;

    public ServerRes(Socket socket, JFrame jf) {
        this.socket = socket;
        this.frame = jf;
        start();
    }

    @Override
    public void run() {

        Rectangle rectangle = null;

        ObjectInputStream ois = null;

        frame.setLayout(new BorderLayout());
//        frame.
//        frame.getContentPane().add(jp, BorderLayout.CENTER);
        frame.setBounds(770, 0, 600, 600);
        frame.add(jp);

        jp.setFocusable(true);
        frame.setVisible(true);

        try {

            ois = new ObjectInputStream(socket.getInputStream());
            rectangle = (Rectangle) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }

        try {

            while (true) {
                ImageIcon imageIcon = (ImageIcon) ois.readObject();
                System.out.println("Receice New Pic" + (count++));
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(jp.getWidth(), jp.getHeight(), Image.SCALE_FAST);

                Graphics graphics = jp.getGraphics();
                graphics.drawImage(image, 0, 0, jp.getWidth(), jp.getHeight(), jp);
            }
            
            
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ServerRes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
