package packet.data;

public enum Results {
	
	NA, INACTIVE, ACTIVE, FINISHED, DQ, NOT_CLASSIFIED, RETIRED;
	
	public static Results fromInt(int i) {
		
		switch(i) {
			case 0:
				return Results.NA;
			case 1:
				return Results.INACTIVE;
			case 2:
				return Results.ACTIVE;
			case 3:
				return Results.FINISHED;
			case 4:
				return Results.DQ;
			case 5:
				return Results.NOT_CLASSIFIED;
			case 6:
				return Results.RETIRED;
			default:
				return null;
		}
	}
}
