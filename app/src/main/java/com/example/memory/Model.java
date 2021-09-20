package com.example.memory;

public class Model {

    String categorytitle;
    String id;

    public Model(String id, String categorytitle) {
        this.categorytitle = categorytitle;
        this.id = id;
    }

    public String getCategorytitle() {
        return categorytitle;
    }

    public void setCategorytitle(String categorytitle) {
        this.categorytitle = categorytitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
