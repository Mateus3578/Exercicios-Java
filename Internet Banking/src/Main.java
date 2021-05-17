import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cliente pessoa = new PessoaFisica("Fulaninho", "123.123.123-12", 58, 'm');

        List<Cliente> clientes = Cliente.carregarDados();
        clientes.add(pessoa);

        Conta cc = new ContaCorrente();
        cc.setDono(pessoa);
        List<Conta> contas = Conta.carregarDados();
        contas.add(cc);

        List<Operacao> operacoes = Operacao.carregarDados();
        cc.setOperacoes(operacoes);

        try {
            cc.setLimite(4000);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                cc.depositar(1000);
                cc.depositar(2000);
                cc.sacar(500);
                cc.depositar(3000);
                cc.sacar(10);
                cc.sacar(15);
            }catch (ValorNegativoException | SemLimiteException e){
                System.out.println(e.getMessage());
            }finally {
                cc.imprimirExtrato(1);
                Cliente.salvarDados(clientes);
                Conta.salvarDados(contas);
                Operacao.salvarDados(cc.getOperacoes());
                System.out.println("Foram salvas " + contas.size() +
                        " contas, " + clientes.size() +
                        " clientes e " + cc.getOperacoes().size() + " operações.");
            }
        }
    }
}

