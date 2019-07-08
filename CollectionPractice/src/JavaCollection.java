import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaCollection {
	
	public static void main(String args[]) {
	
	//An arrayList of fruits:
	List <String> listOfFruits = new ArrayList<>();
	
	listOfFruits.add("Orange");
	listOfFruits.add("Banana");
	listOfFruits.add("Apple");
	listOfFruits.add("Strawberry");
	listOfFruits.add("Mango");
	
	
	System.out.println("List of Fruits:" + listOfFruits);
	
	//Added pineapple to the first element.
	listOfFruits.add(0,"Pineapple");
	System.out.println("Pineapple should be first element:" + listOfFruits);
	
	//Change the 4th element 
	listOfFruits.remove(3);
	listOfFruits.add(3,"Plum");
	System.out.println("Plum should be 4th element:"+ listOfFruits);
	
	
	
	//Replace Strawberry with Blueberry 
	for (int i=0; i < listOfFruits.size();i++) {
		if(listOfFruits.get(i) == "Strawberry")
		{
			listOfFruits.set(i, "Blueberry");
		}		
	}
	System.out.println("Blueberry replaces Strawberry:"+ listOfFruits);
	
	
	//Print first half of ArrayList: 
	System.out.println("first half of list: "+ listOfFruits.subList(0, (int)((listOfFruits.size())*0.5)));
	
	
	//Use an iterator to iterate starting at the 2nd element:
	Iterator <String> iter = listOfFruits.listIterator(1);
	
	while(iter.hasNext()) {
		System.out.println(iter.next());
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	//New HashSet
	HashSet <String> setOfStrings = new HashSet<>();
	
	setOfStrings.add("words");
	setOfStrings.add("thisIsTheLongestStringInThisHashSet");
	setOfStrings.add("string");
	setOfStrings.add("longString");
	setOfStrings.add("shortString");
	
	
	System.out.println("Longest: "+  longestString(setOfStrings));
	System.out.println("Odd Lengthed Strings:  "+  removeEvenString(setOfStrings));
	
	
	//------------------------------------------------------------------------------------------------------------
	
	//Maps
	HashMap <String,String> mapOfColours = new HashMap<>(); 
	mapOfColours.put("Red","#FF0000");
	mapOfColours.put("Yellow","#FFFF00");
	mapOfColours.put("White","#FFFFFF");
	mapOfColours.put("Orange","#FFA500");
	mapOfColours.put("Cyan","#00FFFF");
	mapOfColours.put("Lime","#00FF00");
	mapOfColours.put("Crimson","#FF0000");
	
	System.out.println("Unique map? " + uniqueMap(mapOfColours));
	
	
	
	}//main
	
	//Method that takes a set and returns the string with the most letters
	public static String longestString(HashSet <String> set) {
		
		String result= null; 
		int l =0;
		for(String name: set) {
			
			if(name.length()>l) {
				l= name.length();
				result = name;
			}
		}
		
		
		return result;
	}

	//Method that takes a set and removes all strings with an even length.
	public static HashSet<String> removeEvenString(HashSet <String> set){
		
		HashSet <String> newSet = new HashSet<>();
		for(String name: set) {
			
			if((name.length())%2 != 0) 
			{
				newSet.add(name);
			}
		}
		
		return newSet;
		
	}
	
	
	//Method that sees if the map is unique 
	public static Boolean uniqueMap(HashMap<String,String> map) {
		Boolean result = true;
		
		if(map.isEmpty()) {result = true;}
		else {
			int count = 0;
			
			Map <String,String> colourcode = map;
			for(Map.Entry<String, String> entry1 : colourcode.entrySet()) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if(entry.getValue() == entry1.getValue() && entry.getKey() != entry1.getKey()) {
					result = false;
				}
			}
			}
		}
		
		
		return result;
	}
	
	
}
