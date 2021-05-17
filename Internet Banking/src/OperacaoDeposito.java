public class OperacaoDeposito extends Operacao {

    public OperacaoDeposito(double valor){
        setTipo('d');
        setValor(valor);
    }

    public String toStringTaxas(){return (this.getData() + "\t" + this.getTipo() + "\t" + String.format("%.2f",this.calculaTaxas()));}

    public double calculaTaxas(){
        // Operação isenta de taxas
        return 0;
    }
}
