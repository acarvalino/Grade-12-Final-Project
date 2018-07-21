import java.util.Random;
import java.util.ArrayList;

public class Player extends Character
{
  private int xP = 0;
  private int maxXP = 10;
  private int totalXP = 0;
  
  
  public Player(String name, String species)
  {
    super(name, "knight", species, 100.0, 5.0, 5.0, 2, 1);
    addMove(new Moves("light", 1));
    addMove(new Moves("sabre", 1));
  }
  
  public void xpGain(int experience)
  {
    totalXP += experience;
    if (xP + experience >= maxXP)
    {
      increaseLevel();
      System.out.println(getName() + " leveled up!");
      xP = xP - maxXP + experience;
      increaseHealth(10.0 * (double) getLevel());
      increaseAttack(2.5);
      increaseDefense(2.5);
      increaseSpeed(1);
      
      // generates new move
      addMove(newMove());
      
    }
    else
    {
      xP += experience;
    }
  }
  
  public double attacking(int choice)
  {
    double damage = 0;
    ArrayList<Moves> moves = getMoves();
    while(true)
    {
      /*
      if((move.toLowerCase()).equals("exit"))
      {
        break;
      }
      */
      
      return (moves.get(choice)).getValue() + ((moves.get(choice)).getValue()/100.0) * getAttack();
      
      
      /*
      for(int i = 0; i < moves.size(); i++)
      {
        if((moves.get(i)).equals(move))
        {
          if((moves.get(i)).getUses() > 0)
          {
            input.close();
            return (moves.get(i)).getValue() + (getAttack() / 100) * (moves.get(i)).getValue();
          }
          else
          {
            System.out.println("No uses remaining for " + move);
          }
        }
      }
      System.out.println("Please enter a valid move");
      */
    }
    
  }
  public void showMoves()
  {
    ArrayList<Moves> moveSet = getMoves();
    for(int i = 0; i < moveSet.size(); i++)
    {
      System.out.print(i + 1 + ": " + (moveSet.get(i)).getName() + " with " + (moveSet.get(i)).getUses() + " uses; ");
    }
    System.out.println();
  }
  public int getTotalXP()
  {
    return totalXP;
  }
  
  private Moves newMove()
  {
    Random r = new Random();
    int randomType = r.nextInt(2) + 1;
    String type = "";
    if(randomType == 1)
    {
      type = "light";
    }
    else if(randomType == 2)
    {
      type = "sabre";
    }
    else
    {
      System.out.println("ERROR IN player newMove");
    }
    return new Moves(type, getLevel());
  }
}
