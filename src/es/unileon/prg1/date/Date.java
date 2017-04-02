package es.unileon.prg1.date;

public class Date {

	private int day;
	private int month;
	private int year;
	
	public Date(){
		this.day = 1;
		this.month = 1;
		this.year = 2016;
	}
	
	public Date(int day, int month, int year) throws DateException{
		StringBuffer message = new StringBuffer();

		if ( day <= 0){
			message.append("Valor incorrecto. Los dias no pueden tener valor negativo.");
		}
		if ( month <= 0 ){
			message.append("Valor incorrecto. Los meses no pueden tener valor negativo.");			
		} else {
			if ( month > 12 ){
				message.append("Valor incorrecto. No puede haber mas de 12 meses.");							
			} else {
				if ( day > this.daysOfMonth(month) ){ //METODO AUN POR IMPLEMENTAR
					message.append("La combinacion de mes y dia no es correcta.");						
				}				
			}
		}
		if ( year < 0 ){
			message.append("Valor incorrecto. Los años no pueden ser negativos.");			
		}
		
		if ( message.length() != 0){
			throw new DateException(message.toString());
		} else {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}
	
	private int daysOfMonth(int month){
		int number = 0;
		switch (month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8: 
		case 10: 
		case 12:
			number = 31;
			break;
		case 4: 
		case 6: 
		case 9: 
		case 11: 
			number = 30;
			break;
		case 2:
			number = 28;
			break;
		default:
			number = -1;
		}
		return number;
	}
	
	private boolean isDayRight(int day){
		return ( ( day > 0 ) && (day <= this.daysOfMonth(this.month) ) );
	}
	
	//CLONO EL CONSTRUCTOR
	
	public Date(Date another){
		this.day = another.getDay();
		this.month = another.getMonth();
		this.year = another.getYear();
	}
	
	public int getDay() {
		return this.day;
	}

	public void setDay(int day) throws DateException{
		if ( day <= 0) {
			throw new DateException("Valor incorrecto. Los dias no pueden tener valor negativo.");			
		} else {
			if ( !this.isDayRight(day) ){
				throw new DateException("La combinacion de mes y dia no es correcta.");
			} else {
				this.day = day;
			}
		}
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) throws DateException{
		if ( month <= 0) {
			throw new DateException("Valor incorrecto. Los meses no pueden tener valor negativo.");			
		} else {
			if ( month > 12 ){
				throw new DateException("Valor incorrecto. No puede haber mas de 12 meses.");
			} else {
				this.month = month;
			}
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) throws DateException{
		if ( year < 0) {
			throw new DateException("Valor incorrecto. Los años no pueden tener valor negativo.");			
		} else {
			this.year = year;
		}
	}
	
	public Date tomorrow(){
		Date tomorrow = null;
		int d, m, y;
				
		d = this.day;
		m = this.month;
		y = this.year;
		
		d++;
		if ( d > this.daysOfMonth(month) ) {
			d = 1;
			m++;
			if ( m > 12 ) {
				m = 1;
				y++;
			}	
		}
		
		try{
			tomorrow = new Date(d, m, y);
		} catch (DateException e){
			System.err.println(e.getMessage());
		}

		return tomorrow;
	}
	public boolean isSameDay(Date other){
		if ( this.day == other.getDay() )
			return true;
		else
			return false;
	}
	
	public boolean isSameMonth(Date other){
		if ( this.month == other.getMonth() ) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSameYear(Date other){
		return this.year == other.getYear();
	}

	public boolean isSame (Date other){
		return this.isSameDay(other) && this.isSameMonth(other) && this.isSameYear(other);
	}

	public String getMonthName(){
		String name = null;
		switch (this.month){
		case 1:
			name = "January";
			break;
		case 2:
			name = "February";
			break;
		case 3:
			name = "March";
			break;
		case 4:
			name = "April";
			break;
		case 5:
			name = "May";
			break;
		case 6:
			name = "June";
			break;
		case 7:
			name = "July";
			break;
		case 8:
			name = "August";
			break;
		case 9:
			name = "September";
			break;
		case 10:
			name = "October";
			break;
		case 11:
			name = "November";
			break;
		case 12:
			name = "December";
			break;
		}
		return name;
	}
	
	public int daysOfMonth(){
		return this.daysOfMonth(this.month);
	}
	
	public String getSeasonName(){
		String name = null;
		switch (this.month){
		case 1: 
		case 2: 
		case 3: 
			name = "Winter";
			break;
		case 4: 
		case 5: 
		case 6:
			name = "Spring";
			break;
		case 7: 
		case 8: 
		case 9:
			name = "Summer";
			break;
		case 10:
		case 11: 
		case 12:
			name = "Autumn";
			break;
		}
		
		return name;
	}
	
	public String getMonthsLeft(){
		Date aux = new Date(this);
		StringBuffer monthsLeft = new StringBuffer();
		
		try{
			for (int i = this.month + 1; i <= 12; i++){
				aux.setMonth(i);
				monthsLeft.append(aux.getMonthName() + " ");
			}
		} catch (DateException e){
			System.err.println(e.getMessage());
		}
		return monthsLeft.toString();
	}
	
	public String getDaysLeftOfMonth(){
		Date aux = tomorrow();
		StringBuffer daysLeft = new StringBuffer();
		
		try{
			for (int i = aux.getDay(); isDayRight(i); i++) {
				aux.setDay(i);
				daysLeft.append(aux.toString() + " ");
			}
		} catch (DateException e){
			System.err.println(e.getMessage());
		}
		return daysLeft.toString();
	}
	
	public String getMonthsSameDays(){
		Date aux = new Date(this);
		StringBuffer months = new StringBuffer();

		try{
			for ( int i = 1; i <= 12; i++) {
				aux.setMonth(i);
				if ( aux.daysOfMonth() == this.daysOfMonth() ) {
					months.append(aux.getMonthName() + " ");
				}
			}
		} catch (DateException e){
			System.err.println(e.getMessage());
		}
		return months.toString();
	}

	public int daysPast(){
		int result;
		result = 0;
		
		try{
			Date aux = new Date(1,1,this.year);
		
			for ( int i = 1; i < this.month; i++ ) {
				result += aux.daysOfMonth();
				aux.setMonth(i + 1);
			}
		} catch (DateException e){
			System.err.println(e.getMessage());
		}
		
		return result + this.day - 1;
	}
	
	public int numRandomTriesEqualDate(){
        int tries, d, m, y;
        tries = 0;
        
        try{
        	do{
        		m = (int) (Math.random()*12) + 1;
        		d = (int) (Math.random()*this.daysOfMonth(m) ) + 1;
        		y = this.year;
        		tries++;
        	} while ( !this.isSame(new Date(d,m,y) ) );
		} catch (DateException e){
			System.err.println(e.getMessage());
		}

        return tries;
    }
	
	private String nameOfDay(int day) {
		String dayName;
		switch (day) {
		case 1: 
			dayName = "Monday";
			break;
		case 2: 
			dayName = "Tuesday";
			break;
		case 3: 
			dayName = "Wednesday";
			break;
		case 4: 
			dayName = "Thursday";
			break;
		case 5: 
			dayName = "Friday";
			break;
		case 6: 
			dayName = "Saturday";
			break;
		case 7: 
			dayName = "Sunday";
			break;
		default:
			dayName = "Wrong day";
		}
		return dayName;
	}
	
	public String dayOfWeek(int firstOfJanuary){
		int dayNumber;
		
		dayNumber = ( daysPast() % 7 + firstOfJanuary ) % 7;
		
		return nameOfDay(dayNumber);
	}
	
	public String toString(){
		return this.day + "/" + this.month + "/" + this.year;
	}
}