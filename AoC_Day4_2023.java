package com.scaler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class AoC4 {

	public static void main(String[] args) throws FileNotFoundException {
		
		
        File file = new File("D:\\AoC\\day2.txt");
        Scanner sc = new Scanner(file);  
        List<Pair> matches = new ArrayList<>();
        int sum = 0;
        while (sc.hasNextLine()) {    
	    	String s = sc.nextLine();
	    	
	    	int length = s.length();
	    	int i=0; 
	    	while(i< length && s.charAt(i)!=':') {
	    		i++;
	    	}
	    	i++;
	    	Set<Integer> magicNums = new HashSet<>();
	    	List<Integer> myNums = new ArrayList<>();
	    	boolean barSeen= false;
	    	while(i<length) {
	    		if(s.charAt(i)== '|') {
	    			barSeen = true;
	    			i++;
	    		}
	    		else if(s.charAt(i)==' ') {
	    			i++;
	    		}
	    		else if(s.charAt(i)>='0' && s.charAt(i) <= '9') {
	    			int j= i+1;
	    			int num = s.charAt(i)-'0';
	    			while(j<length && s.charAt(j)>='0' && s.charAt(j)<='9') {
	    				num = num*10 + (s.charAt(j)-'0');
	    				j++;
	    			}
	    			i=j;
	    			if(barSeen == true) {
	    				myNums.add(num);
	    			}
	    			else {
	    				magicNums.add(num);
	    			}
	    		}
	    	}
	    	
	    	int countMatches = 0;
	    	for(Integer num: myNums) {
	    		if(magicNums.contains(num)) {
	    			countMatches++;
	    		}
	    	}
	    	
	    	if(countMatches > 0) {       		
	    		sum += 1<<(countMatches-1);
	    	}
	    	
	    	matches.add(new Pair(1,countMatches));        	
	    }
	    
	    System.out.println(sum);
      
	   for(int i=0; i<matches.size(); i++) {
		   Pair current = matches.get(i);
		   int limitOfCardsToAdd = current.matches;
		   int currentCardCount = current.cardcount;    	  
		   for(int k=i+1, l=1; l<=limitOfCardsToAdd; l++, k++) {
			   matches.get(k).cardcount += currentCardCount;
		   }	   
	   }
       
       int cardsum = 0;
       for(int i=0; i<matches.size(); i++) {
    	   cardsum += matches.get(i).cardcount;
       }
       
       System.out.println(cardsum);
    }        
	
	public static class Pair{
		int cardcount;
		int matches;
		
		public Pair(int cardcount, int matches) {
			this.cardcount = cardcount;
			this.matches = matches;
		}
	}
        
}
