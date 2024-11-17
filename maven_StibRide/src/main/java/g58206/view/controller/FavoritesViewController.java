package g58206.view.controller;

import g58206.exception.RepositoryException;
import g58206.model.repository.dto.FavoriteDto;
import g58206.model.repository.dto.StationDto;
import g58206.presenter.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.util.List;

public class FavoritesViewController {

    @FXML
    private Button addButton;

    @FXML
    private Button goButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField nameField;

    @FXML
    private SearchableComboBox<String> origin;

    @FXML
    private SearchableComboBox<String> destination;

    @FXML
    private TableView<FavoriteDto> favorites;

    @FXML
    private TableColumn<FavoriteDto, String> nameColumn;

    @FXML
    private TableColumn<FavoriteDto, String> originColumn;

    @FXML
    private TableColumn<FavoriteDto, String> destinationColumn;

    public void initialize(List<StationDto> list, List<FavoriteDto> flist) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        favorites.getItems().addAll(flist);

        for (StationDto dto : list) {
            origin.getItems().add(dto.getName());
            destination.getItems().add(dto.getName());
        }
        origin.getSelectionModel().selectFirst();
        destination.getSelectionModel().selectFirst();
    }

    public void addHandler(Presenter presenter) {
        goButton.setOnAction(actionEvent -> {
            presenter.changeScenes();
        });
        addButton.setOnAction(actionEvent -> {
            try {
                presenter.addFav(
                        nameField.getText(),
                        origin.getSelectionModel().getSelectedItem(),
                        destination.getSelectionModel().getSelectedItem());
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        });
        deleteButton.setOnAction(actionEvent -> {
            if (favorites.getSelectionModel().getSelectedItem() != null) {
                try {
                    presenter.deleteFav(favorites.getSelectionModel().getSelectedItem());
                } catch (RepositoryException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void cleanTable() {
        this.favorites.getItems().clear();
    }

    public void addFav(FavoriteDto dto) {
        this.favorites.getItems().add(dto);
    }
}
