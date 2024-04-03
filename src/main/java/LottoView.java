import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Cash;
import domain.LottoGameResult;
import domain.LottoNumber;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.LottoWinningRank;

public class LottoView {

	private static final Scanner SCANNER = new Scanner(System.in);

	public Cash getCash() {
		System.out.println("구입금액을 입력해 주세요.");
		return new Cash(Integer.parseInt(SCANNER.nextLine()));
	}

	public void displayPaidTicketCount(int ticketCount) {
		System.out.printf("%d개를 구매했습니다.%n", ticketCount);
	}

	public void displayLottoTickets(LottoTickets lottoTickets) {
		StringBuilder builder = new StringBuilder();
		for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
			builder.append(lottoTicket.toString());
			builder.append("\n");
		}
		System.out.println(builder);
	}

	public LottoTicket getWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String line = SCANNER.nextLine();
		List<Integer> numberStrings = Stream.of(line.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toUnmodifiableList());
		return LottoTicket.of(numberStrings);
	}

	public LottoNumber getBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return new LottoNumber(Integer.parseInt(SCANNER.nextLine()));
	}

	public void displayResult(LottoGameResult lottoGameResult) {
		System.out.println("당첨 통계");
		System.out.println("---------");

		List<LottoWinningRank> ranksExceptLose = getRanksExceptLose();
		StringBuilder builder = new StringBuilder();
		for (LottoWinningRank lottoWinningRank : ranksExceptLose) {
			appendRankResult(builder, lottoGameResult, lottoWinningRank);
		}
		appendYield(builder, lottoGameResult);
		System.out.println(builder);
	}

	private static void appendRankResult(StringBuilder builder, LottoGameResult lottoGameResult, LottoWinningRank lottoWinningRank) {
		builder.append(String.format("%d개 일치 ", lottoWinningRank.getMatchCount()));

		if (lottoWinningRank.requireBonusNumber()) {
			builder.deleteCharAt(builder.length() - 1);
			builder.append(", 보너스 볼 일치");
		}

		builder.append(String.format("(%d원) - %d개", lottoWinningRank.getPrize(), lottoGameResult.getCount(
			lottoWinningRank)));
		builder.append("\n");
	}

	private void appendYield(StringBuilder builder, LottoGameResult lottoGameResult) {
		builder.append(String.format("총 수익률은 %.2f입니다.", lottoGameResult.calculateYield()));

		if (lottoGameResult.calculateYield() < 1.0) {
			builder.append("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
		}
	}

	private static List<LottoWinningRank> getRanksExceptLose() {
        return Arrays.stream(LottoWinningRank.values())
			.filter(rank -> rank != LottoWinningRank.LOSE)
			.collect(Collectors.toList());
	}
}