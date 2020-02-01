package ru.javaops.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurants_unique_idx")})
public class Restaurant extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Range(min = 0, max = 999999999)
    @Column(name = "zip", nullable = false)
    private int zip;

    @NotBlank
    @Column(name = "country", nullable = false)
    @Size(max = 100)
    private String country;

    @NotBlank
    @Column(name = "city", nullable = false)
    @Size(max = 100)
    private String city;

    @NotBlank
    @Column(name = "address", nullable = false)
    @Size(max = 100)
    private String address;

    public Restaurant(Integer id, String name, int zip, String country, String city, String address) {
        super(id);
        this.name = name;
        this.zip = zip;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getZip(), restaurant.getCountry(), restaurant.getCity(), restaurant.getAddress());
    }

    public Restaurant(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}