package question2;

import java.util.*;

public class BasicStrategy implements Strategy{

    @Override
    public boolean cheat(Bid b, question1.Hand h) {
        question1.Card.Rank nextRank = question1.Card.Rank.getNext(b.getRank()); //rank for the next bid
        Iterator<question1.Card>it = h.iterator();
        while(it.hasNext()){
            //have the required card, no need to cheat
            if(it.next().rank.getRank().equals(nextRank.getRank()) || (it.next().rank.getRank().equals(b.r.getRank())))
                return false;
        }
        return true;
    }

    @Override
    public Bid chooseBid(Bid b, question1.Hand h, boolean cheat){
        int size = h.size();
        System.out.println("Hand size is: "+size);
        question1.Card.Rank nextRank = question1.Card.Rank.getNext(b.getRank()); //rank for the next bid
        question1.Hand hand;
        Random ran = new Random();
        Iterator<question1.Card>it = h.iterator();
        System.out.println(cheat);
        
        if(cheat == true){
            hand = new question1.Hand();
            int cardPosition = ran.nextInt(size); //select a random card
            int count = 0;
            while(it.hasNext()){
                question1.Card temp = it.next();
                if(count == cardPosition) //select one card only
                    hand.addCard(temp);
                count++;
            }
            return new Bid(hand, nextRank);
        }else{
            hand = new question1.Hand();
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
    public boolean callCheat(question1.Hand h, Bid b) {
        question1.Card.Rank currentRank = b.getRank();
        int size = b.getCount();
        int count = 0;
        int value = 0;
        System.out.println(h+" h");
        System.out.println(size+" "+currentRank+" call");
        Iterator<question1.Card>it = h.iterator();
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
        return false;
    }
}