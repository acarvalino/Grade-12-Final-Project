import java.util.Random;

public class Friend extends Character
{
  public Friend(int level)
  {
    super("", "", "", 0.0, 0.0, 0.0, 0, level);  //name, species, health, attack, defense, speed, level
    
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
    if(getCharacterClass().equals("jedi"))
    {
      name += "Master ";
      if(level == 10 || level == 12)
      {
        name += "Yoda";
      }
      else
      {
        if(number == 1)
        {
          name += "Windu";
        }
        else if(number == 2)
        {
          name += "Cheese The Third";
        }
        else
        {
          name = "ERROR IN NAME";
        }
      }
    }
    else if(getCharacterClass().equals("droid"))
    {
      if(level == 8 || level == 10)
      {
        name = "R2-D2";
      }
      else
      {
        if(number == 1)
        {
          name = "Gonk";
        }
        else if(number == 2)
        {
          name = "Cheesy Bot";
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
      return "jedi";
    }
    else if(choice == 2)
    {
      return "droid";
    }
    else
    {
      return "droid";
    }
  }
  private String chooseSpecies(int level, Random random)
  {
    int choice = random.nextInt(5) + 1;
    if(getName().equals("Master Windu"))
    {
      return "human";
    }
    else if(getName().equals("Master Yoda"))
    {
      return "Yoda's Species";
    }
    else if(!getCharacterClass().equals("droid"))
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
    else
    {
      return "droid";
    }
  }
  private double initiateHealth(int level)
  {
    if(getCharacterClass().equals("jedi"))
    {
      return (double) level * 25;
    }
    else if(getCharacterClass().equals("droid"))
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
    if(getCharacterClass().equals("jedi"))
    {
      return (double) level * 5;
    }
    else if(getCharacterClass().equals("droid"))
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
    if(getCharacterClass().equals("jedi"))
    {
      return (double) level * 10;
    }
    else if(getCharacterClass().equals("droid"))
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
    if(getCharacterClass().equals("jedi"))
    {
      return level * 3;
    }
    else if(getCharacterClass().equals("droid"))
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
    if(getCharacterClass().equals("jedi"))
    {
      if(count == 1)
      {
        type = "light";
      }
      else if(count == 2)
      {
        type = "sabre";
      }
    }
    else if(getCharacterClass().equals("droid"))
    {
      if(count == 1)
      {
        type = "heal";
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
    System.out.println(getName() + " used " + move.getName() + "!");
    return damage;
  }
}
