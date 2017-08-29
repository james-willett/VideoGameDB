package com.techietester.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TopDeveloper {

    private int id;
    private String name;
    private Long salary;

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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
