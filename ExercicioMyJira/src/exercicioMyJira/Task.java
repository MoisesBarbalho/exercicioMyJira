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
		this.status = (this.desenvolvedor != null)? "Em andamento" : "Não-iniciada";
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.titulo + "\nDesenvolvedor: ");
		if(this.desenvolvedor == null) {
			sb.append("Sem desenvolvedor.");
		} else {
			sb.append(this.desenvolvedor.getNome());
		}
		sb.append("\nTempo para execução: " + this.prazo);
		sb.append("\nStatus: " + this.status);
		String taskString = sb.toString();
		return taskString;
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Task)) return false;
		if (o == this) return true;
		
		Task oTask = (Task) o;
		return (oTask.getTitulo() == this.titulo);
	}
	@Override
	public int hashCode() {
		int hash = 19;
		return 31 * hash + this.titulo.hashCode();
	}
}
