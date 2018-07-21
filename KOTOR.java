import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/*
 * This is a bad clone of Knights of The Old Republic, where the player creates his/her character
 * and fights a single randomly generated enemy until he/she dies.
 * After leveling up, the user will gain a new move and if it's a multiple of 2,
 * a new friend to help.
 * Leaderboard is the totalXP gained.
 */


public class KOTOR 
{
  
  public static void main(String[] args) throws FileNotFoundException
  { 
    Scanner input = new Scanner(System.in);
    
    // all permanent variables and arrays go here:
    ArrayList<Character> partyMembers = new ArrayList<Character>();
    int partyCounter = 0;
    
    // Intro:
    System.out.println("Star Wars: Knights of the Old Republic");
    System.out.println("You are a jedi knight of the Old Republic, and following the Sith");
    System.out.println("Empire's invasions of Coruscant, must reclaim what remains of the");
    System.out.println("Jedi Temple, fighting the Sith and Mandalorian warriors who remain.");
    System.out.println("Along your way, you will come across friends who will aid you in your mission.");
    skip();
    
    // Character Creation:
    String name = "";
    String species = "";
    String[] speciesList = {"human", "twi'lek", "togruta", "mon calamari","rodian"};
    boolean end = false; // ends the while loop when proper species is selected
    
    System.out.println("Please enter your name:");
    name = input.nextLine();
    System.out.println("What species are you?");
    while(true)
    {
      System.out.println("Please enter a species");
      for(String x: speciesList)
      {
        System.out.print(x + ", ");
      }
      System.out.println();
      species = input.nextLine();
      species.toLowerCase();
      for(int i = 0; i < speciesList.length; i++)
      {
        if(species.equals(speciesList[i]))
        {
          end = true;
        }
      }
      if(end)
      {
        break;
      }
    }
    Character thePlayer = new Player(name, species);
    
    partyMembers.add(thePlayer);
    partyCounter++;
    
    
    // beginning of game:
    System.out.print("you are a " + thePlayer.getSpecies() + " jedi knight and your name is " + thePlayer.getName());
    System.out.print(" and you have as your starting moves: ");
    System.out.println();
    thePlayer.showMoves();
    skip();
    
    // combat goes until player dies
    while(thePlayer.getHealth() > 0.0)
    {
      combat(partyMembers, new Enemy(thePlayer.getLevel()));
      if(thePlayer.getHealth() > 0.0 && thePlayer.getLevel() % 2 == 0 && partyCounter < 4)
      {
        partyMembers.add(new Friend(thePlayer.getLevel()));
        partyCounter++;
        System.out.println("You added: " + (partyMembers.get(partyCounter - 1)).getName() + " to your party");
      }
      else if(thePlayer.getHealth() > 0.0 && thePlayer.getLevel() % 2 == 0 && partyCounter == 4)
      {
        System.out.println((partyMembers.get(1)).getName() + " was removed from your party");
        partyMembers.remove(1);
        partyMembers.add(1, new Friend(thePlayer.getLevel()));
        
        System.out.println("You added: " + (partyMembers.get(partyCounter - 1)).getName() + " to your party");
      }
      else if(partyCounter >= 2)
      {
        for(int i = 1; i < partyMembers.size(); i++)
        {
          if((partyMembers.get(i)).getHealth() <= 0.0)
          {
            System.out.println((partyMembers.get(i)).getName() + " died and was removed from your party");
            partyMembers.remove(i);
            partyCounter--;
            i -= 1;
          }
        }
      }
      skip();
    }
    
    System.out.println("You died");
    
    int[] scores = new int[10];
    
    //String[] names = new String[10];
    
    
    Scanner fileInput = new Scanner(new File("LeaderBoard.txt"));
    int x = 0;
    //String y = "";
    for(int i = 0; i < 10; i++)
    {
      x = fileInput.nextInt();
      //y = fileInput.nextLine();
      scores[i] = x;
      //names[i] = y;
    }
    fileInput.close();
    
    if(((Player) thePlayer).getTotalXP() > scores[9])
    {
      scores[9] = ((Player) thePlayer).getTotalXP();
      ArraySort board = new ArraySort(scores);
      //scores = board.bubbleSort(true);
    }
    /*
    for(int i = 0; i < 10; i++)
    {
      if(((Player) thePlayer).getTotalXP() == scores[i])
      {
        
      }
    }
    */
    
    PrintStream fileOutput = new PrintStream("LeaderBoard.txt");
    
    for(int i = 0; i < scores.length; i++)
    {
      fileOutput.println(scores[i]);
    }
    
    fileOutput.close();
    
    
    
    input.close();
  }
  
