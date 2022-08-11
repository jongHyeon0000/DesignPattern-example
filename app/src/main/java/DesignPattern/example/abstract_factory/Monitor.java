package DesignPattern.example.abstract_factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Supplier;

abstract public class Monitor extends Computer{
  private int inch;
  
  Monitor(int cost, int weight, int inch){
    super(cost, weight);
    
    this.inch = inch;
  }
  
  public int getInch() {
  	return inch;
  }
  
  @SuppressWarnings("unchecked")
	public static <V extends Monitor> Map<Integer, V> getRanking(Monitor...monitors) {
  	Map<Integer, V> map = new TreeMap<Integer, V>();
  	
  	for(Monitor m : monitors) {
  		int star = (m.getInch() * 1000 + m.getWeight() * 10) - m.getCost();

  		map.put(star, (V) m);
  	}
    
    return map;
  }
  
  @SuppressWarnings("unchecked")
	public static <V extends Monitor> Map<Integer, V> getRanking(Comparator<? super Integer> comparator, Monitor...monitors) {
  	Map<Integer, V> map = new TreeMap<Integer, V>(comparator);
  	
  	for(Monitor m : monitors) {
  		int star = (m.getInch() * 1000 + m.getWeight() * 10) - m.getCost();

  		map.put(star, (V) m);
  	}
    
    return map;
  }
  	
  }
}
