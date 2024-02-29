package packet.data;

public enum Session {
	UNKNOWN, P1, P2, P3, SHORT_P, Q1, Q2, Q3, SHORT_Q, OSQ, R, R2, TIME_TRIAL;

	public static Session fromInt(int i) {
		switch(i) {
			case 0:
				return Session.UNKNOWN;
			case 1:
				return Session.P1;
			case 2:
				return Session.P2;
			case 3:
				return Session.P3;
			case 4:
				return Session.SHORT_P;
			case 5:
				return Session.Q1;
			case 6:
				return Session.Q2;
			case 7:
				return Session.Q3;
			case 8:
				return Session.SHORT_Q;
			case 9:
				return Session.OSQ;
			case 10:
				return Session.R;
			case 11:
				return Session.R2;
			case 12:
				return Session.TIME_TRIAL;
			default:
				return null;
		}
	}
}
