package logic;

import classes.Server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerThread extends Thread {
	
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private JTextArea textarea;
	private JTextField texto;
	
	public ServerThread(ObjectOutputStream salida, ObjectInputStream entrada, JTextArea textarea, JTextField texto) {
		this.salida = salida;
		this.entrada = entrada;
		this.textarea = textarea;
		this.texto = texto;
	}
	
	public void run(){
		try{
			while(true){
				if(Server.getClientList().size()>0){
					String recibido = entrada.readObject().toString();
					if(recibido.equals("*")){
						Server.getClientList().remove(salida);
						texto.setText("Conexiones actuales: "+ Server.getClientList().size());
						textarea.append("Se ha desconectado.\n");
					}else{
						textarea.append(recibido);
						for(int i=0;i<Server.getClientList().size();i++){
							ObjectOutputStream salida= Server.getClientList().get(i);
							salida.writeObject(recibido); //Enviar el mensaje a todos los clientes 
						}
					}
				}
			}
		}catch(Exception e){
			textarea.append("Error.\n");
		}
	}
	
}
