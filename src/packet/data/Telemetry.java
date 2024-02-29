package packet.data;

public class Telemetry {
	private int speed;
	private int throttle;
	private int steer;
	private int brake;
	private int clutch;
	private int gear;
	private int engineRpm;
	private boolean drs;
	private int revLightsPercent;
	private Wheels<Integer> brakeTemperature;
	private Wheels<Integer> tireSurfaceTemperature;
	private Wheels<Integer> tireInnerTemperature;
	private int engineTemperature;
	private Wheels<Float> tirePressure;
	
	public Telemetry() {
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getThrottle() {
		return throttle;
	}

	public void setThrottle(int throttle) {
		this.throttle = throttle;
	}

	public int getSteer() {
		return steer;
	}

	public void setSteer(int steer) {
		this.steer = steer;
	}

	public int getBrake() {
		return brake;
	}

	public void setBrake(int brake) {
		this.brake = brake;
	}

	public int getClutch() {
		return clutch;
	}

	public void setClutch(int clutch) {
		this.clutch = clutch;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}

	public int getEngineRpm() {
		return engineRpm;
	}

	public void setEngineRpm(int engineRpm) {
		this.engineRpm = engineRpm;
	}

	public boolean isDrs() {
		return drs;
	}

	public void setDrs(boolean drs) {
		this.drs = drs;
	}

	public int getRevLightsPercent() {
		return revLightsPercent;
	}

	public void setRevLightsPercent(int revLightsPercent) {
		this.revLightsPercent = revLightsPercent;
	}

	public Wheels<Integer> getBrakeTemperature() {
		return brakeTemperature;
	}

	public void setBrakeTemperature(Wheels<Integer> brakeTemperature) {
		this.brakeTemperature = brakeTemperature;
	}

	public Wheels<Integer> getTireSurfaceTemperature() {
		return tireSurfaceTemperature;
	}

	public void setTireSurfaceTemperature(Wheels<Integer> tireSurfaceTemperature) {
		this.tireSurfaceTemperature = tireSurfaceTemperature;
	}

	public Wheels<Integer> getTireInnerTemperature() {
		return tireInnerTemperature;
	}

	public void setTireInnerTemperature(Wheels<Integer> tireInnerTemperature) {
		this.tireInnerTemperature = tireInnerTemperature;
	}

	public int getEngineTemperature() {
		return engineTemperature;
	}

	public void setEngineTemperature(int engineTemperature) {
		this.engineTemperature = engineTemperature;
	}

	public Wheels<Float> getTirePressure() {
		return tirePressure;
	}

	public void setTirePressure(Wheels<Float> tirePressure) {
		this.tirePressure = tirePressure;
	}
}
