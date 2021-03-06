package controllers;

import character.Fighter;
import command.KeyboardCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persist.CommandSerializer;

import java.util.ResourceBundle;

/**
 * The type Choose character.
 */
public class ChooseCharacter {
    /**
     * The Bundle.
     */
    public ResourceBundle bundle;
    /**
     * The Player 1 is ready.
     */
    boolean player1IsReady = false;
    /**
     * The Player 2 is ready.
     */
    boolean player2IsReady = false;
    private String player1Name, player2Name;
    private final Stage myStage;
    private DisplayControl displayControl;
    private KeyboardCommand p1Command, p2Command;
    private final String defaultNameP1 = "Florent";
    private final String defaultNameP2 = "Raphael";
    private Fighter fighter1 = new Fighter("ninja");
    private Fighter fighter2 = new Fighter("samourai");

    /**
     * The Name player 1.
     */
    @FXML
    Text namePlayer1, /**
     * The Name player 2.
     */
    namePlayer2;

    /**
     * The Name input player 1.
     */
    @FXML
    TextField nameInputPlayer1, /**
     * The Name input player 2.
     */
    nameInputPlayer2;

    /**
     * The Btn left.
     */
    @FXML
    Button btn_left, /**
     * The Btn right.
     */
    btn_right;

    /**
     * The Selection p 1.
     */
    @FXML
    Pane selectionP1, /**
     * The Selection p 2.
     */
    selectionP2, /**
     * The Control table.
     */
    controlTable;

    /**
     * The Left box.
     */
    @FXML
    VBox leftBox, /**
     * The Right box.
     */
    rightBox;

    /**
     * The Center box.
     */
    @FXML
    HBox centerBox;

    /**
     * The Root.
     */
    @FXML
    BorderPane root;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

        displayControl = new DisplayControl(p1Command, p2Command,bundle);
        controlTable.getChildren().add(displayControl);
        namePlayer1.setText(bundle.getString("NamePlayer1"));
        namePlayer2.setText(bundle.getString("NamePlayer2"));
        btn_left.setText(bundle.getString("Btn_ready"));
        btn_right.setText(bundle.getString("Btn_ready"));

        fighter2.getSkin().skinAnimation.play();
        fighter1.getSkin().skinAnimation.play();
        selectionP2.getChildren().add(fighter2.getSkin());
        selectionP1.getChildren().add(fighter1.getSkin());

        leftBox.scaleXProperty().bind(myStage.widthProperty().divide(750));
        leftBox.scaleYProperty().bind(myStage.heightProperty().divide(500));
        rightBox.scaleXProperty().bind(myStage.widthProperty().divide(750));
        rightBox.scaleYProperty().bind(myStage.heightProperty().divide(500));
        centerBox.scaleXProperty().bind(myStage.widthProperty().divide(750));
        centerBox.scaleYProperty().bind(myStage.heightProperty().divide(500));

        nameInputPlayer1.setText(defaultNameP1);
        nameInputPlayer1.setPrefWidth(myStage.getWidth() / 3);
        nameInputPlayer2.setText(defaultNameP2);
        nameInputPlayer2.setPrefWidth(myStage.getWidth() / 3);
    }

    /**
     * Instantiates a new Choose character.
     *
     * @param myStage the my stage
     * @param bundle  the bundle
     * @throws Exception the exception
     */
    public ChooseCharacter(Stage myStage, ResourceBundle bundle) throws Exception {
        this.myStage = myStage;
        this.bundle = bundle;
        if (CommandSerializer.isSaved("player1")){
            p1Command = CommandSerializer.load("player1");
        }
        else p1Command = new KeyboardCommand(1);
        if (CommandSerializer.isSaved("player2")){
            p2Command = CommandSerializer.load("player2");
        }
        else p2Command = new KeyboardCommand(2);
    }

    /**
     * Sets ready player 2.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void setReadyPlayer2(ActionEvent actionEvent) throws Exception {
        btn_right.setText(bundle.getString("Btn_ready2"));
        player2Name = nameInputPlayer2.getText();
        player2IsReady = true;
        if (player1IsReady)
            launchGame();
    }

    /**
     * Sets ready player 1.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void setReadyPlayer1(ActionEvent actionEvent) throws Exception {
        btn_left.setText(bundle.getString("Btn_ready2"));
        player1Name = nameInputPlayer1.getText();
        player1IsReady = true;
        if(player2IsReady)
            launchGame();
    }

    /**
     * Select samourai p 1.
     *
     * @param actionEvent the action event
     */
    public void selectSamouraiP1(ActionEvent actionEvent){
        fighter1=refreshSelectionAnimation(selectionP1,"Samourai");
    }

    /**
     * Select ninja p 1.
     *
     * @param actionEvent the action event
     */
    public void selectNinjaP1(ActionEvent actionEvent){
        fighter1=refreshSelectionAnimation(selectionP1,"Ninja");
    }

    /**
     * Select samourai p 2.
     *
     * @param actionEvent the action event
     */
    public void selectSamouraiP2(ActionEvent actionEvent){
        fighter2=refreshSelectionAnimation(selectionP2,"Samourai");
    }

    /**
     * Select ninja p 2.
     *
     * @param actionEvent the action event
     */
    public void selectNinjaP2(ActionEvent actionEvent){
        fighter2=refreshSelectionAnimation(selectionP2,"Ninja");
    }


    /**
     *
     * @param paneSelectionAnimation
     * @param typePerso
     * @return
     */
    private Fighter refreshSelectionAnimation(Pane paneSelectionAnimation,String typePerso)
    {
        Fighter fighter = new Fighter(typePerso);
        fighter.getSkin().skinAnimation.play();
        paneSelectionAnimation.getChildren().set(0,fighter.getSkin());
        return fighter;
    }

    /**
     *
     * @throws Exception
     */
    private void launchGame() throws Exception {
        CommandSerializer.save("player1",p1Command);
        CommandSerializer.save("player2",p2Command);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/gamePage.fxml"));
        loader.setController(new GamePage(myStage,player1Name, fighter1, player2Name, fighter2,p1Command, p2Command, bundle));
        Parent root = loader.load();
        Scene gameScene= new Scene(root);

        myStage.setTitle(bundle.getString("GameTitleGame"));
        myStage.getIcons().add(new Image("images/tux.png"));
        myStage.setScene(gameScene);
    }
}
