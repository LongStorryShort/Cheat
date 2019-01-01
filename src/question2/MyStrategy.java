package question2;

import question2.Strategy;
import java.util.*;
import question1.Card;
import question1.Hand;

public class MyStrategy implements Strategy{

    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank nextRank = Card.Rank.getNext(b.getRank());
        Random ran = new Random(); //Generate a random number to decide cheat or not
        int number = ran.nextInt(2);
        boolean honest;
        boolean cheat = true;
        
        if(number == 0)
            honest = true;
        else
            honest = false;
        
        Iterator<Card>it = h.iterator();
        while(it.hasNext()){
            if(it.next().rank.getRank() == nextRank.getRank() || (it.next().rank.getRank().equals(b.r.getRank())))
                cheat = false;
        }
        return !(cheat == false && honest == true);
    }
    
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank nextRank = Card.Rank.getNext(b.getRank());
        int count = 0;
        Hand outputHand = new Hand();
        Iterator<Card>it = h.iterator();
        System.out.println("Hand size is: "+h.size());
        
        if(cheat == true){
            while(it.hasNext() && count < 2){ //use one card to cheat only
                if(it.next().rank.getRank() != nextRank.getRank()){
                    outputHand.addCard(it.next());
                    count++;
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
        Hand cards = new Hand();
        cards.addHand(h);
        Random ran = new Random();
        int ranNum = ran.nextInt(2);
        
        Iterator<Card>it = cards.iterator();
        while(it.hasNext()){
            if(it.next().rank.getRank() == currentRank.getRank()){
                count++;
                if((count == 3 && size > 1) || (count == 2 && size > 2) || (count == 1 && size > 3) || (count == 4 && size >0))
                    return true;
            }else if(it.next().rank.getRank().equals(b.r.getRank())){
                value++;
                //Must be cheating if the value of count plus size is greater than 4
                if((value == 3 && size > 1) || (value == 2 && size > 2) || (value == 1 && size > 3) || (value == 4 && size >0))
                    return true;
            }
        }
        //Always assume people is cheating 
        if(size > 2 && ranNum > 0)
            return true;
        return false;
    }
}