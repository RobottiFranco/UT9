package com.example.ta7;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class TClasificadorTest {

    int[] vectorAleatorio = {429, 86, 331, 330, 5};
    int[] vectorAscendente = {6, 17, 28, 39, 100};
    int[] vectorDescendente = {30, 20, 10, 7, 6};
    TClasificador clasif = new TClasificador();

    public TClasificadorTest() {
    }

    @Test
    public void testInsercionAleatorio() {
        int[] result = clasif.clasificar(vectorAleatorio.clone(),
                1);
        int[] expResult = {5, 86, 330, 331, 429};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testInsercionAscendente() {
        int[] result = clasif.clasificar(vectorAscendente.clone(),
                1);
        int[] expResult = {6, 17, 28, 39, 100};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testInsercionDesendente() {
        int[] result = clasif.clasificar(vectorDescendente.clone(),
                1);
        int[] expResult = {6, 7, 10, 20, 30};
        assertArrayEquals(expResult, result);
    }


    @Test
    public void testShellAleatorio() {
        int[] result = clasif.clasificar(vectorAleatorio.clone(),
                2);
        int[] expResult = {5, 86, 330, 331, 429};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testShellAscendente() {
        int[] result = clasif.clasificar(vectorAscendente.clone(),
                2);
        int[] expResult = {6, 17, 28, 39, 100};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testShellDescendente() {
        int[] result = clasif.clasificar(vectorDescendente.clone(),
                2);
        int[] expResult = {6, 7, 10, 20, 30};
        assertArrayEquals(expResult, result);
    }


    @Test
    public void testBurbujaAleatorio() {
        int[] result = clasif.clasificar(vectorAleatorio.clone(),
                3);
        int[] expResult = {5, 86, 330, 331, 429};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testBurbujaAscendente() {
        int[] result = clasif.clasificar(vectorAscendente.clone(),
                3);
        int[] expResult = {6, 17, 28, 39, 100};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testBurbujaDescendente() {
        int[] result = clasif.clasificar(vectorDescendente.clone(),
                3);
        int[] expResult = {6, 7, 10, 20, 30};
        assertArrayEquals(expResult, result);
    }


    @Test
    public void testQuickSortAleatorio() {
        int[] result = clasif.clasificar(vectorAleatorio.clone(),
                4);
        int[] expResult = {5, 86, 330, 331, 429};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testQuickSortAscendente() {
        int[] result = clasif.clasificar(vectorAscendente.clone(),
                4);
        int[] expResult = {6, 17, 28, 39, 100};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testQuickSortDescendente() {
        int[] result = clasif.clasificar(vectorDescendente.clone(),
                4);
        int[] expResult = {6, 7, 10, 20, 30};
        assertArrayEquals(expResult, result);
    }


    @Test
    public void testHeapSortAleatorio() {
        int[] result = clasif.clasificar(vectorAleatorio.clone(),
                5);
        int[] expResult = {429, 331, 330, 86, 5};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testHeapSortAscendente() {
        int[] result = clasif.clasificar(vectorAscendente.clone(),
                5);
        int[] expResult = {100, 39, 28, 17, 6};
        assertArrayEquals(expResult, result);
    }
    @Test
    public void testHeapSortDescendente() {
        int[] result = clasif.clasificar(vectorDescendente.clone(),
                5);
        int[] expResult = {30, 20, 10, 7, 6};
        assertArrayEquals(expResult, result);
    }
}
