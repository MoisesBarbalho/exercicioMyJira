package exercicioMyJira;
import java.util.*;
/**
 *Classe que representa um sistema de administração de projeto. Possui como atributo
 *o nome do projeto, a equipe que o compoe e o conjunto de tarefas associados a ele -
 *o chamado backlog. A equipe é um conjunto composto por desenvolvedores (Dev),
 * que estão definidos em sua própria classe. Assim como o backlog é um conjunto
 * composto por tarefas (Task), definidas também em sua própria classe.
 *
 *@author MoisesBarbalho
 */
public class MyJira {
	private String projeto;
	private Set<Task> backlog;
	private Set<Dev> equipe;
	/**
	 * Construtor de classe. Instancia um objeto MyJira a partir do nome dado ao projeto,
	 * inicializando os conjuntos-atributos "equipe" e "backlog".
	 * 
	 * @param projeto será o nome do projeto, pelo qual será identificado.
	 */
	public MyJira(String projeto) {
		this.projeto = projeto;
		this.backlog = new HashSet<Task>();
		this.equipe = new HashSet<Dev>();
	
	}
	/**
	 * Método acessador getter do nome do projeto em questão.
	 * @return retorna o nome do projeto, armazenado no atributo 'projeto'.
	 */
	public String getProjeto() {
		return this.projeto;
	}
	/**
	 * Método acessador que retorna todo o conjunto equipe na forma de String,
	 * utilizando para isso o método toString sobrescrito da classe Dev. Para isso,
	 * utiliza também uma instância StringBuffer.
	 * 
	 * @return retorna na forma de texto todo o conjunto de desenvolvedores (equipe).
	 */
	public String mostraEquipe() {
		StringBuffer sb = new StringBuffer();
		sb.append("---Equipe---" + "\n");
		for (Dev dev : equipe) {
			sb.append("--Desenvolvedor: " + dev.toString() + "\n");
		}
		String equipeString = sb.toString();
		return equipeString;
	}
	/**
	 * Método para acessar as tarefas do backlog uma a uma, a partir do método toString
	 * da classe Task. Filtra as tarefas com base em seu status (opcionalmente).
	 * @param status é o status das tarefas que se quer exibir, pode assumir os valores "Não-iniciada",
	 * "Em andamento" ou "Finalizada".
	 * @return retorna na forma de texto a descrição de cada uma das tarefas que passou no filtro.
	 */
	public String relatorio(String status) {
		if(backlog.isEmpty()) return "Não há tarefas no backlog";
		StringBuffer sb = new StringBuffer();
		sb.append("---Relatorio de Tarefas---" + "\n");
		
		if (status != null) {	
			for (Task tarefa : this.backlog) {
				if(tarefa != null && tarefa.getStatus() == status) {
					sb.append(tarefa.toString() + "\n");
				}
			}
			if (sb.isEmpty()) return "Não há tarefas com esse status.";
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
	/**
	 * Método para acessar todas as tarefas contidas no backlog. Passa por
	 * cada uma delas e retorna sua descrição a partir do seu método toString,
	 * as separando com base no seu status.
	 * @return retorna em formato de texto (String) a descrição de cada uma das 
	 * tarefas contidas no backlog.
	 */
	public String relatorio() {
		return this.relatorio(null);
	}
	/**
	 * Método adicionador, cria uma instância da classe Dev e a adiciona na equipe.
	 * @param nome é o nome do novo desenvolvedor, passado como parâmetro para o 
	 * construtor de classe Dev na construção dessa nova instância.
	 * @param time é o time do novo desenvolvedor, passado como parâmentro para o 
	 * construtor da classe Dev na construção dessa nova instância.
	 */
	public void addDev(String nome, String time) {
		Dev novoDev = new Dev(nome, time);
		for(Dev antigoDev : equipe) {
			if (antigoDev.equals(novoDev)) return;
		}
		this.equipe.add(novoDev);
	}
	/**
	 * Método removedor
	 * @param nome
	 */
	public void removeDev(String nome) {
		if(nome != null) {
			for (Dev dev : this.equipe) {
				if (dev != null && dev.getNome() == nome) {
					this.equipe.remove(dev);
				}
			}
		} else {
			this.equipe.clear();
		}
	}
	public void removeDev() {
		this.removeDev(null);
	}
	public void addTask(String nome, int prazo) {
		Task novaTask = new Task(nome, prazo);
		for (Task task : this.backlog) {
			if(task.equals(novaTask)) return;
		}
		this.backlog.add(novaTask);
	}
	public void removeTask(String nome) {
		if(this.backlog.isEmpty()) return;
		
		if(nome == null) {
			this.backlog.clear();
			return;
		}
		for (Task task : this.backlog) {
			if (task != null && task.getTitulo() == nome) {
				this.backlog.remove(task);
			}
		}
	}
	public void removeTask() {
		this.removeTask(null);
	}
	public void atribuiTask(String dev, String task) {
		if(equipe.isEmpty() || backlog.isEmpty()) return;
		
		for(Dev developer : equipe) {
			if(developer != null && developer.getNome() == dev) {
				for(Task tarefa : backlog) {
					if(tarefa != null && tarefa.getTitulo() == task) {
						developer.addTask(tarefa);
					}
				}
			}
		}
	}
	public String quaisTasks(String dev) {
		if(this.equipe.isEmpty()) return "Equipe Vazia";
		for(Dev developer : equipe) {
			if(developer != null && developer.getNome() == dev) {
				return developer.mostraTarefas();
			}
		}
	return "Desenvolvedor não encontrado";
	}
}
