package com.example.witt.mvplearn03.data;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class StoriesEntity {


    /**
     * title : 作为摄影师，我已经得到了最好的东西
     * ga_prefix : 122307
     * images : ["https://pic1.zhimg.com/v2-8f4119c07c22d325b1c250b4343845ac.jpg"]
     * multipic : true
     * type : 0
     * id : 9662284
     */

    private String title;
    private String ga_prefix;
    private boolean multipic;
    private int type;
    private int id;
    private List<String> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
