package co.com.sofka.soap.utils.enums;

public enum DataGenerationForTest {
	FILLED_OUT(true),
	NOT_FILLED_OUT(false);

	private final boolean value;

	DataGenerationForTest(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}
}
