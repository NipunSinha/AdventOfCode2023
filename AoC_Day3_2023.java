package com.scaler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class AoC_Day3_2023 {

	public static void main(String[] args) throws FileNotFoundException {
		
		
        File file = new File("D:\\AoC\\day2.txt");
        Scanner sc = new Scanner(file);  
        
        Map<Pair,List<Integer>> map = new HashMap<>();
        
        int no_of_lines = 0;  
        int length_of_input = 0;
        while (sc.hasNextLine()) {    
        	String s = sc.nextLine();
        	no_of_lines++;        	        	
        	if(no_of_lines==1) {
        		length_of_input = s.length();
        	}
        	
        }        
        char[][] grid = new char[no_of_lines][length_of_input];
        
        file = new File("D:\\AoC\\day2.txt");
        sc = new Scanner(file);  
        
        int i=0;
        
        while (sc.hasNextLine()) {    
        	String s = sc.nextLine();
        	for(int j=0; j<s.length(); j++) {
        		grid[i][j] = s.charAt(j);
        	}
        	i++;
        }
        
      
        int sum = 0;
        int rowLength = grid.length;
        int colLength = grid[0].length;
      
       
		for(int m=0; m < rowLength; m++) {
			for(int n=0; n < colLength; n++) {
				char temp = grid[m][n];
				if(temp >= '0' && temp <='9') {
					//first capture the number
					StringBuilder sb = new StringBuilder();
					int startPos=n;
					int finalPos=n;
					while(finalPos<colLength && grid[m][finalPos] >= '0' && grid[m][finalPos] <='9' ) {
						sb.append(grid[m][finalPos]);
						finalPos++;
					}
					n=finalPos-1;
					int number = Integer.parseInt(sb.toString());
					boolean flag = false;
					
															
					//check in upper row
					if(m-1 >= 0) {
						for(int j=startPos; j<=finalPos-1; j++) {
							if(checkIfSymbol(grid, m-1, j)) {
								flag = true;
								if(grid[m-1][j]=='*') {
									addToGearAndPartNumberMapping(map,new Pair(m-1,j),number);
								}
							}
						}
					}
						
						
					//check in lower row 
					if(m+1 < rowLength) {
						for(int j=startPos; j<=finalPos-1; j++) {
							if(checkIfSymbol(grid,m+1,j)) {
								flag = true;
								if(grid[m+1][j]=='*') {
									addToGearAndPartNumberMapping(map,new Pair(m+1,j),number);
								}
								
							}
						}
					}	
					
					//check in left column					
					int rowStart = Math.max(m-1,0);
					int rowEnd = Math.min(m+1, rowLength-1);
					if(startPos-1>=0) {
						for(int k=rowStart; k<= rowEnd; k++) {							
							if(checkIfSymbol(grid, k, startPos-1)) {
								flag = true;
								if(grid[k][startPos-1]=='*') {
									addToGearAndPartNumberMapping(map,new Pair(k,startPos-1),number);
								}								
							}
						}
					}
					
					//check in right column					
					if(finalPos<colLength) {
						for(int k=rowStart; k<= rowEnd; k++) {
							if(checkIfSymbol(grid,k,finalPos)) {
								flag = true;
								if(grid[k][finalPos]=='*') {
									addToGearAndPartNumberMapping(map,new Pair(k,finalPos),number);
								}
							}
						}
					}

					if(flag==true) {
						sum+=number;
					}			
				}		
			}
		}
		
		System.out.println(sum);
		int gearRatioSum = 0;
		Set<Pair> gearCoords = map.keySet();
		Iterator<Pair> iter = gearCoords.iterator();
		while(iter.hasNext()) {
			Pair gearCoord = iter.next();
			if(map.get(gearCoord).size()==2) {
				int product = map.get(gearCoord).get(0) * map.get(gearCoord).get(1);
				gearRatioSum += product;
			}
		}
		
		System.out.println(gearRatioSum);
		
	}
	
	public static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
	            return false;
	        }

	        if (obj.getClass() != this.getClass()) {
	            return false;
	        }

	        final Pair other = (Pair) obj;
	        if(this.x == other.x && this.y == other.y) {
	        	return true;
	        }
	        
	        return false;
		}
		
		 @Override
		    public int hashCode() {
		        int hash = 3;
		        hash = 53 * hash + this.y;
		        hash = 53 * hash + this.x;
		        return hash;
		    }
	}
	
	
	public static void addToGearAndPartNumberMapping(Map<Pair, List<Integer>> gearMap, Pair gearCoordinate, int part){
		if(!gearMap.containsKey(gearCoordinate)) {
			List<Integer> partList = new ArrayList<>();
			partList.add(part);
			gearMap.put(gearCoordinate,partList);			
		}
		else {
			gearMap.get(gearCoordinate).add(part);			
		}
	}
	
	public static boolean checkIfSymbol(char[][] grid, int x, int y) {
		return grid[x][y]!='.' && (grid[x][y] < '0' || grid[x][y] > '9');
	}
}
