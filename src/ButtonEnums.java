/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tuyen
 */
public enum ButtonEnums {
    GENERATE("Generate array", "images/generate.png"),
    BUBBLE_SORT("Bubble sort", "images/bubbleSort.PNG"),
    SELECTION_SORT("Selection sort", "images/selectionSort.jpg"),
    INSERTION_SORT("Insertion sort", "images/insertionSort.png"),
    SHELL_SORT("Shell sort", "images/shellSort.png"),
    QUICK_SORT("Quick sort", "images/quickSort.png"),
    MERGE_SORT("Merge sort", "images/mergeSort.png"),
    UNDO("", "images/undo.png");
    
    private String label;
    private String buttonBackgroundImageFile;
    ButtonEnums(String label, String imageFile)
    {
        this.label = label;
        this.buttonBackgroundImageFile = imageFile;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public String getBackgroundImageFile()
    {
        return this.buttonBackgroundImageFile;
    }
}
