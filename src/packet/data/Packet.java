package packet.data;

import f1tel.Header;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Packet {
	
	private Header header;
	
	public Header getHeader() {
		return header;
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(this);
		}catch(Exception e) {
			
		}
		return json.replace("\\u0000", "");
	}
	
}
