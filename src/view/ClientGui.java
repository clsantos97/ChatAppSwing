package view;



import classes.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;

public class ClientGui extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnEnviar;
	private JButton btnSalir;
	private String nombre;
	private Client cliente;

	/**
	 * Create the frame.
	 */
	public ClientGui(Socket socket, String nombre) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 517, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 351, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnEnviar = new JButton("ENVIAR");
		btnEnviar.setBounds(380, 10, 119, 23);
		contentPane.add(btnEnviar);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 42, 489, 283);
		contentPane.add(textArea);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setBounds(193, 336, 143, 32);
		contentPane.add(btnSalir);
		
		btnEnviar.addActionListener((ActionListener) this);
		btnSalir.addActionListener((ActionListener) this);
		
		this.nombre= nombre;
		
		try{
			cliente = new Client(socket, textArea, textField, btnEnviar);
			cliente.sendMsg(nombre+" se ha conectado.\n");
			
		}catch (Exception e) {
			textArea.append("Error en la conexi�n \n");
			System.exit(0);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		
		if(boton == btnEnviar){
			String mensaje = this.nombre+ " --> "+ textField.getText() + "\n";
			textField.setText(null);
			cliente.sendMsg(mensaje);
			
		}else{
			cliente.sendMsg("*");
			System.exit(0);
			dispose();
		}
	}
}