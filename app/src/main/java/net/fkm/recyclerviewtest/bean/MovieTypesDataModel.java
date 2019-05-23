package net.fkm.recyclerviewtest.bean;


import java.util.List;

public class MovieTypesDataModel extends BaseModel {

    private String title;
    private List<MovieTypesContentModel> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieTypesContentModel> getContent() {
        return content;
    }

    public void setContent(List<MovieTypesContentModel> content) {
        this.content = content;
    }

    public static class MovieTypesContentModel extends BaseModel {

        private String name;
        private String url;
        private String thumbnail_pic_s;
        private String tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

}
