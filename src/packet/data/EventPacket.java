package packet.data;

public class EventPacket extends Packet{
	private String eventCode;

	public EventPacket() {}
	
	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
}
