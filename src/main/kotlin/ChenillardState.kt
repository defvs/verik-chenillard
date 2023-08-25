@file:Verik

import io.verik.core.Verik

enum class ChenillardState {
	FORWARD, BACKWARD, STOP, OFF;

	fun nextState() = entries[(entries.indexOf(this) + 1).mod(entries.size)]
}