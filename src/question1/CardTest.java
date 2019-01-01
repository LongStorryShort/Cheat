package question1;

import java.io.*;
import java.util.*;
import static question1.Card.printDeck;
import static question1.Deck.deal;
import static question1.Deck.newDeck;
import static question1.Deck.printDeck;
import static question1.Deck.shuffle;
import static question1.Deck.size;
import static question1.Hand.handValue;
import static question1.Hand.isFlush;
import static question1.Hand.isStraight;
import static question1.Hand.printHand;
import static question1.Hand.removeCard;
import static question1.Hand.removeHand;
import static question1.Hand.removePosition;
import static question1.Hand.sortAscending;
import static question1.Hand.sortDecending;

public class CardTest{
    public static void main(String[]args){
        System.out.println("Tests for the Card class: \n");
        Card.Rank a = Card.Rank.SIX;
        System.out.println("The current rank is: "+a+", the next rank is: "+Card.Rank.getNext(a));
        Card aa = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS);
        Card bb = new Card(Card.Rank.KING, Card.Suit.HEARTS);
        System.out.println("\nCard aa has a rank of: "+aa.rank.getRank()+", and Card bb has a suit of: "+ bb.suit.getSuit());
        System.out.println("The difference in rank between TEN and KING is: "+Card.Rank.difference(aa,bb));
        System.out.println("The difference in value between  TEN and KING is: "+Card.Rank.differenceValue(aa,bb));
        
        List<Card> cards = new ArrayList<>(); //Create a list to store cards for testing
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        
        int n = cards.size();
        System.out.println("\nLet's sort the card into asecending order by rank:");
        Collections.sort(cards);
        Card.printDeck(cards,n);
        
        System.out.println("\nLet's sort the card into descending order by rank:");
        Collections.sort(cards, new Card.CompareDecending());
        printDeck(cards,n);
        
        System.out.println("\nLet's sort the card into ascending order by suit:");
        Collections.sort(cards, Collections.reverseOrder(new Card.CompareSuit()));
        printDeck(cards,n);
        
        System.out.println("\nTests for the Deck class: \n");
        List<Card> Deck; 
        new Deck();
        System.out.println("The current deck is: ");
        printDeck();
        System.out.println();
        
        Deck = shuffle();
        System.out.println("Let's shuffle the deck: ");
        printDeck();
        System.out.println();
        
        Deck = deal();
        printDeck();
        System.out.println("The number of cards remaining in the deck is: "+size());
        
        Deck = newDeck();
        System.out.println("\nLet's reinitialise the deck: ");
        printDeck();
        System.out.println("The number of cards remaining in the deck is: "+size());
        
        List<Card>latest = new ArrayList<Card>(); //Create a temporary ArrayList to store data
        System.out.println("\nLet's sort the deck in odd and even position base on their suit: ");
        for (Card Deck1 : new Deck.OddEvenIterator(Deck)) {
            latest.add(Deck1);
        }
        Deck = latest;
        for(Card aaa: Deck){
            System.out.println(aaa);
        }
        //printDeck();
        
        //Save the deck in OddEvenIterator order
        String filename = "Deck.ser";
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(Deck); 
            out.close();
            System.out.println("\nThe deck has been saved");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //Load the saved deck
        try{
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            Deck = (List<Card>) in.readObject();
            in.close();
            System.out.println("\nThe deck has been loaded: ");
        }catch(Exception e){
            e.printStackTrace();
        }
        for(Card aaaa: Deck){
            System.out.println(aaaa);
        }
        
        System.out.println("\nTests for the Hand class: \n");
        Card [] t = new Card[8];
        t[0] = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS);
        t[1] = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        t[2] = new Card(Card.Rank.THREE, Card.Suit.CLUBS);
        t[3] = new Card(Card.Rank.SIX, Card.Suit.SPADES);
        t[4] = new Card(Card.Rank.TWO, Card.Suit.HEARTS);
        t[5] = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS);
        t[6] = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        t[7] = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS);
        List<Card> hand = new ArrayList();
        new Hand(t);
        for(int b = 0; b <t.length; b++){
            hand.add(t[b]);
        }
        System.out.println("The total value(s) of the cards in the hand is: "+handValue());
        Hand.histogram();
        
        Card test = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS);
        boolean removeCard = removeCard(hand,test);
        if (removeCard == true){
            System.out.println(test+" has been removed sucessfully\n");
            hand.size();
        }else
            System.out.println(test+" is not present, therefore no card has been removed from hand\n");
        
        List<Card>anotherhand = new ArrayList();
        anotherhand.add(t[3]);
        anotherhand.add(t[5]);
        anotherhand.add(t[7]);
        anotherhand.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        anotherhand.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));
        int y = hand.size();
        boolean removeHand = removeHand(hand, anotherhand);
        if (removeHand == true){
            System.out.println("All cards passed were sucessfully removed\n");
            hand.size();
        }else if(hand.size() != y){
            System.out.println("Not all cards passed were successfully removed\n");
            hand.size();
        }else
            System.out.println("No cards passed were successfully removed\n");
        
        int position = 3;
        Card newTest = removePosition(position);
        if(newTest == null)
            System.out.println(position+" is not a valid position, no card has been removed");
        else{
            System.out.println(newTest+" at position: "+position+", has been removed sucessfully");
            hand.size();
        }
        
        System.out.println("Let's sort the hand into ascending order: ");
        hand = sortAscending();
        printHand();
        System.out.println("\nLet's sort the hand into decending order: ");
        hand = sortDecending();
        printHand();
        
        Card.Suit test1 = Card.Suit.DIAMONDS;
        Card.Rank test2 = Card.Rank.ACE;
        System.out.println("\nThe number of suit "+test1+" cards in hand is: "+ Hand.countSuit(test1));
        System.out.println("The number of rank "+test2+" cards in hand is: "+ Hand.countRank(test2));
        System.out.println("The total value(s) of the cards in the hand is: "+handValue());
        
        if(isFlush())
            System.out.println("All the cards are the same unit");
        else
            System.out.println("Not all the cards are the same unit");
        
        if(isStraight())
            System.out.println("All the cards are in consecutive order");
        else
            System.out.println("Not all the cards are in consecutive order");
    }
}