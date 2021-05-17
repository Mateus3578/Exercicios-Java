import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.Comparable;
import java.util.List;

public abstract class Operacao implements ITaxas, Comparable<Operacao>, Serializable {

    private Date data;
    private char tipo;
    private double valor;

    private static String NOME_ARQUIVO = "Operacoes.dat";

    private static int TOTAL_OPERACOES = 0;

    public Operacao() {
        data = new Date();
        Operacao.TOTAL_OPERACOES++;
    }

    public String toString(){
        return (this.getData() + "\t" + this.getTipo() + "\t" + String.format("%.2f",this.getValor()));
    }

    public int compareTo(Operacao o) {
        if(this.getTipo() < o.getTipo()){
            return -1;
        }else if(this.getTipo() > o.getTipo()){
            return 1;
        }else{
            return 0;
        }
    }

    public static List<Operacao> carregarDados(){
        List<Operacao> lista = new ArrayList<>();

        try {
            File f = new File(NOME_ARQUIVO);
            if(f.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f));
                lista = (ArrayList<Operacao>)entrada.readObject();
                entrada.close();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        return(lista);
    }

    public static void salvarDados(List<Operacao> lista){
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

    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo) {
        if(tipo == 'd' || tipo == 's'){
            this.tipo = tipo;
        }
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return this.data;
    }

    public abstract String toStringTaxas();

    public static int getTotalOperacoes() {
        return Operacao.TOTAL_OPERACOES;
    }

}
