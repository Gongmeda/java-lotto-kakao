package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoTicketTest {
	@Test
	void 로또_번호_6개로_생성할_수_있다() {
		assertDoesNotThrow(() -> LottoTicket.of(List.of(1, 2, 3, 4, 5, 6)));
	}

	@Test
	void 로또_번호_6개가_아닌_개수로_생성하면_예외를_던진다() {
		assertAll(
			() -> assertThatThrownBy(() -> LottoTicket.of(List.of(1, 2, 3, 4, 5)))
				.isInstanceOf(IllegalArgumentException.class),
			() -> assertThatThrownBy(() -> LottoTicket.of(List.of(1, 2, 3, 4, 5, 6, 7)))
				.isInstanceOf(IllegalArgumentException.class)
		);
	}

	@Test
	void 동일한_로또_번호가_존재하면_예외를_던진다() {
		assertThatThrownBy(() -> LottoTicket.of(List.of(1, 1, 3, 4, 5, 6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 로또의_가격은_1000원이다() {
		assertThat(LottoTicket.PRICE).isEqualTo(1000);
	}

	@Test
	void 로또에_특정_번호가_포함되었는지_확인할_수_있다() {
		LottoTicket lottoTicket = LottoTicket.of(List.of(1, 2, 3, 4, 5, 6));

		assertAll(
			() -> assertThat(lottoTicket.contains(new LottoNumber(1))).isTrue(),
			() -> assertThat(lottoTicket.contains(new LottoNumber(7))).isFalse()
		);
	}

	@ParameterizedTest
	@CsvSource({
		"1, 2, 3, 4, 5, 6, 6",
		"4, 2, 3, 1, 5, 8, 5",
		"9, 8, 1, 2, 3, 4, 4"})
	void 두_로또_번호_사이의_동일한_번호의_갯수를_반환할_수_있다(int number1, int number2, int number3, int number4, int number5, int number6, int matchCount) {
		LottoTicket lottoTicket1 = LottoTicket.of(List.of(1, 2, 3, 4, 5, 6));
		LottoTicket lottoTicket2 = LottoTicket.of(List.of(number1, number2, number3, number4, number5, number6));

		assertThat(lottoTicket1.countSameNumbers(lottoTicket2)).isEqualTo(matchCount);
	}
}
