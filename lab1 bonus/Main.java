package com.company;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //Declaratii
        int i = 0, j = 0, n = 0, k = 0, ok = 0;
        char c[] = new char[100];
        Date startTimer = new Date();
        String errorMsg = "";

        verificare:
        {
            //Verificare nr parametrii
            if (args.length < 3) {
                ok = 1;
                errorMsg = "Nr de inputuri prea mic";
                break verificare;
            }

            //Primul argument
            for (i = 0; i < args[0].length(); i++)
                if (!(args[0].charAt(i) >= '0' && args[0].charAt(i) <= '9')) {
                    ok = 1;
                    errorMsg = "Primul argument de tipul gresit";
                    break verificare;
                }

            //Al doilea argument
            for (i = 0; i < args[1].length(); i++)
                if (!(args[1].charAt(i) >= '0' && args[1].charAt(i) <= '9')) {
                    ok = 1;
                    errorMsg = "Al doilea argument de tipul gresit";
                    break verificare;
                }

            //Creare variabile
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);

            //Restul parametrilor
            j = 0;
            for (i = 2; i < args.length; i++) {
                if (args[i].length() > 1) {
                    ok = 1;
                    errorMsg = "Trebuie sa introduci caractere, nu stringuri";
                    break verificare;
                }
                if (!((args[i].charAt(0) >= 'a' && args[i].charAt(0) <= 'z')
                        || (args[i].charAt(0) >= 'A' && args[i].charAt(0) <= 'Z'))) {
                    ok = 1;
                    errorMsg = "Caracterul al " + (i - 1) + "-lea nu e o litera";
                    break verificare;
                }
                c[j++] = args[i].charAt(0);
            }
        }

        //Verificare Input
        if (ok == 1) {
            System.out.println("Nu e ok inputul");
            System.out.println(errorMsg);
            return;
        }
        System.out.println("Input acceptat");

        //Creare cuvinte + salvare + afisare
        int lungime_c = j;
        String words[] = new String[n];
        System.out.println("Cuvintele generate sunt: ");
        for (i = 0; i < n; i++) {
            words[i] = "";
            for (j = 0; j < k; j++) {
                int nr_random = (int) (Math.random() * lungime_c);
                words[i] += c[nr_random];
            }
            System.out.println(words[i]);
        }

        //Creare matrice booleana
        boolean x[][] = new boolean[n][n];
        for (i = 0; i < n; i++)
            for (j = i + 1; j < n; j++) {
                ok = 0;
                for (int z = 0; z < k; z++) {
                    if (words[i].charAt(z) == words[j].charAt(z)) {
                        ok = 1;
                        break;
                    }
                }
                x[i][j] = !(ok == 0);
                x[j][i] = x[i][j];
            }

        //Aflare nr vecini min, max + afisare matrice ( daca n < 20)
        int nr[] = new int[n];
        int max = 0;
        int min = n + 1;
        ok = 0;
        if (n <= 100)
            System.out.println("Matricea de vecini: ");

        //parcurgem fiecare linie
        for (i = 0; i < n; i++) {
            //Nr de vecini al cuvantului words[i]
            nr[i] = 0;

            //parcurgem fiecare coloana
            for (j = 0; j < n; j++) {
                //afisam doar ptr nr mic de input
                if (n <= 100)
                    System.out.print(x[i][j] + " ");
                if (x[i][j] == true)
                    nr[i]++;
            }

            //calculare min,max
            if (nr[i] > max) {
                max = nr[i];
            }
            if (nr[i] < min) {
                min = nr[i];
            }

            //verifica daca toate au acelasi nr de vecini
            if (i > 0)
                if (nr[i] != nr[i - 1])
                    ok = 1;

            //ENDL dupa fiecare linie
            if (n <= 100)
                System.out.println();
        }


        //afisare nr min
        System.out.println("Cuvinte cu nr minim de vecini: ");
        for (i = 0; i < n; i++) {
            if (nr[i] == min)
                System.out.println(words[i]);
        }

        //afisare nr max
        System.out.println("Cuvinte cu nr maxim de vecini: ");
        for (i = 0; i < n; i++) {
            if (nr[i] == max)
                System.out.println(words[i]);
        }


        //afisare egalitate vecini
        if (ok == 0)
            System.out.println("Au acelasi numar de vecini cuvintele");
        else System.out.println("Nu au acelasi numar de vecini cuvintele");

        //Verificare timp
        Date finalTimer = new Date();
        float perioada = (finalTimer.getTime() - startTimer.getTime());
        System.out.println("Programul a durat " + perioada + " milisecunde");

        bonus(n, x, words);
    }

    static void bonus(int n, boolean m[][], String words[])
    {
        boolean viz[] = new boolean[n];
        for (boolean v : viz) v = false;

        for (int i  = 0; i < n; i++) {
            if (viz[i] == false) {
                System.out.print("\nComponenta conexa " + (i+1) + ": ");
                dfs(i, n, m, viz, words);
            }
        }
    }

    static void dfs(int i, int n, boolean m[][], boolean viz[], String words[]){
        viz[i] = true;
        System.out.print(words[i] + ", ");
        for (int j = 0; j < n; j++)
            if (m[i][j] == true || m[j][i] == true){
                if (viz[j] == false){
                    dfs(j,n,m,viz,words);
                }
            }
    }
}
