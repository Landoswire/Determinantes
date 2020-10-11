/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 *
 * @author Issei
 */
public class Determinante {
    public static void main(String args[]) {
        int[][]Matriz;

        try {
            BufferedReader br = new BufferedReader(new FileReader("determinante.txt"));
            //Longitud de la matriz
            String linea = br.readLine();
            int longitud = Integer.parseInt(linea);//;lo convierte a entero
            //System.out.println(linea);
            Matriz = new int[longitud][longitud];
            
            //filas de la matriz
            System.out.println(longitud);
            linea = br.readLine();
            int fila=0; 
            while (linea!=null) {//recorre las filas de la matriz
               
                String[] entero = linea.split(" "); //lo separamos en un array donde cada entero es un string individual
                for (int i=0;i<entero.length;i++) {
                    Matriz[fila][i] = Integer.parseInt(entero[i]);//lo pasamos a entero para guardarlos en la matriz
                }

                fila++; //Aumentamos para los siguientes enteros
                linea = br.readLine(); //Leemos la siguiente línea
            }
            br.close(); //Se cierra lector

            //Mostramos la matriz leída
            /*for (int i = 0; i < longitud; i++) {
                for (int j = 0; j < longitud; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }*/
            
            printmat(Matriz);

            System.out.println(determin(Matriz));
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra archivo");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("No se convirtio a entero");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("No se pudo acceder al archivo");
            e.printStackTrace();
        }

    }

    public static void printmat(int matriz[][]) {
        int i, j;
        for (i=0;i<matriz.length;i++) {
            for (j=0;j<matriz.length;j++) {
                System.out.print(String.valueOf(matriz[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] submatrize(int matriz[][], int x, int y) {
        int submatriz[][] = new int[matriz.length - 1][matriz.length - 1];
        int i, j, cx = 0, cy = 0;
        for (i=0;i<matriz.length;i++) {
            if (i!=x) {
                cy = 0;
                for (j=0;j<matriz.length;j++) {
                    if (j!=y) {
                        submatriz[cx][cy] = matriz[i][j];
                        cy++;
                    }
                }
                cx++;
            }
        }
        return submatriz;
    }

    public static int determin(int matriz[][]) {
        int determ = 0;
        int i, mult = 1;
        if (matriz.length>2) {
            for (i=0;i<matriz.length;i++) {
                determ += mult * matriz[i][0] * determin(submatrize(matriz, i, 0));
                mult *= -1;
            }
            return determ;
        } else {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }
    }
    
}

