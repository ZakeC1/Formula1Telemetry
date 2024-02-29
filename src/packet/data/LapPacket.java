package packet.data;

//import packet.data.Laps;
import java.util.List;

public class LapPacket extends Packet{
	
	private List<Laps> dataList;
	
	public LapPacket() {
		
	}
	
	public List<Laps> getDataList() {
		return dataList;
	}
	
	public void setDataList(List<Laps> dataList) {
		this.dataList = dataList;
	}
}
