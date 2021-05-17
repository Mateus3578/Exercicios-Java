public class PessoaJuridica extends Cliente{

    private String cnpj;
    private int numFuncionarios;
    private String setor;

    public PessoaJuridica(String nome, String cnpj, int numFuncionarios, String setor){
        super(nome);
        this.cnpj = cnpj;
        this.numFuncionarios = numFuncionarios;
        this.setor = setor;
    }

    public boolean autenticar(String chave) {
        return chave.equals(this.cnpj);
    }

    public String toString(){
        String imprimir =
                "Tipo de cliente: pessoa Jurídica (PJ)" + "\n" +
                "Nome: " + this.getNome() + "\n" +
                "CNPJ: " + this.getCnpj() + "\n" +
                "Endereço: " + this.getEndereco() + "\n" +
                "Nº de funcionários: " + this.getNumFuncionarios() + "\n" +
                "Setor de atuação: " + this.getSetor() + "\n\n";

        return imprimir;
    }

    public boolean equals(Object obj){
        if(obj instanceof PessoaJuridica){
            return this.cnpj.equals(((PessoaJuridica) obj).cnpj);
        }else{
            return false;
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(int numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
