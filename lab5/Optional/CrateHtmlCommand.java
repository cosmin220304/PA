package com.company;

import java.io.IOException;

public class CrateHtmlCommand extends Command {
    public CrateHtmlCommand(String parameter, Catalog catalog) {
        super(parameter, catalog);
    }

    @Override
    public void execute() throws IOException {
        catalog = super.getCatalog();
        
    }
}
