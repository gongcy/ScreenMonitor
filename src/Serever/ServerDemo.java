/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Serever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ServerDemo {

    private String ip = null;
//    private int count = 1;
    //搭建UI界面
    private static JFrame frame = new JFrame("远程画面");
    private JPanel jp = new JPanel();

    public static void main(String args[]) {
        String port = JOptionPane.showInputDialog("输入监听端口：");
        
        
//        System.out.println("程序运行");
        
        
        Build(Integer.parseInt(port));
    }

    public static void Build(int port) {

        try {
            ServerSocket ss = new ServerSocket(port);

            //初始化，显示界面Screen。
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(770, 0,600, 600);//大小位置和while循环的位置大小保持一致。
            frame.setVisible(true);//保证在开启服务端，就会看到显示界面（虽然会缺少画面）
//            
            while (true) {

                Socket s = ss.accept();
             //获取客户端的IP地址。这个还是很有必要的。
//            Socket s = ss.accept();
//            String ip = ss.getInetAddress().getHostAddress();
   
//                if(!ip.equals((String)s.getInetAddress().getHostAddress())){
                String ip = s.getInetAddress().getHostAddress();
//                }
                //显示客户端地址。明确客户机的来源。
                System.out.println("客户端" + ip + "连接到服务器！");

                new ServerRes(s, frame);
            }
        } catch (IOException ex) {
        }
    }

}
