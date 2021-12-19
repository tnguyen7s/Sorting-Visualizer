import java.util.Random;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
This class is used to initialize a random array of numbers, process the sorting, and create a visualization for the sorting
 */

public class FxArray {
    
    private int size;//capacity of array
    private int[] array;
    private int[] tmpArray;//array to store the unsorted array in case that the user want to try using different sorting algorithms for the same array
    
    private Random random = new Random();
    
    private SequentialTransition st = new SequentialTransition();
    private double speed;
    
    private Rectangle[] bars;
    private double width;

    //constructor
    public FxArray(int size, double speed) {
        this.speed = speed;
        this.size = size;
        this.width = 1000 / size;
        array = new int[size];
        tmpArray = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.abs(random.nextInt()) % 400 + 100; //generate an array of random numbers (lengths from 100-500)
            tmpArray[i] = array[i];
        }
        initializeBars();
    }

    //args constructor
    public FxArray(int[] a, int size, double speed) {
        this.speed = speed;
        this.size = size;
        this.width = 1000 / size;
        array = new int[size];
        tmpArray = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = a[i];
            tmpArray[i] = a[i];
        }
        initializeBars();
    }

    //Create bars 
    private void initializeBars() {
        bars = new Rectangle[size];
        //initialize bars
        for (int i = 0; i < size; i++) {
            bars[i] = new Rectangle();
            bars[i].setLayoutX(width * i + i);
            bars[i].setLayoutY(0);
            bars[i].setHeight(array[i]);//the heights of bars correspond to the value of number
            bars[i].setWidth(width);
            bars[i].setFill(ColorSystem.MALACHITEGREEN);
        }
    }
    //Return bars
    public Rectangle[] getBars() {
        return bars;
    }

    public int[] getUnsortedArray() {
        int[] returnedArray = new int[size];
        for (int i = 0; i < size; i++) {
            returnedArray[i] = tmpArray[i];
        }
        return returnedArray;
    }
    
    //bubble sort
    public void bubbleSort() {

        int tmp;
        boolean swapOccured = true;
        int i;
        for (i = size - 2; i >= 0 && swapOccured; i--) {
            int j;
            swapOccured = false;
            for (j = 0; j <= i; j++) {
                if (array[j] > array[j + 1]) {

                    swapOccured = true;
                    swapTwoBars(j, j + 1);
                }
            }
            hightlightSortedBar(j);
        }
        for (int k = i + 1; k >= 0; k--) {
            hightlightSortedBar(k);
        }
        st.play();//execute the transition to complete the sorting process
    }
    
    //selection sort
    public void selectionSort() {
        int smallest;
        int i;
        for (i = 0; i <= size - 2; i++) {
            smallest = i;
            for (int j = i; j <= size - 1; j++) {
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }
            if (i != smallest) {
                swapTwoBars(i, smallest);
            }
            hightlightSortedBar(i);
        }
        hightlightSortedBar(i);
        st.play();
    }
    
    //insertion Sort
    public void insertionSort() {
        int unsortedN;
        for (int i = 1; i <= size - 1; i++) {
            unsortedN = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > unsortedN) {
                    array[j + 1] = array[j];
                    swapTwoBars(j, j + 1);
                } else {
                    break;
                }
            }
            array[j + 1] = unsortedN;
        }
        for (int i = 0; i < size; i++) {
            hightlightSortedBar(i);
        }

        st.play();
    }
    
    // shell sort
    public void shellSort() {
        int gap = size / 2;
        while (gap >= 1) {
            for (int i = gap; i <= size - 1; i++) {
                int j;
                int unsortedN = array[i];
                for (j = i - gap; j >= 0; j = j - gap) {
                    if (array[j] > unsortedN) {
                        swapTwoBars(j, j + gap);
                    } else {
                        break;
                    }
                }
                array[j + gap] = unsortedN;
            }
            gap = gap / 2;
        }
        for (int i = 0; i < size; i++) {
            hightlightSortedBar(i);
        }
        st.play();
    }
    //quick sort

    public void quickSort() {
        quickSort(0, size - 1);
        st.play();
    }

    public void quickSort(int startingIndex, int endingIndex) {
        if ((startingIndex >= endingIndex) || startingIndex < 0 || startingIndex > size - 1 || endingIndex < 0 || endingIndex > size - 1) {
            if (startingIndex == endingIndex) {
                hightlightSortedBar(startingIndex);
            }
            return;
        }
        int pivotIndex = startingIndex;
        int leftScanner = startingIndex;
        int rightScanner = endingIndex;
        while (leftScanner < rightScanner) {
            while (array[leftScanner] <= array[pivotIndex] && leftScanner + 1 <= endingIndex) {
                leftScanner++;
            }
            while (array[rightScanner] > array[pivotIndex] && rightScanner - 1 >= startingIndex) {
                rightScanner--;
            }
            if (leftScanner < rightScanner) {
                swapTwoBars(leftScanner, rightScanner);
            }
        }
        swapTwoBars(pivotIndex, rightScanner);
        hightlightSortedBar(rightScanner);
        if (endingIndex == startingIndex + 1) {
            hightlightSortedBar(rightScanner - 1);
        }
        quickSort(startingIndex, rightScanner - 1);
        quickSort(rightScanner + 1, endingIndex);
    }
    //merge sort

    public void mergeSort() {
        mergeSort(0, size - 1);
        for (int i = 0; i < size; i++) {
            hightlightSortedBar(i);
        }
        st.play();
    }

    public void mergeSort(int startingIndex, int endingIndex) {
        if (startingIndex >= endingIndex || startingIndex < 0 || startingIndex >= size || endingIndex < 0 || endingIndex >= size) {
            return;
        }
        int middleIndex = startingIndex + (endingIndex - startingIndex) / 2;
        mergeSort(startingIndex, middleIndex);
        mergeSort(middleIndex + 1, endingIndex);
        merge(startingIndex, middleIndex, endingIndex);
    }

    public void merge(int startingIndex, int middleIndex, int endingIndex) {
        ParallelTransition pt = new ParallelTransition();
        for (int i = startingIndex; i <= endingIndex; i++) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(speed));
            tt.setNode(bars[i]);
            tt.setByY(100);
            pt.getChildren().add(tt);
        }
        st.getChildren().add(pt);
        int[] a1 = new int[middleIndex - startingIndex + 1];
        int[] a2 = new int[endingIndex - middleIndex];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = array[i + startingIndex];
        }
        for (int j = 0; j < a2.length; j++) {
            a2[j] = array[j + middleIndex + 1];
        }
        Rectangle[] bars1 = new Rectangle[a1.length];
        for (int i = 0; i < bars1.length; i++) {
            bars1[i] = bars[startingIndex + i];
        }
        Rectangle[] bars2 = new Rectangle[a2.length];
        for (int j = 0; j < bars2.length; j++) {
            bars2[j] = bars[j + middleIndex + 1];
        }
        int m1 = 0;
        int m2 = 0;
        int i;
        for (i = startingIndex; i <= endingIndex && m1 < a1.length && m2 < a2.length; i++) {
            if (a1[m1] <= a2[m2]) {
                array[i] = a1[m1];
                TranslateTransition tt = new TranslateTransition(Duration.millis(speed));
                tt.setNode(bars1[m1]);
                tt.setByX((i - (startingIndex + m1)) * (width + 1));
                tt.setByY(-100);
                st.getChildren().add(tt);
                bars[i] = bars1[m1];
                bars[i].setLayoutY(0);
                m1++;
            } else {
                array[i] = a2[m2];
                TranslateTransition tt = new TranslateTransition(Duration.millis(speed));
                tt.setNode(bars2[m2]);
                tt.setByX((i - (m2 + middleIndex + 1)) * (width + 1));
                tt.setByY(-100);
                st.getChildren().add(tt);
                bars[i] = bars2[m2];
                bars[i].setLayoutY(0);
                m2++;
            }
        }
        if (m1 == a1.length) {
            for (; i <= endingIndex; i++) {
                array[i] = a2[m2];
                TranslateTransition tt = new TranslateTransition(Duration.millis(speed));
                tt.setNode(bars2[m2]);
                tt.setByX((i - (m2 + middleIndex + 1)) * (width + 1));
                tt.setByY(-100);
                st.getChildren().add(tt);
                bars[i] = bars2[m2];
                bars[i].setLayoutY(0);
                m2++;

            }

        }
        if (m2 == a2.length) {
            for (; i <= endingIndex; i++) {
                array[i] = a1[m1];
                TranslateTransition tt = new TranslateTransition(Duration.millis(speed));
                tt.setNode(bars1[m1]);
                tt.setByX((i - (m1 + startingIndex)) * (width + 1));
                tt.setByY(-100);
                st.getChildren().add(tt);
                bars[i] = bars1[m1];
                bars[i].setLayoutY(0);
                m1++;

            }
        }

    }
    //Method to swap two values in the array as well as to swap two rectangle bars on the scene

    private void swapTwoBars(int i, int j) {
        //swap two values in the array
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        //transit two bars and hightlight them
        //hightlight two bars that are being swapped
        FillTransition ft1 = new FillTransition();
        ft1.setToValue((Color) ColorSystem.HARLEQUINGREEN);
        ft1.setShape(bars[i]);

        FillTransition ft2 = new FillTransition();
        ft2.setToValue((Color) ColorSystem.HARLEQUINGREEN);
        ft2.setShape(bars[j]);

        //Create transitions for two bars 
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setNode(bars[i]);
        tt1.setByX((j - i) * (width + 1));
        tt1.setDuration(Duration.millis(speed));

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setNode(bars[j]);
        tt2.setByX((i - j) * (width + 1));
        tt2.setDuration(Duration.millis(speed));

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(ft1, ft2);

        ParallelTransition pt1 = new ParallelTransition();
        pt1.getChildren().addAll(tt1, tt2);

        //Create another parallel transition to bring the color back to its original color
        ParallelTransition pt2 = new ParallelTransition();
        FillTransition ft3 = new FillTransition();
        ft3.setToValue((Color) ColorSystem.MALACHITEGREEN);
        ft3.setShape(bars[i]);
        FillTransition ft4 = new FillTransition();
        ft4.setToValue((Color) ColorSystem.MALACHITEGREEN);
        ft4.setShape(bars[j]);
        pt2.getChildren().addAll(ft3, ft4);
        tt1.setDuration(Duration.millis(speed));
        //add two parallel to the sequential transition
        st.getChildren().addAll(pt, pt1, pt2);

        //swap two bars in the rectangle arrays
        Rectangle tmpBar = bars[i];
        bars[i] = bars[j];
        bars[j] = tmpBar;
    }
    // hightlight the bar that is sorted
    private void hightlightSortedBar(int index) {
        FillTransition ft = new FillTransition();
        ft.setShape(bars[index]);
        ft.setToValue((Color) ColorSystem.SPRINGGREEN);
        ft.setDuration(Duration.millis(100));
        st.getChildren().add(ft);

    }
}
