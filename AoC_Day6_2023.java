import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class AoC_Day6_2023 {

	public static void main(String[] args) throws FileNotFoundException {
		
		
        File file = new File("D:\\AoC\\day2.txt");
        Scanner sc = new Scanner(file);  
        
        List<Double> time = new ArrayList<>();
        List<Double> distance = new ArrayList<>();
        
        String timeInput = sc.nextLine();    	
    	extracted(timeInput,time);
    	
    	String distanceInput = sc.nextLine();    	
    	extracted(distanceInput,distance);
    	
    	int product = 1;
    	for(int i=0; i<time.size(); i++) {
    		double t = time.get(i);
    		double d = distance.get(i);
    		
    		//finding the root of the equation x^2 - t*x + d < 0.
    		//answer is no. of integers between r1 and r2
    		int r1 = (int)(Math.ceil((t - (Math.sqrt(t*t-4*d)))/2));
    		int r2 = (int)(Math.floor((t + (Math.sqrt(t*t-4*d)))/2));
    		
    		// if they are perfect roots, they satisfy x^2 - t*x + d = 0 so increase lower root by 1 and decrease upper root by 2
    		if((t-r1)*r1<=d){
    			r1++;
    		}
    		if((t-r2)*r2<=d){
    			r2--;
    		}
    		int ways = (int)(r2-r1+1);
    		System.out.println(ways);
    		product = product*ways;
    		
    	}
    	
    	System.out.println(product);
    	
    	//For case 2 remove whitespace in the input file
	    	
	         	
    }
	private static void extracted(String input, List<Double> list) {
		int length = input.length();
		int i=0; 
    	while(i< length && input.charAt(i)!=':') {
    		i++;
    	}	    	
    
    	while(i<length) {
    		if(input.charAt(i)>='0' && input.charAt(i) <= '9') {
    			int j= i+1;
    			double num = input.charAt(i)-'0';
    			while(j<length && input.charAt(j)>='0' && input.charAt(j)<='9') {
    				num = num*10 + (input.charAt(j)-'0');
    				j++;
    			}
    			i=j;
    			list.add((double)num);    			
    		}
    		else {
    			i++;
    		}
    	}
	}
	    
	   
        
}