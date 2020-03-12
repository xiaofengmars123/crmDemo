package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员Pojo类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

  private String id;
  private String name;  //会员姓名
  private String nickName; //会员昵称
  private String phoneNum;  //会员手机号
  private String email;  //会员email

}
