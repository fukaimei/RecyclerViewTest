package net.fkm.recyclerviewtest.bean;


import java.util.List;

public class MovieTypesModel extends BaseModel {

    private String reason;
    private int error_code;
    private List<MovieTypesDataModel> data;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<MovieTypesDataModel> getData() {
        return data;
    }

    public void setData(List<MovieTypesDataModel> data) {
        this.data = data;
    }

}
