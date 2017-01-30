
package imclient;

import java.net.*;

/**
 *
 * @author shane
 */
public class IMClient {

    private static DatagramSocket socket;
    private static InetAddress addr;
    private static InetAddress serverIP;
    private static int port = 6776;
    private static byte[] sendData;
    private static byte[] receiveData;
    private static String serverName;

    public static void startClient() {
        Window.handleDialogEvent("Client Starting...");

        sendData = new byte[1024];

        try {
            socket = new DatagramSocket(port);
            addr = InetAddress.getLocalHost();
            Window.handleDialogEvent("IP Address: " + addr.getHostAddress());
            Window.handleDialogEvent("Host Name: " + addr.getHostName());
            Window.handleDialogEvent("Port number: " + port);
            Window.handleDialogEvent("Client Started. Connecting to server...");

            serverIP = InetAddress.getByName(IPInputWindow.serverAddr);

            String c = "Connected";
            sendData = c.getBytes();

            DatagramPacket initiate = new DatagramPacket(sendData, sendData.length, serverIP, port);
            socket.send(initiate);

            serverName = serverIP.getHostName();

            Runnable rc = new ReceivePackets();
            Thread receiver = new Thread(rc);
            receiver.start();

        } catch (Exception e) {
            //e.printStackTrace();
            Window.handleDialogEvent("Invalid IP Address entered");
        }

    }

    /**
     * Send a packet with a message
     *
     * @param input Message to be sent
     */
    public static void sendPacket(String input) {
        try {

            Window.handleDialogEvent("You: " + input);

            sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, port);
            socket.send(sendPacket);

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /*
     Creats a thread to listen for incoming packets. Each message received is 
     also sent to the dialog display
     */
    static class ReceivePackets implements Runnable {

        @Override
        public void run() {

            try {
                while (true) {
                    receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    String message = new String(receivePacket.getData());
                    Window.handleDialogEvent(serverName + " : " + message);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

    }

}
