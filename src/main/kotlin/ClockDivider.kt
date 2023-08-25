@file:Verik

import io.verik.core.*

class ClockDivider<DIVIDER_WIDTH : Cardinal>(
	@In var clkIn: Boolean,
	@In var rst: Boolean,
	@In var divider: Ubit<DIVIDER_WIDTH>,
	@Out var clkOut: Boolean,
): Module() {
	private var dividerCounter: Ubit<DIVIDER_WIDTH> = nc()

	@Seq
	fun divideClock() {
		on(posedge(clkIn)) {
			if (rst) {
				dividerCounter = u0()
				clkOut = false
			}
			else {
				if (dividerCounter == divider) {
					clkOut = !clkOut
					dividerCounter = u0()
				} else dividerCounter += u(1)
			}
		}
	}
}