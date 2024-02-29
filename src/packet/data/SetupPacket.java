package packet.data;

import java.util.List;

public class SetupPacket extends Packet {
	private List<CarSetup> carSetups;
	
	public SetupPacket() {
		
	}
	
	public List<CarSetup> getCarSetups() {
		return carSetups;
	}
	public void setCarSetups(List<CarSetup> carSetups) {
		this.carSetups = carSetups;
	}
}
