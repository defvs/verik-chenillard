@file:Verik

import io.verik.core.*

class Shifter<WIDTH: Cardinal>(
	@In var clk: Boolean,
	@In var rst: Boolean,
	@In var state: ChenillardState,
	@Out var out: Ubit<WIDTH>,
) : Module() {
	private var shiftRegister: Ubit<WIDTH> = nc()

	@Seq
	fun shift() {
		on(posedge(clk)) {
			if (rst) {
				shiftRegister = u(1)
			}
			else {
				shiftRegister = when (state) {
					ChenillardState.FORWARD -> shiftRegister.shr(1)
					ChenillardState.BACKWARD -> shiftRegister.shl(1)
					ChenillardState.STOP, ChenillardState.OFF -> shiftRegister
				}
			}
		}
	}

	@Com
	fun output() {
		out = if (rst) u0()
		else when (state) {
			ChenillardState.OFF -> u0()
			else -> shiftRegister
		}
	}
}