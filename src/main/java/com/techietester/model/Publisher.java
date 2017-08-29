package com.techietester.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Publisher {

    private int id;
    private String name;
    private int founded;
    private List<TopDeveloper> topDevelopers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public List<TopDeveloper> getTopDevelopers() {
        return topDevelopers;
    }

    public void setTopDevelopers(List<TopDeveloper> topDevelopers) {
        this.topDevelopers = topDevelopers;
    }
}
