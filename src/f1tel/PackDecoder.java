package f1tel;

import java.util.ArrayList;
import java.util.List;
import packet.data.*;

public class PackDecoder {
	
	public static final int TOTAL_CARS = 20;
	public static final int MAX_ZONES = 21;
	
	private PackBuffer buffer;
	
	private PackDecoder(byte[] data) {
		buffer = PackBuffer.wrap(data);
	}
	
	public static Packet read(byte[] data) {
		return new PackDecoder(data).buildPack();
	}
	
	private Packet buildPack() {
		
		Header header = buildHeader();

		switch (header.getPackID()) {
		case 0:
			return createMotionPack(header);
		case 1:
			return createSessionPacket(header);
		case 2:
			return createLapPacket(header);
		case 3:
			return createEventPacket(header);
		case 4:
			return createParticipantsPacket(header);
		case 5:
			return createCarSetupPacket(header);
		case 6:
			return createTelemetryPacket(header);
		case 7:
			return createCarStatusPacket(header);
		}

		return null;
	}
	
	private Packet getPack() {
		
		Header header = buildHeader();

		switch (header.getPackID()) {
		case 0:
			return createMotionPack(header);
		case 1:
			return createSessionPacket(header);
		case 2:
			return createLapPacket(header);
		case 3:
			return createEventPacket(header);
		case 4:
			return createParticipantsPacket(header);
		case 5:
			return createCarSetupPacket(header);
		case 6:
			return createTelemetryPacket(header);
		case 7:
			return createCarStatusPacket(header);
		}

		return null;
	}
	
	private Header buildHeader() {

		Header header = new Header();

		header.setPackFormat(buffer.getNextUInt16AsInt());
		header.setPackVersion(buffer.getNextUInt8AsInt());
		header.setPackID(buffer.getNextUInt8AsInt());
		header.setSessionUID(buffer.getNextUInt64AsBigInteger());
		header.setSessionTime(buffer.getNextFloat());
		header.setFrameID(buffer.getNextUIntAsLong());
		header.setCarIndex(buffer.getNextUInt8AsInt());
		return header;
	}
	
	private LapPacket createLapPacket(Header header) {

		LapPacket data = new LapPacket();
		List<Laps> dataList = new ArrayList<>();

		int i = 0;
		int players = header.getCarIndex();
		while (i < TOTAL_CARS) {
			dataList.add(createLap(i, i == players));
			i++;
		}

		data.setHeader(header);
		data.setDataList(dataList);
		return data;
	}
	
	private Laps createLap(int car, boolean player) {

		Laps lapsData = new Laps();

		lapsData.setCar(car);
		lapsData.setPlayersCar(player);
		lapsData.setLastLapTime(buffer.getNextFloat());
		lapsData.setCurrentLapTime(buffer.getNextFloat());
		lapsData.setBestLaptTime(buffer.getNextFloat());
		lapsData.setSect1Time(buffer.getNextFloat());
		lapsData.setSect2Time(buffer.getNextFloat());
		lapsData.setDistance(buffer.getNextFloat());
		lapsData.setTotalDistance(buffer.getNextFloat());
		lapsData.setSafetyCar(buffer.getNextFloat());
		lapsData.setPosition(buffer.getNextUInt8AsInt());
		lapsData.setCurrentLap(buffer.getNextUInt8AsInt());
		lapsData.setPit(Pits.fromInt(buffer.getNextUInt8AsInt()));
		lapsData.setSector(buffer.getNextUInt8AsInt() + 1);
		lapsData.setIsCurrentLapInvalid(buffer.getNextUInt8AsInt() == 1);
		lapsData.setPenalties(buffer.getNextUInt8AsInt());
		lapsData.setGridSpot(buffer.getNextUInt8AsInt());
		lapsData.setDriver(Driver.fromInt(buffer.getNextUInt8AsInt()));
		lapsData.setResults(Results.fromInt(buffer.getNextUInt8AsInt()));

		return lapsData;
	}
	
