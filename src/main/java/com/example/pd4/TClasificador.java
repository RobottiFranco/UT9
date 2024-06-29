package com.example.pd4;

import java.util.Arrays;
import java.util.Random;

import com.example.GeneradorDatosGenericos;

public class TClasificador {

    public static final int METODO_CLASIFICACION_SHELLT = 1;
    public static final int METODO_CLASIFICACION_SHELLH = 2;
    public static final int METODO_CLASIFICACION_SHELLP = 3;
    public static final int METODO_CLASIFICACION_QUICKSORTPA = 4;
    public static final int METODO_CLASIFICACION_QUICKSORTPM = 5;
    public static final int METODO_CLASIFICACION_QUICKSORTPV = 6;

    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     *         solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_SHELLT:
                return ordenarPorShellTokuda(datosParaClasificar);
            case METODO_CLASIFICACION_SHELLH:
                return ordenarPorShellHibbard(datosParaClasificar);
            case METODO_CLASIFICACION_SHELLP:
                return ordenarPorShellPapernovStasevich(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORTPA:
                return ordenarPorQuickSortPivoteAleatorio(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORTPM:
                return ordenarPorQuickSortPivoteMediana(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORTPV:
                return ordenarPorQuickSortPivoteValorMedio(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShellTokuda(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[] { 233, 103, 46, 20, 9, 4, 1 };

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    int aux = datosParaClasificar[i];
                    j = i - inc;
                    while (j >= 0 && aux < datosParaClasificar[j]) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j = j - inc;
                    }
                    datosParaClasificar[j + inc] = aux;
                }
            }
        }
        return datosParaClasificar;
    }

