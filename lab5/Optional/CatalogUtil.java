package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CatalogUtil {

    public static void save(Catalog catalog) throws IOException {
        Path path = Paths.get(catalog.getPath());
        Files.write(path, catalog.allData());
    }

    public static Catalog load(String path) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(path));
        Iterator line = fileLines.iterator();

        Catalog catalog = new Catalog();
        catalog.setName((String) line.next());
        line.hasNext();
        catalog.setPath((String) line.next());
        line.hasNext();

        Document doc = null;
        int index = 0;
        while (line.hasNext()) {

            String value = (String) line.next();

            if (value.equals("["))
            {
                doc = new Document();
                index = 0;
            }
            else if (value.equals("]"))
                catalog.addDocument(doc);
            else {
                switch (index){
                    case 0: doc.setId(value);
                            break;
                    case 1: doc.setName(value);
                            break;
                    case 2: doc.setLocation(value);
                            break;
                }
                index++;
            }
        }

        return  catalog;
    }

    public static void view(Document doc) {
        Desktop desktop = Desktop.getDesktop();
        try {
            if (isWebSite(doc.getLocation()))
                desktop.browse(URI.create(doc.getLocation()));
            else
                desktop.open(new File(doc.getLocation()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isWebSite(String location){
       return location.substring(0,8).equals("https://");
    }
}
