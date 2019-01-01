package question1;

import java.io.*;
import java.util.*;

public class Card implements Serializable, Comparable<Card>{
    
    private static final long serialVersionUID = 100; //Serialisaion ID 100

    public static enum Rank{
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
    NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
        private final int value;
        private static Rank currentRank;
        private static final Rank[] rank = values();
        
        Rank(int x){
            value = x;
        }
        
        //Return next rank
        public static Rank getNext(Rank a){
            if(a==ACE)
                return TWO;
            else{
                currentRank = rank[(a.ordinal()+1)%rank.length];
                return currentRank;
            }
        }
        
        public String getRank(){
            return this.name();
        }
        
        public int getValue(){
            return value;
        }
        
        //Return the difference of rank between 2 cards
        public static int difference(Card q, Card w){
            if(q.rank.ordinal()>w.rank.ordinal())
                return q.rank.ordinal()-w.rank.ordinal();
            else
                return w.rank.ordinal()-q.rank.ordinal();
        }
        
        //Return the difference of rank value between 2 cards
        public static int differenceValue(Card q, Card w){
            if(q.rank.getValue() >= w.rank.getValue())
                return q.rank.getValue()-w.rank.getValue();
            else
                return w.rank.getValue()-q.rank.getValue();
        }
    }
    
    public static enum Suit{CLUBS, DIAMONDS, HEARTS, SPADES;
    
        public String getSuit(){
            return this.name();
        }
    }
    
    public final Rank rank;
    public final Suit suit;
    
    Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }
    
    @Override
    public String toString(){
        return rank+" OF "+suit;
    }
  
    //Sort cards into ascending order by rank
    @Override
    public int compareTo(Card t) {
        if(t.rank.ordinal() == this.rank.ordinal())
            if (this.suit.ordinal() < t.suit.ordinal())
                return -1;
        return this.rank.ordinal() - t.rank.ordinal();
    }
    
    //Sort cards into descending order by rank
    public static class CompareDecending implements Comparator<Card>{
        @Override
        public int compare(Card t, Card t1) {
            if(t.rank.ordinal() == t1.rank.ordinal())
                if (t1.suit.ordinal() > t.suit.ordinal())
                    return -1;
            return t1.rank.ordinal() - t.rank.ordinal();
        }
    }
    
    //Sort cards into ascending order by suit
    public static class CompareSuit implements Comparator<Card>{
        @Override
        public int compare(Card t, Card t1) {
            if(t.suit.ordinal() == t1.suit.ordinal())
                if (t1.rank.ordinal() < t.rank.ordinal())
                    return -1;
            return t1.suit.ordinal() - t.suit.ordinal();
        }
    }
    
    //Print out the cards
    public static void printDeck(List<Card>Deck, int n){
        for(int i = 0; i<n;i++){
            System.out.println(Deck.get(i));
        }
    }
}