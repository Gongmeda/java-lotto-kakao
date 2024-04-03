package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LottoGameResultTest {
	@Test
	void 수익률을_계산할_수_있다() {
		LottoGameResult lottoGameResult = new LottoGameResult();
		lottoGameResult.add(LottoWinningRank.FIRST_PRIZE);
		lottoGameResult.add(LottoWinningRank.SECOND_PRIZE);
		lottoGameResult.add(LottoWinningRank.THIRD_PRIZE);
		lottoGameResult.add(LottoWinningRank.FOURTH_PRIZE);
		lottoGameResult.add(LottoWinningRank.FIFTH_PRIZE);

		assertThat(lottoGameResult.calculateYield()).isEqualTo(406311);
	}
}