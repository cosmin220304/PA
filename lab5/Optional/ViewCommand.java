package com.company;

import java.io.IOException;

public class ViewCommand extends Command{

    public ViewCommand(String parameter, Catalog catalog){
        super(parameter,catalog);
    }

    @Override
    public void execute() throws IOException {
        catalog = super.getCatalog();
        if (catalog == null){
            System.out.println("Please load a catalog before!");
            return;
        }

        try {
            Document doc = catalog.findById(parameter);
            CatalogUtil.view(doc);
        }
        catch (Exception err){
            System.out.println("Invalid document name");
        }
    }
}
