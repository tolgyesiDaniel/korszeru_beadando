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
@Route(value = "beers")
public class BeersView extends AbstractView {

    private VerticalLayout vlGrid = new VerticalLayout();
    private VerticalLayout vlBreweries = new VerticalLayout();
    private VerticalLayout vlContainer = new VerticalLayout();

    private BeerRepository beerRepository = ApplicationContextHelper.getBeerRepository();
    private BreweryRepository breweryRepository = ApplicationContextHelper.getBreweryRepository();

    private PaginatedGrid<Beer> beerGrid = new PaginatedGrid<>();

    private TextField filter = new TextField();

    private String type = "beer";

    private VerticalLayout vlReadDialog = new VerticalLayout();
    private Dialog readDialog = new Dialog();
    private Label dialogHeader = new Label("Informations");

    private CreateController createController = new CreateController();

    private Button readDialogClose = new Button("Close");
    private Button addBeer = new Button("New Beer", new Icon(VaadinIcon.PLUS));

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

        beerGrid.addColumn(Beer::getName).setHeader("Name").setSortable(true);
        beerGrid.addColumn(Beer::getStyle).setHeader("Style").setSortable(true);
        beerGrid.addColumn(Beer::getCategory).setHeader("Category").setSortable(true);
        beerGrid.addColumn(Beer::getAbv).setHeader("Abv").setSortable(true);
        beerGrid.addColumn(Beer::getIbu).setHeader("Ibu").setSortable(true);
        beerGrid.addColumn(Beer::getSrm).setHeader("Srm").setSortable(true);
        beerGrid.addColumn(Beer::getUpc).setHeader("Upc").setSortable(true);

        beerGrid.setPageSize(10);
        beerGrid.setPaginatorSize(2);

        beerGrid.asSingleSelect().addValueChangeListener(selectionEvent -> {
            if(selectionEvent.getValue() != null){
                vlReadDialog.removeAll();
                initDialog(selectionEvent.getValue());
                beerGrid.deselectAll();
            }
        });

        vlContainer.add(filter, addBeer);
        vlGrid.add(beerGrid);
    }

    private void initFilter(){
        filter.setPlaceholder("Search by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> updateList());
    }

    private void initCreate(){
        addBeer.addClickListener(click -> {
            createController.beerCreate();
        });
    }

    private void updateList(){
        if(filter.getValue().equals("")){
            beerGrid.setItems(beerRepository.findAll(type));
        }
        else {
            beerGrid.setItems(beerRepository.findAllByName(filter.getValue()+"%", type));
        }
    }

    private void initDialog(Beer beer){
        readDialog.open();

        List<Brewery> breweryList = breweryRepository.findAllBreweryByBeer(beer.getName());

        Label beerName = new Label("Name: " + beer.getName());
        Label beerCategory = new Label("Category: " + beer.getCategory());
        Label beerDescription = new Label("Description: " + beer.getDescription());
        Label beerIbu = new Label("Ibu: " + beer.getIbu());
        Label beerSrm = new Label("Srm: " + beer.getSrm());
        Label beerStyle = new Label("Style: " + beer.getStyle());
        Label beerType = new Label("Type: " + beer.getType());
        Label beerUpc = new Label("Upc: " + beer.getUpc());
        Label beerUpdate = new Label("Updated: " + beer.getUpdated());
        Label beerBreweries = new Label("Breweries: ");

        readDialogClose.addClickListener(dialogCloseEVent -> {
            readDialog.close();
            vlBreweries.removeAll();
        });

        vlReadDialog.add(
                dialogHeader,
                beerName,
                beerCategory,
                beerDescription,
                beerIbu,
                beerSrm,
                beerStyle,
                beerType,
                beerUpc,
                beerUpdate,
                beerBreweries
        );

        for(Brewery brewery : breweryList){
            Label name = new Label(brewery.getName());
            vlBreweries.add(name);

            Label city = new Label(brewery.getCity());
            city.getStyle().set("margin-top", "0");
            vlBreweries.add(city);

            Label country = new Label(brewery.getCountry());
            country.getStyle().set("margin-top", "0");
            vlBreweries.add(country);
        }

        vlReadDialog.add(vlBreweries, readDialogClose);

        readDialog.add(vlReadDialog);
    }

    private void initComponents(){
        readDialog.setHeight("300px");
        readDialog.setCloseOnEsc(true);
        readDialog.setCloseOnOutsideClick(false);
        readDialog.setHeight("auto");
        readDialog.setWidth("450px");

        dialogHeader.getStyle().set("marginLeft", "-10px");
        dialogHeader.getStyle().set("fontWeight", "bold");
        dialogHeader.getStyle().set("fontSize", "14pt");
        dialogHeader.getStyle().set("padding", "15px 0 15px 0");

        vlReadDialog.setWidth("450px");
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
        addBeer.getStyle().set("float", "right");

        vlGrid.getStyle().set("padding-top", "0");
        vlGrid.getStyle().set("margin-top", "0");
    }

}
