package packet.data;

public class MarshalZones {
	
	private float zoneStart;
	private Flags flags;
	
	public float getZoneStart() {
		return zoneStart;
	}
	
	public void setZoneStart(float start) {
		this.zoneStart = start;
	}
	
	public Flags getFlag() {
		return flags;
	}
	
	public void setFlag(Flags flag) {
		this.flags = flag;
	}
}
