package com.scaler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class AoC_Day5_2023 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("D:\\AoC\\day5.txt");
		Scanner   sc = new Scanner(file);  
        
        List<Long> seeds = new ArrayList<>();
         
    	String seedLine = sc.nextLine();
    	String[] stringSeeds = seedLine.split(": ")[1].split(" ");
    	
    	for(int i=0; i<stringSeeds.length; i++) {
    		seeds.add(Long.parseLong(stringSeeds[i]));
    	}
    	
    	sc.nextLine();
    	
    	Comparator<Mappings> c = new Comparator<Mappings>() {

			@Override
			public int compare(Mappings o1, Mappings o2) {
				if(o1.source+o1.range <= o2.source) {
					return -1;
				}
				else return 1;
			}    		
    	};
    	
    	List<List<Mappings>> listMappings = new ArrayList<>();
    	for(int i=1; i<=7; i++) {
    		listMappings.add(getMappingInput(sc));
    	}
    	
    	for(List<Mappings> list : listMappings) {
    		list.sort(c);
    	}
    	
    	//part - 1 :
    	
    	Long minLocation = Long.MAX_VALUE;
    	for(int i=0; i<seeds.size(); i+=2) {    		
			minLocation = Math.min(minLocation, getLocation(seeds.get(i),listMappings));      		 		
    	}
    	
    	System.out.println(minLocation);
    	
    	//part - 2 : Took 20 minutes to execute
    	
    	 minLocation = Long.MAX_VALUE;
    	for(int i=0; i<seeds.size(); i+=2) {
    		Long start = seeds.get(i);
    		Long end = start + seeds.get(i+1)-1;
    		for(long j=start; j<=end; j++) {
    			minLocation = Math.min(minLocation, getLocation(j,listMappings));  
    		}    		 		
    		System.out.println((i+1+"done "));
    	}
    	
    	System.out.println(minLocation);
    	
    	
	}
	
	private static Long getLocation(Long seed, List<List<Mappings>> listMappings) {
		Long tempSeed = seed;
		for(int i=0; i<listMappings.size(); i++) {
			List<Mappings> allMappings = listMappings.get(i);
			for(int j=0; j<allMappings.size(); j++) {
				Mappings current = allMappings.get(j);				
				if(tempSeed>= current.source && tempSeed <= current.source + current.range-1 ) {
					tempSeed = current.destination + tempSeed-current.source;
					break;
				}
			}
		}		
		return tempSeed;		
	}

	
	public static class Mappings{
		Long destination;
		Long source;
		Long range;
		
		public Mappings(Long destination, Long source, Long range) {
			this.destination = destination;
			this.source = source;
			this.range = range;
		}
		
	}
	
	public static List<Mappings> getMappingInput(Scanner sc){
		
		sc.nextLine();
    	List<Mappings> tempMapping = new ArrayList<>();
    	while(sc.hasNext()) {
    		String s = sc.nextLine();
    		if(s.isEmpty()) {
    			break;
    		}
    		else {
    			String[] mappingLine = s.split(" ");
    			tempMapping.add(new Mappings(Long.parseLong(mappingLine[0]),Long.parseLong(mappingLine[1]),Long.parseLong(mappingLine[2])));
    		}       	       	
        }
    	
    	return tempMapping;
	}
}
