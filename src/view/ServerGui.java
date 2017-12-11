package view;

import classes.Server;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;

/**
 * ServerGui Class
 *
 * @author Carlos
 */
public class ServerGui extends JFrame {

    private JPanel contentPane;
    private JTextField txtServer;
    private JTextArea txtAreaChat;
    private Server server;
    private JButton btnExit;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerGui frame = new ServerGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define the server frame.
     */
    public ServerGui() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 520, 434);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtServer = new JTextField();
        txtServer.setEditable(false);
        txtServer.setBounds(20, 11, 465, 20);
        contentPane.add(txtServer);
        txtServer.setColumns(10);

        txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        txtAreaChat.setBounds(20, 42, 465, 310);
        contentPane.add(txtAreaChat);

        btnExit = new JButton("Salir");
        btnExit.setFont(new Font("Stencil Std", Font.ITALIC, 12));
        btnExit.setBounds(162, 363, 184, 27);
        contentPane.add(btnExit);

        server = new Server(txtAreaChat, txtServer);
        server.start();

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server.disconnect();
                System.exit(0);
            }
        });

    }
}
