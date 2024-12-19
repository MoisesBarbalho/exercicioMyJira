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
	
	public void removeDev(String nome) {
		for (Dev dev : this.equipe) {
			if (dev.getNome() == nome) {
				this.equipe.remove(dev);
			}
		}
	}
	public void addTask(String nome, int prazo) {
		Task novaTask = new Task(nome, prazo);
		this.backlog.add(novaTask);
	}
	public void removeTask(String nome) {
		for (Task task : this.backlog) {
			if (task.getTitulo() == nome) {
				this.backlog.remove(task);
			}
		}
	}
	}
	
}
