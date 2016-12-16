
public class Test4 {

	
	static void process(String inputString){

		 char[] inputSymbol=inputString.toCharArray();
				
		
		
		/*
		for(int i=0; i < inputStr.length(); i++){			
			char tmp = inputStr.charAt(i);
			for(int j=0; i < inputStr.length(); j++){
				if(tmp == inputStr.charAt(j))
			}
		}
		*/
		/*
		int open1 = inputStr.indexOf("(");
		int close1 = inputStr.indexOf(")");		
		int open2 = inputStr.indexOf("[");
		int close2 = inputStr.indexOf("]");		
		int open3 = inputStr.indexOf("{");
		int close3 = inputStr.indexOf("}");
		System.out.println("@@==="+open1);
		System.out.println("@@==="+open2);
		System.out.println("@@==="+open3);
		System.out.println("@@==="+close1);
		System.out.println("@@==="+close2);
		System.out.println("@@==="+close3);
		if(open1 == close1 && open2 == close2 && open3 == close3){
			System.out.println("ok");
		}else{
			System.out.println("not ok");
		}
		*/
		/*
		int open1 = 0, close1 = 0, open2 = 0, close2 = 0, open3 = 0, close3 = 0;		
		for(int i=0; i< inputStr.length(); i++){
			if(inputStr.charAt(i) == '('){
				open1++;
			}else if(inputStr.charAt(i) == ')'){
				close1++;
			}else if(inputStr.charAt(i) == '['){
				open2++;
			}else if(inputStr.charAt(i) == ']'){
				close2++;
			}else if(inputStr.charAt(i) == '{'){
				open3++;
			}else if(inputStr.charAt(i) == '}'){
				close3++;
			}			
		}
		
		if(open1 == close1 && open2 == close2 && open3 == close3){
			System.out.println("ok");
		}else{
			System.out.println("not ok");
		}
*/
	}
	public static void main(String[] args) {
		String inputStr = "[((test)])";
		process(inputStr);

	}

}
