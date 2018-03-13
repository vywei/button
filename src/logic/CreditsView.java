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

public class CreditsView {

    private static String cssTheme = "theme.css";
    private final BorderPane bp;
    protected static Scene credits;

    public CreditsView()
    {
	User temp = Main.getUser();

	Sidebar sidebar = new Sidebar(temp);
	temp.register((Observer) sidebar);

	VBox left = new VBox();
	left.getChildren().addAll(getHeader(), getBody());


	BorderPane rootbp = new BorderPane();
	rootbp.setLeft(left);
	rootbp.setRight(sidebar);
	rootbp.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
	rootbp.getStyleClass().add("root");

	bp = rootbp;

    }
    
    private HBox getHeader()
    {
    HBox header = new HBox();
    
	Label title = new Label("Credits");
	title.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
	title.getStyleClass().add("a-header");

	header.getChildren().add(title);
	header.setAlignment(Pos.CENTER);
	header.setPadding(new Insets(20,0,0,0));
	
	return header;
    }
    
    public Node getView()
    {
	bp.getStylesheets().add(getClass().getResource(cssTheme).toExternalForm());
	bp.getStyleClass().add("root");
	
	return bp;
    }

    private HBox getBody()
    {

    Text textCredits;
    	
    String entirety = "CSC 309 Winter 2018\n"
        + "Prof Davide Falessi\n\n"
        + "Button Programmed by\n"
        + "-------------------------------------------\n"
        + "Cory \"Crazy Tiger\" Baxes\n"
        + "Tanna \"Eternal Slowdance\" Kaul\n"
        + "Devin \"Powerful Sweatervest\" Nicholson\n"
        + "Mitchell \"Omniscient Noodle\" Overfield\n"
        + "Victor \"Trustworthy GPS\" Wei\n"
        + "Paula \"wait what\" Zitnick\n"
        + "-------------------------------------------\n";
    
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
