import java.util.Random;

public class ex1 {
    public static void main(String[] args) {
        int[] tamanhos = {10000, 100000, 1000000};

        for (int tamanho : tamanhos) {
            testarMergeSort(tamanho, ex1::gerarArrayOrdenado, "ordenado");
            testarMergeSort(tamanho, ex1::gerarArrayAleatorio, "aleatório");
            testarMergeSort(tamanho, ex1::gerarArrayDecrescente, "em ordem decrescente");
        }
    }

    public static void testarMergeSort(int tamanho, GeradorArray gerador, String condicao) {
        int[] arr = gerador.gerar(tamanho);
        long inicio = System.currentTimeMillis();
        mergeSort(arr);
        long fim = System.currentTimeMillis();
        long tempoDecorrido = fim - inicio;
        System.out.println("Tempo decorrido para " + tamanho + " elementos " + condicao + ": " + tempoDecorrido + " milissegundos");
    }
    
    
        public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int meio = arr.length / 2;
        
        int[] esquerda = new int[meio];
        int[] direita = new int[arr.length - meio];
      
        for (int i = 0; i < meio; i++) {
            esquerda[i] = arr[i];
        }
        for (int i = meio; i < arr.length; i++) {
            direita[i - meio] = arr[i];
        }
        
        mergeSort(esquerda);
        mergeSort(direita);
        
        
        merge(arr, esquerda, direita);
    }
    

    public static void merge(int[] arr, int[] esquerda, int[] direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                arr[k++] = esquerda[i++];
            } else {
                arr[k++] = direita[j++];
            }
        }
        while (i < esquerda.length) {
            arr[k++] = esquerda[i++];
        }
        while (j < direita.length) {
            arr[k++] = direita[j++];
        }
    }

    public static int[] gerarArrayOrdenado(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] gerarArrayAleatorio(int tamanho) {
        Random random = new Random();
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = random.nextInt(1000000);
        }
        return arr;
    }

    public static int[] gerarArrayDecrescente(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = tamanho - i;
        }
        return arr;
    }

    interface GeradorArray {
        int[] gerar(int tamanho);
    }
}
