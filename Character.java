import java.util.ArrayList;

public abstract class Character 
{
  private String name;
  private String characterClass; // knight/sith (even in attack and defense), droid (weak in both but can heal),
  // bounty hunter (strong attack, weak defense)
  private String species;
  private double maxHealth; // max health points the pokemon has
  private double health; // current health points
  private double attack; // attack multiplier (if attack = 13.0, then the attack is mulitplied by 1.13)
  private double defense; // subtracts a percent from the opponent's attack
  private int speed; // determines who attacks first in battle
  private int level; // character's strength, whether player or AI
  private ArrayList<Moves> moves = new ArrayList<Moves>(); // array of moves for the character (will change to an array if there's time)
  
  // Constructor
  public Character(String name, String type, String species, double maxHealth, double attack, double defense, int speed, int level)
  {
    this.name = name;
    characterClass = type;
    this.species = species;
    this.maxHealth = maxHealth;
    this.attack = attack;
    this.defense = defense;
    this.speed = speed;
    this.level = level;
    health = maxHealth;
  }
  
  // Accessors
  public String getCharacterClass()
  {
    return characterClass;
  }
  public String getName()
  {
    return name;
  }
  public String getSpecies()
  {
    return species;
  }
  public double getMaxHealth()
  {
    return maxHealth;
  }
  public double getHealth()
  {
    return health;
  }
  public double getAttack()
  {
    return attack;
  }
  public double getDefense()
  {
    return defense;
  }
  public int getSpeed()
  {
    return speed;
  }
  public ArrayList<Moves> getMoves()
  {
    return moves;
  }
  public void showMoves()
  {
    for(int i = 1; i < moves.size() + 1; i++)
    {
      System.out.println(i + ": " +(moves.get(i)).getName() + ", of type " + (moves.get(i)).getType() + " with " + (moves.get(i)).getUses() + " uses");
    }
    System.out.println();
  }
  public int getLevel()
  {
    return level;
  }
  
  
  // Mutators
  public void changeName(String newName)
  {
    name = newName;
  }
  public void changeClass(String newCharacterClass)
  {
    characterClass = newCharacterClass;
  }
  public void changeSpecies(String newSpecies)
  {
    species = newSpecies;
  }
  public void changeHealth(double change)
  {
    maxHealth = change;
    health = change;
  }
  public void changeAttack(double change)
  {
    attack = change;
  }
  public void changeDefense(double change)
  {
    defense = change;
  }
  public void changeSpeed(int change)
  {
    speed = change;
  }
  public void increaseHealth(double increase)
  {
    maxHealth += increase;
    health += increase;
  }
  public void increaseAttack(double increase)
  {
    attack += increase;
  }
  public void increaseDefense(double increase)
  {
    defense += increase;
  }
  public void increaseSpeed(int increase)
  {
    speed += increase;
  }
  public void increaseLevel()
  {
    level += 1;
  }
  
  
  public void damageTaken(double damage)
  {
    health -= damage;
  }
  public void healing(double healing)
  {
    health += healing;
    if(health > maxHealth)
    {
      health = maxHealth;
    }
  }
  public void addMove(Moves newMove)
  {
    moves.add(newMove);
  }
  
  // Abstract
  public abstract double attacking(int userOrRandomInt);
}
