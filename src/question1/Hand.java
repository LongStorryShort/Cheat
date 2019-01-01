package question1;

import java.io.*;
import java.util.*;

public class Hand implements Iterable<Card>{
    
    private static final long serialVersionUID = 102; //Serialisation ID 102
    private static List<Card>hand;
    
    //Creates an empty hand
    public Hand(){
        hand = new ArrayList();
        hand.size();
    }
    
    //Takes an array of cards and add them into the hand
    public Hand(Card[]a){
        new Hand();
        for(int b = 0; b <a.length; b++){
            hand.add(a[b]);
        }
        hand.size();
    }
    
    //Takes a different hand and copies all the cards to this hand
    public Hand(Hand hand){
        new Hand();
        Iterator<Card>it = hand.iterator();
        while(it.hasNext()){
            hand.addCard(it.next());
        }
        hand.size();
    }
    
    //Add a card to the hand
    public static void addCard(Card a){
        hand.add(a);
        hand.size();
    }
    
    //Add a collection of cards to the hand
    public static void addCollection(List<Card>a){
        hand.addAll(a);
        hand.size();
    }
    
    //Add another hand to this hand
    public static void addHand(Hand a){
        new Hand(a);
        Iterator<Card>it = a.iterator();
        while(it.hasNext()){
            hand.add(it.next());
        }
        hand.size();
    }
    
    //Removes a card in the hand if that card is present
    public static boolean removeCard(List<Card>hand1, Card a){
        Iterator<Card> q = hand1.iterator();
        while(q.hasNext()){
            if(q.next().equals(a)){
                q.remove();
                return true; //return true if it has been removed successfully
            }
        }
        return false; //Card does not exist in the hand
    }
    
    //Removes all cards from other hand if those cards are present
    public static boolean removeHand(List<Card>hand1, List<Card>hand2){
        Iterator<Card>q = hand1.iterator();
        int d = hand1.size(), f = 0;
        Card [] w = new Card[hand2.size()];
        w = hand2.toArray(w); //Change another hand into an array for comparison
        
        while(q.hasNext()){
            Card a = q.next();
            int e = 0;
            boolean found = false;
            while(e<w.length && !found){
                if(a==w[e]){
                    found = true; //Card is present from hand
                    f++;
                    q.remove();
                    System.out.println(a+" has been removed from hand");
                }
                e++;
            }
            if(found == false)
                System.out.println(a+" is not present from another hand");
        }
        
        System.out.println(f+" card(s) has been removed from hand");
        return hand.size() == (d-hand2.size());
    }
    
    public static int size(){
        int count = 0;
        Iterator<Card>it = hand.iterator();
        while(it.hasNext()){
            count++;
        }
        return count;
    }
    
    //Remove a card at a particular position
    public static Card removePosition(int a){
        if(a<0 || a>=hand.size()) //Invalid position
            return null;
        else{
            Iterator<Card>Hand = hand.iterator();
            Card s;
            while(Hand.hasNext()){
                s = Hand.next();
                if(s==hand.get(a)){
                    Hand.remove();
                    return s;
                }
            }
        }
        return null;
    }
    
