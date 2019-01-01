package question2;

import java.util.*;
import question1.Card;
import question1.Hand;

public class HumanStrategy extends BasicStrategy{

    private final Scanner scan = new Scanner(System.in);
    
    public boolean cheat(Hand hand, Bid bid){
        System.out.println("The current bid is: "+bid+"\nYour current hand is: "+hand.getHand());
        System.out.println("Enter C to cheat or press any key not to cheat.");
        String r = scan.nextLine();
        return r.equals("C")||r.equals("c")||r.equals("cheat");
    }
    
    public Bid chooseBid(Hand hand, Bid bid, Boolean cheat){
        Card.Rank rank = bid.getRank();
        int count = 0;
        Hand q = new Hand();
        Iterator<Card>it = hand.iterator();
        boolean added = false;
        System.out.println("The next bid you will be calling is a rank of "+Card.Rank.getNext(rank)+" or "+rank);
        System.out.println("Your hand size is: "+hand.size());
        
        if(cheat == true){
            String format = "Enter the card that you would like to cheat in the format of 'RANK OF SUIT', or press q to complete the input: ";
            System.out.println(format);
            String str = scan.nextLine().toUpperCase();
            
            while((!str.equals("Q") || !str.equals("QUIT")) && count <4){
                while(it.hasNext() && !added){
                    if(it.next().toString().equals(str))
                        q.addCard(it.next());
                        System.out.println(it.next()+" has been played successfully");
                        added = true;
                }
                if(added == false)
                    System.out.println("The entered card: "+str+" does not exist in your hand");
                count++;
                if(count == 4){
                    System.out.println("Maximum input of card is 4, no more input is allowed");
                }else{
                    System.out.println(format);
                    str = scan.nextLine().toUpperCase();
                }
            }
            System.out.println("Now you have entered "+count+" cards, therefore the you bid will be: "+count+" OF "+Card.Rank.getNext(rank));
            return new Bid(q, Card.Rank.getNext(rank));
        }else{
            int value = 0;
            while(it.hasNext()){
                if(it.next().rank.getRank() == Card.Rank.getNext(rank).getRank()){
                    count++;
                    //q.addCard(it.next());
                }else if(it.next().rank.getRank().equals(bid.r.getRank()))
                    value++;
            }
            System.out.println("You have "+count+" rank "+Card.Rank.getNext(rank)+"card(s) and "+value+" rank "+rank+" card(s)");
            System.out.println("Please enter which rank of card to play in string: ");
            String selection = scan.nextLine().toUpperCase();
            while(!selection.equals(rank.getRank()) && !selection.equals(Card.Rank.getNext(rank).getRank())){
                System.out.println("Invalid input, enter it again please: ");
                selection = scan.nextLine().toUpperCase();
            }
            System.out.println("Enter an integer to set the amount of cards to play: ");
            int number = scan.nextInt();
            while(number > count || number < 0){
                System.out.println("Invalid integer, it must not be smaller than zero or greater than "+count);
                System.out.println("Enter an integer to set the amount of cards to play: ");
                number = scan.nextInt();
                }
            
            System.out.println("You have selected to play "+number+" cards");
            Iterator<Card>out = q.iterator();
            int amount = 0;
            while(out.hasNext()){
                if(amount > count)
                    out.remove();
                amount++;
            }
            return new Bid(q, Card.Rank.getNext(rank));
        }
    }
}