import java.io.*;

public class Revista extends Obra {
	private String periodicidade;

	public Revista(String titulo, String autor, String resumo, String periodicidade) {
		super(titulo, autor, resumo);
		this.periodicidade = periodicidade;
	}

	@Override
	public String getResumo() {
		return "\n| Resumo da Revista: " + resumo;
	}

	@Override
	public String toString() {
		return "\n| Título: " + titulo + "\n| Autor: " + autor + "\n| Periodicidade: " + periodicidade + getResumo();
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
			Revista r = (Revista) ois.readObject();
			System.out.println(r);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("\n !!!!! Não foi possível carregar o arquivo !!!!! \n");
			e.printStackTrace();
		}
    }
}
