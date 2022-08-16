package DesignPattern.example.abstract_builder;

import DesignPattern.example.abstract_builder.npc.NPC;
import DesignPattern.example.abstract_builder.npc.NPC.NPC_Modifier;
import DesignPattern.example.abstract_builder.npc.NPC.NPC_Type;
import DesignPattern.example.abstract_builder.npc.Village_NPC;

public class Main {
  public static void main(String[] args) {
    NPC npc1 = new Village_NPC.Builder("monster", NPC_Type.HELPER)
         .setCost(10).setHelper(true).setNpcModifier(NPC_Modifier.NEUTRAL).Build();
    
    npc1.toString();
  }
}
