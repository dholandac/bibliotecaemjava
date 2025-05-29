import java.io.*;

public class Livro extends Obra {
	private String isbn;

	public Livro(String titulo, String autor, String resumo, String isbn) {
		super(titulo, autor, resumo);
		this.isbn = isbn;
	}

	@Override
	public String getResumo() {
		return "\n| Resumo do Livro: " + resumo;
	}

	@Override
	public String toString() {
		return "\n| Título: " + titulo + "\n| Autor: " + autor + "\n| ISBN: " + isbn + getResumo();
	}

	@Override
	public String toFile() {
		String nomeArquivo = "src/Obras/" + titulo.toLowerCase().replace(" ", "") + ".dat";

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
			oos.writeObject(this);
			return nomeArquivo;
		} catch (IOException e) {
			System.out.println("\n !!!!! Não foi possível salvar o arquivo !!!!! \n");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void loadFile() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Obras/" + titulo.toLowerCase().replace(" ", "") + ".dat"))) {
			Livro l = (Livro) ois.readObject();
			System.out.println(l);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("\n !!!!! Não foi possível carregar o arquivo !!!!! \n");
			e.printStackTrace();
		}
	}
}
