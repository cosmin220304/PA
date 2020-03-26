package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.SaveExample();
        Command cmd = null;

        while (true) {

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String command = input.readLine();
            String parameter = null;
            try {
                parameter = command.substring(5);
            } catch (Exception err) {
            }

            if (command.equals("help")) {
                cmd = new HelpCommand();
                cmd.execute();
                continue;
            }

            if (command.equals("list")) {
                Catalog catalog = cmd.getCatalog();
                cmd = new ListCommand(catalog);
                cmd.execute();
                continue;
            }

            if (command.contains("load")) {
                cmd = new LoadCommand(parameter);
                cmd.execute();
                continue;
            }

            if (command.contains("view")) {
                Catalog catalog = cmd.getCatalog();
                cmd = new ViewCommand(parameter, catalog);
                cmd.execute();
                continue;
            }
        }
    }

    private void SaveExample() throws IOException {

        Catalog catalog = new Catalog("Java Resources", "/home/cosminaf/test");

        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/labs/lab_05.html");
        catalog.add(doc);

        Document doc2 = new Document("java2", "Java Course 2", "https://profs.info.uaic.ro/~acf/java/labs/lab_06.html");
        catalog.add(doc2);

        CatalogUtil.save(catalog);
    }

}