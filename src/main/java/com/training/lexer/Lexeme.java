package com.training.lexer;

public class Lexeme {

    private final String name;
    private final Character lexeme;

    public Lexeme(String name, Character lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    public String getName() {
        return name;
    }

    public Character getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "name='" + name + '\'' +
                ", lexeme='" + lexeme + '\'' +
                '}';
    }
}
