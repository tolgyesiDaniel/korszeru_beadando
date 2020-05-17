package korszeru.adatbazis.beadando.Controllers;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToFloatConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import korszeru.adatbazis.beadando.Entity.Beer;
import korszeru.adatbazis.beadando.Entity.Brewery;
import korszeru.adatbazis.beadando.Entity.Geo;
import korszeru.adatbazis.beadando.Repository.BeerRepository;
import korszeru.adatbazis.beadando.Repository.BreweryRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCreateController extends VerticalLayout {

    public BeerRepository beerRepository = ApplicationContextHelper.getBeerRepository();
    public BreweryRepository breweryRepository = ApplicationContextHelper.getBreweryRepository();

    public Binder<Beer> beerBinder = new Binder<>(Beer.class);
    public Binder<Brewery> breweryBinder = new Binder<>(Brewery.class);
    public Binder<Geo> geoBinder = new Binder<>(Geo.class);

    //Beer fields
    public TextField beerName = new TextField("Name: ");
    public TextField beerAbv = new TextField("Abv: ");
    public TextField beerCategory = new TextField("Category: ");
    public TextField beerDescription = new TextField("Description: ");
    public TextField beerIbu = new TextField("Ibu: ");
    public TextField beerSrm = new TextField("Srm: ");
    public TextField beerStyle = new TextField("Style: ");
    public TextField beerUpc = new TextField("Upc: ");
    public ComboBox<String> beerBreweryComboBox = new ComboBox<>();

    //Brewery fields
    public TextField breweryName = new TextField("Name: ");
    public TextField breweryCode = new TextField("Code: ");
    public TextField breweryDescription = new TextField("Description: ");
    public TextField breweryPhone = new TextField("Phone: ");
    public TextField breweryWebsite = new TextField("Website: ");
    public TextField breweryCountry = new TextField("Country: ");
    public TextField breweryState = new TextField("State: ");
    public TextField breweryCity = new TextField("City: ");
    public TextField breweryAddress = new TextField("Address: ");
    public TextField breweryGeoAccuracy = new TextField("Geo Accuracy: ");
    public TextField breweryGeoLat = new TextField("Geo Lat: ");
    public TextField breweryGeoLon = new TextField("Geo Lon: ");

    public Button confirmButton = new Button("Save", new Icon(VaadinIcon.CHECK));
    public Button cancelButton = new Button("Cancel", new Icon(VaadinIcon.CLOSE));

    public void initBeerBinder(){
        beerBinder.forField(beerName)
                .asRequired("Fill beer name!")
                .bind(Beer::getName, Beer::setName);
        beerBinder.forField(beerAbv)
                .withConverter(new StringToFloatConverter(""))
                .asRequired("Fill beer abv!")
                .bind(Beer::getAbv, Beer::setAbv);
        beerBinder.forField(beerCategory)
                .asRequired("Fill beer category!")
                .bind(Beer::getCategory, Beer::setCategory);
        beerBinder.forField(beerDescription)
                .asRequired("Fill beer description!")
                .bind(Beer::getDescription, Beer::setDescription);
        beerBinder.forField(beerIbu)
                .withConverter(new StringToIntegerConverter(""))
                .asRequired("Fill beer Ibu!")
                .bind(Beer::getIbu, Beer::setIbu);
        beerBinder.forField(beerSrm)
                .asRequired("Fill beer Srm!")
                .bind(Beer::getSrm, Beer::setSrm);
        beerBinder.forField(beerStyle)
                .asRequired("Fill beer style!")
                .bind(Beer::getStyle, Beer::setStyle);
        beerBinder.forField(beerUpc)
                .withConverter(new StringToIntegerConverter(""))
                .asRequired("Fill beer Srm!")
                .bind(Beer::getUpc, Beer::setUpc);
    }

    public void initBreweryBinder(){
        breweryBinder.forField(breweryName)
                .asRequired("Fill brewery name!")
                .bind(Brewery::getName, Brewery::setName);
        breweryBinder.forField(breweryCode)
                .asRequired("Fill brewery code!")
                .bind(Brewery::getCode, Brewery::setCode);
        breweryBinder.forField(breweryDescription)
                .asRequired("Fill brewery description!")
                .bind(Brewery::getDescription, Brewery::setDescription);
        breweryBinder.forField(breweryPhone)
                .asRequired("Fill brewery phone!")
                .bind(Brewery::getPhone, Brewery::setPhone);
        breweryBinder.forField(breweryWebsite)
                .asRequired("Fill brewery website!")
                .bind(Brewery::getWebsite, Brewery::setWebsite);
        breweryBinder.forField(breweryCountry)
                .asRequired("Fill brewery country!")
                .bind(Brewery::getCountry, Brewery::setCountry);
        breweryBinder.forField(breweryState)
                .asRequired("Fill brewery state!")
                .bind(Brewery::getState, Brewery::setState);
        breweryBinder.forField(breweryCity)
                .asRequired("Fill brewery city!")
                .bind(Brewery::getCity, Brewery::setCity);
        breweryBinder.forField(breweryAddress)
                .asRequired("Fill brewery address!")
                .bind(Brewery::getAddress, Brewery::setAddress);
        geoBinder.forField(breweryGeoAccuracy)
                .asRequired("Fill brewery accuracy!")
                .bind(Geo::getAccuracy, Geo::setAccuracy);
        geoBinder.forField(breweryGeoLat)
                .withConverter(new StringToFloatConverter(""))
                .asRequired("Fill brewery lat!")
                .bind(Geo::getLat, Geo::setLat);
        geoBinder.forField(breweryGeoLon)
                .withConverter(new StringToFloatConverter(""))
                .asRequired("Fill brewery lon!")
                .bind(Geo::getLon, Geo::setLon);
    }

    public void initBeerBreweryComboBox(Beer beer){
        beerBreweryComboBox.setLabel("Brewery");
        List<Brewery> breweryNames = breweryRepository.findAllBreweryName("brewery");
        List<String> names = new ArrayList<>();

        for(Brewery brewery : breweryNames){
            names.add(brewery.getName());
        }

        beerBreweryComboBox.setItems(names);

        beerBreweryComboBox.addValueChangeListener(event -> {
            beer.setBrewery_id(beerBreweryComboBox.getValue());
        });
    }

    public void initComponents(Dialog dialog, Label dialogHeader){
        //beer components
        beerName.setValueChangeMode(ValueChangeMode.EAGER);
        beerName.setRequiredIndicatorVisible(true);
        beerName.setWidth("450px");

        beerAbv.setValueChangeMode(ValueChangeMode.EAGER);
        beerAbv.setRequiredIndicatorVisible(true);
        beerAbv.setWidth("450px");

        beerCategory.setValueChangeMode(ValueChangeMode.EAGER);
        beerCategory.setRequiredIndicatorVisible(true);
        beerCategory.setWidth("450px");

        beerDescription.setValueChangeMode(ValueChangeMode.EAGER);
        beerDescription.setRequiredIndicatorVisible(true);
        beerDescription.setWidth("450px");

        beerIbu.setValueChangeMode(ValueChangeMode.EAGER);
        beerIbu.setRequiredIndicatorVisible(true);
        beerIbu.setWidth("450px");

        beerSrm.setValueChangeMode(ValueChangeMode.EAGER);
        beerSrm.setRequiredIndicatorVisible(true);
        beerSrm.setWidth("450px");

        beerStyle.setValueChangeMode(ValueChangeMode.EAGER);
        beerStyle.setRequiredIndicatorVisible(true);
        beerStyle.setWidth("450px");

        beerUpc.setValueChangeMode(ValueChangeMode.EAGER);
        beerUpc.setRequiredIndicatorVisible(true);
        beerUpc.setWidth("450px");

        beerBreweryComboBox.setRequiredIndicatorVisible(true);
        beerBreweryComboBox.isRequired();
        beerBreweryComboBox.setErrorMessage("Choose brewery!");
        beerBreweryComboBox.setWidth("450px");

        //brewery components
        breweryName.setValueChangeMode(ValueChangeMode.EAGER);
        breweryName.setRequiredIndicatorVisible(true);
        breweryName.setWidth("450px");

        breweryCode.setValueChangeMode(ValueChangeMode.EAGER);
        breweryCode.setRequiredIndicatorVisible(true);
        breweryCode.setWidth("450px");

        breweryDescription.setValueChangeMode(ValueChangeMode.EAGER);
        breweryDescription.setRequiredIndicatorVisible(true);
        breweryDescription.setWidth("450px");

        breweryPhone.setValueChangeMode(ValueChangeMode.EAGER);
        breweryPhone.setRequiredIndicatorVisible(true);
        breweryPhone.setWidth("450px");

        breweryWebsite.setValueChangeMode(ValueChangeMode.EAGER);
        breweryWebsite.setRequiredIndicatorVisible(true);
        breweryWebsite.setWidth("450px");

        breweryCountry.setValueChangeMode(ValueChangeMode.EAGER);
        breweryCountry.setRequiredIndicatorVisible(true);
        breweryCountry.setWidth("450px");

        breweryState.setValueChangeMode(ValueChangeMode.EAGER);
        breweryState.setRequiredIndicatorVisible(true);
        breweryState.setWidth("450px");

        breweryCity.setValueChangeMode(ValueChangeMode.EAGER);
        breweryCity.setRequiredIndicatorVisible(true);
        breweryCity.setWidth("450px");

        breweryAddress.setValueChangeMode(ValueChangeMode.EAGER);
        breweryAddress.setRequiredIndicatorVisible(true);
        breweryAddress.setWidth("450px");

        breweryGeoAccuracy.setValueChangeMode(ValueChangeMode.EAGER);
        breweryGeoAccuracy.setRequiredIndicatorVisible(true);
        breweryGeoAccuracy.setWidth("450px");

        breweryGeoLat.setValueChangeMode(ValueChangeMode.EAGER);
        breweryGeoLat.setRequiredIndicatorVisible(true);
        breweryGeoLat.setWidth("450px");

        breweryGeoLon.setValueChangeMode(ValueChangeMode.EAGER);
        breweryGeoLon.setRequiredIndicatorVisible(true);
        breweryGeoLon.setWidth("450px");

        //global components
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(confirmButton, cancelButton);

        confirmButton.getStyle().set("marginRight", "25%");
        confirmButton.getStyle().set("marginTop", "30px");
        confirmButton.getStyle().set("marginLeft", "15%");

        cancelButton.getStyle().set("marginTop", "30px");
        cancelButton.getStyle().set("color", "red");

        dialogHeader.getStyle().set("marginLeft", "-10px");
        dialogHeader.getStyle().set("fontWeight", "bold");
        dialogHeader.getStyle().set("fontSize", "14pt");

        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        dialog.setHeight("auto");
        dialog.setWidth("450px");
    }

}
