import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = getAgeFromBirthday("19851230");
		System.out.println("@@@@@==="+result);
		
	}

	public static int getAgeFromBirthday(String birthday) {
		Calendar cal= Calendar.getInstance ();

        int year = Integer.parseInt(birthday.substring(0, 4)); 
        int month = Integer.parseInt(birthday.substring(4, 6));
        int day = Integer.parseInt(birthday.substring(6, 8));

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, day);

        Calendar now = Calendar.getInstance ();

        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (  	(cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
        		|| (    cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) 
        		&& cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)   )   
        	){
        		age--;
        	}
    return age;
}	
	
	
}
