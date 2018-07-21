import java.util.Random;

public class Enemy extends Character
{
  
  public Enemy(int level)
  {
//    String name = chooseName();
//    String class = chooseType();
//    String species = chooseSpecies();
//    double health = initiateHealth();
//    double attack = initiateAttack();
//    double defense = initiateDefense();
//    int speed = initiateSpeed();
    
    super("", "", "", 0.0, 0.0, 0.0, 0, level);   //name, species, health, attack, defense, speed, level
    
    Random myRandom = new Random();
    
    changeClass(chooseType(level, myRandom));
    changeName(chooseName(level, myRandom));
    changeSpecies(chooseSpecies(level, myRandom));
    changeHealth(initiateHealth(level));
    changeAttack(initiateAttack(level));
    changeDefense(initiateDefense(level));
    changeSpeed(initiateSpeed(level));
    
    int counter = 1;
    addMove(newMove(level, counter));
    counter += 1;
    addMove(newMove(level, counter));
  }
  
  private String chooseName(int level, Random random)
  {
    String name = "";
    int number = random.nextInt(2) + 1;
    if(getCharacterClass().equals("sith"))
    {
      name += "Darth ";
      if(level == 10 || level == 12)
      {
        name += "Revan";
      }
      else
      {
        if(number == 1)
        {
          name += "Sheev";
        }
        else if(number == 2)
        {
          name += "Cheese";
        }
        else
        {
          name = "ERROR IN NAME";
        }
      }
    }
    else if(getCharacterClass().equals("bounty hunter"))
    {
      if(level == 8 || level == 10)
      {
        name = "Boba Fett";
      }
      else
      {
        if(number == 1)
        {
          name = "Cad Bane";
        }
        else if(number == 2)
        {
          name = "Cheese Hunter";
        }
        else
        {
          name = "ERROR IN NAME";
        }
      }
    }
    return name;
  }
  private String chooseType(int level, Random random)
  {
    int choice = random.nextInt(2) + 1;
    if(choice == 1)
    {
      return "sith";
    }
    else if(choice == 2)
    {
      return "bounty hunter";
    }
    else
    {
      return "sith";
    }
  }
  private String chooseSpecies(int level, Random random)
  {
    int choice = random.nextInt(5) + 1;
    if(getName().equals("Darth Revan") || getName().equals("Boba Fett"))
    {
      return "human";
    }
    else
    {
      if(choice == 1)
      {
        return "human";
      }
      else if(choice == 2)
      {
        return "twi'lek";
      }
      else if(choice == 3)
      {
        return "togruta";
      }
      else if(choice == 4)
      {
        return "mon calamari";
      }
      else
      {
        return "rodian";
      }
    }
  }
  private double initiateHealth(int level)
  {
    if(getCharacterClass().equals("sith"))
    {
      return (double) level * 25;
    }
    else if(getCharacterClass().equals("bounty hunter"))
    {
      return (double) level * 15;
    }
    else
    {
      return (double) level * 20;
    }
  }
  private double initiateAttack(int level)
  {
    if(getCharacterClass().equals("sith"))
    {
      return (double) level * 5;
    }
    else if(getCharacterClass().equals("bounty hunter"))
    {
      return (double) level * 10;
    }
    else
    {
      return (double) level * 2;
    }
  }
  private double initiateDefense(int level)
  {
    if(getCharacterClass().equals("sith"))
    {
      return (double) level * 10;
    }
    else if(getCharacterClass().equals("bounty hunter"))
    {
      return (double) level * 5;
    }
    else
    {
      return (double) level * 7;
    }
  }
  private int initiateSpeed(int level)
  {
    if(getCharacterClass().equals("sith"))
    {
      return level * 3;
    }
    else if(getCharacterClass().equals("bounty hunter"))
    {
      return level * 2;
    }
    else
    {
      return level * 20;
    }
  }
  
  private Moves newMove(int level, int count)
  {
    String type = "";
    if(getCharacterClass().equals("sith"))
    {
      if(count == 1)
      {
        type = "dark";
      }
      else if(count == 2)
      {
        type = "sabre";
      }
    }
    else if(getCharacterClass().equals("bounty hunter"))
    {
      if(count == 1)
      {
        type = "blast";
      }
      else if(count == 2)
      {
        type = "sabre";
      }
    }
    else
    {
      System.out.println("ERROR IN ENEMY newMove");
    }
    return new Moves(type, getLevel());
  }
  
  public double attacking(int random)
  {
    Moves move;
    if(random == 1)
    {
      move = (getMoves()).get(0);
    }
    else if(random == 2)
    {
      move = (getMoves()).get(1);
    }
    else
    {
      move = (getMoves()).get(0);
    }
    double damage = move.getValue() + (move.getValue()/100.0) * getAttack();
    System.out.println("Enemy used " + move.getName() + "!");
    return damage;
  }
}
