package com.hz.tt.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-02-25.
 */
@Entity
public class CategoryBean {
    /**
     * id : 1
     * typeCode : category
     * typeName : 123
     */
@Id
    private Long id;
    private String typeCode;
    @Index(unique = true)
    private String typeName;
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeCode() {
        return this.typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1489423515)
    public CategoryBean(Long id, String typeCode, String typeName) {
        this.id = id;
        this.typeCode = typeCode;
        this.typeName = typeName;
    }
    @Generated(hash = 1870435730)
    public CategoryBean() {
    }
}
