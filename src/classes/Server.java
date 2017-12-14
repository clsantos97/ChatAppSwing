package classes;

import logic.ServerThread;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Server Class
 * @author Carlos
 */
public class Server extends Thread {

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final int PORT = 5889;
    private final int MAX_CONN = 2;

    private JTextArea textArea;
    private JTextField text;
    private JList usersList;
    private DefaultListModel usersListModel;

    private ServerThread serverThread;
    static ArrayList<ObjectOutputStream> clientList = new ArrayList<>();

    public Server(JTextArea textArea, JTextField text, DefaultListModel usersListModel, JList usersList) {
        this.textArea = textArea;
        this.text = text;
        this.usersListModel=usersListModel;
        this.usersList=usersList;
    }

    public void run() {
        ServerSocket server = null;
        Socket client = null;

        ObjectInputStream input = null;
        ObjectOutputStream output = null;

        try {
            server = new ServerSocket(PORT);

            while (true) {
                client = server.accept();
                output = new ObjectOutputStream(client.getOutputStream());
                input = new ObjectInputStream(client.getInputStream());
                
                
                System.out.println();
                System.out.println(output.toString());
                
                clientList.add(output);

                if (clientList.size() <= MAX_CONN) {
                    serverThread = new ServerThread(output, input, textArea, text);
                    serverThread.start();

                } else {
                    textArea.append("\nSe ha alcanzado el limite de conecciones.\n");

                }
            }

        } catch (IOException e) {
            logger.severe("Ha ocurrido un error con el funcionamiento del servidor.");
            e.printStackTrace();
        }

    }

    /**
     * Turn off the server
     */
    public void disconnect() {

        for (int i = 0; i < clientList.size(); i++) {
            ObjectOutputStream os = clientList.get(i);
            try {
                os.writeObject("*");
            } catch (IOException e) {
                logger.severe("Ha ocurrido un error al intentar desconectar.");
                //e.printStackTrace();
            }
        }
    }
    
    /**
     * Return list of clients
     * @return 
     */
    public static ArrayList<ObjectOutputStream> getClientList() {
        return clientList;
    }

}
