import java.util.Random;
public class Moves 
{
  private String name;
  private String type; // light, dark, sabre, blast, heal (must have exacly one type)
  private double value; // either damage or healing value
  private int uses;
  
  public Moves(String type, int level) // player level determines the power of the move and # of uses and the class determines the move type
  {
    this.type = type.toLowerCase();
    if(type.equals("light"))
    {
      value = (double) level * 15;
      uses = 10 / level + 10 % level;
      name = chooseName(type, level);
    }
    else if(type.equals("dark"))
    {
      value = (double) level * 30;
      uses = 10 / level + 10 % level;
      name = chooseName(type, level);
    }
    else if(type.equals("sabre"))
    {
      value = (double) level * 5;
      uses = 20 / level + 20 % level;
      name = chooseName(type, level);
    }
    else if(type.equals("blast"))
    {
      value = (double) level * 20;
      uses = 10 / level + 10 % level;
      name = chooseName(type, level);
    }
    else if(type.equals("heal"))
    {
      value = (double) level * 10;
      uses = 15 / level + 15 % level;
      name = chooseName(type, level);
    }
    else
    {
      System.out.println("Error: invalid type!");
    }
  }
  
  public String getType()
  {
    return type;
  }
  public String getName()
  {
    return name;
  }
  public double getValue()
  {
    return value;
  }
  public int getUses()
  {
    return uses;
  }
  
  
  private String chooseName(String type, int level)
  {
    Random myRandom = new Random();
    int choice = myRandom.nextInt(3) + 1;
    if(type.equals("light"))
    {
      if(choice == 1)
      {
        return "force push (" + level + ")";
      }
      else if(choice == 2)
      {
        return "confusion (" + level + ")"; // doesn't confuse in the traditional sense, just does some damage
      }
      else if(choice == 3)
      {
        return "object hurl (" + level + ")";
      }
      else
      {
        System.out.println("ERROR IN NAMING MOVE");
      }
    }
    if(type.equals("dark"))
    {
      if(choice == 1)
      {
        return "force choke (" + level + ")";
      }
      else if(choice == 2 && level > 5)
      {
        return "force lightning (" + level + ")";
      }
      else if(choice == 2 && level <= 5)
      {
        return "force freeze (" + level + ")"; // does not affect Black Panther
      }
      else if(choice == 3)
      {
        return "force push (" + level + ")";
      }
      else
      {
        System.out.println("ERROR IN NAMING MOVE");
      }
    }
    if(type.equals("sabre"))
    {
      if(choice == 1)
      {
        return "stab (" + level + ")";
      }
      else if(choice == 2)
      {
        return "normal strike (" + level + ")";
      }
      else if(choice == 3 && level > 8)
      {
        return "sheev spin (" + level + ")";
      }
      else if(choice == 3 && level <= 8)
      {
        return "swift strike (" + level + ")";
      }
      else
      {
        System.out.println("ERROR IN NAMING MOVE");
      }
    }
    if(type.equals("blast"))
    {
      if(choice == 1)
      {
        return "blast (" + level + ")";
      }
      else if(choice == 2)
      {
        return "grenade (" + level + ")";
      }
      else if(choice == 3)
      {
        return "flame thrower (" + level + ")";
      }
      else
      {
        System.out.println("ERROR IN NAMING MOVE");
      }
    }
    if(type.equals("heal"))
    {
      if(level <= 3)
      {
        return "minor heal (" + level + ")";
      }
      else if(level > 3 && level <= 8)
      {
        return "major heal (" + level + ")";
      }
      else if(choice == 3 && level > 8)
      {
        return "group heal (" + level + ")";
      }
      else
      {
        System.out.println("ERROR IN NAMING MOVE");
      }
    }
    return "ERROR IN NAMING MOVE";
  }
}
