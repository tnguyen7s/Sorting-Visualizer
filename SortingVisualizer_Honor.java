/*
Sorting Visualizer:
=> program can implement different kind of sorting algorithms: bubble sort, selection sort, insertion sort, shell sort, quick sort, and merge sort
+ Rectangle Bars with different lengths representing unsorted array of numbers
+ an entry box to change the capacity of array
+ a button to generate a new array of random numbers
+ a text showing the time it takes to run a specific sorting algorithm
+ as two numbers are swapping, their bars are hightlighted 
+ sorted bars are also hightlighted
*/
package sortingvisualizer_honor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//*********************************************************************************************************************************************

public class SortingVisualizer_Honor extends Application {

    int size = 10; //the capacity by default
    Rectangle[] bars;//store the references of rectangle bars representing numbers
    Pane arrayPane;//Pane for the array of bars
    BorderPane mainPane = new BorderPane();
    double speed = 10;
    Slider slider = new Slider();
    FxArray fxA;//array object dealing with sorting and visualizing
    Array a;// array object dealing with sorting to get parameters related to the process (running time, numbers of comparisons)

    @Override
    public void start(Stage primaryStage) {
        //Create a border pane for the information bar, the tool bar, and the main section illustrating the sorting process
        mainPane.setBackground(new Background(new BackgroundFill(ColorSystem.TEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        //VBox for tool bar and information bar
        VBox vbox = new VBox(20);
        vbox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setTop(vbox);
        //HBox for the tool bar
        HBox toolBox = new HBox(25);
        toolBox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //Entry box to change the capacity of the array
        Text text = new Text("Capacity");
        text.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 25));
        text.setFill(Paint.valueOf("white"));
        TextField entry = new TextField();
        entry.setPrefWidth(40);
        entry.setPrefHeight(48);
        entry.setEditable(true);
        entry.setOnAction(e
                -> {
            size = Integer.parseInt(entry.getText());
        });
        HBox entryBox = new HBox(5);
        entryBox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        entryBox.getChildren().addAll(text, entry);
        //Buttons
        Button generate = new Button("Generate array", new ImageView(new Image("generate.PNG", 40, 40, false, true)));
        generate.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        Button bubble = new Button("Bubble sort", new ImageView(new Image("bubbleSort.png", 40, 40, false, true)));
        bubble.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        Button selection = new Button("Selection sort", new ImageView(new Image("selectionSort.png", 40, 40, false, true)));
        selection.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        Button insertion = new Button("Insertion sort", new ImageView(new Image("insertionSort.png", 40, 40, false, true)));
        insertion.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        Button shell = new Button("Shell sort", new ImageView(new Image("shellSort.png", 40, 40, false, true)));
        shell.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        Button quick = new Button("Quick sort", new ImageView(new Image("quickSort.png", 40, 40, false, true)));
        quick.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        Button merge = new Button("Merge sort", new ImageView(new Image("mergeSort.png", 40, 40, false, true)));
        merge.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        //add tools (entryBox and buttons) to the toolBox
        toolBox.getChildren().addAll(entryBox, generate, bubble, selection, insertion, shell, quick, merge);
        //infoBox: texts showing information of sorting process: running time, number of comparisions
        HBox infoBox = new HBox(100);
        infoBox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Text time = new Text("Running time: ");
        time.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 25));
        time.setFill(Paint.valueOf("white"));
        Text comparisons = new Text("#Comparisons: ");
        comparisons.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 25));
        comparisons.setFill(Paint.valueOf("white"));
        infoBox.getChildren().addAll(time, comparisons);
        //add the toolBox and infoBox into the vbox
        vbox.getChildren().addAll(toolBox, infoBox);

        //Create a warning if the array has not been generated but the sort button is clicked
        Text warning = new Text("");
        warning.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        warning.setFill(Paint.valueOf("red"));
        infoBox.getChildren().add(warning);
        //create a button to return back to the old unsorted array
        Button undo = new Button("", new ImageView(new Image("undonbg.png", 40, 40, false, true)));
        undo.setMaxSize(40, 40);
        infoBox.getChildren().add(undo);
        undo.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array!");
            } else {
                fxA = new FxArray(fxA.getUnsortedArray(), size, speed);
                a = new Array(fxA.getUnsortedArray());
                createMainInterface(fxA.getBars());
                warning.setText("");
            }
        });
        //create a slider to adjust the speed of sorting algorithm
        slider.setPrefSize(200, 50);
        slider.setMajorTickUnit(500);
        slider.setMax(2000);
        slider.setMinorTickCount(5);
        slider.setMin(0);
        slider.setOnMouseReleased(e -> {
            if (slider.getValue() != 0) {
                speed = slider.getValue();
            }
        });
        Text t = new Text("Speed (0-2000 millis)");
        t.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 20));
        t.setFill(Paint.valueOf("white"));
        VBox sliderBox = new VBox(5);
        sliderBox.getChildren().addAll(t, slider);
        infoBox.getChildren().add(sliderBox);
        //set action for generate array button (as the user click on the button, an array is generated and thus the stage shows bars of different lengths)
        generate.setOnAction(e -> {
            //create new array object possessing main sorting operations
            fxA = new FxArray(size, speed);
            a = new Array(fxA.getUnsortedArray());
            createMainInterface(fxA.getBars());
            warning.setText("");
        });
        //set action for bubble sort button
        bubble.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array before sorting!");
            } else {
                fxA.bubbleSort();
                int[] parameters = a.bubbleSort();
                time.setText("Running Time: " + parameters[0] + "ns");
                comparisons.setText("#Comparisons: " + parameters[1]);
            }

        });
        //set action for selection sort button
        selection.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array before sorting!");
            } else {
                fxA.selectionSort();
                int[] parameters = a.selectionSort();
                time.setText("Running Time: " + parameters[0] + "ns");
                comparisons.setText("#Comparisons: " + parameters[1]);
            }

        });
        //set action for insertion sort button
        insertion.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array before sorting!");
            } else {
                fxA.insertionSort();
                int[] parameters = a.insertionSort();
                time.setText("Running Time: " + parameters[0] + "ns");
                comparisons.setText("#Comparisons: " + parameters[1]);
            }

        });
        //set action for shell sort button
        shell.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array before sorting!");
            } else {
                fxA.shellSort();
                int[] parameters = a.shellSort();
                time.setText("Running Time: " + parameters[0]+"ns");
                comparisons.setText("#Comparisons: " + parameters[1]);
            }

        });
        //set action for quick sort button
        quick.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array before sorting!");
            } else {
                int[] parameters = a.quickSort();
                time.setText("Running Time: " + parameters[0] + "ns");
                comparisons.setText("#Comparisons: " + parameters[1]);
                fxA.quickSort();
            }

        });
        //set action for merge sort button
        merge.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array before sorting!");
            } else {
                fxA.mergeSort();
                int[] parameters = a.mergeSort();
                time.setText("Running Time: " + parameters[0] + "ns");
                comparisons.setText("#Comparisons: " + parameters[1]);
            }

        });

        Scene scene = new Scene(mainPane, 1366, 700);
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    private void createMainInterface(Rectangle[] createdBars) {
        //pane for the array of bars
        bars = createdBars;
        arrayPane = new Pane();
        arrayPane.setBackground(new Background(new BackgroundFill(ColorSystem.TEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setCenter(arrayPane);
        BorderPane.setMargin(arrayPane, new Insets(0, 50, 20, 50));
        //add each bar into the pane
        for (int i = 0; i < size; i++) {
            arrayPane.getChildren().add(bars[i]);
        }
    }

}
