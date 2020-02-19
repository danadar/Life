import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXRunner extends Application {

    private LifeGrid lg;
    private GridPane grid;
    public FXRunner() {
        super();
        lg = new LifeGrid(20,11);
        grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(2);
        grid.setStyle("-fx-background-color: black;");

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int width = 500;
        int height = 400;


        primaryStage.setTitle("Game of Life");
        int startCol = 6;
        int startRow = 4;
        for(int i = startCol; i <=startCol + 7; i++){
            lg.change(startRow,i);
            lg.change(startRow + 1,i);
            lg.change(startRow + 2,i);
        }
        lg.change(startRow + 1,startCol + 1);
        lg.change(startRow + 1,startCol + 7 - 1);



        Slider slider = new Slider(1,20,5);


        Animation timeline = new Timeline(
                new KeyFrame(Duration.millis(5000), e ->{
                    lg.next();
                    update();
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.rateProperty().bind(slider.valueProperty());



        Button startBtn = new Button("Start");
        startBtn.setOnAction(event -> timeline.play());
        Button stopBtn = new Button("Stop");

        stopBtn.setOnAction(event -> timeline.stop());
        Button nextBtn = new Button("Next");

        nextBtn.setOnAction(event -> {
            lg.next();
            update();
        });

        final int size = width/lg.cols()-1;

        for(int i = 0; i < lg.rows(); i++) {
            for (int j = 0; j < lg.cols(); j++) {
                Button b = new Button();
                b.setMaxSize(size,size);
                b.setMinSize(size,size);
                if (lg.get(i, j))
                    b.setStyle("-fx-background-color: #00FF00");
                else
                    b.setStyle("-fx-background-color: #FF0000");

                final int finalI = i;
                final int finalJ = j;
                b.setOnAction( e-> {
                        lg.change(finalI, finalJ);
                        update();
                    }
                );

                grid.add(b, j, i);
            }
        }



        FlowPane buttons = new FlowPane();
        buttons.setAlignment(Pos.CENTER);
        buttons.setOrientation(Orientation.HORIZONTAL);

        buttons.getChildren().add(startBtn);
        buttons.getChildren().add(stopBtn);
        buttons.getChildren().add(nextBtn);

        buttons.setHgap(10);


        VBox root = new VBox(20,grid,buttons,slider);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);



        Scene scene = new Scene(root, width + 20,height);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void update() {

        int index = 0;
        for(int i = 0; i < lg.rows(); i++) {
            for (int j = 0; j < lg.cols(); j++) {
                Button b = (Button) grid.getChildren().get(index++);
                if (lg.get(i, j))
                    b.setStyle("-fx-background-color: #00ff00");
                else
                    b.setStyle("-fx-background-color: #FF0000");
            }
        }
    }
}
