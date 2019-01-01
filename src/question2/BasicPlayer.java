package question2;

import java.util.*;

public class BasicPlayer implements Player{
    
    Strategy strategy;
    BasicCheat cheat;
    List<question1.Card>list = new ArrayList(); //list to store player's card
    CardGame game;
    question1.Hand hand;
    
    public BasicPlayer(Strategy strategy, BasicCheat cheat){
        this.strategy = strategy;
        this.cheat = cheat;
    }
    
    @Override
    public void addCard(question1.Card c) {
        list.add(c);
    }

    @Override
    public void addHand(question1.Hand h) {
       Iterator<question1.Card>it = h.iterator();
       while(it.hasNext()){
           list.add(it.next());
       }
    }

    @Override
    public int cardsLeft() {
        return hand.size();
    }

    @Override
    public void setGame(CardGame g) {
        game = g;
    }

    @Override
    public void setStrategy(Strategy s) {
        strategy = s;
    }

    @Override
    public Bid playHand(Bid b){
        Iterator<question1.Card>it = list.iterator();
        hand = new question1.Hand();
        while(it.hasNext()){
            hand.addCard(it.next()); //Store the player's cards into Hand class
        }
        System.out.println(hand); //Player's current hand
        boolean decision = strategy.cheat(b,hand);
        return strategy.chooseBid(b, hand, decision);
    }
    
    @Override
    public boolean callCheat(Bid b) {
        Iterator<question1.Card>it = list.iterator();
        while(it.hasNext()){
            hand.addCard(it.next());
        }
        return strategy.callCheat(hand, b);
    }
}