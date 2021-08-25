package com.luxoft.springdb.lab3.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String codeName;

    public Country() {
    }

    public Country(int id, String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

    public String toString() {
        return id + ". " + name + " (" + codeName + ")";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!Objects.equals(codeName, country.codeName)) return false;
        return Objects.equals(name, country.name);
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (codeName != null ? codeName.hashCode() : 0);
        return result;
    }
}
