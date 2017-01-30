
package server;

import java.net.*;
import java.util.HashSet;

/**
 *
 * @author shane
 */
public class IMServer {

    private static DatagramSocket socket;
    private static InetAddress addr;
    private static HashSet<SocketAddress> group;
    private static boolean firstTime = true;
    private static int port = 6776;
    private static byte[] rcvData;
    private static byte[] sendData;

    /**
     * Starts the server.
     */
    public static void startServer() {
        Window.handleDialogEvent("Server starting...");

        try {
            group = new HashSet();
            socket = new DatagramSocket(port);
            addr = InetAddress.getLocalHost();
            Window.handleDialogEvent("IP Address: " + addr.getHostAddress());
            Window.handleDialogEvent("Host Name: " + addr.getHostName());
            Window.handleDialogEvent("Port Number: " + port);
            Window.handleDialogEvent("Server started. Listening for clients...");

            Runnable rc = new ReceivePackets();
            Thread receiver = new Thread(rc);
            receiver.start();

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /*
     This thread is used to 'listen' for incoming packets
     */
    static class ReceivePackets implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    rcvData = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(rcvData, rcvData.length);
                    socket.receive(packet);

                    group.add(packet.getSocketAddress());

                    for (SocketAddress s : group) {
                        if (firstTime == true) {
                            String gr = "Connection Successful";
                            DatagramPacket greet = new DatagramPacket(gr.getBytes(), gr.length(), s);
                            socket.send(greet);
                            firstTime = false;
                        }

                        String message = new String(packet.getData());
                        Window.handleDialogEvent(packet.getAddress() + " : " + message);
                    }

                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
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

            // for each loop used to send the packet to all clients
            for (SocketAddress s : group) {
                DatagramPacket sendPkt = new DatagramPacket(sendData, sendData.length, s);
                socket.send(sendPkt);
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

}
