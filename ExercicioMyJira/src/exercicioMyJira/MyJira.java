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
	private String projeto; //nome do projeto
	private Set<Task> backlog; //conjunto de tarefas
	private Set<Dev> equipe; //conjunto de desenvolvedores
	/**
	 * Construtor de classe. Instancia um objeto MyJira a partir do nome dado ao projeto,
	 * inicializando os conjuntos-atributos "equipe" e "backlog".
	 * 
	 * @param projeto será o nome do projeto, pelo qual será identificado.
	 */
	public MyJira(String projeto) {
		this.projeto = projeto;
		this.backlog = new HashSet<Task>(); //inicialização dos Sets, por padrão, como HashSet.
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
		if (this.equipe.isEmpty()) return "A equipe está vazia"; //testa para o caso de não haver devs adicionados.
		StringBuffer sb = new StringBuffer(); //objeto StringBuffer para criar a String de retorno;
		sb.append("---Equipe---" + "\n");
		for (Dev dev : equipe) { //Para cada desenvolvedor na equipe...
			sb.append("--Desenvolvedor: " + dev.toString() + "\n");  //...Adiciona sua representação String à StringBuffer.
		}
		String equipeString = sb.toString(); //converte objeto StringBuffer para String.
		return equipeString; //devolve a String.
	}
	/**
	 * Método para acessar as tarefas do backlog uma a uma, a partir do método toString
	 * da classe Task. Filtra as tarefas com base em seu status (opcionalmente).
	 * @param status é o status das tarefas que se quer exibir, pode assumir os valores "Não-iniciada",
	 * "Em andamento" ou "Finalizada".
	 * @return retorna na forma de texto a descrição de cada uma das tarefas que passou no filtro.
	 */
	public String relatorio(String status) {
		if(backlog.isEmpty()) return "Não há tarefas no backlog"; //Testa para o caso de não haver tarefas adicionadas.
		StringBuffer sb = new StringBuffer(); //Objeto sb instanciado para auxilio na construção da String de retorno.
		sb.append("---Relatorio de Tarefas---" + "\n");
		
		if (status != null) {	//testa para o caso de não haver status, o que será utilizado no método de mesmo nome sem parâmetro.
			for (Task tarefa : this.backlog) { //Para cada tarefa no backlog...
				if(tarefa != null && tarefa.getStatus() == status) { //...Se tiver esse status...
					sb.append(tarefa.toString() + "\n"); //...É adicionada à StringBuffer.
				}
			}
			if (sb.isEmpty()) return "Não há tarefas com esse status."; //Testa para o caso de não haver tarefas com o status especificado.
		} else {
			sb.append("--Não-iniciadas--" + "\n");
			this.relatorio("Não-iniciada"); //Invoca o próprio método, dessa vez com parâmetros;
			sb.append("--Em andamento--" + "\n");
			this.relatorio("Em andamento");
			sb.append("--Finalizadas--" + "\n");
			this.relatorio("Finalizada");
		}
		String relatorio = sb.toString(); //converção da StringBuffer para String.
		return relatorio; //devolve a String construída, seja constando apenas um Status ou todos eles.
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
	public void cadastraDev(String nome, String time) {
		Dev novoDev = new Dev(nome, time);
		for(Dev antigoDev : equipe) {
			if (antigoDev.equals(novoDev)) return; //Testa para o caso do desenvolvedor já estar cadastrado.
		}
		this.equipe.add(novoDev);
	}
	/**
	 * Método removedor. Remove o desenvolvedor (instancia da classe Dev)
	 * da equipe a partir de seu nome de identificação.
	 * @param nome nome do desenvolvedor a ser removido.
	 */
	public void removeDev(String nome) {
		if(nome != null) { //Testa se o parâmetro foi passado, possibilitando a chamada sem parâmetro.
			for (Dev dev : this.equipe) { //Para cada desenvolvedor na equipe...
				if (dev != null && dev.getNome() == nome) { //...Se existir e tiver esse nome...
					this.equipe.remove(dev); //...É removido.
				}
			}
		} else {
			this.equipe.clear(); //Caso em que não foi passado parâmetro. Limpa o conjunto equipe, excluindo todos os desenvolvedores.
		}
	}
	/**
	 * Quando o método removedor removeDev é invocado sem parâmetro, ele
	 * limpa o conjunto equipe, removendo todos os desenvolvedores.
	 */
	public void removeDev() {
		this.removeDev(null); //Chamada do método com parâmetro nulo.
	}
	/**
	 * Método para adicionar uma tarefa ao backlog. Cria uma instância
	 * da classe Task a partir do nome e do prazo da tarefa e então a
	 * adiciona no backlog do projeto.
	 * @param nome titulo da tarefa, será passado como parâmetro pro construtor
	 * da classe Task.
	 * @param prazo tempo de realização da tarefa, será passado como parâmetro
	 * pro construtor da classe Task.
	 */
	public void cadastraTask(String nome, int prazo) {
		Task novaTask = new Task(nome, prazo); //Construção de uma nova instância da classe Task
		for (Task task : this.backlog) {
			if(task.equals(novaTask)) return; //Testa para o caso da tarefa já estar cadastrada
		}
		this.backlog.add(novaTask); //Adiciona a tarefa criada ao backlog
	}
	/**
	 * Método removedor, remove uma tarefa cadastrada no backlog, a partir do seu nome.
	 * @param nome titulo da tarefa a ser excluida do backlog.
	 */
	public void removeTask(String nome) {
		if(this.backlog.isEmpty()) return; //Testa para o caso de não haver tarefas no backlog.
		
		if(nome == null) {
			this.backlog.clear(); //Caso em que não é passado parâmetro. Remove tudo.
			return;
		}
		for (Task task : this.backlog) { //Para cada tarefa no backlog...
			if (task != null && task.getTitulo() == nome) { //...Se existir e tiver esse titulo...
				this.backlog.remove(task); //...É removida
			}
		}
	}
	/**
	 * Método removeTask invocado sem parâmetros. Limpa o backlog, removendo todas
	 * as tarefas dele.
	 */
	public void removeTask() {
		this.removeTask(null);
	}
	/**
	 * Método que atribui a um desenvolvedor cadastrado uma tarefa cadastrada, a 
	 * partir da equipe e do backlog.
	 * Se os conjuntos não forem vazios, caso haja um desenvolvedor com o nome 
	 * passado como parâmetro e uma tarefa com o titulo passado como parâmetro,
	 * será atribuido a esse desenvolvedor essa tarefa. Isto é, a tarefa será 
	 * adicionada ao seu conjunto de tarefas e ele será vinculado ao atributo
	 * "desenvolvedor" na tarefa.
	 * @param dev nome do desenvolvedor cadastrado na equipe.
	 * @param task nome da tarefa a ser atribuida a esse desenvolvedor.
	 */
	public void atribuiTask(String dev, String task) {
		if(equipe.isEmpty() || backlog.isEmpty()) return; //Testa para conjuntos vazios.
		
		for(Dev developer : equipe) { // Para cada desenvolvedor na equipe...
			if(developer != null && developer.getNome() == dev) { //...Se tiver esse nome de identificação...
				for(Task tarefa : backlog) { //...E para cada tarefa no backlog...
					if(tarefa != null && tarefa.getTitulo() == task) { //...Se tiver esse titulo...
						developer.addTask(tarefa); //...A esse desenvolvedor será adiconada essa tarefa...
						tarefa.setDev(developer); //...E a essa tarefa, esse desenvolvedor.
					}
				}
			}
		}
	}
	/**
	 * Método que retorna as tarefas de um determinado desenvolvedor a partir do seu nome.
	 * Primeiro esse é identificado para que então seja retornado o seu relatório de tarefas.
	 * @param dev nome do desenvolvedor, que servirá para identificá-lo.
	 * @return relatório das tarefas daquele desenvolvedor específico.
	 */
	public String quaisTasks(String dev) {
		if(this.equipe.isEmpty()) return "Equipe Vazia"; //Testa para caso a equipe esteja vazia.
		for(Dev developer : equipe) { //Para cada desenvolvedor na equipe...
			if(developer != null && developer.getNome() == dev) { //...Se tiver esse nome...
				return developer.mostraTarefas(); //...Mostra suas tarefas.
			}
		}
	return "Desenvolvedor não encontrado"; //Caso não haja desenvolvedor com esse nome na equipe.
	}
}