	private MotionPacket createMotionPack(Header header) {

		MotionPacket motionPacket = new MotionPacket();

		motionPacket.setHeader(header);
		List<CarMotions> carMotionsList = new ArrayList<>();
		int carIndex = 0;
		int playersCarIndex = motionPacket.getHeader().getCarIndex();
		while (carIndex < TOTAL_CARS) {
			carMotionsList.add(createCarMotions(carIndex, carIndex == playersCarIndex));
			carIndex++;
		}
		motionPacket.setCarMotionsList(carMotionsList);
		motionPacket.setSuspensionPos(new Wheels<Float>(buffer.getNextFloatArray(4)));
		motionPacket.setSuspensionVel(new Wheels<Float>(buffer.getNextFloatArray(4)));
		motionPacket.setSuspensionAcc(new Wheels<Float>(buffer.getNextFloatArray(4)));
		motionPacket.setWheelSpeed(new Wheels<Float>(buffer.getNextFloatArray(4)));
		motionPacket.setWheelSlip(new Wheels<Float>(buffer.getNextFloatArray(4)));
		motionPacket.setLocalVelX(buffer.getNextFloat());
		motionPacket.setLocalVelY(buffer.getNextFloat());
		motionPacket.setLocalVelZ(buffer.getNextFloat());
		motionPacket.setAngularVelX(buffer.getNextFloat());
		motionPacket.setAngularVelY(buffer.getNextFloat());
		motionPacket.setAngularVelZ(buffer.getNextFloat());
		motionPacket.setAngularAccX(buffer.getNextFloat());
		motionPacket.setAngularAccY(buffer.getNextFloat());
		motionPacket.setAngularAccZ(buffer.getNextFloat());
		motionPacket.setFrontWheelsAngle(buffer.getNextFloat());

		return motionPacket;
	}
	
	private CarMotions createCarMotions(int car, boolean playersCar) {

		final float denormalizer = 32767.0f;

		CarMotions carMotions = new CarMotions();

		carMotions.setCar(car);
		carMotions.setPlayersCar(playersCar);
		carMotions.setWorldPosX(buffer.getNextFloat());
		carMotions.setWorldPosY(buffer.getNextFloat());
		carMotions.setWorldPosZ(buffer.getNextFloat());
		carMotions.setWorldVelX(buffer.getNextFloat());
		carMotions.setWorldVelY(buffer.getNextFloat());
		carMotions.setWorldVelZ(buffer.getNextFloat());
		carMotions.setWorldForwardDirX(buffer.getNextUInt16AsInt() / denormalizer);
		carMotions.setWorldForwardDirY(buffer.getNextUInt16AsInt() / denormalizer);
		carMotions.setWorldForwardDirZ(buffer.getNextUInt16AsInt() / denormalizer);
		carMotions.setWorldRightDirX(buffer.getNextUInt16AsInt() / denormalizer);
		carMotions.setWorldRightDirY(buffer.getNextUInt16AsInt() / denormalizer);
		carMotions.setWorldRightDirZ(buffer.getNextUInt16AsInt() / denormalizer);
		carMotions.setgForceLateral(buffer.getNextFloat());
		carMotions.setgForceLongitudinal(buffer.getNextFloat());
		carMotions.setgForceVertical(buffer.getNextFloat());
		carMotions.setYaw(buffer.getNextFloat());
		carMotions.setPitch(buffer.getNextFloat());
		carMotions.setRoll(buffer.getNextFloat());

		return carMotions;

	}

	private SessionPacket createSessionPacket(Header header) {

		SessionPacket sessionData = new SessionPacket();

		sessionData.setHeader(header);
		sessionData.setWeather(Weather.fromInt(buffer.getNextUInt8AsInt()));
		sessionData.setTrackTemp(buffer.getNextInt8AsInt());
		sessionData.setAirTemp(buffer.getNextInt8AsInt());
		sessionData.setTotalLaps(buffer.getNextUInt8AsInt());
		sessionData.setTrackLength(buffer.getNextUInt16AsInt());
		sessionData.setSession(Session.fromInt(buffer.getNextUInt8AsInt()));
		sessionData.setTrackID(buffer.getNextInt8AsInt());
		sessionData.setEra(Era.fromInt(buffer.getNextInt8AsInt()));
		sessionData.setSessionTimeLeft(buffer.getNextUInt16AsInt());
		sessionData.setSessionDuration(buffer.getNextUInt16AsInt());
		sessionData.setPitSpeedLimit(buffer.getNextUInt8AsInt());
		sessionData.setGamePaused(buffer.getNextUInt8AsBoolean());
		sessionData.setSpectating(buffer.getNextUInt8AsBoolean());
		sessionData.setSliProNativeSupport(buffer.getNextUInt8AsBoolean());
		sessionData.setNumMarshalZones(buffer.getNextInt8AsInt());
		sessionData.setMarshalZones(createMarshalZones());
		sessionData.setSafetyCar(SafetyCar.fromInt(buffer.getNextUInt8AsInt()));
		sessionData.setNetworkGame(buffer.getNextUInt8AsBoolean());

		return sessionData;
	}
	
