package tcpprogramimg;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.text.DefaultCaret;

public class Client extends JFrame implements ActionListener {

    JTextArea txt_massage = new JTextArea();
    JTextField txt_send = new JTextField();
    DefaultCaret caret;
    PrintWriter sendtoserver;
    BufferedReader recivefromServer;
    private static DataInputStream infromserver;
    private static String fileName;
    private static String[] name;
    Scanner sc;
    Servermultithread svmt;

    public static void main(String[] args) throws IOException {
        new Client();
    }

    public Client() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("select file : "), BorderLayout.WEST);  // ซ้ายไปขวา
        panel.add(txt_send, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(txt_massage), BorderLayout.CENTER);
        setSize(850, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CLIENT");
        Font f = new Font("Tahoma", Font.PLAIN, 20);
        txt_send.setFont(f);
        txt_massage.setFont(f);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        txt_send.addActionListener(this);
        caret = (DefaultCaret) txt_massage.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  //สกอเลื่อน frame
        runner();
    }

    public void runner() throws IOException {
        // start connetion server 
        Socket myClient = new Socket("localhost", 6789);

        txt_massage.append("connection w/Server Complete\n");
        try {
            infromserver = new DataInputStream(myClient.getInputStream());
            int num = 0;
            while (!(fileName = infromserver.readUTF()).equalsIgnoreCase("STOP")) {   //ชื่อไฟล์แสดง gui (fileToSend)
                name = fileName.split(", ");
                for (int i = 0; i < name.length; ++i) {
                    txt_massage.append("Select " + (num + 1) + " for download : " + name[i] + "\n");
                    num++;
                }
            }

            sendtoserver = new PrintWriter(myClient.getOutputStream(), true);

            recivefromServer = new BufferedReader(new InputStreamReader(myClient.getInputStream()));

            while (true) {
                String fromserv = recivefromServer.readLine();
                txt_massage.append("Choose Filename : " + fromserv + "\n");  // ค่าที่มาจาก server (printfilename) ,แสดงหน้า client 
                recivefile(myClient); //รับไฟล์ในการดาวโหลด 
            }

        } catch (IOException e) {
            System.err.println("yes");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() instanceof JTextField) {
            if (!txt_send.getText().isEmpty()) {
                String massege = txt_send.getText();
                txt_send.setText(""); //ช่องที่เติม set เป็นค่าว่าง
                sendtoserver.println(massege); //ข้อความที่ส่งไปให้ server (messageClient) ,แสดงหน้า server 
            }
        }
    }

    public void recivefile(Socket myClient) {

        txt_massage.append("recive success : ");

        while (true) {
            try {
                DataInputStream dataInputStream = new DataInputStream(myClient.getInputStream());

                int fileNameLength = dataInputStream.readInt();  //ความยาวชื่อ (fileNameBytes.length))

                if (fileNameLength > 0) {

                    byte[] fileNameBytes = new byte[fileNameLength];  //ชื่อไฟล์  
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length); // (fileNameBytes)

                    String fileName = new String(fileNameBytes);

                    int fileBytesLength = dataInputStream.readInt(); // (fileBytes.length)

                    if (fileBytesLength > 0) {
                        byte[] fileBytes = new byte[fileBytesLength];

                        dataInputStream.readFully(fileBytes, 0, fileBytes.length); // (fileBytes)

                        File fileToDownload = new File(fileName);
                        try {
                            // Create a stream to write data to the file.
                            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                            // Write the actual file data to the file.

                            fileOutputStream.write(fileBytes);

                            txt_massage.append(fileName + "\n");

                            // Close the stream
                            fileOutputStream.close();

                        } catch (IOException ex) {

                            System.exit(1);
                        }
                    }
                }
            } catch (Exception e) {

                System.exit(1);
            }
        }
    }
}
