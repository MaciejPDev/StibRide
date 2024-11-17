package g58206.view.controller;

import g58206.exception.GraphException;
import g58206.exception.RepositoryException;
import g58206.model.repository.dto.StationDto;
import g58206.model.tools.Pair;
import g58206.presenter.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.util.List;

public class StibViewController {

    @FXML
    private Button searchButton;

    @FXML
    private Button seeButton;

    @FXML
    private SearchableComboBox<String> origin;

    @FXML
    private SearchableComboBox<String> destination;

    @FXML
    private TableView<Pair<List<Integer>, String>> path;

    @FXML
    private TableColumn<Pair<List<Integer>, String>, String> stationColumn;

    @FXML
    private TableColumn<Pair<List<Integer>, String>, List<Integer>> linesColumn;

    public void initialize(List<StationDto> list) {
        stationColumn.setCellValueFactory(new PropertyValueFactory<>("second"));
        linesColumn.setCellValueFactory(new PropertyValueFactory<>("first"));

        for (StationDto dto : list) {
            origin.getItems().add(dto.getName());
            destination.getItems().add(dto.getName());
        }
        origin.getSelectionModel().selectFirst();
        destination.getSelectionModel().selectFirst();
    }

    public void addHandler(Presenter presenter) {
        seeButton.setOnAction(actionEvent -> {
            presenter.changeScenes();
        });
        searchButton.setOnAction(actionEvent -> {
            try {
                presenter.dijkstra(origin.getSelectionModel().getSelectedItem(), destination.getSelectionModel().getSelectedItem());
            } catch (GraphException | RepositoryException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void cleanTable() {
        this.path.getItems().clear();
    }

    public void addPath(Pair<List<Integer>, String> element) {
        this.path.getItems().add(element);
    }
}
