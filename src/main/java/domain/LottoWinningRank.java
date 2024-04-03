package domain;

import java.util.Arrays;

public enum LottoWinningRank {
	LOSE(0, false, 0),
	FIFTH_PRIZE(3, false, 5_000),
	FOURTH_PRIZE(4, false, 50_000),
	THIRD_PRIZE(5, false, 1_500_000),
	SECOND_PRIZE(5, true, 30_000_000),
	FIRST_PRIZE(6, false, 2_000_000_000);

	private final int matchCount;
	private final boolean requireBonusNumber;
	private final int prize;

	LottoWinningRank(int matchCount, boolean requireBonusNumber, int prize) {
		this.matchCount = matchCount;
		this.requireBonusNumber = requireBonusNumber;
		this.prize = prize;
	}

	public static LottoWinningRank calculateRank(int matchCount, boolean hasBonusNumber) {
		return Arrays.stream(values())
			.filter(r -> isSecondPrize(matchCount, hasBonusNumber, r))
			.findFirst()
			.orElse(LottoWinningRank.calculateRank(matchCount));
	}

	private static boolean isSecondPrize(int matchCount, boolean hasBonusNumber, LottoWinningRank lottoWinningRank) {
		return lottoWinningRank.matchCount == matchCount && lottoWinningRank.matchCount == 5 && lottoWinningRank.requireBonusNumber && hasBonusNumber;
	}

	private static LottoWinningRank calculateRank(int matchCount) {
		return Arrays.stream(values())
			.filter(r -> r.matchCount == matchCount)
			.findFirst()
			.orElse(LOSE);
	}

	public int getPrize() {
		return prize;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean requireBonusNumber() {
		return requireBonusNumber;
	}
}
