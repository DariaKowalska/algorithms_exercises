package pl.java.case1_ModifiedTable;

//Zadanie 1a Zakładając, że dane w tablicy są zawsze posortowane,
// zmodyfikuj powyższą metodę tak, aby w przypadku braku elementu o dokładnej zadanej
// wartości zwracała index pierwszego większego elementu


public class ModifiedMyList1a {
    int counter = 0;
    int[] myList = new int[100000];

    public double sortBinFindTime = 0.0d;
    public double insertElementTime = 0.0d;


    public int findIndex(int value) throws Exception {
        for (int i = 0; i < counter; i++) {
            if (value == myList[i]) {
                return i;
            }
        }

        throw new Exception("Brak elementu o wartosic: " + value);
    }

    public int sortBinFindIndex(int value) {
        long startTime = System.nanoTime();
        int left = 0;
        int right = counter;

        while (left < right) {
            int center = (left + right) / 2;
            if (myList[center] < value) {
                left = center + 1;
            } else {
                right = center;
            }
        }

        long stopTime = System.nanoTime();
        sortBinFindTime += (stopTime - startTime) / 1_000_000.0d;

        return left;
    }

    public int sortFindIndex(int value) throws Exception {
        for (int i = 0; i < counter; i++) {
            if (value == myList[i]) {
                return i;
            }

            if (myList[i] > value) {
                return i;
            }
        }

        throw new Exception("Tablica jest pusta lub brak większej wartości");
    }

    public int getLength() {
        return counter;
    }

    public int getElement(int index) {
        return myList[index];
    }

// Następnie używając metody wstawiania i wyszukiwania napisz implementację wstawiania,
// która będzie zawsze utrzymywała tablicę w porządku posortowanym rosnąco.

    public void insertElementAndSort(int element) {
        int index;
        try {
            index = sortBinFindIndex(element);
        } catch (Exception e) {
            index = counter;
        }

        insertElement(index, element);
    }

    public void insertElement(int index, int element) {
        long startTime = System.nanoTime();

        int myListIdx = counter + 1;

        int[] temp = new int[counter - index]; // todo: nie zero
        int tempIndex = 0;
        for (int i = index; i < counter; i++) {
            temp[tempIndex] = myList[i];
            tempIndex++;
        }

        myList[index] = element;
        counter++;

        tempIndex = 0;
        for (int i = index + 1; i < counter; i++) {
            myList[i] = temp[tempIndex];
            tempIndex++;
        }
        long stopTime = System.nanoTime();
        insertElementTime += (stopTime - startTime) / 1_000_000.0d;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < counter; i++) {
            result += i + ":" + myList[i] + "\n";
        }

        result += "\nCounter: " + counter + "\n";

        return result;
    }
}
