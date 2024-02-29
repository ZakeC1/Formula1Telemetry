package packet.data;

public class Participants {
	private boolean ai;
	private int driverID;
	private int teamID;
	private int raceNum;
	private int nationality;
	private String name;
	
	public Participants() {}
	
	public boolean isAiControlled() {
		return ai;
	}
	public void setAiControlled(boolean ai) {
		this.ai = ai;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driver) {
		this.driverID = driver;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int team) {
		this.teamID = team;
	}
	public int getRaceNum() {
		return raceNum;
	}
	public void setRaceNum(int num) {
		this.raceNum = num;
	}
	public int getNationality() {
		return nationality;
	}
	public void setNationality(int nationality) {
		this.nationality = nationality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