    private int[] ordenarPorShellHibbard(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[] { 31, 15, 7, 3, 1 };

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    int aux = datosParaClasificar[i];
                    j = i - inc;
                    while (j >= 0 && aux < datosParaClasificar[j]) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j = j - inc;
                    }
                    datosParaClasificar[j + inc] = aux;
                }
            }
        }
        return datosParaClasificar;
    }

    private int[] ordenarPorShellPapernovStasevich(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[] { 65, 33, 17, 9, 5, 3, 1 };

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    int aux = datosParaClasificar[i];
                    j = i - inc;
                    while (j >= 0 && aux < datosParaClasificar[j]) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j = j - inc;
                    }
                    datosParaClasificar[j + inc] = aux;
                }
            }
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorQuickSortPivoteAleatorio(int[] datosParaClasificar) {
        quicksortA(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksortA(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivoteA(izquierda, derecha);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha) {
                quicksortA(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksortA(entrada, izquierda, j);
            }
        }
    }

    public int encuentraPivoteA(int min, int max) {
        Random result = new Random();
        int resultado = result.nextInt(min, max);
        return resultado;
    }

    protected int[] ordenarPorQuickSortPivoteMediana(int[] datosParaClasificar) {
        quicksortM(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksortM(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivoteM(entrada, izquierda, derecha);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha) {
                quicksortM(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksortM(entrada, izquierda, j);
            }
        }
    }

    public int encuentraPivoteM(int[] entrada, int i, int j) {
        int[] rango = Arrays.copyOfRange(entrada, i, j + 1);
        int[] copiaRango = rango.clone();
        Arrays.sort(rango);
        int mediana = rango[(rango.length - 1) / 2];
        int pos = i;
        while (pos < rango.length && copiaRango[pos] != mediana) {
            pos++;
        }
        return pos;
    }

    protected int[] ordenarPorQuickSortPivoteValorMedio(int[] datosParaClasificar) {
        quicksortV(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksortV(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivoteV(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha) {
                quicksortV(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksortV(entrada, izquierda, j);
            }
        }
    }

    public int encuentraPivoteV(int primero, int ultimo, int[] entrada) {
        int medio = primero + ((ultimo - primero) / 2);
        int vPrimero = entrada[primero];
        int vMedio = entrada[medio];
        int vUltimo = entrada[ultimo];
        if (vPrimero <= vMedio && vMedio < vUltimo || vPrimero >= vMedio && vMedio > vUltimo) {
            return medio;
        } else if (vMedio < vPrimero && vPrimero <= vUltimo || vMedio > vPrimero && vPrimero >= vUltimo) {
            return primero;
        } else {
            return ultimo;
        }
    }

    public static boolean estaOrdenado(int[] vector) {
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i] > vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean estaOrdenadoInvertido(int[] vector) {
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i] < vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] vectorAleatorio = gdg.generarDatosAleatorios();
        int[] vectorAscendente = gdg.generarDatosAscendentes();
        int[] vectorDescendente = gdg.generarDatosDescendentes();

        System.out.println("_____________________SHELL TOKUDA________________________");
        System.out.println("ALEATORIO: ");
        int[] resAleatorioT = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resAleatorioT.length; i++) {
            System.out.print(resAleatorioT[i] + " ");
        }
        System.out.println("");
        System.out.println("ASCENDENTE: ");
        int[] resAscendenteT = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resAscendenteT.length; i++) {
            System.out.print(resAscendenteT[i] + " ");
        }
        System.out.println("");
        System.out.println("DESCENDENTE: ");
        int[] resDescendenteT = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resDescendenteT.length; i++) {
            System.out.print(resDescendenteT[i] + " ");
        }
        System.out.println("");

        System.out.println("_____________________SHELL HIBBARD________________________");
        System.out.println("ALEATORIO: ");
        int[] resAleatorioH = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resAleatorioH.length; i++) {
            System.out.print(resAleatorioH[i] + " ");
        }
        System.out.println("");
        System.out.println("ASCENDENTE: ");
        int[] resAscendenteH = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resAscendenteH.length; i++) {
            System.out.print(resAscendenteH[i] + " ");
        }
        System.out.println("");
        System.out.println("DESCENDENTE: ");
        int[] resDescendenteH = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resDescendenteH.length; i++) {
            System.out.print(resDescendenteH[i] + " ");
        }
        System.out.println("");

        System.out.println("_____________________SHELL PAPERNOV & STASEVICH________________________");
        System.out.println("ALEATORIO: ");
        int[] resAleatorioP = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resAleatorioP.length; i++) {
            System.out.print(resAleatorioP[i] + " ");
        }
        System.out.println("");
        System.out.println("ASCENDENTE: ");
        int[] resAscendenteP = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resAscendenteP.length; i++) {
            System.out.print(resAscendenteP[i] + " ");
        }
        System.out.println("");
        System.out.println("DESCENDENTE: ");
        int[] resDescendenteP = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_SHELLT);
        for (int i = 0; i < resDescendenteP.length; i++) {
            System.out.print(resDescendenteP[i] + " ");
        }
        System.out.println("");

        System.out.println("_____________________QUICKSORT PIVOTE ALEATORIO________________________");
        System.out.println("ALEATORIO: ");
        int[] resAleatorioA = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_QUICKSORTPA);
        for (int i = 0; i < resAleatorioA.length; i++) {
            System.out.print(resAleatorioA[i] + " ");
        }
        System.out.println("");
        System.out.println("ASCENDENTE: ");
        int[] resAscendenteA = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_QUICKSORTPA);
        for (int i = 0; i < resAscendenteA.length; i++) {
            System.out.print(resAscendenteA[i] + " ");
        }
        System.out.println("");
        System.out.println("DESCENDENTE: ");
        int[] resDescendenteA = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_QUICKSORTPA);
        for (int i = 0; i < resDescendenteA.length; i++) {
            System.out.print(resDescendenteA[i] + " ");
        }
        System.out.println("");

        System.out.println("_____________________QUICKSORT PIVOTE MEDIANA________________________");
        System.out.println("ALEATORIO: ");
        int[] resAleatorioM = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_QUICKSORTPM);
        for (int i = 0; i < resAleatorioM.length; i++) {
            System.out.print(resAleatorioM[i] + " ");
        }
        System.out.println("");
        System.out.println("ASCENDENTE: ");
        int[] resAscendenteM = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_QUICKSORTPM);
        for (int i = 0; i < resAscendenteM.length; i++) {
            System.out.print(resAscendenteM[i] + " ");
        }
        System.out.println("");
        System.out.println("DESCENDENTE: ");
        int[] resDescendenteM = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_QUICKSORTPM);
        for (int i = 0; i < resDescendenteM.length; i++) {
            System.out.print(resDescendenteM[i] + " ");
        }
        System.out.println("");

        System.out.println("_____________________QUICKSORT PIVOTE VALOR MEDIO________________________");
        System.out.println("ALEATORIO: ");
        int[] resAleatorioV = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_QUICKSORTPV);
        for (int i = 0; i < resAleatorioV.length; i++) {
            System.out.print(resAleatorioV[i] + " ");
        }
        System.out.println("");
        System.out.println("ASCENDENTE: ");
        int[] resAscendenteV = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_QUICKSORTPV);
        for (int i = 0; i < resAscendenteV.length; i++) {
            System.out.print(resAscendenteV[i] + " ");
        }
        System.out.println("");
        System.out.println("DESCENDENTE: ");
        int[] resDescendenteV = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_QUICKSORTPV);
        for (int i = 0; i < resDescendenteV.length; i++) {
            System.out.print(resDescendenteV[i] + " ");
        }
        System.out.println("");
    }
}
