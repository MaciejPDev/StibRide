package g58206;

import g58206.config.ConfigManager;
import g58206.exception.GraphException;
import g58206.model.Model;
import g58206.presenter.Presenter;
import g58206.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, GraphException {

        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            System.err.println("Problem while loading the config manager");
            throw e;
        }

        Model model = new Model();
        View view = new View(stage);
        Presenter presenter = new Presenter(model, view);

        model.addObserver(presenter);
        view.addHandler(presenter);

        presenter.initializeStib();
        presenter.initializeFavorites();
    }


}