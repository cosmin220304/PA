package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog (String name, String path){
        this.name = name;
        this.path = path;
    }

    public Catalog() {

    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public Document findById(String id) {
        return documents.stream().filter(d -> d.getId().equals(id)).findAny().orElse(null);
    }

    public String getPath() {
        return this.path;
    }

    public String getDocumentNames()
    {
        String ret = "";
        for (Document d : documents)
            ret += d.getId() + "\n";
        return ret;
    }

    public String allData(){
        String ret = name + "\n" + path + "\n";
        for (Document d : documents)
            ret += d + "\n";
        return ret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document) {
        this.documents.add(document);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }
}