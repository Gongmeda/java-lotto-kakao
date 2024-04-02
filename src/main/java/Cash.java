public class Cash {

	private final int value;

	public Cash(int value) {
		validateNegative(value);
		validateLessThanTicketPrice(value);
		this.value = value;
	}

	private void validateNegative(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Cash의 값은 음수가 아니여야 합니다.");
		}
	}

	private void validateLessThanTicketPrice(int value) {
		if (value < LottoTicket.PRICE) {
			throw new IllegalArgumentException("잔여 Cash가 부족합니다.");
		}
	}

	public int getTicketCount() {
		return value / LottoTicket.PRICE;
	}
}
