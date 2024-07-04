    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    // Classe que representa uma cidade com um intervalo de CEPs
    class Cidade {
        String nome;
        int cepInicio;
        int cepFim;
    
        // Construtor da classe Cidade
        Cidade(String nome, int cepInicio, int cepFim) {
            this.nome = nome;
            this.cepInicio = cepInicio;
            this.cepFim = cepFim;
        }
    
        // Método para verificar se um CEP está dentro do intervalo da cidade
        boolean contemCep(int cep) {
            return cep >= cepInicio && cep <= cepFim;
        }
    }
    
    public class Parte01 {
        
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<Cidade> cidades = new ArrayList<>();
            
            System.out.println("Digite a lista das cidades.");

            while (true) {
                String linha = scanner.nextLine();
                if (linha.equals("--")) {
                    break; // Interrompe a leitura ao encontrar "--"
                }
    
                // Divide a linha em partes: nome, cepInicio e cepFim
                String[] partes = linha.split(",");
                String nome = partes[0];
                int cepInicio = Integer.parseInt(partes[1]);
                int cepFim = Integer.parseInt(partes[2]);
    
                // Adiciona a cidade à lista
                cidades.add(new Cidade(nome, cepInicio, cepFim));
            }
    
            // Lê o CEP que será consultado
            int cepConsulta = Integer.parseInt(scanner.nextLine());
    
            // Procura o CEP na lista de cidades
            for (Cidade cidade : cidades) {
                if (cidade.contemCep(cepConsulta)) {
                    System.out.println(cidade.nome); // Imprime o nome da cidade correspondente
                    return; 
                }
            }
    
            // Caso nenhum CEP corresponda, imprime mensagem de erro
            System.out.println("CEP não encontrado em nenhuma cidade.");
        }
    }
    
