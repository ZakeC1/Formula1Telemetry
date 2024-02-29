package packet.data;

import java.util.List;

public class ParticipantsPacket extends Packet {
	private int numCars;
	private List<Participants> participants;
	
	public ParticipantsPacket() {
		
	}
	
	public int getNumCars() {
		return numCars;
	}
	public void setNumCars(int numCars) {
		this.numCars = numCars;
	}
	public List<Participants> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participants> participants) {
		this.participants = participants;
	}
}
