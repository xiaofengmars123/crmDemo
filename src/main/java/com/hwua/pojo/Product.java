package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private String id;
  private String productNum;
  private String productName;
  private String cityName;
  private String departureTime;
  private double productPrice;
  private String productDesc;
  private Integer productStatus;
  private List<Orders> ordersList;



}
