package com.hz.tt.mvp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-01-31.
 */
@Entity
public class Sister {
    @Id
    private Long id;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 860484638)
    public Sister(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1625662498)
    public Sister() {
    }

}
