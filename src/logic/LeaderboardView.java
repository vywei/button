
package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaderboardView {

    private static String cssTheme = "theme.css";
    private final BorderPane bp;
    protected static Scene credits;
    private static Text textCredits;
    private static Leaderboard leader;


    public LeaderboardView()
    {
        User temp = Main.getUser();

        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);

        VBox left = new VBox();
        left.getChildren().addAll(getHeader(), getBody());


        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(left);
        root.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
        root.getStyleClass().add("root");

        bp = root;
        leader = Leaderboard.getLeaderboard();

    }
    public Node getView()
    {
        bp.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
        bp.getStyleClass().add("root");
        return bp;
    }

    private HBox getHeader()
    {
        Label title = new Label("Credits");
        title.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
        title.getStyleClass().add("a-header");

        HBox header = new HBox();
        header.getChildren().add(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20,0,0,0));
        return header;
    }

    private HBox getBody()
    {
	leader.update();  
	String entirety = leader.listLeaderboard();

	textCredits = new Text();
	textCredits.setText(entirety);
	textCredits.setCache(true);

	HBox textBox = new HBox();
	textBox.getChildren().addAll(textCredits);
	textBox.setPadding(new Insets(130,0,0,0));
	textBox.setMinWidth(450);
	textBox.setMaxWidth(Double.MAX_VALUE);
	textBox.setAlignment(Pos.CENTER);
	textBox.setSpacing(30);

	return textBox;
    }


}
