
import javafx.geometry.Insets;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tuyen
 */
public class UIElements {
    /*
    Return the main pane of the UI for the information bar, the tool bar, and the main section illustrating the sorting process
    */
    public static BorderPane getMainPane()
    {
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(ColorSystem.TEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        return mainPane;
    }
    
    /*
    Return the pane where the number bars reside in
    */
    public static Pane getPaneForNumberBars(Rectangle[] bars)
    {
        Pane arrayPane = new Pane();
        arrayPane.setBackground(new Background(new BackgroundFill(ColorSystem.TEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        BorderPane.setMargin(arrayPane, new Insets(0, 50, 20, 50));
        //add each bar into the pane
        for (int i = 0; i < bars.length; i++) {
            arrayPane.getChildren().add(bars[i]);
        }
        
        return arrayPane;
    }
    
    /*
    Return a VBox for tool bar and information bar
    */
    public static VBox getTopBarVBox(HBox toolBox, HBox infoBox)
    {
        VBox vbox = new VBox(20);
        vbox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.getChildren().addAll(toolBox, infoBox);
        return vbox;
    }
    
    /*
    Return HBox for the tool bar
    */
    public static HBox getToolBarHBox(HBox entryBox, Button generate, Button bubble, Button selection, Button insertion, Button shell, Button quick, Button merge)
    {
        HBox toolBox = new HBox(25);
        toolBox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //add tools (entryBox and buttons) to the toolBox
        toolBox.getChildren().addAll(entryBox, generate, bubble, selection, insertion, shell, quick, merge);
        
        return toolBox;
    }
    
    public static Text getCapacityLabel()
    {
        Text text = new Text("Capacity");
        text.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 25));
        text.setFill(Paint.valueOf("white"));
        return text;
    }
    
    /*
    Return the entry to change the capacity of the array
    */
    public static TextField getCapacityTextField()
    {
        TextField entry = new TextField();
        entry.setPrefWidth(40);
        entry.setPrefHeight(48);
        entry.setEditable(true);
        return entry;
    }
    
    /*
    Return the HBox containning the label and the text field to change the capacity.
    */
    public static HBox getCapacityHBox(Text label, TextField entry)
    {
        HBox entryBox = new HBox(5);
        entryBox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        entryBox.getChildren().addAll(label, entry);
        return entryBox;
    }
    
    public static Button getButton(ButtonEnums buttonType)
    {
        Button bt = new Button(buttonType.getLabel(), new ImageView(new Image(buttonType.getBackgroundImageFile(), 40, 40, false, true)));
        bt.setFont(Font.font("Open Sans", FontWeight.BOLD, 15));
        return bt;
    }
    
    /*
    Return running time text UI object
    */
    public static Text getRunningTimeText()
    {
        Text time = new Text("Running time: ");
        time.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 25));
        time.setFill(Paint.valueOf("white"));
        
        return time;
    }
    
    /*
    Return Number of Comparisions text UI object
    */
    public static Text getNumberOfComparisionText()
    {
        Text comparisons = new Text("#Comparisons: ");
        comparisons.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 25));
        comparisons.setFill(Paint.valueOf("white"));
        
        return comparisons;
    }
    
    /*
    Return info HBox: texts showing information of sorting process: running time, number of comparisions
    */
    public static HBox getInfoHBox(Text time, Text comparisons)
    {
        HBox infoBox = new HBox(100);
        infoBox.setBackground(new Background(new BackgroundFill(ColorSystem.DARTHMOUNTHGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        infoBox.getChildren().addAll(time, comparisons);
        
        return infoBox;
    }
    
    /*
    Return the warning text
    */
    public static Text getWarningText()
    {  
        Text warning = new Text("");
        warning.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        warning.setFill(Paint.valueOf("red"));
        
        return warning;
    }
    
    /*
    Return a slider to adjust the speed of sorting algorithm
    */
    public static Slider getSpeedAdjustSlider()
    {
            Slider slider = new Slider();
            slider.setPrefSize(200, 50);
            slider.setMajorTickUnit(500);
            slider.setMax(2000);
            slider.setMinorTickCount(5);
            slider.setMin(0);
            
            return slider;
    }
    
    /*
    Return the label Speed.
    */
    public static Text getSpeedLabel()
    {
        Text t = new Text("Speed (0-2000 millis)");
        t.setFont(Font.font("Open Sans", FontWeight.MEDIUM, 20));
        t.setFill(Paint.valueOf("white"));
        
        return t;
    }
    
        /*
    Return the VBox for the slider.
    */
    public static VBox getSliderVBox(Text label, Slider slider)
    {
        VBox sliderBox = new VBox(5);
        sliderBox.getChildren().addAll(label, slider);
        
        return sliderBox;
    }
    
}
