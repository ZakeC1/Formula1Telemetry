package f1tel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import packet.data.*;

public class F1UDPclient {

	private static final int PORT = 20777;
	private static final int MAX_PACKET = 1341;
	private static final String ADDRESS = "0.0.0.0";
	private Consumer<Packet> packetC;
	
	public static Packet packet;
	public static MotionPacket motionPack;
	public static SessionPacket sessPack;
	public static LapPacket lapPack;
	public static EventPacket eventPack;
	public static ParticipantsPacket partPack;
	public static SetupPacket setupPack;
	public static TelemetryPacket telPack;
	public static StatusPacket statusPack;
	
	private String address;
	private int port;
	private static F1Dashboard dash = new F1Dashboard();
	
	private F1UDPclient() {
		address = ADDRESS;
		port = PORT;
	}
	
	public static F1UDPclient create() {
		return new F1UDPclient();
	}
	
	public F1UDPclient bindAddress(String address) {
		this.address = address;
		return this;
	}
	
	public F1UDPclient bindPort(int port) {
		this.port = port;
		return this;
	}
	
	public F1UDPclient pack(Consumer<Packet> packet) {
		packetC = packet;
		return this;
	}
	
	public void start() throws IOException {
		if (packetC == null) {
			throw new IllegalStateException("Packet Error");
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		try (DatagramChannel channel = DatagramChannel.open()) {
			channel.socket().bind(new InetSocketAddress(address, port));
			ByteBuffer buf = ByteBuffer.allocate(MAX_PACKET);
			buf.order(ByteOrder.LITTLE_ENDIAN);
			while (true) {
				channel.receive(buf);
				packet = PackDecoder.read(buf.array());
				//TypeCasting
				switch (packet.getHeader().getPackID()) {
				case 0:
					motionPack = (MotionPacket) packet;
					break;
				case 1:
					sessPack = (SessionPacket) packet;
					break;
				case 2:
					lapPack = (LapPacket) packet;
					break;
				case 3:
					eventPack = (EventPacket) packet;
					break;
				case 4:
					partPack = (ParticipantsPacket) packet;
					break;
				case 5:
					setupPack = (SetupPacket) packet;
					break;
				case 6:
					telPack = (TelemetryPacket) packet;
					break;
				case 7:
					statusPack = (StatusPacket) packet;
					break;
				}
				executor.submit(() -> {
					packetC.accept(packet);
				});
				buf.clear();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Thread thread = new Thread() {
			public void run() {
				dash.main(args);
				System.exit(0);
			};
		};
		thread.start();
		F1UDPclient client = F1UDPclient.create();
		client.bindAddress("0.0.0.0");
		client.bindPort(20777);
		client.pack((p) -> {});
		client.start();
	}
}
