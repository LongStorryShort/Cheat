package question2;

import java.util.*;
import question1.Card;
import question1.Hand;

public class ThinkerStrategy implements Strategy{
    
    //Find the actual value for sorting
    private int findNextValue(Card.Rank b){
        int cardValue;
        
        if(b == Card.Rank.JACK)
            cardValue = 11;
        else if(b == Card.Rank.QUEEN)
            cardValue = 12;
        else if(b == Card.Rank.KING)
            cardValue = 13;
        else if(b == Card.Rank.ACE)
            cardValue = 1;
        else
            cardValue = b.getValue();
         
        return cardValue;
    }
    
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank nextRank = Card.Rank.getNext(b.getRank());
        int currentPlayers = BasicCheat.MINPLAYERS;
        int count = 0;
        boolean cheat = true;
        boolean decision = false;
        Card.Rank myAnotherRoundRank = Card.Rank.getNext(nextRank); //Rank for your next round
        while(count < currentPlayers-1){
            myAnotherRoundRank = Card.Rank.getNext(myAnotherRoundRank);
            count++;
        }
        
        Iterator<Card>it = h.iterator();
        while(it.hasNext()){
            //Look for the required card for this round
            if(it.next().rank.getRank().equals(nextRank.getRank()) || (it.next().rank.getRank().equals(b.r.getRank())))
                cheat = false;
            //Look for the required card for the next round
            else if(it.next().rank.getRank() == myAnotherRoundRank.getRank() || it.next().rank.getRank() == Card.Rank.getNext(myAnotherRoundRank).getRank())
                decision = true;
        }
        
        if(cheat == false && decision == true) 
            //Definitely have to cheat if you have the required cards for both rounds,
            //Because no one can make sure you are cheating or not
            //So that you can get rid of those unwant cards (cards won't be used for this round and next round)
            return true;
        else if(cheat == false && decision == false)
            //Better be honest in this round and good luck for your next round
            return false;
        else if(cheat == true && decision == true)
            //Better cheat this round and be honest in your next round
            return true;
        else
            //Ain't no other choices, have to cheat anyway
            return true;
    }

    private static final List<Card>discarded = new ArrayList();
    
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank nextRank = Card.Rank.getNext(b.getRank());
        int currentPlayers = BasicCheat.MINPLAYERS;
        int value = 0;
        int count = 0;
        Card.Rank myAnotherRoundRank = Card.Rank.getNext(nextRank);//Rank for your next round
        while(value < currentPlayers-1){
            myAnotherRoundRank = Card.Rank.getNext(myAnotherRoundRank);
            value++;
        }
        
        Hand potentialHand = new Hand(); //first attempt hand
        Hand outputHand = new Hand(); //finally decision hand for bid
        Iterator<Card>it = h.iterator();
        System.out.println("Hand size is: "+h.size());
        
        if(cheat == true){
            while(it.hasNext()){
                //Count how many safety cards you have
                if(it.next().rank.getRank() == nextRank.getRank())
                    count++;
                //If those cards are not safety cards, add it to the potential hand for cheating
                if(it.next().rank.getRank() != nextRank.getRank() && it.next().rank.getRank() != myAnotherRoundRank.getRank())
                    if(findNextValue(it.next().rank) > findNextValue(b.getRank()) || findNextValue(it.next().rank) > 8)
                        potentialHand.addCard(it.next());
            }
            Iterator<Card>a = potentialHand.iterator();
            value = 0;
            if(count>1){
                while(a.hasNext()){
                    //final hand for the bid
                    if(value < count){
                        outputHand.addCard(it.next());
                        discarded.add(it.next());
                    }
                    value++;
                }
            }else{
                while(a.hasNext()){
                    if(value < 1){
                        outputHand.addCard(it.next());
                        discarded.add(it.next());
                    }
                    value++;
                }
            }
            return new Bid(outputHand, nextRank);
        }else{
            question1.Hand hand = new question1.Hand();
            boolean lowest = false;
            while(it.hasNext()){
                question1.Card temp = it.next();
                if(temp.rank.getRank().equals(b.r.getRank())) //play the maximum number of cards possible
                    lowest = true;
            }
            while(it.hasNext()){
                question1.Card temp = it.next();
                //play the possible lowest rank of card
                if(temp.rank.getRank().equals(b.r.getRank()) && lowest == true)
                    hand.addCard(temp);
                else if(temp.rank.getRank().equals(nextRank.getRank()) && lowest == false)
                    hand.addCard(temp);
            }
            return new Bid(hand, nextRank);
        }
    }
    
    @Override
    public boolean callCheat(Hand h, Bid b) {
        Card.Rank currentRank = b.getRank();
        int size = b.getCount();
        int count = 0;
        int value = 0;
        Hand cards = new Hand(); //new hand for predictions
        cards.addHand(h);
        cards.addCollection(discarded);
        Random ran = new Random();
        int p = ran.nextInt(10)+1; //probability 
        
        Iterator<Card>it = cards.iterator();
        while(it.hasNext()){
            if(it.next().rank.getRank().equals(currentRank.getRank())){
                count++;
                //Must be cheating if the result of count plus size is greater than 4
                if((count == 3 && size > 1) || (count == 2 && size > 2) || (count == 1 && size > 3) || (count == 4 && size >0))
                    return true;
            }else if(it.next().rank.getRank().equals(b.r.getRank())){
                value++;
                //Must be cheating if the value of count plus size is greater than 4
                if((value == 3 && size > 1) || (value == 2 && size > 2) || (value == 1 && size > 3) || (value == 4 && size >0))
                    return true;
            }
        }
        count = 0;
        Iterator<Card>guess = discarded.iterator();
        while(guess.hasNext()){
            if(guess.next().rank.getRank() == currentRank.getRank()){
                count++;
                if(count > 4 && p > 4)
                    return true;
            }
        }
        return false;
    }
}