	private List<MarshalZones> createMarshalZones() {
		List<MarshalZones> marshalZones = new ArrayList<>();
		for (int i = 0; i < MAX_ZONES; i++) {
			MarshalZones marshalZone = new MarshalZones();
			marshalZone.setZoneStart(buffer.getNextFloat());
			marshalZone.setFlag(Flags.fromInt(buffer.getNextInt8AsInt()));
			marshalZones.add(marshalZone);
		}
		return marshalZones;
	}
	
	private EventPacket createEventPacket(Header header) {
		EventPacket event = new EventPacket();
		event.setHeader(header);
		event.setEventCode(buffer.getNextCharArrayAsString(4));

		return event;
	}
	
	private ParticipantsPacket createParticipantsPacket(Header header) {

		ParticipantsPacket participantsPack = new ParticipantsPacket();
		participantsPack.setHeader(header);
		participantsPack.setNumCars(buffer.getNextUInt8AsInt());
		List<Participants> participants = new ArrayList<>();
		for (int i = 0; i < participantsPack.getNumCars(); i++) {
			participants.add(createParticipants());
		}
		participantsPack.setParticipants(participants);
		return participantsPack;
	}
	
	private Participants createParticipants() {
		Participants participant = new Participants();
		participant.setAiControlled(buffer.getNextUInt8AsBoolean());
		participant.setDriverID(buffer.getNextUInt8AsInt());
		participant.setTeamID(buffer.getNextUInt8AsInt());
		participant.setRaceNum(buffer.getNextUInt8AsInt());
		participant.setNationality(buffer.getNextUInt8AsInt());
		participant.setName(buffer.getNextCharArrayAsString(48));
		return participant;
	}
	
	private SetupPacket createCarSetupPacket(Header header) {
		SetupPacket carSetup = new SetupPacket();
		carSetup.setHeader(header);
		List<CarSetup> carSetups = new ArrayList<>();
		for (int k = 0; k < TOTAL_CARS; k++) {
			carSetups.add(createCarSetup());
		}
		carSetup.setCarSetups(carSetups);
		return carSetup;
	}
	
	private CarSetup createCarSetup() {
		CarSetup setupData = new CarSetup();
		setupData.setFrontWing(buffer.getNextUInt8AsInt());
		setupData.setRearWing(buffer.getNextUInt8AsInt());
		setupData.setOnThrottle(buffer.getNextUInt8AsInt());
		setupData.setOffThrottle(buffer.getNextUInt8AsInt());
		setupData.setFrontCamber(buffer.getNextFloat());
		setupData.setRearCamber(buffer.getNextFloat());
		setupData.setFrontToe(buffer.getNextFloat());
		setupData.setRearToe(buffer.getNextFloat());
		setupData.setFrontSuspension(buffer.getNextUInt8AsInt());
		setupData.setRearSuspension(buffer.getNextUInt8AsInt());
		setupData.setFrontAntiRollBar(buffer.getNextUInt8AsInt());
		setupData.setRearAntiRollBar(buffer.getNextUInt8AsInt());
		setupData.setFrontSuspensionHeight(buffer.getNextUInt8AsInt());
		setupData.setRearSuspensionHeight(buffer.getNextUInt8AsInt());
		setupData.setBrakePressure(buffer.getNextUInt8AsInt());
		setupData.setBrakeBias(buffer.getNextUInt8AsInt());
		setupData.setFrontTirePressure(buffer.getNextFloat());
		setupData.setRearTirePressure(buffer.getNextFloat());
		setupData.setBallast(buffer.getNextUInt8AsInt());
		setupData.setFuelLoad(buffer.getNextFloat());
		return setupData;
	}
	
	private TelemetryPacket createTelemetryPacket(Header header) {
		TelemetryPacket telemetryPacket = new TelemetryPacket();
		telemetryPacket.setHeader(header);
		List<Telemetry> telemetry = new ArrayList<>();
		for (int k = 0; k < TOTAL_CARS; k++) {
			telemetry.add(createTelemetry());
		}
		telemetryPacket.setButton(createButtons());
		telemetryPacket.setCarTelemetryData(telemetry);
		return telemetryPacket;
	}
	
	private Telemetry createTelemetry() {

		Telemetry telemetry = new Telemetry();

		telemetry.setSpeed(buffer.getNextUInt16AsInt());
		telemetry.setThrottle(buffer.getNextUInt8AsInt());
		telemetry.setSteer(buffer.getNextInt8AsInt());
		telemetry.setBrake(buffer.getNextUInt8AsInt());
		telemetry.setClutch(buffer.getNextUInt8AsInt());
		telemetry.setGear(buffer.getNextInt8AsInt());
		telemetry.setEngineRpm(buffer.getNextUInt16AsInt());
		telemetry.setDrs(buffer.getNextUInt8AsBoolean());
		telemetry.setRevLightsPercent(buffer.getNextUInt8AsInt());
		telemetry.setBrakeTemperature(new Wheels<Integer>(buffer.getNextUInt16ArrayAsIntegerArray(4)));
		telemetry.setTireSurfaceTemperature(new Wheels<Integer>(buffer.getNextUInt16ArrayAsIntegerArray(4)));
		telemetry.setTireInnerTemperature(new Wheels<Integer>(buffer.getNextUInt16ArrayAsIntegerArray(4)));
		telemetry.setEngineTemperature(buffer.getNextUInt16AsInt());
		telemetry.setTirePressure(new Wheels<Float>(buffer.getNextFloatArray(4)));

		return telemetry;
	}
	
