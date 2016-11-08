/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class ClientRes extends Thread{
    Socket socket = null;
    Robot robot = null; // 用来捕获屏幕
    Rectangle rectangle = null; //用来表示屏幕尺寸
    private int count = 1;

    public ClientRes(Socket socket, Robot robot, Rectangle rect) {
        this.socket = socket;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    @Override
    public void run() {
        try {        
            ObjectOutputStream oos = null; 
            
//            ios = new ImageOutputStream();
//            ios.flushBefore(socket.getOutputStream());

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rectangle);
            
            
            while (true) {
                
                BufferedImage bufi = robot.createScreenCapture(rectangle);
                
                ImageIcon imageIcon = new ImageIcon(bufi);
                
                oos.writeObject(imageIcon);
                oos.reset();
                oos.flush();
//                ObjectOutputStream.drain();
                System.out.println("New Pic Sent..." + (count++));
                try {
                    //发送截图的间隔时间。控制sleep()，从而控制网络流量。
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientRes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }   } catch (IOException ex) {
            Logger.getLogger(ClientRes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
