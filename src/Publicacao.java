public interface Publicacao {
	String getTitulo();
	String getAutor();
	String getResumo();
	String toFile();
	void loadFile();
}