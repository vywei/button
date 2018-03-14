
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
import javafx.scene.paint.Color;

public class LeaderboardView {

    private static String cssTheme = "theme.css";
    private final BorderPane bp;
    protected static Scene credits;
    private Leaderboard leader;


    public LeaderboardView()
    {
        User temp = Main.getUser();

        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);

        leader = Leaderboard.getLeaderboard();
        
        VBox left = new VBox();
        left.getChildren().addAll(getHeader(), getBody());


        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(left);
        root.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
        root.getStyleClass().add("root");

        bp = root;

    }
    public Node getView()
    {
        bp.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
        bp.getStyleClass().add("root");
        return bp;
    }

    private HBox getHeader()
    {
        Label title = new Label("Leaderboard");
        title.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
        title.getStyleClass().add("a-header");

        HBox header = new HBox();
        header.getChildren().add(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20,0,0,0));
        return header;
    }

    private BorderPane getBody()
    {
    Text textLeft;
    Text textCenter;
    Text textRight;
	leader.update();  
	String left = leader.getLeftList();
	String center = leader.getCenterList();
	String right = leader.getRightList();

	textLeft = new Text();
	textCenter = new Text();
	textRight = new Text();
	
	textLeft.setText(left);
	textLeft.setCache(true);
	
	textCenter.setText(center);
    textCenter.setCache(true);
    
    textRight.setText(right);
    textRight.setCache(true);
    
    textLeft.setFill(Color.WHITE);
    textRight.setFill(Color.WHITE);
    textCenter.setFill(Color.WHITE);

	BorderPane textBox = new BorderPane();
	textBox.setRight(textRight);
	textBox.setCenter(textCenter);
	textBox.setLeft(textLeft);
	textBox.setPadding(new Insets(130,20,0,20));
	textBox.setMinWidth(450);
	textBox.setMaxWidth(Double.MAX_VALUE);

	return textBox;
    }


}
