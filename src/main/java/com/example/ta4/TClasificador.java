package com.example.ta4;

import com.example.GeneradorDatosGenericos;

public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;

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
        int[] vectorAleatorio = gdg.generarDatosAleatorios();
        int[] vectorAscendente = gdg.generarDatosAscendentes();
        int[] vectorDescendente = gdg.generarDatosDescendentes();

        System.out.println("___________________INSERCION______________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_INSERCION"));
        int[] resAleatorioI = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resAleatorioI.length; i++) {
            System.out.print(resAleatorioI[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAleatorioI));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_INSERCION"));
        int[] resAscendenteI = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resAscendenteI.length; i++) {
            System.out.print(resAscendenteI[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAscendenteI));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_INSERCION"));
        int[] resDescendenteI = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resDescendenteI.length; i++) {
            System.out.print(resDescendenteI[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resDescendenteI));

        System.out.println("_____________________SHELL________________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_SHELL"));
        int[] resAleatorioS = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resAleatorioS.length; i++) {
            System.out.print(resAleatorioS[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAleatorioS));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_SHELL"));
        int[] resAscendenteS = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resAscendenteS.length; i++) {
            System.out.print(resAscendenteS[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAscendenteS));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_SHELL"));
        int[] resDescendenteS = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resDescendenteS.length; i++) {
            System.out.print(resDescendenteS[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resDescendenteS));

        System.out.println("____________________BURBUJA_______________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_BURBUJA"));
        int[] resAleatorioB = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resAleatorioB.length; i++) {
            System.out.print(resAleatorioB[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAleatorioB));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_BURBUJA"));
        int[] resAscendenteB = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resAscendenteB.length; i++) {
            System.out.print(resAscendenteB[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAscendenteB));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_BURBUJA"));
        int[] resDescendenteB = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resDescendenteB.length; i++) {
            System.out.print(resDescendenteB[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resDescendenteB));

        System.out.println("___________________QUICKSORT______________________");
        System.out.println("ALEATORIO: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAleatorio.clone(), "METODO_CLASIFICACION_QUICKSORT"));
        System.out.println("altura: " + altura);
        int[] resAleatorioQ = clasif.clasificar(vectorAleatorio.clone(),
                METODO_CLASIFICACION_QUICKSORT);
        for (int i = 0; i < resAleatorioQ.length; i++) {
            System.out.print(resAleatorioQ[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAleatorioQ));
        System.out.println("ASCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorAscendente.clone(), "METODO_CLASIFICACION_QUICKSORT"));
        System.out.println("altura: " + altura);
        int[] resAscendenteQ = clasif.clasificar(vectorAscendente.clone(),
                METODO_CLASIFICACION_QUICKSORT);
        for (int i = 0; i < resAscendenteQ.length; i++) {
            System.out.print(resAscendenteQ[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resAscendenteQ));
        System.out.println("DESCENDENTE: ");
        System.out.print("Tiempo de ejecución: ");
        System.out.println(clasif.obtenerTiempoDeEjecucion(vectorDescendente.clone(), "METODO_CLASIFICACION_QUICKSORT"));
        System.out.println("altura: " + altura);
        int[] resDescendenteQ = clasif.clasificar(vectorDescendente.clone(),
                METODO_CLASIFICACION_QUICKSORT);
        for (int i = 0; i < resDescendenteQ.length; i++) {
            System.out.print(resDescendenteQ[i] + " ");
        }
        System.out.println("");
        System.out.println("¿Está ordenado? " + estaOrdenado(resDescendenteQ));

    }
}
