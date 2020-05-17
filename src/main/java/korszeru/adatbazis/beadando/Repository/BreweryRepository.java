package korszeru.adatbazis.beadando.Repository;

import korszeru.adatbazis.beadando.Entity.Brewery;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "breweries")
public interface BreweryRepository extends CouchbasePagingAndSortingRepository<Brewery, String> {

    //GEO?
    @Query("#{#n1ql.selectEntity} WHERE type = $1 ORDER BY name")
    List<Brewery> findAll(String type);

    @Query("#{#n1ql.selectEntity} WHERE name LIKE $1 AND type=$2 ORDER BY name")
    List<Brewery> findAllByName(String value, String type);

    @Query("#{#n1ql.selectEntity} WHERE type = $1 ORDER BY updated DESC LIMIT 5")
    List<Brewery> findNewestUpdate(String type);

    @Query("SELECT brewery.name, brewery.country, brewery.city, META(brewery).id AS _ID, META(brewery).cas AS _CAS " +
            "FROM #{#n1ql.bucket} beer " +
            "JOIN #{#n1ql.bucket} brewery " +
            "ON meta(brewery).id = beer.brewery_id " +
            "WHERE beer.name LIKE $1")
    List<Brewery> findAllBreweryByBeer(String beerName);

    @Query("SELECT name, META(brewery).id AS _ID, META(brewery).cas AS _CAS " +
            "FROM #{#n1ql.bucket} brewery " +
            "WHERE type = $1")
    List<Brewery> findAllBreweryName(String type);

}
