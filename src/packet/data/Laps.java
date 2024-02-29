package packet.data;

public class Laps {
	private float car;
	private boolean playersCar;
	private float lastLapTime;
	private float currentLapTime;
	private float bestLaptTime;
	private float sect1Time;
	private float sect2Time;
	private float distance;
	private float totalDistance;
	private float safetyCar;
	private int position;
	private int currentLap;
	private Pits pit;
	private int sector;
	private boolean isCurrentLapInvalid;
	private int penalties;
	private int gridSpot;
	private Driver driver;
	private Results results;
	
	public Laps() {}

	public float getCar() {
		return car;
	}
	public void setCar(float car) {
		this.car = car;
	}
	public boolean isPlayersCar() {
		return playersCar;
	}
	public void setPlayersCar(boolean playersCar) {
		this.playersCar = playersCar;
	}
	public float getLastLapTime() {
		return lastLapTime;
	}
	public void setLastLapTime(float lastLapTime) {
		this.lastLapTime = lastLapTime;
	}
	public float getCurrentLapTime() {
		return currentLapTime;
	}
	public void setCurrentLapTime(float currentLapTime) {
		this.currentLapTime = currentLapTime;
	}
	public float getBestLaptTime() {
		return bestLaptTime;
	}
	public void setBestLaptTime(float bestLaptTime) {
		this.bestLaptTime = bestLaptTime;
	}
	public float getSect1Time() {
		return sect1Time;
	}
	public void setSect1Time(float sect1) {
		this.sect1Time = sect1;
	}
	public float getSect2Time() {
		return sect2Time;
	}
	public void setSect2Time(float sect2) {
		this.sect2Time = sect2;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public float getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(float totalDistance) {
		this.totalDistance = totalDistance;
	}
	public float getSafetyCar() {
		return safetyCar;
	}
	public void setSafetyCar(float safetyCar) {
		this.safetyCar = safetyCar;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getCurrentLap() {
		return currentLap;
	}
	public void setCurrentLap(int currentLap) {
		this.currentLap = currentLap;
	}
	public Pits getPit() {
		return pit;
	}
	public void setPit(Pits pit) {
		this.pit = pit;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	public boolean isCurrentLapInvalid() {
		return isCurrentLapInvalid;
	}
	public void setIsCurrentLapInvalid(boolean isCurrentLapInvalid) {
		this.isCurrentLapInvalid = isCurrentLapInvalid;
	}
	public int getPenalties() {
		return penalties;
	}
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	public int getGridSpot() {
		return gridSpot;
	}
	public void setGridSpot(int gridSpot) {
		this.gridSpot = gridSpot;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Results getResults() {
		return results;
	}
	public void setResults(Results results) {
		this.results = results;
	}
}
