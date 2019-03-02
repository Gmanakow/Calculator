public class Lexer {
    private int counter = 0;
    private String string;
    private int openCounter;
    private int closeCounter;

    public Lexer(String string){
        this.string = string;
        openCounter = 0;
        closeCounter = 0;
    }

    public Lexeme getLexeme() throws LexerException{
        boolean condition = true;
        Lexeme.LexemeType type = Lexeme.LexemeType.EMPTY;
        String curr = "";
        char c;
        while (condition) {
            condition = false;
            if (counter>=string.length()) break;
            c = string.charAt(counter);
            if ((c >='0' && c <= '9')||(c == '.')){
                curr += c;
                condition = true;
                type = Lexeme.LexemeType.NUMBER;
                counter++;
                continue;
            }
            if (curr != "") break;
            counter++;
            switch (c){
                case ('+'): curr += "+"; type = Lexeme.LexemeType.PLUS; break;
                case ('-'): curr += "-"; type = Lexeme.LexemeType.MINUS; break;
                case ('*'): curr += "*"; type = Lexeme.LexemeType.MULTIPLY; break;
                case ('/'): curr += "/"; type = Lexeme.LexemeType.DIVIDE; break;
                case ('('): curr += "("; type = Lexeme.LexemeType.OPEN; openCounter++; break;
                case (')'): curr += ")"; type = Lexeme.LexemeType.CLOSE; closeCounter++; break;
                default: throw new LexerException("wrong input");
            }
        }
        return new Lexeme(curr,type);
    }

    public boolean checkOC(){
        if (openCounter == closeCounter) {
            return true;
        }
        return false;
    }
}
