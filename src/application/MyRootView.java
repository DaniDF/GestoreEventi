package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import it.Daniele.gestore.calendario.controller.Controller;
import it.Daniele.gestore.calendario.model.CompetitionType;
import it.Daniele.gestore.calendario.model.Event;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyRootView extends GridPane implements EventHandler<ActionEvent> {
	private List<CheckBox> filterEvent = new ArrayList<>();
	private ComboBox<Event> possibleEvent;
	private Controller controller;
	private Stage stage;
	private Text outputArea;
	private Button refreshButton;
	
	public MyRootView(Stage stage, Controller controller) {
		super();
		this.controller = controller;
		this.stage = stage;
		
		this.add(this.getRefresh(), 0, 0);
		
		this.add(this.getInputControl(), 0, 1);
		this.add(this.getOutputview(), 1, 1);
		
		this.add(new Label("Prossimo evento: "), 0, 2);
		this.add(this.getNextEvent(), 1, 2);

		this.setVgap(50);
		this.setHgap(20);
		this.setPadding(new Insets(15));
	}

	private Node getRefresh() {
		ImageView refreshImg = new ImageView(new Image("file:img/refresh.png"));
		refreshImg.setFitHeight(24);
		refreshImg.setFitWidth(24);
		this.refreshButton = new Button("Refresh",refreshImg);
		
		this.refreshButton.setOnAction(x -> {it.Daniele.gestore.remote.RemoteControl.main(null);
											 new MySelectView(this.stage).setRootView();
											});
		
		return this.refreshButton;
	}

	private Node getInputControl() {
		VBox result = new VBox();
		
		result.getChildren().add(this.getInputEvent());
		result.getChildren().add(this.getInputEventFilter());
		
		return result;
	}

	private Node getInputEventFilter() {
		FlowPane result = new FlowPane();
		
		Arrays.asList(CompetitionType.values()).forEach(x -> this.filterEvent.add(new CheckBox(x.toString() + "\t")));
		
		this.filterEvent.forEach(x -> x.setSelected(true));
		this.filterEvent.forEach(x -> x.setOnAction(this));
		this.filterEvent.forEach(x -> FlowPane.setMargin(x, new Insets(2,2,0,2)));
		
		result.getChildren().addAll(this.filterEvent);
		
		result.setPadding(new Insets(5,0,0,0));
		
		return result;
	}

	private Node getInputEvent() {
		VBox result = new VBox();
		
		Label eventLabel = new Label("Scegli evento");
		
		this.possibleEvent = new ComboBox<>(FXCollections.observableArrayList(this.controller.getAllEvents()));
		this.possibleEvent.setOnAction(this::viewEventHandler);
		this.possibleEvent.setPlaceholder(new Label("Seleziona un evento"));
		
		result.getChildren().addAll(eventLabel,this.possibleEvent);
		
		VBox.setMargin(eventLabel, new Insets(0,0,0,0));
		VBox.setMargin(this.possibleEvent, new Insets(5,0,0,0));
		
		return result;
	}

	private Node getOutputview() {
		VBox result = new VBox();
		
		this.outputArea = new Text();
		
		result.getChildren().add(this.outputArea);
		
		return result;
	}
	
	private Node getNextEvent() {
		Text result = new Text();
		List<Event> nextEvent = this.controller.getNextEvent();
		
		if(nextEvent.size() > 0) {
			StringBuilder str = new StringBuilder();
			
			nextEvent.parallelStream().forEach(x -> str.append(x.print() + "\n\n"));
			
			result.setText(str.toString());
		}
		
		return result;
	}
	
	public void viewEventHandler(ActionEvent arg0) {
		Event sel = this.possibleEvent.getValue();
			
		if(sel == null) this.outputArea.setText("");
		else this.outputArea.setText(sel.print());
		
		this.add(new Text(), 2, 0);
	}

	@Override
	public void handle(ActionEvent arg0) {
		SortedSet<Event> toShow = this.controller.getAllEvents();
		
		for(CheckBox x : this.filterEvent) {
			if(!x.isSelected()) {
				toShow = toShow.parallelStream().filter(y -> !this.checkEquals(x,y))
									   			.collect(Collectors.toCollection(TreeSet::new));
			}
			
		}
		
		this.possibleEvent.setItems(FXCollections.observableArrayList(toShow));
		
		this.possibleEvent.setDisable(toShow.size() == 0);		
	}

	private boolean checkEquals(CheckBox x, Event y) {
		if(x == null || y == null) throw new IllegalArgumentException("Invalid operands");
		
		return x.toString()
				.contains(y.getCompetitionType().toString());
	}
}
