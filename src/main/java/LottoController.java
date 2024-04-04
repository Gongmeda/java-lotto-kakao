import domain.*;

public class LottoController {
	private final LottoView view;

	public LottoController(LottoView view) {
		this.view = view;
	}

	public void play() {
		Cash cash = view.getCash();
		int manualTicketCount = view.getManualTicketCount();
		cash.buyTickets(manualTicketCount);
		LottoTickets manualTickets = view.getManualTickets(manualTicketCount);

		LottoTickets lottoTickets = getLottoTickets(cash, manualTicketCount, manualTickets);

		WinningLottoTicket winningLottoTicket = new WinningLottoTicket(view.getWinningNumbers(), view.getBonusNumber());

		LottoGame lottoGame = new LottoGame(lottoTickets, winningLottoTicket);
		view.displayResult(lottoGame.calculateResult());
	}

	private LottoTickets getLottoTickets(Cash cash, int manualTicketCount, LottoTickets manualTickets) {
		int randomTicketCount = cash.getTicketCount();
		view.displayPaidTicketCount(manualTicketCount, randomTicketCount);
		LottoTickets lottoTickets = manualTickets.add(LottoTickets.ofRandom(randomTicketCount));
		view.displayLottoTickets(lottoTickets);
		return lottoTickets;
	}
}
