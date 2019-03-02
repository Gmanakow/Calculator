public class Parser {
    private int result;
    private float currentNumber = 0;
    private Lexer lexer;
    private Lexeme current;

    public Parser(String string) throws LexerException{
        this.lexer = new Lexer(string);
        current = new Lexeme("", Lexeme.LexemeType.EMPTY);
        current = lexer.getLexeme();
    }

    public boolean checkOC(){return lexer.checkOC();}

    public double parseExpression() throws LexerException, ParserException{
        double tmp = parseTerm();
        while (current.type == Lexeme.LexemeType.PLUS ||
               current.type == Lexeme.LexemeType.MINUS) {
           if (current.type == Lexeme.LexemeType.PLUS){
               current = lexer.getLexeme();
               tmp += parseTerm();
           } else {
               current = lexer.getLexeme();
               tmp -= parseTerm();
           }
       }
       return tmp;

    }

    public double parseTerm() throws LexerException, ParserException{
        double tmp = parseFactor();
        while (current.type == Lexeme.LexemeType.MULTIPLY||
                current.type == Lexeme.LexemeType.DIVIDE) {
            if (current.type == Lexeme.LexemeType.MULTIPLY){
                current = lexer.getLexeme();
                tmp *= parseFactor();
            } else {
                current = lexer.getLexeme();
                tmp /= parseFactor();
            }
        }
        return tmp;
    }

    public double parseFactor() throws LexerException, ParserException{
        if (current.type == Lexeme.LexemeType.MINUS){
            current = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    public double parseAtom() throws LexerException, ParserException{
        double res;
        if (current.type == Lexeme.LexemeType.NUMBER){
            res = Double.parseDouble(current.text);
            current = lexer.getLexeme();
            return res;
        }
        if (current.type == Lexeme.LexemeType.OPEN){
            current = lexer.getLexeme();
            res = parseExpression();
            if (current.type == Lexeme.LexemeType.CLOSE) {
                current = lexer.getLexeme();
                return res;
            }
        }
        throw new ParserException("wrong input" + current.text);
    }

}
