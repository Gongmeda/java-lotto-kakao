package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
	private static final int LOTTO_NUMBER_COUNT = 6;
	public static final int PRICE = 1000;

	private final List<LottoNumber> lottoNumbers;

	private LottoTicket(List<LottoNumber> lottoNumbers) {
		validateSize(lottoNumbers);
		validateDuplicate(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	public static LottoTicket of(List<Integer> lottoNumbers) {
		return new LottoTicket(lottoNumbers.stream().map(LottoNumber::of).collect(Collectors.toList()));
	}

	public static LottoTicket random() {
		List<LottoNumber> shuffledNumbers = getShuffledNumbers();
		return new LottoTicket(shuffledNumbers.subList(0, LOTTO_NUMBER_COUNT));
	}

	private static List<LottoNumber> getShuffledNumbers() {
		List<LottoNumber> shuffledNumbers = new ArrayList<>(LottoNumber.getAllNumbers());
		Collections.shuffle(shuffledNumbers);
		return shuffledNumbers;
	}

	private void validateSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException(String.format("로또 번호는 %d개 입니다.", LOTTO_NUMBER_COUNT));
		}
	}

	private void validateDuplicate(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != lottoNumbers.stream().distinct().count()) {
			throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int countSameNumbers(LottoTicket lottoTicket) {
		return Long.valueOf(lottoNumbers.stream().filter(lottoTicket::contains).count()).intValue();
	}

	@Override
	public String toString() {
		List<LottoNumber> sortedLottoNumbers = new ArrayList<>(lottoNumbers);
		Collections.sort(sortedLottoNumbers);
		return sortedLottoNumbers.toString();
	}
}