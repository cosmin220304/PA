package com.company;

public class Token{
    Integer number;

    public Token(Integer number){
        this.number = number;
    }

    public Token(){
        number = null;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        if (number != null)
        return "token(" + number + ")";

        return "token(empty)";
    }
}
