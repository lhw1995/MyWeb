package com.restaurant.controller.dto;

/**
 * Created by lhw on 2016/12/31.
 */
public class AssessDto {
    private int itemId;
    private String content;
    private int score;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AssessDto{" +
                "itemId=" + itemId +
                ", content='" + content + '\'' +
                ", score=" + score +
                '}';
    }
}
