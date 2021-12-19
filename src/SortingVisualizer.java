/*
Sorting Visualizer:
=> program can implement different kind of sorting algorithms: bubble sort, selection sort, insertion sort, shell sort, quick sort, and merge sort
+ Rectangle Bars with different lengths representing unsorted array of numbers
+ an entry box to change the capacity of array
+ a button to generate a new array of random numbers
+ a text showing the time it takes to run a specific sorting algorithm
+ as two numbers are swapping, their bars are hightlighted 
+ two bars compared
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class SortingVisualizer extends Application {

    int size = 10; //the capacity by default
    Rectangle[] bars;//store the references of rectangle bars representing numbers
    Pane arrayPane;//Pane for the array of bars
    BorderPane mainPane;
    double speed = 2;
    Slider slider = new Slider();
    FxArray fxA;//array object dealing with sorting and visualizing
    Array a;// array object dealing with sorting to get parameters related to the process (running time, numbers of comparisons)

    @Override
    public void start(Stage primaryStage) {
        mainPane = UIElements.getMainPane();
        
        // Entry box to change the capacity of the array
        Text text = UIElements.getCapacityLabel();
        TextField entry = UIElements.getCapacityTextField();
     
        entry.setOnAction(e
                -> {
            size = Integer.parseInt(entry.getText());
        });
        
        HBox entryBox = UIElements.getCapacityHBox(text, entry);
        
        //Buttons
        Button generate = UIElements.getButton(ButtonEnums.GENERATE);
        Button bubble = UIElements.getButton(ButtonEnums.BUBBLE_SORT);
        Button selection = UIElements.getButton(ButtonEnums.SELECTION_SORT);
        Button insertion = UIElements.getButton(ButtonEnums.INSERTION_SORT);
        Button shell = UIElements.getButton(ButtonEnums.SHELL_SORT);
        Button quick = UIElements.getButton(ButtonEnums.QUICK_SORT);
        Button merge = UIElements.getButton(ButtonEnums.MERGE_SORT);
        
         // HBox for the tool bar
        HBox toolBox = UIElements.getToolBarHBox(entryBox, generate, bubble, selection, insertion, shell, quick, merge);
        
        //infoBox: texts showing information of sorting process: running time, number of comparisions
        Text time = UIElements.getRunningTimeText();
        Text comparisons = UIElements.getNumberOfComparisionText();
        
        HBox infoBox = UIElements.getInfoHBox(time, comparisons);
        
        //VBox for tool bar and information bar
        VBox vbox = UIElements.getTopBarVBox(toolBox, infoBox);
        
        // set the top of the main pane to be the vbox where the tool bar and the information bar live in
        mainPane.setTop(vbox);
        
        //Create a warning if the array has not been generated but the sort button is clicked
        Text warning = UIElements.getWarningText();
        infoBox.getChildren().add(warning);
        
        //create a button to return back to the old unsorted array
        Button undo = UIElements.getButton(ButtonEnums.UNDO);
        infoBox.getChildren().add(undo);
        
        undo.setOnAction(e -> {
            if (a == null) {
                warning.setText("Please generate an array!");
            } 
            else {
                fxA = new FxArray(fxA.getUnsortedArray(), size, speed);
                a = new Array(fxA.getUnsortedArray());
                createMainInterface(fxA.getBars());
                warning.setText("");
            }
        });
        
        //create a slider to adjust the speed of sorting algorithm
        slider = UIElements.getSpeedAdjustSlider();
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!newValue.equals(0)) 
                {
                    speed = (double) newValue;
                } 
                else {
                    speed = 1;
                }
            }
        });

        Text sliderLabel= UIElements.getSpeedLabel();
        VBox sliderBox = UIElements.getSliderVBox(sliderLabel, slider);
        
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
                time.setText("Running Time: " + parameters[0] + "ns");
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
        arrayPane = UIElements.getPaneForNumberBars(bars);
        
        mainPane.setCenter(arrayPane);
    }

}
