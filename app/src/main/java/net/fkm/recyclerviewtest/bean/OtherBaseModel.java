package net.fkm.recyclerviewtest.bean;

import java.util.List;

public class OtherBaseModel extends BaseModel {

    private String desc;
    private List<String> header;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

}
