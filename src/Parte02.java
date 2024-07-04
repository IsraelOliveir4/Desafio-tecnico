import java.util.*;

public class Parte02 {

    // Classe para representar uma cidade com nome e intervalo de CEPs
    static class Cidade {
        String nome;
        int cepInicial;
        int cepFinal;

        Cidade(String nome, int cepInicial, int cepFinal) {
            this.nome = nome;
            this.cepInicial = cepInicial;
            this.cepFinal = cepFinal;
        }
    }

    // Classe para representar uma aresta no grafo, com origem, destino e custo
    static class Aresta {
        String origem;
        String destino;
        int custo;

        Aresta(String origem, String destino, int custo) {
            this.origem = origem;
            this.destino = destino;
            this.custo = custo;
        }
    }

    // Classe para representar o grafo e executar o algoritmo de Dijkstra
    static class Grafo {
        private Map<String, List<Aresta>> adjacencias = new HashMap<>();

        // Método para adicionar uma aresta ao grafo
        void adicionarAresta(String origem, String destino, int custo) {
            adjacencias.computeIfAbsent(origem, k -> new ArrayList<>()).add(new Aresta(origem, destino, custo));
            adjacencias.computeIfAbsent(destino, k -> new ArrayList<>()).add(new Aresta(destino, origem, custo));
        }

        // Método para encontrar o menor caminho usando o algoritmo de Dijkstra
        List<String> dijkstra(String inicio, String fim) {
            Map<String, Integer> distancias = new HashMap<>();
            Map<String, String> anteriores = new HashMap<>();
            PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.custo));

            // Inicializa as distâncias com infinito
            adjacencias.keySet().forEach(cidade -> distancias.put(cidade, Integer.MAX_VALUE));
            distancias.put(inicio, 0);
            pq.add(new Aresta(null, inicio, 0));

            while (!pq.isEmpty()) {
                Aresta atual = pq.poll();
                if (atual.custo > distancias.get(atual.destino)) continue;

                for (Aresta aresta : adjacencias.get(atual.destino)) {
                    int novaDistancia = distancias.get(atual.destino) + aresta.custo;
                    if (novaDistancia < distancias.get(aresta.destino)) {
                        distancias.put(aresta.destino, novaDistancia);
                        anteriores.put(aresta.destino, atual.destino);
                        pq.add(new Aresta(atual.destino, aresta.destino, novaDistancia));
                    }
                }
            }

            List<String> caminho = new ArrayList<>();
            for (String at = fim; at != null; at = anteriores.get(at)) {
                caminho.add(at);
            }
            Collections.reverse(caminho);
            return caminho;
        }

        // Método para calcular o custo total do caminho encontrado
        int getCustoTotal(List<String> caminho) {
            int total = 0;
            for (int i = 0; i < caminho.size() - 1; i++) {
                String origem = caminho.get(i);
                String destino = caminho.get(i + 1);
                for (Aresta aresta : adjacencias.get(origem)) {
                    if (aresta.destino.equals(destino)) {
                        total += aresta.custo;
                        break;
                    }
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Cidade> cidades = new ArrayList<>();
        Map<Integer, Cidade> cepParaCidade = new TreeMap<>();

        // Ler cidades e CEPs
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.equals("--")) break;
            String[] partes = linha.split(",");
            String nome = partes[0];
            int cepInicial = Integer.parseInt(partes[1]);
            int cepFinal = Integer.parseInt(partes[2]);
            Cidade cidade = new Cidade(nome, cepInicial, cepFinal);
            cidades.add(cidade);
            cepParaCidade.put(cepInicial, cidade);
            cepParaCidade.put(cepFinal, cidade);
        }

        Grafo grafo = new Grafo();

        // Ler arestas e custos
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.equals("--")) break;
            String[] partes = linha.split(",");
            String origem = partes[0];
            String destino = partes[1];
            int custo = Integer.parseInt(partes[2]);
            grafo.adicionarAresta(origem, destino, custo);
        }

        // Ler os CEPs de origem e destino
        if (scanner.hasNextLine()) {
            String[] ceps = scanner.nextLine().split(",");
            int cepOrigem = Integer.parseInt(ceps[0]);
            int cepDestino = Integer.parseInt(ceps[1]);

            // Determinar as cidades correspondentes aos CEPs
            String cidadeOrigem = null;
            String cidadeDestino = null;
            for (Map.Entry<Integer, Cidade> entry : cepParaCidade.entrySet()) {
                if (cepOrigem >= entry.getValue().cepInicial && cepOrigem <= entry.getValue().cepFinal) {
                    cidadeOrigem = entry.getValue().nome;
                }
                if (cepDestino >= entry.getValue().cepInicial && cepDestino <= entry.getValue().cepFinal) {
                    cidadeDestino = entry.getValue().nome;
                }
                if (cidadeOrigem != null && cidadeDestino != null) {
                    break;
                }
            }

            // Verificação para garantir que as cidades foram encontradas
            if (cidadeOrigem == null || cidadeDestino == null) {
                System.out.println("Cidades não encontradas para os CEPs fornecidos. Favor tentar novamente!");
                return;
            }

            // Encontrar o menor caminho usando o algoritmo Dijkstra
            List<String> caminho = grafo.dijkstra(cidadeOrigem, cidadeDestino);
            int custoTotal = grafo.getCustoTotal(caminho);

            // Imprimir o caminho e o custo total
            System.out.println(String.join(",", caminho));
            System.out.println(custoTotal);
        }

        scanner.close();
    }
}
