package com.gehua.moban.model.entity.uc;

/**
 * Created by rono on 2016/1/20.
 */
public class Items {
    private Integer id;
    private String name;
    private Double price;

    public Items() {
    }

    public Items(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
