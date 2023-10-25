import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

class Cliente {
    public String nome;
    public int tempoDeEspera;
}

class Funcionario {
    public String nome;

    public void atenderCliente(Cliente cliente) {
        System.out.println(nome + " está atendendo o cliente " + cliente.nome + " por " + cliente.tempoDeEspera + " segundos.");
        try {
            Thread.sleep(cliente.tempoDeEspera * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nome + " atendeu o cliente " + cliente.nome + " em " + cliente.tempoDeEspera + " segundos.");
    }
}

public class Main {
    public static void main(String[] args) {
        Queue<Cliente> fila = new LinkedList<>();
        List<Funcionario> funcionarios = new LinkedList<>();

        funcionarios.add(new Funcionario() {{
            nome = "Funcionário 1";
        }});
        funcionarios.add(new Funcionario() {{
            nome = "Funcionário 2";
        }});

        Random random = new Random();

        
        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente();
            cliente.nome = "Cliente " + i;
            cliente.tempoDeEspera = random.nextInt(5) + 1; 
            fila.add(cliente);
            System.out.println("Novo cliente na fila: " + cliente.nome + " (Tempo de espera: " + cliente.tempoDeEspera + " segundos)");
        }

       
        while (!fila.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                if (fila.isEmpty())
                    break;

                Cliente cliente = fila.poll();
                funcionario.atenderCliente(cliente);
            }
        }

        System.out.println("Todos os clientes foram atendidos.");
    }
}
