package tcpprogramimg;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextArea;

public class Servermultithread implements Runnable {

    Socket serversocket;
    JTextArea txt_massage;
    BufferedReader fromClient;
    PrintWriter toclient;
    DataInputStream inputfromclient;
    DataOutputStream outputfromserver;
    File[] fileToSend;
    int number;
    int[] count;

    public Servermultithread(Socket serversocket, JTextArea txt_massage) {
        this.serversocket = serversocket;
        this.txt_massage = txt_massage;
    }

    @Override
    public void run() {
        try {
            outputfromserver = new DataOutputStream(serversocket.getOutputStream()); //ส่งข้อมูลไปที่ client
            NameFiletoClient(outputfromserver); // ส่งชื่อไป client

            inputfromclient = new DataInputStream(serversocket.getInputStream());
            fromClient = new BufferedReader(new InputStreamReader(inputfromclient)); //ตัวแปรอ่านค่าที่ตัว client ส่งมา

            while (true) {
                // fromClient รับชื่อไฟล์ที่ client ต้องการดาวน์โหลด
                String messageClient = fromClient.readLine();  // อ่านค่า string
                txt_massage.append(" Client choose file : " + messageClient + "\n");  //client ส่งข้อความอะไรมา  (massege), อยู่หน้า server         
                sendfileDowload(messageClient);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NameFiletoClient(DataOutputStream outtoclient) {  // ที่ส่งรายชื่อไฟล์ให้ client ดู
        try {
            File filepath = new File("D:\\OSdemo");
            fileToSend = filepath.listFiles(); //แสดงชื่อไฟล์ทั้งหมด (array)

            for (int i = 0; i < fileToSend.length; i++) {
                outtoclient.writeUTF(fileToSend[i].getName()); // ส่งชื่อไฟล์ทั้งหมดไปให้ client (fileName)
            }
            outtoclient.writeUTF("STOP");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendfileDowload(String messageClient) {

        int MyNumber = Integer.parseInt(messageClient) - 1;

        try {

            File filepath = new File(fileToSend[MyNumber].getPath());

            String fileName = filepath.getName();

            txt_massage.append("sendfile : " + fileName + " to client");

            toclient = new PrintWriter(outputfromserver, true);  //ส่งค่าคืนตัว client
            toclient.println(fileName); //ส่งชื่อไป client (fromserv)

            FileInputStream fileInputStream = new FileInputStream(filepath);

            DataOutputStream dataOutputStream = new DataOutputStream(outputfromserver);

            byte[] fileNameBytes = fileName.getBytes();
            byte[] fileBytes = new byte[(int) filepath.length()];  //ขนาดของไฟล์

            fileInputStream.read(fileBytes); //อ่านขนาดของไฟล์

            dataOutputStream.writeInt(fileNameBytes.length);  //ความยาวชื่อ  
            dataOutputStream.write(fileNameBytes);  // ส่งชื่อเป็นไบต์

            dataOutputStream.writeInt(fileBytes.length); // ขนาดของไฟล์
            dataOutputStream.write(fileBytes); // ไฟล์

        } catch (Exception e) {
            txt_massage.append("Not file");
        }
    }
}
