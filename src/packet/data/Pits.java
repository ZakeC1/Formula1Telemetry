package packet.data;

public enum Pits {
	
	PITTING, IN_PITS, NOT_PITTING;
	
	public static Pits fromInt(int i) {
		
		switch(i) {
		case 1: 
			return Pits.PITTING;
		case 2: 
			return Pits.IN_PITS;
		default: 
			return Pits.NOT_PITTING;	
		}
	}
}
