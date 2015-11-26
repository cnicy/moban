package com.gehua.moban.model.entity.uc;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by rono on 2015/11/5.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "uc_user")
public class User {
    @Id
    @Column(name = "uc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="uc_name")
    private String name;
    @Column(name="uc_pswd")
    private String pswd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
