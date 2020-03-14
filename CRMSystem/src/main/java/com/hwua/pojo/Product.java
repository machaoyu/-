package com.hwua.pojo;


import lombok.Data;

import java.util.Date;
@Data
public class Product {

  private String id;
  private String productNum;
  private String productName;
  private String cityName;
  private Date departureTime;
  private double productPrice;
  private String productDesc;
  private long productStatus;




}
