package com.data.req;

import lombok.Data;

@Data // getter, setter, toString
public class ProductUpdateReq {

    private String name;
    private double price;
    private int quantity;

}
