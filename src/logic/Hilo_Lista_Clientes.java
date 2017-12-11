package logic;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JTextArea;

public class Hilo_Lista_Clientes extends Thread {

	private JTextArea listanombres;
	private static ArrayList<String> nombres;
	private static ArrayList<String> nombres2 = new ArrayList<String>();

	public Hilo_Lista_Clientes(JTextArea listanombres, ArrayList<String> nombres) {
		this.nombres = nombres;
		this.listanombres = listanombres;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("Se ejecuto el hilo");
		while (true) {
			listanombres.setText("");

			if (!nombres.isEmpty()) {
				nombres2.clear();
				for (int x = 0; x < nombres.size(); x++) {

					nombres2.add(nombres.get(x));

				}
				Collections.sort(nombres2);
				for (int x = 0; x < nombres2.size(); x++) {

					listanombres.setText(listanombres.getText() + nombres2.get(x) + "\n");
					// System.out.println("Nombre:"+nombres.get(x));

				}
			} else {
				System.out.print("");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}