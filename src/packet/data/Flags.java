package packet.data;

public enum Flags {
	NONE, GREEN, BLUE, YELLOW, RED, UNKNOWN;
	
	public static Flags fromInt(int i) {
		switch(i) {
			case 0:
				return Flags.NONE;
			case 1:
				return Flags.GREEN;
			case 2:
				return Flags.BLUE;
			case 3:
				return Flags.YELLOW;
			case 4:
				return Flags.RED;
			default:
				return Flags.UNKNOWN;
		}
	}
}
