package domain;

public class LottoGame {

	private final LottoTickets lottoTickets;
	private final WinningLottoTicket winningLottoTicket;

	public LottoGame(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
		this.lottoTickets = lottoTickets;
		this.winningLottoTicket = winningLottoTicket;
	}

	public LottoGameResult calculateResult() {
		LottoGameResult result = new LottoGameResult();
		for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
			LottoWinningRank lottoWinningRank = winningLottoTicket.calculateResult(lottoTicket);
			result.add(lottoWinningRank);
		}
		return result;
	}
}
