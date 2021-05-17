public class ContaCorrente extends Conta{


    public void setLimite(double limite) {
        if(limite < -100){
            throw new IllegalArgumentException("Valor fora do limite para esse tipo de conta");
        }else{
            System.out.println("Limite alterado para " + limite);
            this.limite = limite;
        }
    }

    public double calculaTaxas(){
        if(this.getDono() instanceof PessoaFisica){
            return 10;
        }else if (this.getDono() instanceof PessoaJuridica){
            return 20;
        }
        else{
            return 0;
        }
    }


}
