package DesignPattern.example.Builder;

import DesignPattern.example.Builder.NPC.NPC_Modifier;
import DesignPattern.example.Builder.NPC.NPC_Type;

public class Main {
  public static void main(String[] args) {
     NPC monster = new NPC.Builder("monster", NPC_Type.MERCHANT)
         .setCost(10).setHelper(true).setNpcModifier(NPC_Modifier.FRIENDLY).Build();
     
     System.out.println(monster);
  }
}
