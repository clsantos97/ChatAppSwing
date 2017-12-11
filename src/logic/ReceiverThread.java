package logic;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReceiverThread extends Thread {

	private ObjectInputStream entrada;
	private JTextField texto;
	private JTextArea textArea;
	private JButton btnEnviar;
	
	public ReceiverThread(ObjectInputStream entrada, JTextField texto, JTextArea textArea, JButton jButton) {
		this.entrada = entrada;
		this.texto = texto;
		this.textArea = textArea;
		this.btnEnviar=jButton;
	}
	
	public void run(){
		String mensaje = null;
		while(true){
			try {
				mensaje = entrada.readObject().toString();
				if(mensaje .equals("*")){  //Cuando cerremos el servidor le mandaremos al cliente un * y este al recibirlo como mensaje se cierra  
					textArea.append("El servidor se ha detenido");
					System.exit(0);
					break;
				}
				else{
					textArea.append(mensaje);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			btnEnviar.setEnabled(true);
		}
	}
	
	
	
	
}

