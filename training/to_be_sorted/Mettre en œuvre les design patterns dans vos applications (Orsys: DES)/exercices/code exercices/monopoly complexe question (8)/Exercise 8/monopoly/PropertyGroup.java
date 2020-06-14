package monopoly;

import java.util.*;

public abstract class PropertyGroup {
  private String name;
  private Vector members;

  public PropertyGroup(String name) {
     this.name = name;
     members = new Vector();
  }
  public abstract int getRentFactorFor(Player owner);

  public void add(PropertySquare prop){
    members.add(prop);
  }
  public boolean isComplete(){
    PropertySquare pFirst = (PropertySquare) members.firstElement();
    for (Enumeration e = members.elements();
         e.hasMoreElements(); ){
      PropertySquare pNext = (PropertySquare) e.nextElement();
      if (pNext.getOwner() != pFirst.getOwner())
        return false;
    }
    return true;
  }
  public int countPropertiesOwnedBy(Player owner)
  {
    int nbOwned = 0;
    PropertySquare pNext = null;
    for (Enumeration e = members.elements(); e.hasMoreElements(); ){
      pNext = (PropertySquare) e.nextElement();
      if (pNext.getOwner() == owner)
         nbOwned++;
    }
    return nbOwned;
  }
}