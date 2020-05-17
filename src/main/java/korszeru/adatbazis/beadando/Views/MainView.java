package korszeru.adatbazis.beadando.Views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import korszeru.adatbazis.beadando.Controllers.ApplicationContextHelper;
import korszeru.adatbazis.beadando.Entity.Beer;
import korszeru.adatbazis.beadando.Entity.Brewery;
import korszeru.adatbazis.beadando.Repository.BeerRepository;
import korszeru.adatbazis.beadando.Repository.BreweryRepository;

import javax.annotation.PostConstruct;

@PreserveOnRefresh
@Route
public class MainView extends AbstractView{

    private BeerRepository beerRepository = ApplicationContextHelper.getBeerRepository();
    private BreweryRepository breweryRepository = ApplicationContextHelper.getBreweryRepository();

    private Grid<Beer> beerGrid = new Grid<>();
    private Grid<Brewery> breweryGrid = new Grid<>();

    private VerticalLayout vlBeerGrid = new VerticalLayout();
    private VerticalLayout vlBreweryGrid = new VerticalLayout();

    @PostConstruct
    public void init(){
        load();
        initView();
        initBeer();
        initBrewery();

        add(vlBeerGrid, vlBreweryGrid);
    }

    private void initBeer(){
        Label label = new Label("Top 5 Beer Abv");
        load();

        beerGrid.addColumn(Beer::getName).setHeader("Name");
        beerGrid.addColumn(Beer::getAbv).setHeader("Abv");

        vlBeerGrid.add(label, beerGrid);
    }

    private void initBrewery(){
        Label label = new Label("Last 5 updated brewery");
        load();

        breweryGrid.addColumn(Brewery::getName).setHeader("Name");
        breweryGrid.addColumn(Brewery::getUpdated).setHeader("Updated");
        breweryGrid.addColumn(Brewery::getAddress).setHeader("Address");

        vlBreweryGrid.add(label, breweryGrid);
    }

    private void load(){
        beerGrid.setItems(beerRepository.findHighestAvb("beer"));
        breweryGrid.setItems(breweryRepository.findNewestUpdate("brewery"));
    }
}
