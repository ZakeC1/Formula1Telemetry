package f1tel;

import java.math.BigInteger;
public class Header {
	
	private int packFormat;
	private long packVersion;
	private int packID;
	private BigInteger sessionUID;
	private float sessionTime;
	private long frameID;
	private int carIndex;
	
	public Header() {}

	public int getPackFormat() {
		return packFormat;
	}
	
	public void setPackFormat(int packFormat) {
		this.packFormat = packFormat;
	}
	
	public long getPackVersion() {
		return packVersion;
	}
	
	public void setPackVersion(long packVersion) {
		this.packVersion = packVersion;
	}
	
	public int getPackID() {
		return packID;
	}
	
	public void setPackID(int packID) {
		this.packID = packID;
	}
	
	public BigInteger getSessionUID() {
		return sessionUID;
	}
	
	public void setSessionUID(BigInteger sessionUID) {
		this.sessionUID = sessionUID;
	}
	
	public float getSessionTime() {
		return sessionTime;
	}
	
	public void setSessionTime(float sessionTime) {
		this.sessionTime = sessionTime;
	}
	
	public long getFrameID() {
		return frameID;
	}
	
	public void setFrameID(long frameID) {
		this.frameID = frameID;
	}
	
	public int getCarIndex() {
		return carIndex;
	}
	
	public void setCarIndex(int carIndex) {
		this.carIndex = carIndex;
	}
	
	@Override
	public String toString() {
		return "Header :: packFormat:" + this.getPackFormat() + 
		", version:" + this.getPackVersion() +
		", packetId:" + this.getPackID() + 
		", sessionUID:" + this.getSessionUID() + 
		", sessionTime:" + this.getSessionTime() +
		", frameIdentifier:" + this.getFrameID() +
		", playerCarIndex:" + this.getCarIndex();
	}
}
