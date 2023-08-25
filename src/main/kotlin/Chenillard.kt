@file:Verik

import io.verik.core.*


class Chenillard<SHIFTER_WIDTH : Cardinal, DIVIDER_WIDTH : Cardinal>(
	@In val ENABLE_INTERFACE_CONTROL: Boolean,

	@In var clk: Boolean,
	@In var rst: Boolean,
	@In var switchState: Boolean,
	@In var divider: Ubit<DIVIDER_WIDTH>,
	@Out var output: Ubit<SHIFTER_WIDTH>,
) : Module() {
	private var state: ChenillardState = nc()
	private var divClk: Boolean = nc()

	@Make
	val clockDivider = ClockDivider(clk, rst, divider, divClk)

	@Make
	val shifter = Shifter<SHIFTER_WIDTH>(divClk, rst, state, output)

	@Make
	val stateController = optional(ENABLE_INTERFACE_CONTROL) {
		StateController(clk, rst, switchState, state)
	}

	@Make
	val noStateController = optional(!ENABLE_INTERFACE_CONTROL) {
		state = ChenillardState.FORWARD
	}
}