package DesignPattern.example.command;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
      String[] alphabetList = new String[]{
          "ABCAAC",
          "AABBCC",
          "CCCCCC",
          "CCBBAA",
          "CCCAAA"
      };
      
   // String[]  ->  Stream<String[]>  ->  Stream<Stream<String>> ->  Stream<Long> -> List<Long>
      System.out.println(Arrays.stream(alphabetList).
          map(s -> Arrays.stream(s.split("")).distinct().count()).collect(Collectors.toList()));
      
   // "ABCAAC" "AABBCC" "CCCCCC"
   // "A" "B" "C" "A" "A" "C"   
   // String[]  ->  Stream<String[]>  ->  Stream<Stream<String>>  ->  Stream<Map<String, Long>>  ->  List<Map<String, Long>>
      System.out.println(Stream.of(alphabetList).map(s -> Stream.of(s.split("")).
          collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))).collect(Collectors.toList()));
      
      
  // 1. alphabetList 각각의 스트링에 있는 고유한 알파벳 개수가 들어있는 
  // List<Long> 형태의 배열을 반환하시오.
  // [3, 3, 1, 3, 2]
      
      
  // 2. alphabetList 각각의 스트링에 대해 알파벳을 키로, 
  // 키에 해당하는 알파벳 개수를 값으로 하는
  // List<Map<String, Long>> 형태의 배열을 반환하시오.
  
  // 3. alphabetList 각각의 스트링에 대해 알파벳을 키로, 
  // 키에 해당하는 알파벳의 index 배열을 값으로 하는
  // List<Map<String, List<Integer>>> 형태의 배열을 반환하시오.
  
      
  // [3, 3, 1, 3, 2]
  // [{A=3, B=1, C=2}, {A=2, B=2, C=2}, {C=6}, {A=2, B=2, C=2}, {A=3, C=3}]
  // [{A=[0, 3, 4], B=[1], C=[2, 5]}, {A=[0, 1], B=[2, 3], C=[4, 5]},
  // {C=[0, 1, 2, 3, 4, 5]}, {A=[4, 5], B=[2, 3], C=[0, 1]}, {A=[3, 4, 5], C=[0, 1, 2]}]
  }   
}
