
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TipCalculator extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Tip Calculator");

        Label billLabel = new Label("Bill Amount (₹):");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage: 15%");

        Slider tipSlider = new Slider(0, 30, 15);
        tipSlider.setShowTickLabels(true);
        tipSlider.setShowTickMarks(true);
        tipSlider.setMajorTickUnit(5);
        tipSlider.setBlockIncrement(1);

        Label tipResult = new Label("Tip: ₹0.00");
        Label totalResult = new Label("Total: ₹0.00");
        Label message = new Label();

        Button calculateButton = new Button("Calculate");
        Button clearButton = new Button("Clear");

        // Respond to slider interaction
        tipSlider.valueProperty().addListener(
            (obs, oldVal, newVal) -> {
                tipLabel.setText(
                    "Tip Percentage: " +
                    Math.round(newVal.doubleValue()) + "%"
                );
            }
        );

        // Respond to Calculate button click
        calculateButton.setOnAction(e -> {
            try {
                double bill = Double.parseDouble(
                    billField.getText().trim()
                );

                if (!Double.isFinite(bill) || bill < 0) {
                    message.setText(
                        "Please enter a valid bill amount."
                    );
                    return;
                }

                double percentage = tipSlider.getValue();
                double tip = bill * percentage / 100;
                double total = bill + tip;

                tipResult.setText(
                    String.format("Tip: ₹%.2f", tip)
                );

                totalResult.setText(
                    String.format("Total: ₹%.2f", total)
                );

                message.setText("Calculation complete!");

            } catch (NumberFormatException ex) {
                message.setText(
                    "Please enter a valid number."
                );
            }
        });

        // Respond to Clear button click
        clearButton.setOnAction(e -> {
            billField.clear();
            tipSlider.setValue(15);
            tipResult.setText("Tip: ₹0.00");
            totalResult.setText("Total: ₹0.00");
            message.setText("");
        });

        VBox root = new VBox(
            12,
            title,
            billLabel,
            billField,
            tipLabel,
            tipSlider,
            calculateButton,
            clearButton,
            tipResult,
            totalResult,
            message
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle(
            "-fx-font-size: 14px; -fx-background-color: #f0f8ff;"
        );

        Scene scene = new Scene(root, 350, 480);

        stage.setTitle("JavaFX Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
