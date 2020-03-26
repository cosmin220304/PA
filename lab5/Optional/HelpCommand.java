package com.company;

import java.io.IOException;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(null,null);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("to load a catalog: <load path_to_catalog>");
        System.out.println("to list available documents id: <list> [needs a catalog to be loaded before]");
        System.out.println("to view a document: <view document_id [needs a catalog to be loaded before]");
    }
}
