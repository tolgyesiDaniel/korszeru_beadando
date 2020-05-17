package korszeru.adatbazis.beadando.Entity;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brewery {

    @Id
    private String brewery_id;

    @NotNull
    @Field
    private String address;

    @NotNull
    @Field
    private String city;

    @NotNull
    @Field
    private String code;

    @NotNull
    @Field
    private String country;

    @NotNull
    @Field
    private String description;

    @Field
    private Geo geo = new Geo();

    @NotNull
    @Field
    private String name;

    @NotNull
    @Field
    private String phone;

    @NotNull
    @Field
    private String state;

    @NotNull
    @Field
    private String type;

    @NotNull
    @Field
    private String updated;

    @NotNull
    @Field
    private String website;

    public String getBrewery_id() {
        return brewery_id;
    }

    public void setBrewery_id(String brewery_id) {
        this.brewery_id = brewery_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
