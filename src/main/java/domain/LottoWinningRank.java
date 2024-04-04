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
		if (matchCount == 5 && hasBonusNumber) {
			return LottoWinningRank.SECOND_PRIZE;
		}

		return Arrays.stream(values())
				.filter(rank -> rank.matchCount == matchCount)
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
