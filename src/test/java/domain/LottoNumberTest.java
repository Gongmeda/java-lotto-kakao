package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottoNumberTest {
	@Test
	void 로또_번호는_1이상_45이하여야합니다() {
		assertAll(
			() -> assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> LottoNumber.of(0)),
			() -> assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> LottoNumber.of(46))
		);
	}

	@Test
	void 가능한_모든_로또_번호_리스트_반환() {
		assertThat(LottoNumber.getAllNumbers().size()).isEqualTo(45);
	}
}
