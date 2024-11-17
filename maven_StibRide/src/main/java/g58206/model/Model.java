package g58206.model;

import g58206.dp.Observable;
import g58206.dp.Observer;
import g58206.exception.GraphException;
import g58206.exception.RepositoryException;
import g58206.model.repository.FavoriteRepository;
import g58206.model.repository.StationRepository;
import g58206.model.repository.StopRepository;
import g58206.model.repository.dto.FavoriteDto;
import g58206.model.repository.dto.StationDto;
import g58206.model.tools.Dijkstra;
import g58206.model.tools.Graph;
import g58206.model.tools.Node;
import g58206.model.tools.Pair;

import java.util.ArrayList;
import java.util.List;

public class Model extends Observable {

    private final StationRepository stationRepository;
    private final StopRepository stopRepository;
    private final FavoriteRepository favoriteRepository;
    private final Graph graph;
    private final List<Pair<List<Integer>, String>> shortestPath;

    public Model() throws GraphException {
        this.stationRepository = new StationRepository();
        this.stopRepository = new StopRepository();
        this.favoriteRepository = new FavoriteRepository();
        this.graph = new Graph(stationRepository, stopRepository);
        this.shortestPath = new ArrayList<>();
    }

    public List<Pair<List<Integer>, String>> getShortestPath() {
        return shortestPath;
    }

    public void dijkstra(String source, String destination) throws GraphException, RepositoryException {
        shortestPath.clear();
        StationDto s = stationRepository.get(source);
        StationDto d = stationRepository.get(destination);
        Graph dijkstra = Dijkstra.calculateShortestPathFromSource(this.graph, graph.getNode(s.getNumber()));
        for (Node n : dijkstra.getNode(d.getNumber()).getShortestPath()) {
            shortestPath.add(
                    new Pair<>(
                            stopRepository.getLines(n.getStation()),
                            stationRepository.get(n.getStation()).getName()
                    )
            );
        }
        notifyObservers("path");
    }

    public List<StationDto> getStations() {
        try {
            return stationRepository.getAll();
        } catch (RepositoryException e) {
            System.err.println("Error while retrieving stations");
            throw new RuntimeException(e);
        }
    }

    public List<FavoriteDto> getFavorites() {
        try {
            return favoriteRepository.getAll();
        } catch (RepositoryException e) {
            System.err.println("Error while retrieving stations");
            throw new RuntimeException(e);
        }
    }

    public void addFav(FavoriteDto fav) throws RepositoryException {
        favoriteRepository.add(fav);
        notifyObservers("fav");
    }

    public void deleteFav(FavoriteDto fav) throws RepositoryException {
        favoriteRepository.remove(fav.getKey());
        notifyObservers("fav");
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer); //To change body of generated methods, choose Tools | Templates.
    }

}
