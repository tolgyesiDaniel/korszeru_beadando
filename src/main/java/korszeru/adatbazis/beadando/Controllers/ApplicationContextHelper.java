package korszeru.adatbazis.beadando.Controllers;

import korszeru.adatbazis.beadando.Repository.BeerRepository;
import korszeru.adatbazis.beadando.Repository.BreweryRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@ConditionalOnMissingBean(name = "applicationContextHelperOverrideBean")
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext context){
        ApplicationContextHelper.context = context;
    }

    public static <T> T getBean(final Class<T> requiredType){
        return context.getBean(requiredType);
    }

    public static BeerRepository getBeerRepository(){
        return getBean(BeerRepository.class);
    }

    public static BreweryRepository getBreweryRepository() { return getBean(BreweryRepository.class); }

}
