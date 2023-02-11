package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class RewardsModel {

    private Long id;

    private CustomerModel customer;

    @NotNull
    private Integer totalPoints;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
}
