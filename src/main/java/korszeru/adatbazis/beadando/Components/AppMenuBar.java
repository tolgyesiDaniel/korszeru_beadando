package korszeru.adatbazis.beadando.Components;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@PWA(name = "Beer-Sample", shortName = "Beer-Sample")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class AppMenuBar extends AppLayout {

    private FlexLayout centeredLayout = new FlexLayout();

    public AppMenuBar(){
        centeredLayout.setSizeFull();
        centeredLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        centeredLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        buildAnchor("Home", "/");
        buildAnchor("Beers", "/beers");
        buildAnchor("Breweries", "/breweries");

        addToNavbar(centeredLayout);
    }

    private void buildAnchor(String text, String href){
        Anchor anchor = new Anchor();
        anchor.setText(text);
        anchor.setHref(href);

        Anchor modifiedAnchor = anchorStyler(anchor);
        centeredLayout.add(modifiedAnchor);
    }

    private Anchor anchorStyler(Anchor anchor){
        anchor.getStyle().set("padding", "10px");
        anchor.getStyle().set("text-decoration", "none");
        anchor.getStyle().set("font-size", "17px");
        anchor.getStyle().set("border-bottom", "1px solid #1676f3");
        anchor.getStyle().set("margin-right", "20px");

        return anchor;
    }

}
