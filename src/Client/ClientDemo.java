/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ClientDemo {
    //创建Socket客户端对象。
    static Socket socket = null;

    public static void main(String[] args) throws IOException, AWTException {
        String ip = JOptionPane.showInputDialog("请输入服务器IP：");
        String port = JOptionPane.showInputDialog("请输入服务端口：");

        Build(ip, Integer.parseInt(port));
    }

    public static void Build(String ip, int port) throws IOException, AWTException {
        //创建Robot对象。
        Robot robot = null;
        //创建Rectangle。
        Rectangle rectangle = null;
        
        //获取Socket流中的输出流。
        socket = new Socket(ip, port);
        System.out.println("客户端已连接(Client is ... connnected)");
        
        //创建GraphicsEnvironment对象，获取本地配置资源。
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        rectangle = new Rectangle(dim);
        
        robot = new Robot(gd);

        /**
         * 设置捕获器配件
         */
        JFrame frame = new JFrame("远程连接");
        JButton button = new JButton("捕获");
        
        frame.setBounds(500,500 , 150, 80);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(button);
        frame.setVisible(true);
        
        new ClientRes(socket, robot, rectangle);

    }

}
