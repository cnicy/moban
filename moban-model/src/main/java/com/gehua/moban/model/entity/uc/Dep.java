package com.gehua.moban.model.entity.uc;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 代码生成器自动生成
 * Date:2015-11-16 13:12:31
 * @author
 */
@Table(name = "uc_dep")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dep implements Serializable{
	private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "ud_id")
  	  private Integer id; // 主键id
      @Column(name = "ud_name")
  	  private String name; // 名称
      @Column(name = "ud_phone")
  	  private String phone; // 联系电话
  	  /**
	   * 主键id
	   * @return id
	   */
	  public Integer getId(){
	   return id;
	  }
	  /**
	   * 主键id
	   * @param id 
	   */
	  public void setId(Integer id){
	    this.id = id;
	  }
  	  /**
	   * 名称
	   * @return name
	   */
	  public String getName(){
	   return name;
	  }
	  /**
	   * 名称
	   * @param name 
	   */
	  public void setName(String name){
	    this.name = name;
	  }
  	  /**
	   * 联系电话
	   * @return phone
	   */
	  public String getPhone(){
	   return phone;
	  }
	  /**
	   * 联系电话
	   * @param phone 
	   */
	  public void setPhone(String phone){
	    this.phone = phone;
	  }
}