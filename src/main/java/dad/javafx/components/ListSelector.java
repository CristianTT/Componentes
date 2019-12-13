package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class ListSelector<T> extends HBox implements Initializable {

	// Model
	private ListProperty<T> leftItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<T> rightItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty leftLabel = new SimpleStringProperty();
	private StringProperty rightLabel = new SimpleStringProperty();

	@FXML
	private Label izquierdaLabel, derechaLabel;
	@FXML
	private ListView<T> izquierdaList, derechaList;
	@FXML
	private Button moverDerechaBtn, moverTodoDerechaBtn, moverTodoIzquierdaBtn, moverIzquierdaBtn;

	public ListSelector() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListSelectorView.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		izquierdaLabel.textProperty().bind(leftLabel);
		derechaLabel.textProperty().bind(rightLabel);
		izquierdaList.itemsProperty().bind(leftItems);
		derechaList.itemsProperty().bind(rightItems);

		moverDerechaBtn.disableProperty().bind(izquierdaList.getSelectionModel().selectedItemProperty().isNull());
		moverIzquierdaBtn.disableProperty().bind(derechaList.getSelectionModel().selectedItemProperty().isNull());
		moverTodoDerechaBtn.disableProperty().bind(Bindings.isEmpty(leftItems));
		moverTodoIzquierdaBtn.disableProperty().bind(Bindings.isEmpty(rightItems));
	}

	@FXML
	void onMoverDerechaAction(ActionEvent event) {
		rightItems.addAll(izquierdaList.getSelectionModel().getSelectedItems());
		leftItems.removeAll(izquierdaList.getSelectionModel().getSelectedItems());
	}

	@FXML
	void onMoverIzquierdaAction(ActionEvent event) {
		leftItems.addAll(derechaList.getSelectionModel().getSelectedItems());
		rightItems.removeAll(derechaList.getSelectionModel().getSelectedItems());
	}

	@FXML
	void onMoverTodoDerechaAction(ActionEvent event) {
		rightItems.addAll(leftItems);
		leftItems.clear();
	}

	@FXML
	void onMoverTodoIzquierdaAction(ActionEvent event) {
		leftItems.addAll(rightItems);
		rightItems.clear();
	}

	public final ListProperty<T> leftItemsProperty() {
		return this.leftItems;
	}

	public final ObservableList<T> getLeftItems() {
		return this.leftItemsProperty().get();
	}

	public final void setLeftItems(final ObservableList<T> leftItems) {
		this.leftItemsProperty().set(leftItems);
	}

	public final ListProperty<T> rightItemsProperty() {
		return this.rightItems;
	}

	public final ObservableList<T> getRightItems() {
		return this.rightItemsProperty().get();
	}

	public final void setRightItems(final ObservableList<T> rightItems) {
		this.rightItemsProperty().set(rightItems);
	}

	public final StringProperty leftLabelProperty() {
		return this.leftLabel;
	}

	public final String getLeftLabel() {
		return this.leftLabelProperty().get();
	}

	public final void setLeftLabel(final String leftLabel) {
		this.leftLabelProperty().set(leftLabel);
	}

	public final StringProperty rightLabelProperty() {
		return this.rightLabel;
	}

	public final String getRightLabel() {
		return this.rightLabelProperty().get();
	}

	public final void setRightLabel(final String rightLabel) {
		this.rightLabelProperty().set(rightLabel);
	}

}
