import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SpiderSolitaire {

    private Deck deck;
    private List<List<Card>> columns;
    private int deals;
    private boolean valid;
    private int lastNum = 49;
    private LinkedList<List<List<Card>>> undoStack;

    private SpiderSolitaire(Scanner scan){
        deals = 5;
        valid = false;
        columns = new ArrayList<>(10);
        undoStack = new LinkedList<>();

        int mode = 0;
        do{
            if(scan.hasNextInt()) {
                mode = scan.nextInt();
                valid = true;
            } else {
                scan.nextLine();
                System.out.println("Please enter an integer.");
            }
        } while(!valid);
        valid = false;
        deck = new Deck(mode);
        for (int i = 0; i < 10; i++){
            columns.add(new ArrayList<>());
            if (i < 4){
                for (int j = 0; j < 6; j++){
                    Card card = deck.drawCard();
                    columns.get(i).add(card);
                    if (j == 5){
                        card.open();
                    }
                }
            } else {
                for (int j = 0; j < 5; j++){
                    Card card = deck.drawCard();
                    columns.get(i).add(card);
                    if (j == 4){
                        card.open();
                    }
                }
            }
        }
        printBoard_noneSelected();
    }

    private void printBoard() {
        boolean printNum = false;
        for (int j = 0; j < 50; j++){
            for (int i = 0; i < 10; i++){
                if (columns.get(i).size() > j){
                    System.out.print(columns.get(i).get(j).cardString() + "  ");
                    printNum = true;
                } else {
                    System.out.print("      ");
                }
            }
            if (printNum){
                System.out.print(" " + j);
                printNum = false;
            } else{
                lastNum = j;
                break;
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printBoard_noneSelected(){
        System.out.println("\nDeals remaining: " + deals);
        System.out.print("\n ");
        for (int i = 0; i < 10; i++){
            System.out.print(i + "     ");
        }
        System.out.println();
        printBoard();
    }

    private void printBoard_colSelected(int column){
        System.out.println("\nDeals remaining: " + deals);
        System.out.print("\n ");
        for (int i = 0; i < 10; i++){
            if (column == i){
                System.out.print(i + "*    ");
            } else{
                System.out.print(i + "     ");
            }
        }
        System.out.println();
        printBoard();
    }

    private void printBoard_cardSelected(int column, int row){
        boolean printNum = false;
        System.out.println("\nDeals remaining: " + deals);
        System.out.print("\n ");
        for (int i = 0; i < 10; i++){
            if (column == i){
                System.out.print(i + "*    ");
            } else{
                System.out.print(i + "     ");
            }
        }
        System.out.println();
        for (int j = 0; j < 50; j++){
            for (int i = 0; i < 10; i++){
                if (columns.get(i).size() > j){
                    if (i == column && j == row){
                        System.out.print(columns.get(i).get(j).cardString() + "* ");
                    } else if (i == column-1 && j == row){
                        System.out.print(columns.get(i).get(j).cardString() + " *");
                    } else {
                        System.out.print(columns.get(i).get(j).cardString() + "  ");
                    }

                    printNum = true;
                } else {
                    System.out.print("      ");
                }
            }
            if (printNum){
                if (j == row){
                    System.out.println(" " + j + "*");
                } else {
                    System.out.println(" " + j);
                }
                printNum = false;
            } else{
                lastNum = j;
                break;
            }
        }
        System.out.println();
    }

    private void deal(){
        for(int i = 0; i < 10; i++){
            Card card = deck.drawCard();
            card.open();
            columns.get(i).add(card);
        }
        deals--;
        undoStack.clear();
    }

    private List<Card> setComplete(List<Card> list){
        int state = 0;
        Card.Suit firstSuit = null;
        int firstIndex = 0;
        for (Card card : list) {
            if (state == 0 && card.getValue().equals("K") && !card.isFaceDown()) {
                firstSuit = card.getSuit();
                firstIndex = list.indexOf(card);
                state++;
            } else if (state == 1 && card.getValue().equals("Q") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 2 && card.getValue().equals("J") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 3 && card.getValue().equals("0") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 4 && card.getValue().equals("9") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 5 && card.getValue().equals("8") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 6 && card.getValue().equals("7") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 7 && card.getValue().equals("6") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 8 && card.getValue().equals("5") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 9 && card.getValue().equals("4") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 10 && card.getValue().equals("3") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 11 && card.getValue().equals("2") && card.getSuit() == firstSuit) {
                state++;
            } else if (state == 12 && card.getValue().equals("A") && card.getSuit() == firstSuit) {
                state++;
            } else{
                state = 0;
            }
        }
        if (state == 13){
            return new ArrayList<>(list.subList(firstIndex, list.size()));
        } else {
            return null;
        }
    }

    private int getCommand(Scanner scan){
        String temp;
        int command = 5;
        do{
            temp = scan.nextLine();
            switch (temp) {
                case "move":
                    command = 0;
                    valid = true;
                    break;
                case "deal":
                    command = 1;
                    valid = true;
                    break;
                case "quit":
                    command = 2;
                    valid = true;
                    break;
                case "undo":
                    command = 3;
                    valid = true;
                    break;
                case "restart":
                    command = 4;
                    valid = true;
                    break;
                default:
                    System.out.print("\nPlease enter a valid command: ");
            }
        } while(!valid);
        valid = false;
        return command;
    }

    private int getColumn(Scanner scan) {
        int temp;
        int column = 10;
        do{
            if(scan.hasNextInt()) {
                temp = scan.nextInt();
                if (temp >= 10){
                    System.out.print("Please enter a column number smaller than 10: ");
                    continue;
                } else if (temp < -1){
                    System.out.print("Please enter a column number 0 or greater, or -1 to cancel: ");
                } else {
                    column = temp;
                }
                valid = true;
            } else {
                scan.nextLine();
                System.out.println("Please enter an integer in range.");
            }
        } while(!valid);
        valid = false;
        return column;
    }

    private int getRow(Scanner scan, int column) {
        int temp;
        int row = lastNum;
        do{
            if(scan.hasNextInt()) {
                temp = scan.nextInt();
                if (temp >= columns.get(column).size()){
                    System.out.print("Please enter a row number smaller than " + columns.get(column).size() + ": ");
                    continue;
                } else if (temp < -1){
                    System.out.print("Please enter a column number 0 or greater, or -1 to cancel: ");
                } else {
                    row = temp;
                }
                valid = true;
            } else {
                scan.nextLine();
                System.out.println("Please enter an integer in range.");
            }
        } while(!valid);
        valid = false;
        return row;
    }

    private boolean chainCompareDown(List<Card> list, int startInd){
        for (int i = startInd; i < list.size()-1; i++){
            if (!list.get(i+1).comesImmediatelyBefore(list.get(i)) || list.get(i).getSuit() != list.get(i+1).getSuit()){
                return false;
            }
        }
        return true;
    }

    private void updateUndoStack(){
        List<List<Card>> columnsCopy = new ArrayList<>(columns.size());
        for (int i = 0; i < columns.size(); i++){
            columnsCopy.add(new ArrayList<>(columns.get(i).size()));
            for (int j = 0; j < columns.get(i).size(); j++){
                columnsCopy.get(i).add(columns.get(i).get(j).deepCopy());
            }
        }
        if (undoStack.size() >= 20){
            undoStack.removeLast();
            undoStack.addFirst(columnsCopy);
        } else{
            undoStack.addFirst(columnsCopy);
        }
    }

    private void undo(){
        columns = undoStack.pollFirst();
    }

    public static void main(String[] args) {
        boolean gameOver = false;
        while (!gameOver) {
            System.out.print("Spider Solitaire by Denis Lomakin\nEnter 0 for 1-suit, 1 for 2-suit, or 2 (or more) for 4-suit: ");
            Scanner scan = new Scanner(System.in);
            SpiderSolitaire game = new SpiderSolitaire(scan);

            boolean move = false;
            int row = game.lastNum;
            int columnInd;
            int columnDestInd;
            while (true) {
                game.valid = false;
                boolean confirm = false;

                scan.nextLine();
                while (!move) {
                    System.out.print("Choose whether to (move) cards, (undo), (deal), (restart), or (quit): ");
                    int command = game.getCommand(scan);
                    if (command == 0) {
                        move = true;
                    } else if (command == 1) {
                        if (game.deals == 0) {
                            System.out.println("No deals remaining.");
                            continue;
                        }
                        game.deal();
                        game.printBoard_noneSelected();
                    } else if (command == 2) {
                        System.out.println("Thank you for playing!");
                        return;
                    } else if (command == 3) {
                        if (game.undoStack.size() > 0) {
                            game.undo();
                            game.printBoard_noneSelected();
                        } else {
                            System.out.println("No undos available");
                        }
                    } else if (command == 4){
                        break;
                    }
                }
                if (!move){
                    break;
                }
                game.updateUndoStack();

                System.out.print("Select a column: ");
                columnInd = game.getColumn(scan);
                if (columnInd == -1) {
                    move = false;
                    game.undoStack.pollFirst();
                    continue;
                }
                List<Card> columnInit = game.columns.get(columnInd);
                game.printBoard_colSelected(columnInd);

                while (!confirm) {
                    System.out.print("Select a row: ");
                    row = game.getRow(scan, columnInd);
                    if (row == -1) {
                        break;
                    }
                    if (columnInit.get(row).isFaceDown()) {
                        System.out.println("Please select a face up card.\n");
                    } else if (!game.chainCompareDown(columnInit, row)) {
                        System.out.println("Please select a movable card (one with only lower values and same suits underneath).\n");
                    } else {
                        game.printBoard_cardSelected(columnInd, row);
                        confirm = true;
                    }
                }
                if (!confirm) {
                    move = false;
                    game.undoStack.pollFirst();
                    continue;
                }
                confirm = false;

                while (!confirm) {
                    System.out.print("Select a destination column: ");
                    columnDestInd = game.getColumn(scan);
                    if (columnDestInd == -1) {
                        break;
                    }
                    List<Card> columnDest = game.columns.get(columnDestInd);
                    if ((columnDestInd != columnInd) && (columnDest.isEmpty() || columnInit.get(row).comesImmediatelyBefore(columnDest.get(columnDest.size() - 1)))) {
                        List<Card> sub = new ArrayList<>(columnInit.subList(row, columnInit.size()));
                        columnInit.removeAll(sub);
                        if (!columnInit.isEmpty()) {
                            columnInit.get(row - 1).open();
                        }
                        columnDest.addAll(sub);
                        confirm = true;

                        if (game.setComplete(columnDest) != null) {
                            columnDest.removeAll(game.setComplete(columnDest));
                            if (!columnDest.isEmpty()){
                                columnDest.get(columnDest.size()-1).open();
                            }
                        }
                    } else {
                        System.out.println("Please select a valid destination.\n");
                    }
                }
                if (!confirm) {
                    move = false;
                    game.undoStack.pollFirst();
                    continue;
                }

                gameOver = true;
                for (int i = 0; i < 10; i++) {
                    if (!game.columns.get(i).isEmpty()) {
                        gameOver = false;
                        break;
                    }
                }

                if (gameOver) {
                    System.out.println("Congratulations! You win!");
                    break;
                } else {
                    game.printBoard_noneSelected();
                }
            }
        }
    }
}
