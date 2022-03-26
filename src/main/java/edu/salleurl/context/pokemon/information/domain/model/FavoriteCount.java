package edu.salleurl.context.pokemon.information.domain.model;

public class FavoriteCount {

    private Integer count;


    public FavoriteCount() {
        count = 0;
    }

    public FavoriteCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