    private static String [] countRank;
    private static String [] countSuit;
    private static Object[][] totalSuit = {{"CLUBS", "DIAMONDS", "SPADES", "HEARTS"},{0,0,0,0}};
    private static Object[][] totalRank = {{"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", 
                                    "JACK", "QUEEN", "KING", "ACE"}, {0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public static void histogram(){
        int clubs = 0, diamonds = 0, spades = 0, hearts = 0;
        int two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine =0,
                ten = 0, jack = 0, queen = 0, king = 0, ace = 0;
        
        countRank = new String[hand.size()];
        countSuit = new String[hand.size()];
        
        for(int i =0; i<hand.size();i++){
            countRank[i] = hand.get(i).rank.getRank();
            countSuit[i] = hand.get(i).suit.getSuit();
        }
        
        for(int q = 0; q<totalSuit[0].length;q++){
            totalSuit[1][q] = 0;
        }
        
        for(int q = 0; q<hand.size();q++){
            if(totalSuit[0][0].equals(countSuit[q])){
                clubs++;
                totalSuit[1][0] = clubs;
            }else if(totalSuit[0][1].equals(countSuit[q])){
                diamonds++;
                totalSuit[1][1] =diamonds;
            }else if(totalSuit[0][2].equals(countSuit[q])){
                spades++;
                totalSuit[1][2] =spades;
            }else if(totalSuit[0][3].equals(countSuit[q])){
                hearts++;
                totalSuit[1][3] =hearts;
            }
        }
        
        for(int q =0;q<totalRank[0].length;q++){
            totalRank[1][q] = 0;
        }
        
        for(int q = 0; q<hand.size();q++){
            if(totalRank[0][0].equals(countRank[q])){
                two++;
                totalRank[1][0] = two;
            }else if(totalRank[0][1].equals(countRank[q])){
                three++;
                totalRank[1][1] = three;
            }else if(totalRank[0][2].equals(countRank[q])){
                four++;
                totalRank[1][2] = four;
            }else if(totalRank[0][3].equals(countRank[q])){
                five++;
                totalRank[1][3] = five;
            }else if(totalRank[0][4].equals(countRank[q])){
                six++;
                totalRank[1][4] = six;
            }else if(totalRank[0][5].equals(countRank[q])){
                seven++;
                totalRank[1][5] = seven;
            }else if(totalRank[0][6].equals(countRank[q])){
                eight++;
                totalRank[1][6] = eight;
            }else if(totalRank[0][7].equals(countRank[q])){
                nine++;
                totalRank[1][7] = nine;
            }else if(totalRank[0][8].equals(countRank[q])){
                ten++;
                totalRank[1][8] = ten;
            }else if(totalRank[0][9].equals(countRank[q])){
                jack++;
                totalRank[1][9] = jack;
            }else if(totalRank[0][10].equals(countRank[q])){
                queen++;
                totalRank[1][10] = queen;
            }else if(totalRank[0][11].equals(countRank[q])){
                king++;
                totalRank[1][11] = king;
            }else if(totalRank[0][12].equals(countRank[q])){
                ace++;
                totalRank[1][12] = ace;
            }
        }
        
        System.out.println("Here comes the histogram:");
        for(int q = 0, w=0; q<totalRank[0].length+totalSuit[0].length;q++){
            if(q<totalRank[0].length)
                System.out.println(totalRank[0][q]+": "+totalRank[1][q]);
            else{
                System.out.println(totalSuit[0][w]+": "+totalSuit[1][w]);
                w++;
            }
        }
        System.out.println();
        handValue();
    }
    
    //Counts the number of a particular suit in the hand
    public static int countSuit(Card.Suit a){
        Iterator<Card>b = hand.iterator();
        String name = a.name();
        int count = 0;
        Card temp;
        while(b.hasNext()){
            temp = b.next();
            if(temp.suit.getSuit() == name)
                count++;
        }
        return count;
    }
    
    //Counts the number of a particular rank in the hand
    public static int countRank(Card.Rank a){
        Iterator<Card>b = hand.iterator();
        String name = a.name();
        int count = 0;
        Card temp;
        while(b.hasNext()){
            temp = b.next();
            if(temp.rank.getRank() == name)
                count++;
        }
        return count;
    }
    
    //Total value of cards in the hand;
    public static int handValue(){
        int totalvalue = 0;
        for(int q = 0; q<hand.size();q++){
            totalvalue+= hand.get(q).rank.getValue();
        }
        return totalvalue;
    }
    
    //Print out the hand
    public static void printHand(){
        for(Card a: hand){
            System.out.println(a);
        }
    }

    @Override
    public String toString(){
        return hand.toString();
    }
    
    @Override
    public Iterator<Card> iterator() {
        return hand.iterator();
    }
    
    //Sort the hand in ascending order
    public static List<Card> sortAscending(){
        Collections.sort(hand);
        return hand;
    }
    
    //Sort the hand in descending order
    public static List<Card> sortDecending(){
        Collections.sort(hand, new Card.CompareDecending());
        return hand;
    }
    
    //Return true if all cards in the hand are the same suit
    public static boolean isFlush(){
        if(hand.isEmpty())
            return false;
        Iterator<Card> it = hand.iterator();
        Card firstSuit = it.next();
        Card compareSuit;
        while(it.hasNext()){
            compareSuit = it.next();
            if(firstSuit.suit.getSuit() != compareSuit.suit.getSuit()){
                return false;
            }
        }
        return true;
    }
    
    //Return true if all the cards are in consecutive order
    public static boolean isStraight(){
        if(hand.isEmpty())
            return false;
        Collections.sort(hand);
        Iterator<Card>it = hand.iterator();
        Card next;
        int i = 0;
        int firstOrdinal = it.next().rank.ordinal();
        while(it.hasNext() && i < hand.size()){
            if(i !=0){
                next = it.next();
                if(firstOrdinal+1 != it.next().rank.ordinal())
                    return false;
                else
                    firstOrdinal++;
            }
            i++;
        }
        return true;
    }
    
    //Convert the ArrayList into Hand, and return it
    public static Hand getHand(){
        Iterator<Card>it = hand.iterator();
        Hand latest = new Hand();
        while(it.hasNext()){
            latest.addCard(it.next());
        }
        return latest;
    }
}