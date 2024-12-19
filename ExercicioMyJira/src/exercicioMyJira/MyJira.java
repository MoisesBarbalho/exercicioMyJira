package exercicioMyJira;

import java.util.*;

public class MyJira {
	private String projeto;
	private ArrayList<Task> backlog;
	private ArrayList<Dev> equipe;
	
	public MyJira(String projeto) {
		this.projeto = projeto;
	}
	
	public void addDev(String nome, String time) {
		Dev novoDev = new Dev(nome, time);
		this.equipe.add(novoDev);
	}
	
	public void removeDev(String nome, String time) {
		Dev demitido = new Dev(nome, time);
		for (Dev dev : this.equipe) {
			if (demitido.equals(dev)) {
				this.equipe.remove(dev);
			}
		}
	}
}
