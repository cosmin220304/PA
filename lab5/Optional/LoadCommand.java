package com.company;

import java.io.IOException;
import java.nio.file.Paths;

public class LoadCommand extends Command {

    public LoadCommand(String parameter) {
        super(parameter, null);
    }

    @Override
    public void execute() throws IOException {
        try {
            Paths.get(parameter);
            super.catalog = CatalogUtil.load(parameter);
        }
        catch (Exception err){
            System.out.println("Invalid path");
        }
    }
}
