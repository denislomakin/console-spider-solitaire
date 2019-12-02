import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Deck {
    private List<List<Card>> suits;

    Deck(int mode){
        if(mode == 0){
            suits = new ArrayList<>(1);
            Card.Suit suit = Card.Suit.SPADES;
            suits.add(new ArrayList<>(104));
            for (int j = 2; j < 15; j++) {
                if (j <= 10) {
                    for (int k = 0; k < 8; k++){
                        if (j == 10){
                            suits.get(0).add(new Card(suit, "0"));
                        } else{
                            suits.get(0).add(new Card(suit, String.valueOf(j)));
                        }
                    }
                } else if (j == 11) {
                    for (int k = 0; k < 8; k++){
                        suits.get(0).add(new Card(suit, "J"));
                    }
                } else if (j == 12) {
                    for (int k = 0; k < 8; k++){
                        suits.get(0).add(new Card(suit, "Q"));
                    }
                } else if (j == 13) {
                    for (int k = 0; k < 8; k++){
                        suits.get(0).add(new Card(suit, "K"));
                    }
                } else {
                    for (int k = 0; k < 8; k++){
                        suits.get(0).add(new Card(suit, "A"));
                    }
                }
            }
        } else if(mode == 1){
            suits = new ArrayList<>(4);
            for (int i = 0; i < 2; i++) {
                Card.Suit suit = null;
                switch (i) {
                    case 0:
                        suit = Card.Suit.HEARTS;
                        break;
                    case 1:
                        suit = Card.Suit.SPADES;
                }
                suits.add(new ArrayList<>(52));
                for (int j = 2; j < 15; j++) {
                    if (j <= 10) {
                        for (int k = 0; k < 4; k++){
                            if (j == 10){
                                suits.get(0).add(new Card(suit, "0"));
                            } else{
                                suits.get(0).add(new Card(suit, String.valueOf(j)));
                            }
                        }
                    } else if (j == 11) {
                        for (int k = 0; k < 4; k++){
                            suits.get(i).add(new Card(suit, "J"));
                        }
                    } else if (j == 12) {
                        for (int k = 0; k < 4; k++){
                            suits.get(i).add(new Card(suit, "Q"));
                        }
                    } else if (j == 13) {
                        for (int k = 0; k < 4; k++){
                            suits.get(i).add(new Card(suit, "K"));
                        }
                    } else {
                        for (int k = 0; k < 4; k++){
                            suits.get(i).add(new Card(suit, "A"));
                        }
                    }
                }
            }
        } else {
            suits = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
                Card.Suit suit = null;
                switch (i) {
                    case 0:
                        suit = Card.Suit.HEARTS;
                        break;
                    case 1:
                        suit = Card.Suit.SPADES;
                        break;
                    case 2:
                        suit = Card.Suit.DIAMONDS;
                        break;
                    case 3:
                        suit = Card.Suit.CLUBS;
                }
                suits.add(new ArrayList<>(26));
                for (int j = 2; j < 15; j++) {
                    if (j <= 10) {
                        if (j == 10){
                            suits.get(0).add(new Card(suit, "0"));
                            suits.get(0).add(new Card(suit, "0"));
                        } else{
                            suits.get(0).add(new Card(suit, String.valueOf(j)));
                            suits.get(0).add(new Card(suit, String.valueOf(j)));
                        }
                    } else if (j == 11) {
                        suits.get(i).add(new Card(suit, "J"));
                        suits.get(i).add(new Card(suit, "J"));
                    } else if (j == 12) {
                        suits.get(i).add(new Card(suit, "Q"));
                        suits.get(i).add(new Card(suit, "Q"));
                    } else if (j == 13) {
                        suits.get(i).add(new Card(suit, "K"));
                        suits.get(i).add(new Card(suit, "K"));
                    } else {
                        suits.get(i).add(new Card(suit, "A"));
                        suits.get(i).add(new Card(suit, "A"));
                    }
                }
            }
        }
    }

    Card drawCard(){
        if(suits.isEmpty()){
            System.out.println("Deck empty");
            return null;
        }
        Random rand = new Random();
        int suit = rand.nextInt(suits.size());
        Card card = suits.get(suit).remove(rand.nextInt(suits.get(suit).size()));
        if (suits.get(suit).isEmpty()){
            suits.remove(suit);
        }
        return card;
    }
}
