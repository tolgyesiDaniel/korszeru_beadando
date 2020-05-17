package korszeru.adatbazis.beadando.Views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import korszeru.adatbazis.beadando.Components.AppMenuBar;

@PreserveOnRefresh
public abstract class AbstractView extends VerticalLayout {

    public void initView(){
        add(new AppMenuBar());
    }
}
