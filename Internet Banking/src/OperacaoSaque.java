public class OperacaoSaque extends Operacao {

    public OperacaoSaque(double valor){
        setTipo('s');
        setValor(valor);
    }

    public String toStringTaxas(){return (this.getData() + "\t" + this.getTipo() + "\t" + String.format("%.2f",this.calculaTaxas()));}

    public double calculaTaxas(){
        return 0.05;
    }
}
