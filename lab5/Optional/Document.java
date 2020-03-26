package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;

    public Document( String id, String name, String location){
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Document() {   }

    public String getId() { return id; }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return  "[\n"+
                id + '\n' +
                name + '\n' +
                location + '\n' +
                "]";
    }
}
