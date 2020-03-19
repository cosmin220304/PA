package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {

    public static void save(Catalog catalog) throws IOException {
        try (
                var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))
        ) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws IOException {
        try (
                var ois = new ObjectInputStream(new FileInputStream(path));
        ) {
            return (Catalog) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
