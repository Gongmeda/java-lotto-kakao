package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final Map<Integer, LottoNumber> CACHE;

	static {
		Map<Integer, LottoNumber> cache = new HashMap<>();
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			cache.put(i, new LottoNumber(i));
		}
		CACHE = Collections.unmodifiableMap(cache);
	}

	private final int value;

	private LottoNumber(int value) {
		validateLottoNumber(value);
		this.value = value;
	}

	public static LottoNumber of(int number) {
		LottoNumber lottoNumber = CACHE.get(number);

		if (lottoNumber == null) {
			return new LottoNumber(number);
		}
		return lottoNumber;
	}

	private void validateLottoNumber(int value) {
		if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(
				String.format("로또 번호는 %d이상 %d이하 여야합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
		}
	}

	public static List<LottoNumber> getAllNumbers() {
		return CACHE.values().stream().collect(Collectors.toUnmodifiableList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public int compareTo(LottoNumber o) {
		return Integer.compare(value, o.value);
	}
}
