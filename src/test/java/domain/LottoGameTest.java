package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoGameTest {

	@Test
	void 당첨_통계를_계산하여_반환한다() {
		LottoTickets lottoTickets = new LottoTickets(List.of(
			LottoTicket.of(List.of(11, 12, 13, 14, 15, 16)),
			LottoTicket.of(List.of(1, 12, 13, 14, 15, 16)),
			LottoTicket.of(List.of(1, 2, 13, 14, 15, 16)),
			LottoTicket.of(List.of(1, 2, 3, 14, 15, 16)),
			LottoTicket.of(List.of(1, 2, 3, 4, 15, 16)),
			LottoTicket.of(List.of(1, 2, 3, 4, 5, 16)),
			LottoTicket.of(List.of(1, 2, 3, 4, 5, 7)),
			LottoTicket.of(List.of(1, 2, 3, 4, 5, 6))
		));

		WinningLottoTicket winningLottoTicket = new WinningLottoTicket(
			LottoTicket.of(List.of(1, 2, 3, 4, 5, 6)),
			new LottoNumber(7)
		);

		LottoGame lottoGame = new LottoGame(lottoTickets, winningLottoTicket);

		LottoGameResult result = lottoGame.calculateResult();

		assertAll(
			() -> assertThat(result.getCount(LottoWinningRank.FIRST_PRIZE)).isEqualTo(1),
			() -> assertThat(result.getCount(LottoWinningRank.SECOND_PRIZE)).isEqualTo(1),
			() -> assertThat(result.getCount(LottoWinningRank.THIRD_PRIZE)).isEqualTo(1),
			() -> assertThat(result.getCount(LottoWinningRank.FOURTH_PRIZE)).isEqualTo(1),
			() -> assertThat(result.getCount(LottoWinningRank.FIFTH_PRIZE)).isEqualTo(1),
			() -> assertThat(result.getCount(LottoWinningRank.LOSE)).isEqualTo(3)
		);
	}
}
