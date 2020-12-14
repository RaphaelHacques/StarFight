package world;

import character.Fighter;
import character.Player;
import command.Input;
import command.KeyboardCommandPlayer1;
import command.KeyboardCommandPlayer2;
import javafx.scene.image.Image;
import manager.ManagerFighter;

import java.util.ArrayList;
import java.util.List;


public class World {
    private final Image map;
    public Fighter ninja = new Fighter("Ninja");
    public Fighter samourai = new Fighter("samourai");

    private final String name = "test";
    public List<Player> listPlayers = new ArrayList<>();
    public Player player1, player2;
    private final ManagerFighter managerFighter = new ManagerFighter();

    public World(String urlMap, Input input) {
        this.player1 = new Player(ninja, name, 0, new KeyboardCommandPlayer1(input));
        this.player2 = new Player(samourai, name, 0, new KeyboardCommandPlayer2(input));
        listPlayers.add(this.player1);
        listPlayers.add(this.player2);
        this.map = new Image(urlMap);
        this.player1.getHisFighter().getSkin().skinAnimation.play();
        this.player2.getHisFighter().getSkin().skinAnimation.play();
    }

    public ManagerFighter getManagerFighter() {
        return managerFighter;
    }

    public Image getMap() {
        return map;
    }

}
