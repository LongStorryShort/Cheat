package question2;

import java.util.*;

public class StrategyFactory{
    public static void setStrategy(){
        Scanner scan = new Scanner(System.in);
        Strategy strategy;
        System.out.println("You have 4 strategies to choose");
        System.out.println("1 = BasicStrategy, 2 = HumanStrategy, 3 = ThinkerStrategy, 4 = MyStrategy");
        System.out.println("Enter an integer to choose a strategy: ");
        int value = scan.nextInt();
        
        //Check the input value
        while (value < 0 || value > 4){
            System.out.println("Invalid input, integer must be between 0 and 4");
            System.out.println("Enter an integer to choose a strategy: ");
            value = scan.nextInt();
        }
        
        if(value == 1){
            strategy = new BasicStrategy();
            BasicPlayer basicPlayer = new BasicPlayer(strategy, new BasicCheat());
            basicPlayer.setStrategy(strategy);
        }else if(value == 2){
            strategy = new HumanStrategy();
            BasicPlayer basicPlayer = new BasicPlayer(strategy, new BasicCheat());
            basicPlayer.setStrategy(strategy);
        }else if(value == 3){
            strategy = new ThinkerStrategy();
            BasicPlayer basicPlayer = new BasicPlayer(strategy, new BasicCheat());
            basicPlayer.setStrategy(strategy);
        }else{
            strategy = new MyStrategy();
            BasicPlayer basicPlayer = new BasicPlayer(strategy, new BasicCheat());
            basicPlayer.setStrategy(strategy);
        }
    }
}