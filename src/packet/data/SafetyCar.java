package packet.data;

public enum SafetyCar {
	NO_SC,
	FULL_SC,
	VIRTUAL_SC;
	
	public static SafetyCar fromInt(int i) {
		switch (i) {
		case 0:
			return SafetyCar.NO_SC;
		case 1:
			return SafetyCar.FULL_SC;
		case 2:
			return SafetyCar.VIRTUAL_SC;
		default:
			return null;
		}
	}
}
