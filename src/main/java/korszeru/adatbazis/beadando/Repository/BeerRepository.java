package korszeru.adatbazis.beadando.Repository;

import korszeru.adatbazis.beadando.Entity.Beer;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "beers")
public interface BeerRepository extends CouchbasePagingAndSortingRepository<Beer, String> {

    @Query("#{#n1ql.selectEntity} WHERE type = $1 ORDER BY name")
    List<Beer> findAll(String type);

    @Query("#{#n1ql.selectEntity} WHERE name LIKE $1 AND type=$2 ORDER BY name")
    List<Beer> findAllByName(String value, String type);

    @Query("#{#n1ql.selectEntity} WHERE type = $1 ORDER BY abv DESC LIMIT 5")
    List<Beer> findHighestAvb(String type);

    @Query("SELECT beer.name, beer.country, beer.city, META(beer).id AS _ID, META(beer).cas AS _CAS " +
            "FROM #{#n1ql.bucket} beer " +
            "JOIN #{#n1ql.bucket} brewery " +
            "ON meta(brewery).id = beer.brewery_id " +
            "WHERE brewery.name LIKE $1")
    List<Beer> findAllBeerByBrewery(String breweryName);

}
