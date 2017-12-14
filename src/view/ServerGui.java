package view;

import classes.Server;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

/**
 * ServerGui Class
 *
 * @author Carlos
 */
public class ServerGui extends JFrame {

    private JPanel contentPane;
    private JTextField txtServer;
    private JTextArea txtAreaChat;
    private JLabel lblTitle;
    private Server server;
    private JButton btnSend;
    private JList usersList;
    private DefaultListModel usersListModel;
    private JScrollPane listScroller;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerGui frame = new ServerGui();
                    
                    frame.addWindowListener(new WindowAdapter() {

                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
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

        initUsersList();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 520, 434);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitle = new JLabel("Chat Admin");
        lblTitle.setFont(new Font("Stencil Std", Font.ITALIC, 20));
        lblTitle.setBounds(20, 10, 200, 30);
        contentPane.add(lblTitle);

        txtServer = new JTextField();
        txtServer.setEditable(true);
        txtServer.setBounds(20, 355, 345, 30);
        txtServer.setColumns(10);
        contentPane.add(txtServer);
        

        txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        txtAreaChat.setBounds(20, 42, 345, 310);
        txtAreaChat.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(txtAreaChat);

        btnSend = new JButton("Enviar");
        btnSend.setFont(new Font("Stencil Std", Font.ITALIC, 12));
        btnSend.setBounds(370, 355, 120, 30);
        contentPane.add(btnSend);
        contentPane.add(listScroller, BorderLayout.EAST);

        server = new Server(txtAreaChat, txtServer, usersListModel, usersList);
        server.start();

        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO SEND MSG AS SERVER
            }
        });
        
        txtServer.requestFocusInWindow();

    }

    private void initUsersList() {
        usersListModel = new DefaultListModel();
        usersList = new JList(usersListModel); //data has type Object[]
        usersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        usersList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        usersList.setVisibleRowCount(-1);

        listScroller = new JScrollPane(usersList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setBounds(370, 42, 120, 310);
        listScroller.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public JList getUsersList() {
        return usersList;
    }

    public DefaultListModel getUsersListModel() {
        return usersListModel;
    }

}
