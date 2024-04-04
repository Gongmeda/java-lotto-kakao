package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CashTest {
	@Test
	void 캐시가_음수면_예외를_던진다() {
		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() -> new Cash(-1));
	}

	@Test
	void 캐시가_부족하면_예외를_던진다() {
		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() -> new Cash(500));
	}

	@Test
	void 구매_가능한_티켓_개수를_계산한다() {
		Cash cash = new Cash(15100);

		assertThat(cash.getTicketCount()).isEqualTo(15);
	}

	@Test
	void 잔여_금액보다_많은_금액의_티켓을_구매하면_예외를_던진다() {
		Cash cash = new Cash(14000);

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> cash.buyTickets(15));
	}

	@Test
	void 티켓을_구매하면_잔여_금액으로_구매_가능한_티켓_개수를_계산한다() {
		Cash cash = new Cash(14000);
		cash.buyTickets(3);

		assertThat(cash.getTicketCount()).isEqualTo(11);
	}
}
