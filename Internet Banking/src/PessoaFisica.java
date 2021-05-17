public class PessoaFisica extends Cliente{

    private String cpf;
    private int idade;
    private char sexo;

    public PessoaFisica(String nome, String cpf, int idade, char sexo){
        super(nome);
        this.cpf = cpf;
        this.idade =  idade;
        this.sexo = sexo;
    }

    public boolean autenticar(String chave) {
        return chave.equals(this.cpf);
    }

    public String toString(){
        String imprimir =
            "Tipo de cliente: Pessoa Fisica (PF)" + "\n" +
            "Nome: " + this.getNome() + "\n" +
            "CPF: " + this.getCpf() + "\n" +
            "Endereço: " + this.getEndereco() + "\n" +
            "Idade: " + this.getIdade() + " anos" + "\n" +
            "Sexo: " + this.getSexo() + "\n\n";

        return imprimir;
    }

    public boolean equals(Object obj){
        if(obj instanceof PessoaFisica){
            return this.cpf.equals(((PessoaFisica) obj).cpf);
        }else{
            return false;
        }
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return this.sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
