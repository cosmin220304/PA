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
        Files.write(path, Collections.singleton(catalog.allData())); //intellij wanted it to be casted to singleton to make it immutable
    }

    public static Catalog load(String path) throws IOException {

        //We read all line then iterate through them
        List<String> fileLines = Files.readAllLines(Paths.get(path));
        Iterator line = fileLines.iterator();

        //Get name and path of catalog
        Catalog catalog = new Catalog();
        catalog.setName((String) line.next());
        line.hasNext();
        catalog.setPath((String) line.next());
        line.hasNext();

        /*Every 5 lines we have the following lines data describing a document:
          line1: [
          line2: id
          line3: name
          line4: location
          line5: ]
        */

        Document doc = null;
        int index = 0;

        while (line.hasNext()) {

            String value = (String) line.next();

            //When we have "[", a new document is being read
            if (value.equals("[")) {
                doc = new Document();
                index = 0;
            }

            //When we have "]" the reading of the document is finished
            // and we can add it to the catalog
            else if (value.equals("]")) {
                catalog.addDocument(doc);
            }

            //We set values accordingly (as described above)
            else {
                switch (index) {
                    case 0:
                        doc.setId(value);
                        break;
                    case 1:
                        doc.setName(value);
                        break;
                    case 2:
                        doc.setLocation(value);
                }
                index++;
            }
        }

        return catalog;
    }

    public static void view(Document doc) {
        if (doc == null){
            System.out.println("Document not found");
            return;
        }

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
