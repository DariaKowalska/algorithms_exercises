package pl.java.case1_ModifiedTable;

public class ModifiedTable {
    public static void main(String[] args) {
        int[] numberTable = new int[]{152, 565, 14, 15, 187, 98, 55, 336, 9};

//        a) wszystkie po kolei

        for (int x : numberTable) {
            System.out.println(x);
        }

//        b) wszystkie od końca
        for (int i = numberTable.length - 1; i >= 0; i--) {
            System.out.println(numberTable[i]);


//        c) wszystkie na nieparzystych pozycjach

            for (i = 0; i < numberTable.length; i += 2) {
                System.out.print(numberTable[i] + " ");
            }
//        d) wszystkie podzielne przez 3
            for (i = 0; i < numberTable.length; i++) {
                if (numberTable[i] % 3 == 0)
                    System.out.print(numberTable[i] + " ");
            }
//        e) sumę wszystkich

            int suma = 0;
            for (i = 0; i < numberTable.length; i++) {
                suma += numberTable[i];
            }
            System.out.println(suma);

//        f) sumę pierwszych 4

            suma = 0;
            for (i = 0; i < numberTable.length && i < 4; i++) {
                suma += numberTable[i];
            }
            System.out.println(suma);
//        g) sumę ostatnich 5 większych od 2
            suma = 0;
            int temp = 5;
            for (i = numberTable.length - 1; i >= 0; i--) {
                if (numberTable[i] > 2) {
                    suma += numberTable[i];
                    temp--;
                }
                if (temp == 0)
                    break;

            }
            System.out.println(suma);

//        h) ilość liczb idąc od początku tablicy, by ich suma przekroczyła 10
            temp = 0;
            suma = 0;
            for (i = 0; i < numberTable.length; i++) {
                suma += numberTable[i];
                temp++;
                if (suma > 10)
                    break;
            }
            System.out.println(temp);
        }
    }
}
