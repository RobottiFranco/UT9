package com.example.ta7;

import java.util.Arrays;

import com.example.GeneradorDatosGenericos;


public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_SELECCION = 6;
    public static final int METODO_CLASIFICACION_CUENTA = 7;

    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            case METODO_CLASIFICACION_HEAPSORT:
                return ordenarPorHeapSort(datosParaClasificar);
            case METODO_CLASIFICACION_SELECCION:
                return ordenarPorSeleccion(datosParaClasificar);
            case METODO_CLASIFICACION_CUENTA:
                return ordenarPorCuenta(datosParaClasificar);
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
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

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

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int aux = datosParaClasificar[i];
                int j = i - 1;
                while ((j >= 0) && (aux < datosParaClasificar[j])) {
                    datosParaClasificar[j + 1] = datosParaClasificar[j];
                    j--;
                }
                datosParaClasificar[j + 1] = aux;
            }
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    static int altura = 0;
    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        altura = quicksortAltura(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private int quicksortAltura(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
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

            int maxAltura = 0;

            if (i < derecha) {
                int alturaIzquierda = quicksortAltura(entrada, i, derecha);
                if (maxAltura < alturaIzquierda) {
                    maxAltura = alturaIzquierda;
                }
            }
            if (izquierda < j) {
                int alturaDerecha = quicksortAltura(entrada, izquierda, j);
                if (maxAltura < alturaDerecha) {
                    maxAltura = alturaDerecha;
                }
            }

            return maxAltura + 1;
        }
        return 0;
    }

    /*private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
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
                quicksort(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j);
            }
        }
    }*/

    public int encuentraPivote(int i, int j, int[] vector) {
        return (i + j) / 2;
    }

    public int encuentraPivote2(int i, int j, int[] vector) {
        int mayor;
        int menor;
        if (vector[0] > vector[1]) {
            mayor = vector[0];
            menor = vector[1];
        } else {
            mayor = vector[1];
            menor = vector[0];
        }
        if (vector.length > 2) {
            for (int k = 0; k < 2; k++) {
                if (vector[k] < menor) {
                    menor = vector[k];
                } else if (vector[k] > mayor) {
                    mayor = vector[k];
                }
            }
            if (vector[0] != menor && vector[0] != mayor) {
                return 0;
            }
            if (vector[1] != menor && vector[1] != mayor) {
                return 1;
            }
            if (vector[2] != menor && vector[2] != mayor) {
                return 2;
            }
        }
        return mayor;
    }

    public int encuentraPivote3(int i, int j, int[] vector) {
        return (int) ((Math.random() * (i - j)) + i);
    }

    public int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i >= 1; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] > datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                    }
                    r = ultimo;
                } else { //r tiene 2 hijos
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * r] > datosParaClasificar[2 * r + 1]) {
                        posicionIntercambio = 2 * r + 1;
                    } else {
                        posicionIntercambio = 2 * r;
                    }
                    if (datosParaClasificar[r] > datosParaClasificar[posicionIntercambio]) {
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    protected int[] ordenarPorSeleccion(int[] datosParaClasificar){
        for (int i = 0 ; i < datosParaClasificar.length - 1 ; i++){
            int indiceMenor = i;
            int claveMenor = datosParaClasificar[i];
            for (int j = i + 1 ; j < datosParaClasificar.length ; j++){
                if (datosParaClasificar[j] < claveMenor){
                    indiceMenor = j;
                    claveMenor = datosParaClasificar[j];
                }
            }
            intercambiar(datosParaClasificar, i, indiceMenor);
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorCuenta(int[] datosParaClasificar){
        int u = Arrays.stream(datosParaClasificar).min().getAsInt();
        int v = Arrays.stream(datosParaClasificar).max().getAsInt();
        int[] cuenta = new int[v+1];
        int[] salida = new int[datosParaClasificar.length];
        for (int i = 0 ; i < v ; i++){
            cuenta[i] = 0;
        }
        for (int j = 0 ; j < datosParaClasificar.length ; j++){
            cuenta[datosParaClasificar[j]]++;
        }
        for (int i = u + 1 ; i <= v ; i++){
            cuenta[i] = cuenta[i] + cuenta[i-1];
        }
        int i;
        for (int j = datosParaClasificar.length - 1; j >= 0 ; j--){
            i = cuenta[datosParaClasificar[j]];
            salida[i-1] = datosParaClasificar[j];
            cuenta[datosParaClasificar[j]] = cuenta[datosParaClasificar[j]]-1;
        }
        return salida;
    }

    public static boolean estaOrdenado(int[] vector) {
        for (int i = 0 ; i < vector.length-1 ; i++) {
            if (vector[i] > vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean estaOrdenadoInvertido(int[] vector) {
        for (int i = 0 ; i < vector.length-1 ; i++) {
            if (vector[i] < vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public long obtenerTiempoDeEjecucion(int[] vectorOriginal, String metodoDeClasificacion) {
        long t1 = System.nanoTime();
        long total = 0;
        int cantLlamadas = 0;
        long tiempoResolucion = 1000000000; //1 segundo en nanosegundos

        while (total < tiempoResolucion) {
            cantLlamadas++;
            int[] datosCopia = vectorOriginal.clone();
            this.clasificar(datosCopia,
                METODO_CLASIFICACION_QUICKSORT);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        long tiempoMedioAlgoritmoBase = total / cantLlamadas;

        t1 = System.nanoTime();
        total = 0;
        cantLlamadas = 0;
        while (total < tiempoResolucion) {
            cantLlamadas++;
            int[] datosCopia = vectorOriginal.clone();
            this.clasificar(datosCopia,
                METODO_CLASIFICACION_QUICKSORT);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        long tiempoCascara = total / cantLlamadas;

        long tiempoMedioAlgoritmo = tiempoMedioAlgoritmoBase - tiempoCascara;

        return tiempoMedioAlgoritmo;
    }

    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] vectorAleatorio = {2,5,3,0,2,3,0,3};
        int[] vectorAscendente = {0,0,2,2,3,3,3,5};
        int[] vectorDescendente = {5,3,3,3,2,2,0,0};

        System.out.println("_____________________CUENTA________________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_CUENTA"));
        int[] resAleatorioC = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_CUENTA);
        for (int i = 0; i < resAleatorioC.length; i++) {
            System.out.print(resAleatorioC[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAleatorioC));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_CUENTA"));
        int[] resAscendenteC = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_CUENTA);
        for (int i = 0; i < resAscendenteC.length; i++) {
            System.out.print(resAscendenteC[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAscendenteC));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_CUENTA"));
        int[] resDescendenteC = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_CUENTA);
        for (int i = 0; i < resDescendenteC.length; i++) {
            System.out.print(resDescendenteC[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resDescendenteC));

        vectorAleatorio = gdg.generarDatosAleatorios();
        vectorAscendente = gdg.generarDatosAscendentes();
        vectorDescendente = gdg.generarDatosDescendentes();
        System.out.println("_____________________CUENTA DISTRIBUCIÓN________________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_CUENTA"));
        int[] resAleatorioCD = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_CUENTA);
        for (int i = 0; i < resAleatorioCD.length; i++) {
            System.out.print(resAleatorioCD[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAleatorioCD));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_CUENTA"));
        int[] resAscendenteCD = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_CUENTA);
        for (int i = 0; i < resAscendenteCD.length; i++) {
            System.out.print(resAscendenteCD[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAscendenteCD));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_CUENTA"));
        int[] resDescendenteCD = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_CUENTA);
        for (int i = 0; i < resDescendenteCD.length; i++) {
            System.out.print(resDescendenteCD[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resDescendenteCD));

        System.out.println("_____________________HEAPSORT________________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_HEAPSORT"));
        int[] resAleatorioH = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_HEAPSORT);
        for (int i = resAleatorioH.length - 1; i >= 0 ; i--) {
            System.out.print(resAleatorioH[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenadoInvertido(resAleatorioH));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_HEAPSORT"));
        int[] resAscendenteH = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_HEAPSORT);
        for (int i = resAscendenteH.length - 1; i >= 0 ; i--) {
            System.out.print(resAscendenteH[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenadoInvertido(resAscendenteH));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_HEAPSORT"));
        int[] resDescendenteH = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_HEAPSORT);
        for (int i = resDescendenteH.length - 1; i >= 0 ; i--) {
            System.out.print(resDescendenteH[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenadoInvertido(resDescendenteH));
    }
}
