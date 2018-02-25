package com.hz.tt.mvp.model.entity.response;

import com.hz.tt.mvp.model.entity.CategoryBean;
import com.hz.tt.mvp.model.entity.CountryBean;
import com.hz.tt.mvp.model.entity.OriginBean;
import com.hz.tt.mvp.model.entity.VolumeBean;

import java.util.List;

/**
 * Created by Administrator on 2018-02-25.
 */
public class LiquorResponse {


    /**
     * category : [{"id":1,"typeCode":"category","typeName":"123"},{"id":6,"typeCode":"category","typeName":"½´ÏãÐÍ"},{"id":12,"typeCode":"category","typeName":"酱香型"},{"id":16,"typeCode":"category","typeName":"干型"}]
     * country : [{"id":2,"typeCode":"country","typeName":"123"},{"id":7,"typeCode":"country","typeName":"ÃÀ¹ú"},{"id":10,"typeCode":"country","typeName":"ÖÐ¹ú"},{"id":13,"typeCode":"country","typeName":"美国"},{"id":15,"typeCode":"country","typeName":"中国"},{"id":17,"typeCode":"country","typeName":"智利"}]
     * errorCode :
     * errorMsg :
     * origin : [{"id":3,"typeCode":"origin","typeName":"123"},{"id":8,"typeCode":"origin","typeName":"º¼ÖÝ"},{"id":14,"typeCode":"origin","typeName":"杭州"},{"id":18,"typeCode":"origin","typeName":"科尔查瓜山谷"},{"id":20,"typeCode":"origin","typeName":"贵州"},{"id":21,"typeCode":"origin","typeName":"智利"}]
     * volume : [{"id":4,"typeCode":"volume","typeName":"123"},{"id":5,"typeCode":"volume","typeName":"321"},{"id":9,"typeCode":"volume","typeName":"2L"},{"id":11,"typeCode":"volume","typeName":"3L"},{"id":19,"typeCode":"volume","typeName":"187ml"}]
     */

    private String errorCode;
    private String errorMsg;
    private List<CategoryBean> category;
    private List<CountryBean> country;
    private List<OriginBean> origin;
    private List<VolumeBean> volume;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<CountryBean> getCountry() {
        return country;
    }

    public void setCountry(List<CountryBean> country) {
        this.country = country;
    }

    public List<OriginBean> getOrigin() {
        return origin;
    }

    public void setOrigin(List<OriginBean> origin) {
        this.origin = origin;
    }

    public List<VolumeBean> getVolume() {
        return volume;
    }

    public void setVolume(List<VolumeBean> volume) {
        this.volume = volume;
    }
}
