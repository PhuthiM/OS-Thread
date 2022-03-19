package tcpprogramimg;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.io.*;
import javax.swing.text.DefaultCaret;

//read = อ่านหรือว่ารับเข้ามา , write = เขียนหรือว่าส่งออกไป
//คลาสสร้างหน้าต่างของ server
public class Server extends JFrame {

    JTextArea txt_massage = new JTextArea();
    BufferedReader fromclient;
    DefaultCaret caret;
    PrintWriter toclient;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(txt_massage), BorderLayout.CENTER);
        setSize(850, 500);
        txt_massage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        setDefaultCloseOperation(EXIT_ON_CLOSE); //ตัวปิดหน้าต่าง , ปิดการรันโปรแกรม
        setTitle("SERVER");
        setResizable(false); //ไม่สามารถปรับขนาดได้
        setLocationRelativeTo(null); //ทำให้อยู่ตรงกลาง
        setVisible(true);
        caret = (DefaultCaret) txt_massage.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  //สกอเลื่อน frame
        runner();
    }

    //เรียกให้เมธอด Server ทำงาน
    private void runner() {
        int num = 0;
        //เมื่อรันจะแสดง
        txt_massage.append("Status active.\nWait Client connection...\n");
        try {
            //สร้าง socket เพื่อสร้างเส้นทางการติดต่อสื่อสาร
            ServerSocket myServer = new ServerSocket(6789);
            while (true) {
                try {
                    Socket connection = myServer.accept(); // รอให้ client เชื่อมต่อ num++;
                    Thread serverthread = new Thread(new Servermultithread(connection, txt_massage));
                    serverthread.start();

                    ++num;
                    txt_massage.append("Client connection complete\n >> Client No :" + num + " Client port : " + connection.getPort() + "\n");  //ถ้ามีตัว client มาเชื่อมจะพิมส่วนนี้
                } catch (Exception e) {
                    System.out.println("Cannot create this connection");

                }
            }
        } catch (IOException ex) {
            System.exit(1);
        }
    }
}
