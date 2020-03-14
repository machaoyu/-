package com.hwua.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class Orders {

  private String id;
  private String orderNum;
  private Date orderTime;
  private long peopleCount;
  private String orderDesc;
  private long payType;
  private long orderStatus;
  private String productId;
  private String memberId;
  private Product product;
  private Member member;
  private List<Traveller> travellers=new ArrayList<>();



}
