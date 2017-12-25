package com.example.witt.mvplearn03.data;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/27.
 */
public class RootEntity {
    private String data;
    private ArrayList<StoriesEntity> stories ;
    public void setStories(ArrayList<StoriesEntity> stories){
        this.stories = stories;
    }
    public ArrayList<StoriesEntity> getStories(){
        return this.stories;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RootEntity{" +
                "data='" + data + '\'' +
                ", stories=" + stories +
                '}';
    }
}
