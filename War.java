
/**
 * War game class
 *
 * @author Mr. Jaffe
 * @version 2022-10-19
 */
public class War {
    Deck deck = new Deck();
    Deck player1;
    Deck player2;
    Deck pile = new Deck();

    /**
     * Constructor for the game
     * Include your initialization here -- card decks, shuffling, etc
     * Run the event loop after you've done the initializations
     */
    public War() {
        // Initializations here...
        deck.initializeNewDeck();
        deck.shuffle();
        Deck[] halves = deck.dealDeck();
        this.player1 = halves[0];
        this.player2 = halves[1];
        // ...then run the event loop
        this.runEventLoop();
    }

    /**
     * This is the game's event loop. The code in here should come
     * from the War flowchart you created for this game
     */
    public void runEventLoop() {
        int iterations = 0;
        while (this.player1.getDeckSize() > 0 && this.player2.getDeckSize() > 0 && iterations < 300) {
            iterations++;

            Card player1Card = this.player1.dealCardFromDeck();
            Card player2Card = this.player2.dealCardFromDeck();
            this.pile.addCardToDeck(player1Card);
            this.pile.addCardToDeck(player2Card);
            System.out.println("P1: " + player1Card.toString());
            System.out.println("P2: " + player2Card.toString());

            if (player1Card.getRank() > player2Card.getRank()) {
                System.out.println("Winner: P1");
                this.givePileToPlayer(player1);
            } else if (player1Card.getRank() < player2Card.getRank()) {
                System.out.println("Winner: P2");
                this.givePileToPlayer(player2);
            } else if (player1Card.getRank() == player2Card.getRank()) {
                if (this.player1.getDeckSize() > 3 && this.player2.getDeckSize() > 3) {
                    System.out.println("WAR! Both players put 3 cards face down");
                    this.pile.addCardToDeck(this.player1.dealCardFromDeck());
                    this.pile.addCardToDeck(this.player1.dealCardFromDeck());
                    this.pile.addCardToDeck(this.player1.dealCardFromDeck());
                    this.pile.addCardToDeck(this.player2.dealCardFromDeck());
                    this.pile.addCardToDeck(this.player2.dealCardFromDeck());
                    this.pile.addCardToDeck(this.player2.dealCardFromDeck());
                }
            }

        }

        System.out.println("------------------");
        System.out.println("Rounds: " + iterations);
        System.out.println("P1: " + this.player1.getDeckSize() + " cards");
        System.out.println("P2: " + this.player2.getDeckSize() + " cards");
        if (this.player1.getDeckSize() > this.player2.getDeckSize()) {
            System.out.println("Player one won");
        } else if (this.player2.getDeckSize() > this.player1.getDeckSize()) {
            System.out.println("Player two won");
        } else if (this.player1.getDeckSize() == this.player2.getDeckSize()) {
            System.out.println("They tied");
        }
    }

    private void givePileToPlayer(Deck player) {
        while (this.pile.getDeckSize() > 0) {
            Card pileCard = pile.dealCardFromDeck();
            player.addCardToDeck(pileCard);
        }
    }

    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }

}
