package pl.java.case1_ModifiedTable;


// 1 Napisz implementację rozszerzalnej tablicy, do której można wstawić element w dowolne
// miejsce
// Implementacja może na początku tworzyć dużą przewymiarowaną tablicę (np. milion elementów)
// i operować na niej
// Wymaga się funkcji:
// pobierającej wielkość aktualnie wypełnionej tablicy,
// pobieranie elementu o podanym indexie
// wstawianie elementu przed podanym indexem.

public class ModifiedMyList {
    int nextFreeAddress = 1;
    int[][] myList = new int[100000][2];
    double sortTime = 0.0d;
    double insertTime = 0.0d;

    public void sortInsertElement(int value) {
        long startTime = System.nanoTime();
        int index = findIndexByValue(value);
        long stopTime = System.nanoTime();
        sortTime += (stopTime - startTime) / 1_000_000.0d;

        startTime = System.nanoTime();
        insertElement(index, value);
        stopTime = System.nanoTime();
        insertTime += (stopTime - startTime) / 1_000_000.0d;
    }

    public void insertElement(int index, int value) {
        int current = myList[0][1];

        if (index == nextFreeAddress - 1) {
            addElement(value);
            return;
        }

        if (index == 0) {
            myList[nextFreeAddress][1] = getPointerByIndex(0);
            myList[nextFreeAddress][0] = value;
            myList[0][1] = nextFreeAddress;
            nextFreeAddress++;
            return;
        }

        int currentIdx = 0;
        while (true) {
            if (currentIdx == index - 1) {
                myList[nextFreeAddress][1] = myList[current][1];
                myList[current][1] = nextFreeAddress;
                myList[nextFreeAddress][0] = value;
                break;
            }

            if (current == 0) {
                break;
            }

            current = myList[current][1];
            currentIdx++;
        }

        nextFreeAddress++;
    }

    public void addElement(int value) {
        int prevPointer = myList[0][1];
        int currentPointer = myList[0][1];

        while (true) {
            if (currentPointer == 0) {
                myList[prevPointer][1] = nextFreeAddress;
                myList[nextFreeAddress][0] = value;
                break;
            }
            prevPointer = currentPointer;
            currentPointer = myList[currentPointer][1];
        }

        nextFreeAddress++;
    }

    private int findIndexByValue(int value) {
        int left = 0;
        int right = nextFreeAddress - 1;

        while (left < right) {
            int center = (left + right) / 2;

            if (myList[getPointerByIndex(center)][0] < value) {
                left = center + 1;
            } else {
                right = center;
            }
        }

        return left;
    }

    public int getPointerByIndex(int index) {
        int currentPointer = myList[0][1];
        for (int i = 0; i < nextFreeAddress - 1; i++) {
            if (index == i) {
                return currentPointer;
            }
            currentPointer = myList[currentPointer][1];
        }

        return -1;
    }

    @Override
    public String toString() {
        String result = "";
        int current = myList[0][1];
        int i = 0;

        while (current != 0) {
            result += i + ":" + myList[current][0] + "\n";
            current = myList[current][1];
            i++;
        }

        return result;
    }
}
