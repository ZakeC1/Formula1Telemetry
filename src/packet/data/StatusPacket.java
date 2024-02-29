package packet.data;

import java.util.List;

public class StatusPacket extends Packet{
	private List<CarStatus> carStatuses;

	public StatusPacket() {
		
	}

	public List<CarStatus> getCarStatuses() {
		return carStatuses;
	}

	public void setCarStatuses(List<CarStatus> carStatuses) {
		this.carStatuses = carStatuses;
	}
}
