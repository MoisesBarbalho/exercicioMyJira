package exercicioMyJira;

import java.util.*;

public class MyJira {
	private String projeto;
	private Set<Task> backlog;
	private Set<Dev> equipe;
	
	public MyJira(String projeto) {
		this.projeto = projeto;
	}
	public String getProjeto() {
		return this.projeto;
	}
	public String mostraEquipe() {
		StringBuffer sb = new StringBuffer();
		sb.append("---Equipe---" + "\n");
		for (Dev dev : equipe) {
			sb.append("--Desenvolvedor: " + dev.toString() + "\n");
		}
		String equipeString = sb.toString();
		return equipeString;
	}
	public String relatorio(String status) {
		StringBuffer sb = new StringBuffer();
		sb.append("---Relatorio de Tarefas---" + "\n");
		
		if (status != null) {	
			for (Task tarefa : this.backlog) {
				if(tarefa.getStatus() == status) {
					sb.append(tarefa.toString() + "\n");
				}
			}
		} else {
			sb.append("--Não-iniciadas--" + "\n");
			this.relatorio("Não-iniciada");
			sb.append("--Em andamento--" + "\n");
			this.relatorio("Em andamento");
			sb.append("--Finalizadas--" + "\n");
			this.relatorio("Finalizada");
		}
		String relatorio = sb.toString();
		return relatorio;
	}
	public String relatorio() {
		return this.relatorio(null);
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
