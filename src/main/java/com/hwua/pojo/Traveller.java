package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 旅行人员Pojo类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traveller {

  private String id;
  private String name;    //姓名
  private String sex;     //性别
  private String phoneNum;  //手机号
  private long credentialsType; //证件类型
  private String credentialsNum; //身份证
  private long travellerType;


}