	private Buttons createButtons() {
		
		long flags = buffer.getNextUIntAsLong();
		
		Buttons controller = new Buttons();
		controller.setCrossAPressed((flags & 0x0001) == 1);
		controller.setTriangleYPressed((flags & 0x0002) == 1);
		controller.setCircleBPressed((flags & 0x0004) == 1);
		controller.setSquareXPressed((flags & 0x0008) == 1);
		controller.setDpadLeftPressed((flags & 0x0010) == 1);
		controller.setDpadRightPressed((flags & 0x0020) == 1);
		controller.setDpadUpPressed((flags & 0x0040) == 1);
		controller.setDpadDownPressed((flags & 0x0080) == 1);
		controller.setOptionsMenuPressed((flags & 0x0100) == 1);
		controller.setL1LBPressed((flags & 0x0200) == 1);
		controller.setR1RBPressed((flags & 0x0400) == 1);
		controller.setL2LTPressed((flags & 0x0800) == 1);
		controller.setR2RTPressed((flags & 0x1000) == 1);
		controller.setLeftStickPressed((flags & 0x2000) == 1);
		controller.setRightStickPressed((flags & 0x4000) == 1);
		
		return controller;
	}
	
	private StatusPacket createCarStatusPacket(Header header) {

		StatusPacket packetCarStatus = new StatusPacket();

		packetCarStatus.setHeader(header);
		List<CarStatus> carStatuses = new ArrayList<>();
		for (int i = 0; i < TOTAL_CARS; i++) {
			carStatuses.add(createCarStatus());
		}
		packetCarStatus.setCarStatuses(carStatuses);

		return packetCarStatus;
	}

	private CarStatus createCarStatus() {

		CarStatus carStatus = new CarStatus();

		carStatus.setTractionControl(buffer.getNextUInt8AsInt());
		carStatus.setAntiLockBrakes(buffer.getNextUInt8AsBoolean());
		carStatus.setFuelMix(buffer.getNextUInt8AsInt());
		carStatus.setFrontBrakeBias(buffer.getNextUInt8AsInt());
		carStatus.setPitLimiterOn(buffer.getNextUInt8AsBoolean());
		carStatus.setFuelInTank(buffer.getNextFloat());
		carStatus.setFuelCapacity(buffer.getNextFloat());
		carStatus.setMaxRpm(buffer.getNextUInt16AsInt());
		carStatus.setIdleRpm(buffer.getNextUInt16AsInt());
		carStatus.setMaxGears(buffer.getNextUInt8AsInt());
		carStatus.setDrsAllowed(buffer.getNextUInt8AsInt());
		carStatus.setTyresWear(new Wheels<Integer>(buffer.getNextUInt8ArrayAsIntegerArray(4)));
		carStatus.setTyreCompound(buffer.getNextUInt8AsInt());
		carStatus.setTyresDamage(new Wheels<Integer>(buffer.getNextUInt8ArrayAsIntegerArray(4)));
		carStatus.setFrontLeftWheelDamage(buffer.getNextUInt8AsInt());
		carStatus.setFrontRightWingDamage(buffer.getNextUInt8AsInt());
		carStatus.setRearWingDamage(buffer.getNextUInt8AsInt());
		carStatus.setEngineDamage(buffer.getNextUInt8AsInt());
		carStatus.setGearBoxDamage(buffer.getNextUInt8AsInt());
		carStatus.setExhaustDamage(buffer.getNextUInt8AsInt());
		carStatus.setVehicleFiaFlags(buffer.getNextInt8AsInt());
		carStatus.setErsStoreEngery(buffer.getNextFloat());
		carStatus.setErsDeployMode(buffer.getNextUInt8AsInt());
		carStatus.setErsHarvestedThisLapMGUK(buffer.getNextFloat());
		carStatus.setErsHarvestedThisLapMGUH(buffer.getNextFloat());
		carStatus.setErsDeployedThisLap(buffer.getNextFloat());

		return carStatus;
	}

}
