package packet.data;

import java.util.List;


public class SessionPacket extends Packet{
	private Weather weather;
	private int trackTemp;
	private int airTemp;
	private int totalLaps;
	private int trackLength;
	private Session session;
	private int trackID;
	private Era era;
	private int sessionTimeLeft;
	private int sessionDuration;
	private int pitSpeedLimit;
	private boolean gamePaused;
	private boolean spectating;
	private int spectatorCar;
	private boolean sliProNativeSupport;
	private int numMarshalZones;
	private List<MarshalZones> marshalZones;
	private SafetyCar safetyCar;
	private boolean networkGame;
	
	public SessionPacket() {
		
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public int getTrackTemp() {
		return trackTemp;
	}

	public void setTrackTemp(int trackTemp) {
		this.trackTemp = trackTemp;
	}

	public int getAirTemp() {
		return airTemp;
	}

	public void setAirTemp(int airTemp) {
		this.airTemp = airTemp;
	}

	public int getTotalLaps() {
		return totalLaps;
	}

	public void setTotalLaps(int totalLaps) {
		this.totalLaps = totalLaps;
	}

	public int getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(int trackLength) {
		this.trackLength = trackLength;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getTrackID() {
		return trackID;
	}

	public void setTrackID(int trackID) {
		this.trackID = trackID;
	}

	public Era getEra() {
		return era;
	}

	public void setEra(Era era) {
		this.era = era;
	}

	public int getSessionTimeLeft() {
		return sessionTimeLeft;
	}

	public void setSessionTimeLeft(int sessionTimeLeft) {
		this.sessionTimeLeft = sessionTimeLeft;
	}

	public int getSessionDuration() {
		return sessionDuration;
	}

	public void setSessionDuration(int sessionDuration) {
		this.sessionDuration = sessionDuration;
	}

	public int getPitSpeedLimit() {
		return pitSpeedLimit;
	}

	public void setPitSpeedLimit(int pitSpeedLimit) {
		this.pitSpeedLimit = pitSpeedLimit;
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public boolean isSpectating() {
		return spectating;
	}

	public void setSpectating(boolean spectating) {
		this.spectating = spectating;
	}

	public int getSpectatorCar() {
		return spectatorCar;
	}

	public void setSpectatorCar(int spectatorCar) {
		this.spectatorCar = spectatorCar;
	}

	public boolean isSliProNativeSupport() {
		return sliProNativeSupport;
	}

	public void setSliProNativeSupport(boolean sliProNativeSupport) {
		this.sliProNativeSupport = sliProNativeSupport;
	}

	public int getNumMarshalZones() {
		return numMarshalZones;
	}

	public void setNumMarshalZones(int numMarshalZones) {
		this.numMarshalZones = numMarshalZones;
	}

	public List<MarshalZones> getMarshalZones() {
		return marshalZones;
	}

	public void setMarshalZones(List<MarshalZones> marshalZones) {
		this.marshalZones = marshalZones;
	}

	public SafetyCar getSafetyCar() {
		return safetyCar;
	}

	public void setSafetyCar(SafetyCar safetyCar) {
		this.safetyCar = safetyCar;
	}

	public boolean isNetworkGame() {
		return networkGame;
	}

	public void setNetworkGame(boolean networkGame) {
		this.networkGame = networkGame;
	}
}
