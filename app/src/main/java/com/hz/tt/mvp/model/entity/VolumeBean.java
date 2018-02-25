package com.hz.tt.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-02-25.
 */
@Entity
public class VolumeBean {
    /**
     * id : 4
     * typeCode : volume
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
    @Generated(hash = 1177314238)
    public VolumeBean(Long id, String typeCode, String typeName) {
        this.id = id;
        this.typeCode = typeCode;
        this.typeName = typeName;
    }
    @Generated(hash = 201239694)
    public VolumeBean() {
    }

}
