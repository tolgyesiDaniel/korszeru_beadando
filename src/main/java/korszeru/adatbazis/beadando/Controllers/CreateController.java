package korszeru.adatbazis.beadando.Controllers;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import korszeru.adatbazis.beadando.Entity.Beer;
import korszeru.adatbazis.beadando.Entity.Brewery;
import korszeru.adatbazis.beadando.Entity.Geo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateController extends AbstractCreateController {

    private Beer beer = new Beer();
    private Brewery brewery = new Brewery();
    private Geo geo = new Geo();

    private Dialog createDialog = new Dialog();
    private Label dialogHeader = new Label("Add");

    public void beerCreate(){
        createDialog.open();
        initBeerBreweryComboBox(beer);

        confirmButton.addClickListener(buttonClickEvent -> {
            if(beerBreweryComboBox.isEmpty()){
                beerBreweryComboBox.setInvalid(true);
            }
            else if(beerBinder.writeBeanIfValid(beer)){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());

                beer.setType("beer");
                beer.setUpdated(formatter.format(date));
                beer.setBrewery_id((beerBreweryComboBox.getValue()).replace(" ", "_").toLowerCase());

                beerRepository.save(beer);
                createDialog.close();
                UI.getCurrent().getPage().reload();
            }
        });

        cancelButton.addClickListener(buttonClickEvent -> {
            createDialog.close();
            beerBinder.readBean(null);
            beerBreweryComboBox.setInvalid(false);
        });

        if(beerBinder.isValid()){
            beerBinder.readBean(beer);
            initBeerBinder();
        }

        initComponents(createDialog, dialogHeader);
        createDialog.add(
                dialogHeader,
                beerName,
                beerAbv,
                beerCategory,
                beerDescription,
                beerIbu,
                beerSrm,
                beerStyle,
                beerUpc,
                beerBreweryComboBox,
                confirmButton,
                cancelButton
        );
    }

    public void breweryCreate(){
        createDialog.open();

        confirmButton.addClickListener(buttonClickEvent -> {
            if(breweryBinder.writeBeanIfValid(brewery) && geoBinder.writeBeanIfValid(geo)){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());

                brewery.setBrewery_id(brewery.getName().replace(" ", "_").toLowerCase());
                brewery.setType("brewery");
                brewery.setUpdated(formatter.format(date));
                brewery.setGeo(geo);

                breweryRepository.save(brewery);
                createDialog.close();
                UI.getCurrent().getPage().reload();
            }
        });

        cancelButton.addClickListener(buttonClickEvent -> {
            createDialog.close();
            breweryBinder.readBean(null);
        });

        if(beerBinder.isValid()){
            breweryBinder.readBean(brewery);
            initBreweryBinder();
        }

        initComponents(createDialog, dialogHeader);
        createDialog.add(
                dialogHeader,
                breweryName,
                breweryCode,
                breweryDescription,
                breweryPhone,
                breweryWebsite,
                breweryCountry,
                breweryState,
                breweryCity,
                breweryAddress,
                breweryGeoAccuracy,
                breweryGeoLat,
                breweryGeoLon,
                confirmButton,
                cancelButton
        );
    }

}
