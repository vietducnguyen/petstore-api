package de.dn.petstore.domain;

import jakarta.persistence.Entity;

@Entity
public class Pet extends NamedEntity {
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
