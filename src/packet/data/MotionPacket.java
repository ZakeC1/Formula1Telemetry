package packet.data;

import java.util.List;

public class MotionPacket extends Packet {
	
	private List<CarMotions> carMotionsList;
	private Wheels<Float> suspensionPos;
	private Wheels<Float> suspensionVel;
	private Wheels<Float> suspensionAcc;
	private Wheels<Float> wheelSpeed;
	private Wheels<Float> wheelSlip;
	private float localVelX;
	private float localVelY;
	private float localVelZ;
	private float angularVelX;
	private float angularVelY;
	private float angularVelZ;
	private float angularAccX;
	private float angularAccY;
	private float angularAccZ;
	private float frontWheelsAngle;

	public MotionPacket() {
		
	}

	public List<CarMotions> getCarMotionDataList() {
		return carMotionsList;
	}

	public void setCarMotionsList(List<CarMotions> carMotionsList) {
		this.carMotionsList = carMotionsList;
	}

	public Wheels<Float> getSuspensionPos() {
		return suspensionPos;
	}

	public void setSuspensionPos(Wheels<Float> suspensionPos) {
		this.suspensionPos = suspensionPos;
	}

	public Wheels<Float> getSuspensionVel() {
		return suspensionVel;
	}

	public void setSuspensionVel(Wheels<Float> suspensionVel) {
		this.suspensionVel = suspensionVel;
	}

	public Wheels<Float> getSuspensionAcc() {
		return suspensionAcc;
	}

	public void setSuspensionAcc(Wheels<Float> suspensionAcceleration) {
		this.suspensionAcc = suspensionAcceleration;
	}

	public Wheels<Float> getWheelSpeed() {
		return wheelSpeed;
	}

	public void setWheelSpeed(Wheels<Float> wheelSpeed) {
		this.wheelSpeed = wheelSpeed;
	}

	public Wheels<Float> getWheelSlip() {
		return wheelSlip;
	}

	public void setWheelSlip(Wheels<Float> wheelSlip) {
		this.wheelSlip = wheelSlip;
	}

	public float getLocalVelX() {
		return localVelX;
	}

	public void setLocalVelX(float localVelX) {
		this.localVelX = localVelX;
	}

	public float getLocalVelY() {
		return localVelY;
	}

	public void setLocalVelY(float localVelY) {
		this.localVelY = localVelY;
	}

	public float getLocalVelZ() {
		return localVelZ;
	}

	public void setLocalVelZ(float localVelZ) {
		this.localVelZ = localVelZ;
	}

	public float getAngularVelX() {
		return angularVelX;
	}

	public void setAngularVelX(float angularVelX) {
		this.angularVelX = angularVelX;
	}

	public float getAngularVelY() {
		return angularVelY;
	}

	public void setAngularVelY(float angularVelY) {
		this.angularVelY = angularVelY;
	}

	public float getAngularVelZ() {
		return angularVelZ;
	}

	public void setAngularVelZ(float angularVelZ) {
		this.angularVelZ = angularVelZ;
	}

	public float getAngularAccX() {
		return angularAccX;
	}

	public void setAngularAccX(float angularAccX) {
		this.angularAccX = angularAccX;
	}

	public float getAngularAccY() {
		return angularAccY;
	}

	public void setAngularAccY(float angularAccY) {
		this.angularAccY = angularAccY;
	}

	public float getAngularAccZ() {
		return angularAccZ;
	}

	public void setAngularAccZ(float angularAccZ) {
		this.angularAccZ = angularAccZ;
	}

	public float getFrontWheelsAngle() {
		return frontWheelsAngle;
	}

	public void setFrontWheelsAngle(float frontWheelsAngle) {
		this.frontWheelsAngle = frontWheelsAngle;
	}
}
