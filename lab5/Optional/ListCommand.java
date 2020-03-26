package com.company;

import java.io.IOException;

public class ListCommand extends Command {
    Catalog catalog;

    public ListCommand(Catalog catalog) {
        super(null, catalog);
    }

    @Override
    public void execute() throws IOException {
        catalog = super.getCatalog();
        if (catalog == null) {
            System.out.println("please load a catalog before!");
        }
        else {
            System.out.println(catalog.getDocumentNames());
        }
    }
}
