
public class Card implements Comparable<Card> {

    @Override
    public int compareTo(Card card2) {
        if (this.getValue().equals(card2.getValue())){
            return 0;
        } else {
            if (this.getValue().equals("K") || card2.getValue().equals("A")
                    || (this.getValue().equals("Q") && !card2.getValue().equals("K"))
                    || (this.getValue().equals("J") && (!card2.getValue().equals("K")) && !card2.getValue().equals("Q"))
                    || (this.getValue().equals("0") && (!card2.getValue().equals("K")) && !card2.getValue().equals("Q") && !card2.getValue().equals("J"))
                    || (((!card2.getValue().equals("K")) && !card2.getValue().equals("Q") && !card2.getValue().equals("J") && !card2.getValue().equals("0") && !card2.getValue().equals("A")) && !this.getValue().equals("A")
                    && Integer.parseInt(this.getValue()) > Integer.parseInt(card2.getValue()))
            ) {
                return 1;
            } else{
                return -1;
            }
        }
    }

    boolean comesImmediatelyBefore(Card card2){
        return this.getValue().equals("Q") && card2.getValue().equals("K")
                || (this.getValue().equals("J") && card2.getValue().equals("Q"))
                || (this.getValue().equals("0") && card2.getValue().equals("J"))
                || (this.getValue().equals("9") && card2.getValue().equals("0"))
                || (this.getValue().equals("8") && card2.getValue().equals("9"))
                || (this.getValue().equals("7") && card2.getValue().equals("8"))
                || (this.getValue().equals("6") && card2.getValue().equals("7"))
                || (this.getValue().equals("5") && card2.getValue().equals("6"))
                || (this.getValue().equals("4") && card2.getValue().equals("5"))
                || (this.getValue().equals("3") && card2.getValue().equals("4"))
                || (this.getValue().equals("2") && card2.getValue().equals("3"))
                || (this.getValue().equals("A") && card2.getValue().equals("2"));
    }

    public enum Suit{
        HEARTS, CLUBS, SPADES, DIAMONDS
    }

    private Suit suit;
    private String value;
    private boolean hidden;

    Card(Suit suit, String value){
        this.suit = suit;
        this.value = value;
        this.hidden = true;
    }

    Suit getSuit(){
        return this.suit;
    }

    String getValue(){
        return this.value;
    }

    boolean isFaceDown(){
        return this.hidden;
    }

    void open(){
        this.hidden = false;
    }

    String cardString(){
        if (hidden){
            return "#(#)";
        } else{
            String suitStr = "";
            switch(suit){
                case HEARTS: suitStr += "H"; break;
                case SPADES: suitStr += "S"; break;
                case CLUBS: suitStr += "C"; break;
                case DIAMONDS: suitStr += "D";
            }
            return suitStr + "(" + value + ")";
        }
    }

    Card deepCopy(){
        Card card = new Card(this.suit, this.value);
        card.hidden = this.hidden;
        return card;
    }
}
