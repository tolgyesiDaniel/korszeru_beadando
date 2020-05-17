package korszeru.adatbazis.beadando.Views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import korszeru.adatbazis.beadando.Controllers.ApplicationContextHelper;
import korszeru.adatbazis.beadando.Controllers.CreateController;
import korszeru.adatbazis.beadando.Entity.Beer;
import korszeru.adatbazis.beadando.Entity.Brewery;
import korszeru.adatbazis.beadando.Repository.BeerRepository;
import korszeru.adatbazis.beadando.Repository.BreweryRepository;
import org.vaadin.klaudeta.PaginatedGrid;

import javax.annotation.PostConstruct;
import java.util.List;

@PreserveOnRefresh
@Route(value = "breweries")
public class BreweriesView extends AbstractView {

    private VerticalLayout vlGrid = new VerticalLayout();
    private VerticalLayout vlContainer = new VerticalLayout();
    private VerticalLayout vlBeers = new VerticalLayout();

    private BreweryRepository breweryRepository = ApplicationContextHelper.getBreweryRepository();
    private BeerRepository beerRepository = ApplicationContextHelper.getBeerRepository();

    private PaginatedGrid<Brewery> breweryGrid = new PaginatedGrid<>();

    private TextField filter = new TextField();

    private String type = "brewery";

    private VerticalLayout vlReadDialog = new VerticalLayout();
    private Dialog readDialog = new Dialog();
    private Label dialogHeader = new Label("Informations");

    private CreateController createController = new CreateController();

    private Button readDialogClose = new Button("Close");
    private Button addBrewery = new Button("New Brewery", new Icon(VaadinIcon.PLUS));

    @PostConstruct
    public void init(){
        initView();
        initComponents();

        initFilter();
        initCreate();
        initGrid();

        add(vlContainer, vlGrid);
    }

    private void initGrid(){
        updateList();

        breweryGrid.addColumn(Brewery::getName).setHeader("Name").setSortable(true);
        breweryGrid.addColumn(Brewery::getCity).setHeader("City").setSortable(true);
        breweryGrid.addColumn(Brewery::getCountry).setHeader("Country").setSortable(true);
        breweryGrid.addColumn(Brewery::getAddress).setHeader("Address").setSortable(true);

        breweryGrid.setPageSize(10);
        breweryGrid.setPaginatorSize(2);

        breweryGrid.asSingleSelect().addValueChangeListener(selectionEvent -> {
            if(selectionEvent.getValue() != null){
                vlReadDialog.removeAll();
                initDialog(selectionEvent.getValue());
                breweryGrid.deselectAll();
            }
        });

        vlContainer.add(filter, addBrewery);
        vlGrid.add(breweryGrid);
    }

    private void initFilter(){
        filter.setPlaceholder("Search by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> updateList());
    }

    private void updateList(){
        if(filter.getValue().equals("")){
            breweryGrid.setItems(breweryRepository.findAll(type));
        }
        else {
            breweryGrid.setItems(breweryRepository.findAllByName(filter.getValue()+"%", type));
        }
    }

    private void initCreate(){
        addBrewery.addClickListener(click -> {
            createController.breweryCreate();
        });
    }

    private void initDialog(Brewery brewery){
        readDialog.open();

        List<Beer> beerList = beerRepository.findAllBeerByBrewery(brewery.getName());

        Label breweryName = new Label("Name: " + brewery.getName());
        Label breweryCode = new Label("Code: " + brewery.getCode());
        Label breweryDescription = new Label("Description: " + brewery.getDescription());
        Label breweryPhone = new Label("Phone: " + brewery.getPhone());
        Label breweryType = new Label("Type: " + brewery.getType());
        Label breweryWebsite = new Label("Website: " + brewery.getWebsite());
        Label breweryCountry = new Label("Country: " + brewery.getCountry());
        Label breweryState = new Label("State: " + brewery.getState());
        Label breweryCity = new Label("City: " + brewery.getCity());
        Label breweryAddress = new Label("Address: " + brewery.getAddress());
        Label breweryAccuracy = new Label("Geo accuracy: " + brewery.getGeo().getAccuracy());
        Label breweryLat = new Label("Geo lat: " + brewery.getGeo().getLat());
        Label breweryLon = new Label("Geo lon: " + brewery.getGeo().getLon());
        Label breweryUpdated = new Label("Updated: " + brewery.getUpdated());
        Label breweryBeers = new Label("Beers: ");

        readDialogClose.addClickListener(dialogCloseEVent -> {
            readDialog.close();
            vlBeers.removeAll();
        });

        vlReadDialog.add(
                dialogHeader,
                breweryName,
                breweryCode,
                breweryDescription,
                breweryPhone,
                breweryType,
                breweryWebsite,
                breweryCountry,
                breweryState,
                breweryCity,
                breweryAddress,
                breweryAccuracy,
                breweryLat,
                breweryLon,
                breweryUpdated,
                breweryBeers
        );

        for(Beer beer : beerList){
            Label name = new Label(beer.getName());
            vlBeers.add(name);
        }

        vlReadDialog.add(vlBeers, readDialogClose);

        readDialog.add(vlReadDialog);
    }

    private void initComponents(){
        readDialog.setHeight("300px");
        readDialog.setCloseOnEsc(true);
        readDialog.setCloseOnOutsideClick(false);
        readDialog.setHeight("auto");
        readDialog.setWidth("600px");

        dialogHeader.getStyle().set("marginLeft", "-10px");
        dialogHeader.getStyle().set("fontWeight", "bold");
        dialogHeader.getStyle().set("fontSize", "14pt");
        dialogHeader.getStyle().set("padding", "15px 0 15px 0");

        vlReadDialog.setWidth("600px");
        vlReadDialog.getStyle().set("text-align", "left");
        vlReadDialog.getStyle().set("font-size", "14pt");

        readDialogClose.getStyle().set("color", "red");
        readDialogClose.getStyle().set("width", "100%");
        readDialogClose.getStyle().set("margin-top", "40px");
        readDialogClose.getStyle().set("border", "1px solid red");

        vlContainer.getStyle().set("display", "inline");
        vlContainer.getStyle().set("margin", "0");
        vlContainer.getStyle().set("padding-bottom", "0");
        filter.getStyle().set("float", "left");
        addBrewery.getStyle().set("float", "right");

        vlGrid.getStyle().set("padding-top", "0");
        vlGrid.getStyle().set("margin-top", "0");
    }

}
