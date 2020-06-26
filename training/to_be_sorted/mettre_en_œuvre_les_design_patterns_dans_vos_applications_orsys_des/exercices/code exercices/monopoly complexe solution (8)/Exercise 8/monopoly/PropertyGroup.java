package monopoly;

import java.util.*;

public class PropertyGroup {
  private String name;
  private Vector members;

  public PropertyGroup(String name) {
     this.name = name;
     members = new Vector();
  }
  public void add(PropertySquare prop){
    members.add(prop);
  }
  public boolean isComplete(){
    PropertySquare pFirst = (PropertySquare) members.firstElement();

    // test if the first owner holds all the properties
    if (countPropertiesOwnedBy(pFirst.getOwner()) == members.size())
        return true;
    return false;
  }
  public int countPropertiesOwnedBy(Player owner)
  {
    int nbOwned = 0;
    for (Enumeration e = members.elements(); e.hasMoreElements(); ){
      PropertySquare pNext = (PropertySquare) e.nextElement();
      if (pNext.getOwner() == owner)
         nbOwned++;
    }
    return nbOwned;
  }
}