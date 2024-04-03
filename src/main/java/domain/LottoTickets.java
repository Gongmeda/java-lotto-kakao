package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets = new ArrayList<>();

	public static LottoTickets ofRandom(int count) {
		List<LottoTicket> lottoTickets = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottoTickets.add(LottoTicket.random());
		}
		return new LottoTickets(lottoTickets);
	}

	public LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets.addAll(lottoTickets);
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}
}
