package question1;

import java.io.*;
import java.util.*;

public class Deck implements Serializable, Iterable<Card>{
    
    private static final long serialVersionUID = 101; //Serialisation ID 101
    private static List<Card> Deck;
    
    //Initialise all the cards in the deck
    public Deck(){
        Deck = new ArrayList();
        Card.Rank[]ranks = Card.Rank.values();
        Card.Suit[]suits = Card.Suit.values();
        
        for(Card.Suit Suit: suits){
            for(Card.Rank Rank: ranks){
                Deck.add(new Card(Rank,Suit));
            }
        }
    }
    
    //Randomise the cards in the deck
    public static List<Card> shuffle(){
        Random ran = new Random();
        int n = Deck.size();
        for(int i = 0; i<n; i++){
            int position = i+ran.nextInt(n-i);
            Card temp = Deck.get(i);
            Deck.set(i, Deck.get(position));
            Deck.set(position, temp);
        }
        return Deck;
    }
    
    //Remove the top card of the deck
    public static List<Card> deal(){
        int i = 0;
        Card q = Deck.get(i);
        System.out.println("The top card from the deck ("+q+") has been removed and now the deck is: ");
        
        Card[] deck = new Card[Deck.size()-1];
        //Use a for-loop to store cards in an array which is without the removed card
        for(int b = 0; b<deck.length;b++){
            deck[b] = Deck.get(b+1);
        }
        
        Deck = Arrays.asList(deck); //Change the deck back to an ArrayList for return
        return Deck;
    }
    
    //Return number of cards remaining in the deck
    public static int size(){
        return Deck.size();
    }
    
    //Reinitalise the deck
    public static List<Card> newDeck(){
        Deck = new ArrayList();
        Card.Rank[]ranks = Card.Rank.values();
        Card.Suit[]suits = Card.Suit.values();
        
        for(Card.Suit suit: suits){
            for(Card.Rank rank: ranks){
                Deck.add(new Card(rank,suit));
            }
        }
        return Deck;
    }
    
    //Iterator for the deck
    @Override
    public Iterator<Card> iterator() {
        return Deck.iterator();
    }
    
    //Traverse the cards by first going through all the cards in odd positions, then the ones in even position
    public static class OddEvenIterator implements Iterable<Card>{
        List<Card>odd = new ArrayList(); //List to store odd position cards
        List<Card>even = new ArrayList(); //List to store even position cards
        List<Card>sorted = new ArrayList(); // List to store cards in the odd list first, then store cards in the even list
        int i = 0;
    
        public OddEvenIterator(List<Card>card){
            Deck = card;
        }
        
        @Override
        public Iterator<Card> iterator() {
            Iterator<Card> a = Deck.iterator();
            while(a.hasNext()){
                if(i%2 ==0)
                    odd.add(a.next()); //Card is odd, so store it in the odd list
                else
                    even.add(a.next()); //Card is even, it has to be stored in the even list
                i++;
            }
            sorted.addAll(odd);
            sorted.addAll(even);
            a = sorted.iterator(); //Make a as the sorted iterator for return
            return a;
        }
    }
    
    //Print out the deck
    public static void printDeck(){
        for(Card a: Deck){
            System.out.println(a);
        }
    }
}