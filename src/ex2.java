import java.util.Random;

public class ex2 {
    public static void main(String[] args) {
        Random random = new Random();
        
        int tamanhoPequeno = 10000;
        int tamanhoMedio = 100000;
        int tamanhoGrande = 1000000;
        
        System.out.println("Quicksort com diferentes pivôs:");

        System.out.println("Usando o primeiro elemento como pivô:");
        testarQuicksortComPivo("a", tamanhoPequeno, random);
        testarQuicksortComPivo("a", tamanhoMedio, random);
        testarQuicksortComPivo("a", tamanhoGrande, random);

        System.out.println("Usando o último elemento como pivô:");
        testarQuicksortComPivo("b", tamanhoPequeno, random);
        testarQuicksortComPivo("b", tamanhoMedio, random);
        testarQuicksortComPivo("b", tamanhoGrande, random);

        System.out.println("Usando o elemento do meio como pivô:");
        testarQuicksortComPivo("c", tamanhoPequeno, random);
        testarQuicksortComPivo("c", tamanhoMedio, random);
        testarQuicksortComPivo("c", tamanhoGrande, random);
    }

    public static void testarQuicksortComPivo(String tipoPivo, int tamanho, Random random) {
        int[] array = gerarArrayAleatorio(tamanho, random);
        long inicio = System.currentTimeMillis();
        
        switch (tipoPivo) {
            case "a":
                quicksortPrimeiroPivo(array, 0, array.length - 1);
                break;
            case "b":
                quicksortUltimoPivo(array, 0, array.length - 1);
                break;
            case "c":
                quicksortMeioPivo(array, 0, array.length - 1);
                break;
        }
        
        long fim = System.currentTimeMillis();
        System.out.println("Tempo decorrido para " + tamanho + " elementos com pivô " + tipoPivo + ": " + (fim - inicio) + " milissegundos");

    }

    public static void quicksortPrimeiroPivo(int[] array, int baixo, int alto) {
        if (baixo < alto) {
            int indicePivo = particionarPrimeiroPivo(array, baixo, alto);
            quicksortPrimeiroPivo(array, baixo, indicePivo - 1);
            quicksortPrimeiroPivo(array, indicePivo + 1, alto);
        }
    }

    public static void quicksortUltimoPivo(int[] array, int baixo, int alto) {
        if (baixo < alto) {
            int indicePivo = particionarUltimoPivo(array, baixo, alto);
            quicksortUltimoPivo(array, baixo, indicePivo - 1);
            quicksortUltimoPivo(array, indicePivo + 1, alto);
        }
    }

    public static void quicksortMeioPivo(int[] array, int baixo, int alto) {
        if (baixo < alto) {
            int indicePivo = particionarMeioPivo(array, baixo, alto);
            quicksortMeioPivo(array, baixo, indicePivo - 1);
            quicksortMeioPivo(array, indicePivo + 1, alto);
        }
    }

    public static int particionarPrimeiroPivo(int[] array, int baixo, int alto) {
        int pivo = array[baixo];
        int i = baixo;
        int j = alto + 1;

        while (true) {
            while (array[++i] < pivo) {
                if (i == alto)
                    break;
            }
            while (array[--j] > pivo) {
                if (j == baixo)
                    break;
            }
            if (i >= j)
                break;
            trocar(array, i, j);
        }

        trocar(array, baixo, j);
        return j;
    }

    public static int particionarUltimoPivo(int[] array, int baixo, int alto) {
        int pivo = array[alto];
        int i = baixo - 1;

        for (int j = baixo; j < alto; j++) {
            if (array[j] <= pivo) {
                i++;
                trocar(array, i, j);
            }
        }

        trocar(array, i + 1, alto);
        return i + 1;
    }

    public static int particionarMeioPivo(int[] array, int baixo, int alto) {
        int meio = (baixo + alto) / 2;
        int pivo = array[meio];
        int i = baixo - 1;
        int j = alto + 1;

        while (true) {
            do {
                i++;
            } while (array[i] < pivo);
            do {
                j--;
            } while (array[j] > pivo);

            if (i >= j)
                return j;

            trocar(array, i, j);
        }
    }

    public static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] gerarArrayAleatorio(int tamanho, Random random) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}
