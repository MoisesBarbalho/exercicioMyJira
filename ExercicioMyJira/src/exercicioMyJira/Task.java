package exercicioMyJira;

public class Task {
	private String titulo;
	private int prazo;
	private Dev desenvolvedor;
	private String status;
	
	public Task(String titulo, int prazo, Dev desenvolvedor) {
		this.titulo = titulo;
		this.prazo = prazo;
		this.desenvolvedor = desenvolvedor != null ? desenvolvedor : null;
		this.attStatus();
	}
	public Task(String titulo, int prazo) {
		this(titulo, prazo, null);
	}
	public String getTitulo() {
		return this.titulo;
	}
	public int getPrazo() {
		return this.prazo;
	}
	public void setPrazo(int novoPrazo) {
		this.prazo = novoPrazo;
	}
	public Dev getDev() {
		return this.desenvolvedor;
	}
	public void setDev(Dev desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
		this.attStatus();
	}
	public void removeDev() {
		this.desenvolvedor = null;
		this.attStatus();
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String novoStatus) {
		this.status = novoStatus;
	}
	private void attStatus() {
		this.status = (this.desenvolvedor != null)? "Em andamento" : "Não iniciada";
	}
}
