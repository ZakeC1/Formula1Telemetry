package f1tel;

import java.math.BigInteger;

public class PackBuffer {
	
	private final byte[] ba;
	private int i;

	private PackBuffer(byte[] array) {
		this.ba = array;
		this.i = 0;
	}
	
	public static PackBuffer wrap(byte[] array) {
		return new PackBuffer(array);
	}
	
	public byte[] getNextBytes(int size) {
		byte[] next = new byte[size];
		int j = 0;
		while (j < size) {
			next[j++] = ba[i++];
		}
		return next;
	}
	
	public int getNextUInt8AsInt() {
		return ba[i++] & 0xFF;
	}
	
	public boolean getNextUInt8AsBoolean() {
		return 1 == (ba[i++] & 0xFF);
	}
	
	public int getNextUInt16AsInt() {
		return (ba[i++] & 0xFF) | ((ba[i++] & 0xFF) << 8);
	}
	
	public long getNextUIntAsLong() {
		return (ba[i++] & 0xFF) | ((ba[i++] & 0xFF) << 8) | ((ba[i++] & 0xFF) << 16) | ((ba[i++] & 0xFF) << 24);
	}
	
	public int getNextInt8AsInt() {
		return ba[i++];
	}
	
	public BigInteger getNextUInt64AsBigInteger() {
		byte[] uint64 = getNextBytes(8);
		return new BigInteger(1, uint64);
	}
	
	public float getNextFloat() {
		int floatAsInt = (ba[i++] & 0xFF) | ((ba[i++] & 0xFF) << 8) | ((ba[i++] & 0xFF) << 16)
				| ((ba[i++] & 0xFF) << 24);
		return Float.intBitsToFloat(floatAsInt);
	}
	
	public Float[] getNextFloatArray(int count) {
		Float[] floats = new Float[count];
		for (int k = 0; k < count; k++) {
			floats[k] = getNextFloat();
		}
		return floats;
	}
	
	public Integer[] getNextUInt8ArrayAsIntegerArray(int count) {
		Integer[] ints = new Integer[count];
		for (int k = 0; k < count; k++) {
			ints[k] = getNextUInt8AsInt();
		}
		return ints;
	}
	
	public Integer[] getNextUInt16ArrayAsIntegerArray(int count) {
		Integer[] ints = new Integer[count];
		for (int k = 0; k < count; k++) {
			ints[k] = getNextUInt16AsInt();
		}
		return ints;
	}
	
	public String getNextCharArrayAsString(int count) {
		char[] charArr = new char[count];
		boolean reachedEnd = false;
		for (int k = 0; k < count; k++) {
			char curr = (char) ba[i++];
			if(curr == '\u0000') {
				reachedEnd = true;
			}else if (!reachedEnd) {
				charArr[k] = curr;
			}
		}
		return new String(charArr);

	}
	
	public int getCurrentPosition() {
		return i;
	}
	
	public int getSize() {
		return ba.length;
	}
	
	public boolean hasNext() {
		return i < (ba.length - 1);
	}
}
