package domain;

public class WinningLottoTicket {

	private final LottoTicket lottoTicket;
	private final LottoNumber bonusNumber;

	public WinningLottoTicket(LottoTicket lottoTicket, LottoNumber bonusNumber) {
		validateDuplicateBonusNumber(lottoTicket, bonusNumber);
		this.lottoTicket = lottoTicket;
		this.bonusNumber = bonusNumber;
	}

	private void validateDuplicateBonusNumber(LottoTicket winningNumbers, LottoNumber bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함되선 안됩니다.");
		}
	}

	public LottoWinningRank calculateResult(LottoTicket lottoTicket) {
		return LottoWinningRank.calculateRank(lottoTicket.countSameNumbers(this.lottoTicket), lottoTicket.contains(bonusNumber));
	}
}
