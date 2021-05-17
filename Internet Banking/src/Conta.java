import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas, Serializable {

    private int numero;
    private Cliente dono;
    private double saldo;
    protected double limite;
    private List<Operacao> operacoes;

    private static String NOME_ARQUIVO = "Contas.dat";

    private static int TOTAL_CONTAS = 0;

    public Conta(){
        this.dono = null;
        this.numero = TOTAL_CONTAS + 1;
        this.saldo = 0 - this.calculaTaxas();
        this.limite = 0;
        this.operacoes = new ArrayList<>();

        Conta.TOTAL_CONTAS++;
    }

    public String toString(){
        return
            "----------- Dados da conta nº " + this.numero + " -------------" + "\n" +
            this.dono.toString() +
            "Saldo da conta: " + String.format("%.2f", this.saldo) + "\n" +
            "Limite da conta: " + String.format("%.2f", this.limite) + "\n" +
            "---------------------------------------------" + "\n";
    }

    public void imprimirExtrato(int formato) {
        System.out.printf("----------- Extrato da conta nº %d -----------%n", this.numero);
        if (formato == 1) {
            List<Operacao> auxiliar = new ArrayList<>(this.operacoes);
            Collections.sort(auxiliar);

            for (Operacao atual : auxiliar) {
                System.out.println(atual);
            }
        }
        else{
            for (Operacao atual : this.operacoes) {
                System.out.println(atual);
            }
        }
        System.out.println("---------------------------------------------\n");
    }

    public void imprimirExtratoTaxas(){
        double total = 0;

        System.out.printf("------ Extrato de taxas da conta nº %d -------%n", this.numero);
        System.out.println("Abertura da conta: " + String.format("%.2f",this.calculaTaxas()) + "\n");

        total += this.calculaTaxas();

        for(Operacao atual : this.operacoes){
            System.out.println(atual.toStringTaxas());
            total += atual.calculaTaxas();
        }

        System.out.println("\nTotal: " + String.format("%.2f",total));
        System.out.println("---------------------------------------------\n");
    }

    public boolean equals(Object obj){
        if(obj instanceof Conta){
            return this.numero == ((Conta) obj).numero;
        }else{
            return false;
        }
    }

    public void depositar(double valor) throws ValorNegativoException {
        if(valor < 0){
            throw new ValorNegativoException("Erro: Depósito negativo: " + valor);
        }

        this.operacoes.add(new OperacaoDeposito(valor));
        this.saldo += valor;
    }

    public boolean sacar(double valor) throws ValorNegativoException, SemLimiteException {
        if(valor < 0){
            throw new ValorNegativoException("Erro: Saque negativo: " + valor);
        }
        if(valor > this.limite){
            throw new SemLimiteException("Erro: Valor maior que o limite: " + valor + "/" + this.limite);
        }

        if(valor <= this.saldo){
            this.operacoes.add(new OperacaoSaque(valor));
            this.saldo -= valor;
            return true;
        }else{
            return false;
        }
    }

    public boolean transferir(Conta contaDestino, double valor) throws ValorNegativoException, SemLimiteException {

        boolean retirou = this.sacar(valor);

        if (retirou) {
            contaDestino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public static List<Conta> carregarDados(){
        List<Conta> lista = new ArrayList<>();

        try {
            File f = new File(NOME_ARQUIVO);
            if(f.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f));
                lista = (ArrayList<Conta>)entrada.readObject();
                entrada.close();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        return(lista);
    }

    public static void salvarDados(List<Conta> lista){
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

    public abstract void setLimite(double limite);

    public double getLimite() {
        return this.limite;
    }

    public static int getTotalContas() {
        return Conta.TOTAL_CONTAS;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        System.out.printf("Número alterado de %d para %d.%n", this.numero, numero);
        this.numero = numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Cliente getDono() {
        return this.dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes.addAll(operacoes);
    }
}

