package packet.data;

import java.util.List;

public class TelemetryPacket extends Packet {
	private List<Telemetry> telemetry;
	private Buttons buttons;
	
	public TelemetryPacket() {
		
	}
	
	public List<Telemetry> getCarTelemetryData() {
		return telemetry;
	}
	public void setCarTelemetryData(List<Telemetry> telemetry) {
		this.telemetry = telemetry;
	}

	public Buttons getButton() {
		return buttons;
	}

	public void setButton(Buttons button) {
		this.buttons = button;
	}
}
