public class Lexeme{
    enum LexemeType {PLUS, MINUS, MULTIPLY, DIVIDE, OPEN, CLOSE, NUMBER, EMPTY}
    LexemeType type;
    String text;

    public Lexeme(String string, LexemeType type){
        this.text = string;
        this.type = type;
    }
}