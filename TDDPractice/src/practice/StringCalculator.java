package practice;

public class StringCalculator {
	public int Add(String inputString){
	
	int result=0; 
	
	int length = inputString.length();
	
	
	
	
	if(inputString.isEmpty()) {result =0;}

	else if(!inputString.contains(",")) {result = Integer.parseInt(inputString);}
	
	else {
		int split =0;
		int commaIndex = inputString.substring(split,length).indexOf(",");
		String tempString = inputString;
		
		while (tempString.contains(",")){
			
			result += Integer.parseInt(inputString.substring(split,commaIndex));
			//System.out.println(split + ", "+commaIndex + "result: " + result);
			split = commaIndex+1;
			tempString = inputString.substring(split,length);
			if(!tempString.contains(",")) {break;}
			commaIndex = split+ inputString.substring(split,length).indexOf(",");
			
			
			//System.out.println("TS: "+ tempString);
			//System.out.println(split + ", "+commaIndex);
			
		}
		
		//System.out.println("HERE");
		//System.out.println(split + ", "+commaIndex + "result: " + result);
		result += Integer.parseInt(inputString.substring(split,length));
		
		
		}
		

	return result;
	};
	

}
