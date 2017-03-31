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
			throw new DateException("Negative days are not allowed - wrong value for day: " + day + "\n");			
		} else {
			if ( !this.isDayRight(day) ){
				throw new DateException("Day/Month wrong combination: " + day + "/" + this.month + "\n");
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
			throw new DateException("Negative months are not allowed - wrong value for month: " + month + "\n");			
		} else {
			if ( month > 12 ){
				throw new DateException("There are only 12 months - wrong value for month: " + month + "\n");
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
			throw new DateException("Negative years are not allowed - wrong value for year: " + year + "\n");			
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
			System.err.println("Date.tomorrow: " + e.getMessage());
		}

		return tomorrow;
	}
}