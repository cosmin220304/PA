package com.company;

import java.io.IOException;

public abstract class Command {
    static Catalog catalog;
    String parameter;

    public Command(String parameter, Catalog catalog){
        this.parameter = parameter;
        this.catalog = catalog;
    }

    public abstract void execute() throws IOException;

    public Catalog getCatalog(){
        return catalog;
    }
}
