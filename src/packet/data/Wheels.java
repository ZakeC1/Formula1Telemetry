package packet.data;

public class Wheels<T> {
	private T rearLeft;
	private T rearRight;
	private T frontLeft;
	private T frontRight;
	
	public Wheels() {}
	
	public Wheels(T[] datapoints) {
		this.setRearLeft(datapoints[0]);
		this.setRearRight(datapoints[1]);
		this.setFrontLeft(datapoints[2]);
		this.setFrontRight(datapoints[3]);
	}
	
	public Wheels(T rl, T rr, T fl, T fr) {
		this.setRearLeft(rl);
		this.setRearRight(rr);
		this.setFrontLeft(fl);
		this.setFrontRight(fr);
	}
	
	public T getRearLeft() {
		return rearLeft;
	}
	public void setRearLeft(T rearLeft) {
		this.rearLeft = rearLeft;
	}
	public T getRearRight() {
		return rearRight;
	}
	public void setRearRight(T rearRight) {
		this.rearRight = rearRight;
	}
	public T getFrontLeft() {
		return frontLeft;
	}
	public void setFrontLeft(T frontLeft) {
		this.frontLeft = frontLeft;
	}
	public T getFrontRight() {
		return frontRight;
	}
	public void setFrontRight(T frontRight) {
		this.frontRight = frontRight;
	}
}
