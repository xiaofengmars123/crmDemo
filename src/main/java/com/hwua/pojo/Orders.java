package com.hwua.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单Pojo类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

  private String id;
  private String orderNum; //订单编号
  private String orderTime; //下单时间
  private long peopleCount; //人数
  private String orderDesc; //订单描述
  private long payType; //支付方式
  private long orderStatus;//订单状态
  private String productId;//旅游产品Id,外键绑定
  private String memberId; //订购的会员Id,外键绑定
  //多表联查区
  private Product product; //旅游产品
  private Member member; //订购的会员信息
  private String travellerId;//此订单旅游人员的外键绑定
  private List<Traveller> travellers; //此订单的旅游人员的信息


}
