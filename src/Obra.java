import java.io.Serializable;

public abstract class Obra implements Publicacao, Serializable {
	protected String titulo;
	protected String autor;
	protected String resumo;

    public Obra() {
    }
	
	public Obra(String titulo,String autor, String resumo) {
		this.titulo = titulo;
		this.autor = autor;
		this.resumo = resumo;
	}

	@Override
	public String getTitulo() {
		return titulo;
	}

	@Override
	public String getAutor() {
		return autor;
	}
}
