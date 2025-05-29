import java.io.*;

public class Artigo extends Obra {
	private String refUnica;
	
	public Artigo (String titulo, String autor, String resumo, String refUnica) {
		super(titulo, autor, resumo);
		this.refUnica = refUnica;
	}

	@Override
	public String getResumo() {
		return "\n| Resumo do Artigo: " + resumo;
	}

	@Override
	public String toString() {
		return "\n| Título: " + titulo + "\n| Autor: " + autor + "\n| Referência Única: " + refUnica + getResumo();
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
			Artigo a = (Artigo) ois.readObject();
			System.out.println(a);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("\n !!!!! Não foi possível carregar o arquivo !!!!! \n");
			e.printStackTrace();
		}
	}
}
