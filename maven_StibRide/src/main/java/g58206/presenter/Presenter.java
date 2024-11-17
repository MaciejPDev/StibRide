package g58206.presenter;

import g58206.dp.Observable;
import g58206.dp.Observer;
import g58206.exception.GraphException;
import g58206.exception.RepositoryException;
import g58206.model.Model;
import g58206.model.repository.dto.FavoriteDto;
import g58206.model.repository.dto.StationDto;
import g58206.view.View;

import java.util.List;
import java.util.Objects;

public class Presenter implements Observer {

    private final Model model;
    private final View view;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initializeStib() {
        List<StationDto> list = model.getStations();
        view.initializeStib(list);
    }

    public void initializeFavorites() {
        List<StationDto> list = model.getStations();
        List<FavoriteDto> flist = model.getFavorites();
        view.initializeFavorites(list, flist);
    }

    public void changeScenes() {
        view.changeScenes();
    }

    public void dijkstra(String source, String destination) throws GraphException, RepositoryException {
        model.dijkstra(source, destination);
    }

    public void addFav(String name, String source, String destination) throws RepositoryException {
        model.addFav(new FavoriteDto(name, source, destination));
    }

    public void deleteFav(FavoriteDto fav) throws RepositoryException {
        model.deleteFav(fav);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (Objects.equals(arg, "path")) {
            view.updateStib(model.getShortestPath());
        } else if (arg == "fav") {
            view.updateFav(model.getFavorites());
        }
    }

}