  public static void combat(ArrayList<Character> partyMembers, Enemy enemy)
  {
    //System.out.println("debug");
    Scanner input = new Scanner(System.in);
    Random myRandom = new Random();
    boolean playerTurn = true;
    double damageDone = 0.0;
    double healingDone = 0.0;
    int random = 0;
    int choice = 0;
    
    System.out.println("You have come across " + enemy.getName() + ", who is a " + enemy.getCharacterClass());
    System.out.println("with " + enemy.getHealth() + " health!");
    if(enemy.getSpeed() > (partyMembers.get(0)).getSpeed()) 
    {
      playerTurn = false;
    }
    while((partyMembers.get(0)).getHealth() > 0.0 && enemy.getHealth() > 0.0) 
    {
      random = 0;
      damageDone = 0.0;
      if(playerTurn) 
      {
        for(int i = 0; i < partyMembers.size(); i++)
        {
          if(i == 0) // player is first to attack in team
          {
            while(true)
            {
              System.out.println("Please select a move or press 0 to skip your turn");
              (partyMembers.get(0)).showMoves();
              try
              {
                choice = input.nextInt(); // add try catch!!!!!
                
                if(choice <= ((partyMembers.get(0)).getMoves()).size() && choice > 0)
                {
                  damageDone = (partyMembers.get(i)).attacking(choice - 1);
                  enemy.damageTaken(damageDone);
                  break;
                }
                else if(choice == 0)
                {
                  System.out.println("You skipped your turn");
                  break;
                }
                else
                {
                  System.out.println("Please enter a valid input");
                }
              }
              catch(Exception e)
              {
                System.out.println("Please enter an integer");
                input.nextLine();
              }
            }
          }
          else
          {
            if((partyMembers.get(i)).getHealth() > 0 && !(partyMembers.get(i)).getCharacterClass().equals("droid"))
            {
              random = myRandom.nextInt(2) + 1;
              damageDone = (partyMembers.get(i)).attacking(random);
              enemy.damageTaken(damageDone);
            }
            else if((partyMembers.get(i)).getHealth() > 0 && (partyMembers.get(i)).getCharacterClass().equals("droid"))
            {
              damageDone = 0.0;
              healingDone = (partyMembers.get(i)).attacking(random);
              for(int j = 0; j < partyMembers.size(); j++)
              {
                (partyMembers.get(j)).healing(healingDone);
              }
              System.out.println((partyMembers.get(i)).getName() + " healed the group for: " + healingDone + " health");
            }
            else
            {
              damageDone = 0.0;
              (partyMembers.get(i)).changeHealth(0.0);
            }
          }
          System.out.println((partyMembers.get(i)).getName() + " did " + damageDone + " damage!");
          
          if(enemy.getHealth() < 0)
          {
            System.out.println("The enemy: " + enemy.getName() + " has 0 health left");
          }
          else
          {
            System.out.println("The enemy: " + enemy.getName() + " has " + enemy.getHealth() + " health left");
          }
        }
        playerTurn = false;
      }
      else if(!playerTurn)
      {
        for(int i = 0; i < partyMembers.size(); i++)
        {
          random = myRandom.nextInt(2) + 1; // selects move to use
          damageDone = enemy.attacking(random);
          (partyMembers.get(i)).damageTaken(damageDone);
          if((partyMembers.get(i)).getHealth() < 0.0)
          {
            (partyMembers.get(i)).changeHealth(0.0);
          }
          System.out.println("The enemy: " + enemy.getName() + ", did " + damageDone + " damage to " + (partyMembers.get(i)).getName() + "!");
          System.out.println((partyMembers.get(i)).getName() + " has " + (partyMembers.get(i)).getHealth() + " left");
        }
        playerTurn = true;
      }
      else
      {
        System.out.println("ERROR IN COMBAT METHOD");
      }
    }
    
    if((partyMembers.get(0)).getHealth() > 0)
    {
      System.out.println("You defeated " + enemy.getName());
      System.out.println("You gained: " + (4 + enemy.getLevel()) + " xP!");
      ((Player) partyMembers.get(0)).xpGain(10 + enemy.getLevel());
    }
  }
  
  
  public static void skip()
  {
    System.out.println();
    System.out.println();
    System.out.println();
  }
}
