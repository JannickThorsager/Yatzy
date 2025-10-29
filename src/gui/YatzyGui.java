package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import models.Die;
import models.RaffleCup;


public class YatzyGui extends Application {

    private final Label[] lblDice = new Label[5];
    private final CheckBox[] chbHold = new CheckBox[5];
    private final Label lblAntalKastTilbage = new Label("Kast tilbage: ");
    private final Button btnThrowDice = new Button("Kast terningerne");
    private int kastTilbage = 3;


    @Override
    public void start(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(15));


        GridPane dicePane = new GridPane();
        dicePane.setPadding(new Insets(20));
        dicePane.setHgap(10);
        dicePane.setVgap(10);
        dicePane.setAlignment(Pos.CENTER);
        dicePane.setStyle("""
            -fx-border-color: black;
            -fx-border-width: 1;
            -fx-border-radius: 8;
            -fx-background-color: #f8f8f8;
            -fx-background-radius: 8;
        """);

        initContent(dicePane);

        GridPane scorePane = new GridPane();
        scorePane.setPadding(new Insets(20));
        scorePane.setHgap(10);
        scorePane.setVgap(10);
        scorePane.setAlignment(Pos.TOP_LEFT);
        scorePane.setStyle("""
            -fx-border-color: black;
            -fx-border-width: 1;
            -fx-border-radius: 8;
            -fx-background-color: #f8f8f8;
            -fx-background-radius: 8;
        """);


        scorePane.add(new Label("Pointtabel (kommer her)"), 0, 0);


        root.getChildren().addAll(dicePane, scorePane);

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Yatzy");
        stage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);


        // --- Terningestil ---
        String diceStyle = """
                    -fx-border-color: gray;
                    -fx-border-radius: 8;
                    -fx-background-color: white;
                    -fx-background-radius: 8;
                    -fx-font-size: 22;
                    -fx-alignment: center;
                    -fx-padding: 15 25;
                    -fx-min-width: 50;
                """;

        // === Øverste række: terninger ===
        for (int i = 0; i < 5; i++) {
            lblDice[i] = new Label("");
            lblDice[i].setStyle(diceStyle);
            pane.add(lblDice[i], i + 1, 0); // kolonner 1-5, række 0
        }

        // === Anden række: Hold-checkbokse ===
        for (int i = 0; i < 5; i++) {
            chbHold[i] = new CheckBox("Hold");
            pane.add(chbHold[i], i + 1, 1); // kolonner 1-5, række 1
        }

        // === Tredje række: status og knap ===
        pane.add(lblAntalKastTilbage, 1, 3);
        pane.add(btnThrowDice, 4, 3, 2, 1);
        btnThrowDice.setOnAction(event -> ThrowDice());
    }

    private void ThrowDice() {
        if (kastTilbage <= 0) return;

        RaffleCup raffleCup = new RaffleCup();
        Die[] dice = raffleCup.getDice();

        // Kast kun de terninger, der ikke er holdt
        for (int i = 0; i < dice.length; i++) {
            if (!chbHold[i].isSelected()) {
                dice[i].roll();
                lblDice[i].setText(String.valueOf(dice[i].getEyes()));
            }
        }
        kastTilbage--;
        lblAntalKastTilbage.setText("Kast tilbage: " + kastTilbage);


        if (kastTilbage == 0) {
            btnThrowDice.setDisable(true);
        }
    }


}


