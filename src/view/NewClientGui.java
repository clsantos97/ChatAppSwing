package view;

import classes.Client;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * NewClientGui Class Asks for nickname, nickname validations.
 *
 * @author Carlos
 */
public class NewClientGui extends JFrame {

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private JPanel contentPane;
    private JTextField txtNickname;
    private JLabel lblNickname;
    private JButton btnOk;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NewClientGui frame = new NewClientGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define new client frame. (Aks for nickname)
     */
    public NewClientGui() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 358, 197);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblNickname = new JLabel("Nickname:");
        lblNickname.setFont(new Font("Utsaah", Font.ITALIC, 17));
        lblNickname.setBounds(10, 11, 188, 14);
        contentPane.add(lblNickname);

        txtNickname = new JTextField();
        txtNickname.setBounds(55, 55, 234, 29);
        contentPane.add(txtNickname);
        txtNickname.setColumns(10);

        btnOk = new JButton("Confirmar");
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnOk.setBounds(123, 95, 106, 29);
        contentPane.add(btnOk);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!txtNickname.getText().isEmpty() && !txtNickname.getText().trim().contains(" ")) {
                    try {
                        Socket s = new Socket(Client.getHost(), Client.getPort());

                        if (nicknameExists(txtNickname.getText(), s)) {
                            ClientGui c = new ClientGui(s, txtNickname.getText());
                            c.setVisible(true);
                            dispose();
                        }

                    } catch (UnknownHostException e1) {
                        //JOptionPane.showMessageDialog(null, "No se puede encontrar el Host.");
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        //JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
                        e1.printStackTrace();
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "El nickname es obligatorio y no puede contener espacios vacios.");
                    //lblError.setText("El nickname es obligatorio y no puede contener espacios vacios.");
                }
            }
        });

    }

    private boolean nicknameExists(String nickname, Socket socket) {
        boolean exists = true;

        try {

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            output.writeObject(nickname);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.severe("El usuario < " + nickname + " > ya existe.");
        }

        return exists;
    }
}
