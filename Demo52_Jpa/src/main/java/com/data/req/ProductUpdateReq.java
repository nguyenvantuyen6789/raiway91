package com.data.req;

import lombok.Data;

@Data // getter, setter, toString
public class ProductCreateReq {

    private String name;
    private double price;
    private int quantity;

}
