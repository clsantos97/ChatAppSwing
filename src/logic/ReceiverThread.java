package logic;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReceiverThread extends Thread {

	private ObjectInputStream input;
	private JTextField text;
	private JTextArea textArea;
	private JButton btnSend;
	
	public ReceiverThread(ObjectInputStream input, JTextField text, JTextArea textArea, JButton btnSend) {
		this.input = input;
		this.text = text;
		this.textArea = textArea;
		this.btnSend=btnSend;
	}
	
	public void run(){
		String mensaje = null;
		while(true){
			try {
				mensaje = input.readObject().toString();
				if(mensaje .equals("*")){ 
					textArea.append("El servidor se ha detenido");
					System.exit(0);
					break;
				}
				else{
					textArea.append(mensaje);
				}
				
			} catch (IOException e) {
				// TODO exception
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO exception
				//e.printStackTrace();
			}
			btnSend.setEnabled(true);
		}
	}
	
	
	
	
}

