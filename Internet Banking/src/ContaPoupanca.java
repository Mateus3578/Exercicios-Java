public class ContaPoupanca extends Conta{

    public void setLimite(double limite) {
        if(limite < 100 || limite > 1000){
            throw new IllegalArgumentException("Valor fora do limite para esse tipo de conta");
        }else{
            System.out.println("Limite alterado para " + limite);
            this.limite = limite;
        }
    }

    public double calculaTaxas(){
        //tipo de conta isenta de taxas de manutenção
        return 0;
    }

}
