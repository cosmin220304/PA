package com.company;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("Java Resources", "/home/cosminaf/test");
        Document doc = new Document("java1", "Java Course 1", "/home/cosminaf/test2");
        catalog.add(doc);
        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws IOException {
        Catalog catalog = CatalogUtil.load("/home/cosminaf/test");
        System.out.println(catalog);
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}