package classes;

import logic.ReceiverThread;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Client Class
 *
 * @author Carlos
 */
public class Client {

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private static final int PORT = 5889;
    private static final String HOST = "127.0.0.1";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ReceiverThread threadIn;
    private Socket socket;
    private JTextArea textArea;
    private JTextField text;
    private JButton btnSend;
    
    
    public Client(Socket socket, JTextArea textArea, JTextField text, JButton btnSend) throws IOException {

        this.socket = socket;
        this.textArea = textArea;
        this.text = text;
        this.btnSend = btnSend;

        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        threadIn = new ReceiverThread(input, text, textArea, btnSend);
        threadIn.start();

    }

    /**
     * Returns port
     *
     * @return server port
     */
    public static int getPort() {
        return PORT;
    }

    /**
     * Returns host
     *
     * @return HOST server ip
     */
    public static String getHost() {
        return HOST;
    }

    /**
     * Adds time to msg and then sends it.
     * @param msg Written msg
     */
    public void sendMsg(String msg) {
        try {

            System.out.println(dtf.format(LocalDateTime.now())); //2016/11/16 12:08:43
            
            // It's formated to show only time, but it can be changed.
            msg= dtf.format(LocalDateTime.now())+" - "+msg;
            
            output.writeObject(msg);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.severe("Ha ocurrido un error al enviar el mensaje: " + msg);
        }
    }

}
