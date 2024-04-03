public class LottoApplication {

	public static void main(String[] args) {
		LottoController controller = new LottoController(new LottoView());
		controller.play();
	}
}
