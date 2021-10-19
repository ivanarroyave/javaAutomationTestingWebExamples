package co.com.sofka.soap.utils;

public enum Seconds {
	ONE_SECOND(1),
	TWO_SECONDS(2),
	THREE_SECONDS(3),
	FOUR_SECONDS(4),
	FIVE_SECONDS(5),
	SIX_SECONDS(6),
	SEVEN_SECONDS(7),
	EIGHT_SECONDS(8),
	NINE_SECONDS(9),
	TEN_SECONDS(10),
	TWENTY_SECONDS(20),
	THIRTY_SECONDS(30),
	FORTY_SECONDS(40),
	FIFTY_SECONDS(50),
	SIXTY_SECONDS(60),
	SEVENTY_SECONDS(70),
	EIGHTY_SECONDS(80),
	NINETY_SECONDS(90),
	ONE_HUNDRED_SECONDS(100),
	TWO_HUNDRED_SECONDS(200),
	THREE_HUNDRED_SECONDS(300);
	
	private final int value;
	
	Seconds(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
