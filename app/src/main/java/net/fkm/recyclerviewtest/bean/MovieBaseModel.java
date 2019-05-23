package net.fkm.recyclerviewtest.bean;


import java.util.List;

public class MovieBaseModel extends BaseModel {

   private String Code;
   private String Msg;
   private List<MovieDataModel> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public List<MovieDataModel> getData() {
        return Data;
    }

    public void setData(List<MovieDataModel> data) {
        Data = data;
    }

}
