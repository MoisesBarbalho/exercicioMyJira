package exercicioMyJira;

import java.util.*;

public class Dev {
	private String nome;
	private String time;
	private ArrayList<Task> tarefas;
	
	public Dev(String nome, String time) {
		this.nome = nome;
		this.time = time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void addTask(Task tarefa) {
		this.tarefas.add(tarefa);
	}
	public void removeTask(Task tarefa) {
		this.tarefas.remove(tarefa);
	}
	public String getNome() {
		return this.nome;
	}
	public String getTime() {
		return this.time;
	}
	public ArrayList<Task> getTarefas() {
		return this.tarefas;
	}
	
}
