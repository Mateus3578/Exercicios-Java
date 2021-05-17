import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.Comparable;
import java.util.List;

public abstract class Cliente implements Comparable<Cliente>, Serializable {

    private String nome;
    private String endereco;
    private Date dataCriacao;

    private static String NOME_ARQUIVO = "Clientes.dat";

    public Cliente(){
        this.dataCriacao = new Date();
    }

    public Cliente(String nome){
        this.nome = nome;
        this.endereco = "<INVALIDO>";
        this.dataCriacao = new Date();
    }

    public int compareTo(Cliente o) {
        if(this.nome.compareToIgnoreCase(o.nome) < 0){
            return -1;
        }else if(this.nome.compareToIgnoreCase(o.nome) > 0){
            return 1;
        }else{
            return 0;
        }
    }

    public abstract boolean autenticar(String chave);

    public void imprimir(){
        System.out.println("Cliente inválido");
    }

    public static List<Cliente> carregarDados(){
        List<Cliente> lista = new ArrayList<>();

        try {
            File f = new File(NOME_ARQUIVO);
            if(f.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f));
                lista = (ArrayList<Cliente>)entrada.readObject();
                entrada.close();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        return(lista);
    }

    public static void salvarDados(List<Cliente> lista){
        File f = new File(NOME_ARQUIVO);

        try{
            f.delete();
            f.createNewFile();

            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(f));
            saida.writeObject(lista);
            saida.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
}
