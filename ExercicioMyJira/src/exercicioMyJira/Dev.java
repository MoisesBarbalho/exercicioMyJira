package exercicioMyJira;

import java.util.*;

public class Dev {
	private String nome;
	private String time;
	private Set<Task> tarefas;
	
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
	public Set<Task> getTarefas() {
		return this.tarefas;
	}
	public Task getTarefa(String titulo) {
		for (Task tarefa : this.tarefas) {
			if (tarefa != null && tarefa.getTitulo() == titulo) {
				return tarefa;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return this.nome + " : " + this.time;
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Dev)) return false;
		if (o == this) return true;
		
		Dev oDev = (Dev) o;
		return (oDev.getNome() == this.nome);
	}
	@Override
	public int hashCode() {
		int hash = 19;
		return 31 * hash + this.nome.hashCode();
	}
}
