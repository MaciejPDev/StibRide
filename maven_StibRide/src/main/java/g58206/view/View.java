package g58206.view;


import g58206.model.repository.dto.FavoriteDto;
import g58206.model.repository.dto.StationDto;
import g58206.model.tools.Pair;
import g58206.presenter.Presenter;
import g58206.view.controller.FavoritesViewController;
import g58206.view.controller.StibViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class View {


    private final Scene scene;
    private int info;
    private final FXMLLoader stibLoader;
    private final FXMLLoader favoritesLoader;
    private final StibViewController stibViewController;
    private final FavoritesViewController favoritesViewController;


    public View(Stage stage) throws IOException {
        this.info = 1;
        stibLoader = new FXMLLoader(getClass().getResource("/stibView.fxml"));
        favoritesLoader = new FXMLLoader(getClass().getResource("/favoritesView.fxml"));
        Parent root = stibLoader.load();
        favoritesLoader.load();

        stibViewController = stibLoader.getController();
        favoritesViewController = favoritesLoader.getController();

        this.scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("STIB");
        stage.show();
    }

    public void initializeStib(List<StationDto> list) {
        stibViewController.initialize(list);
    }

    public void initializeFavorites(List<StationDto> list, List<FavoriteDto> fList) {
        favoritesViewController.initialize(list, fList);
    }

    public void addHandler(Presenter presenter) {
        stibViewController.addHandler(presenter);
        favoritesViewController.addHandler(presenter);
    }

    public void changeScenes() {
        Parent root;
        if (this.info == 1) {
            root = favoritesLoader.getRoot();
            this.info = 2;
        } else {
            root = stibLoader.getRoot();
            this.info = 1;
        }
        this.scene.setRoot(root);
    }

    public void updateStib(List<Pair<List<Integer>, String>> list) {
        stibViewController.cleanTable();
        for (Pair<List<Integer>, String> element : list) {
            stibViewController.addPath(element);
        }
    }

    public void updateFav(List<FavoriteDto> flist) {
        favoritesViewController.cleanTable();
        for (FavoriteDto dto : flist) {
            favoritesViewController.addFav(dto);
        }
    }

}
