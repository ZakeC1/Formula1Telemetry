package packet.data;

public enum Driver {
	
	GARAGE, FLYING_LAP, IN_LAP, OUT_LAP, ON_TRACK;
	
	public static Driver fromInt(int i) {
		
		switch(i) {
			case 0:
				return Driver.GARAGE;
			case 1:
				return Driver.FLYING_LAP;
			case 2:
				return Driver.IN_LAP;
			case 3:
				return Driver.OUT_LAP;
			default:
				return Driver.ON_TRACK;
				
		}
	}
}
