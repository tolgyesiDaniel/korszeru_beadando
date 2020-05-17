package korszeru.adatbazis.beadando.Entity;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotNull;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String beer_id;

    @NotNull
    @Field
    private float abv;

    @NotNull
    @Field
    private String brewery_id;

    @NotNull
    @Field
    private String category;

    @NotNull
    @Field
    private String description;

    @NotNull
    @Field
    private int ibu;

    @NotNull
    @Field
    private String name;

    @NotNull
    @Field
    private String srm;

    @NotNull
    @Field
    private String style;

    @NotNull
    @Field
    private String type;

    @NotNull
    @Field
    private int upc;

    @NotNull
    @Field
    private String updated;

    public String getBeer_id() {
        return this.beer_id;
    }

    public void setBeer_id(String beer_id) {
        this.beer_id = beer_id;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

    public String getBrewery_id() {
        return brewery_id;
    }

    public void setBrewery_id(String brewery_id) {
        this.brewery_id = brewery_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrm() {
        return srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
