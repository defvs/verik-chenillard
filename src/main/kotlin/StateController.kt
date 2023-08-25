@file:Verik

import io.verik.core.*

class StateController(
	@In var clk: Boolean,
	@In var rst: Boolean,
	@In var switchState: Boolean,
	@Out var state: ChenillardState,
) : Module() {
	@Seq
	fun setState() = on(posedge(clk)) {
		if (rst) state = ChenillardState.OFF
		else if (switchState) state = state.nextState()
	}
}