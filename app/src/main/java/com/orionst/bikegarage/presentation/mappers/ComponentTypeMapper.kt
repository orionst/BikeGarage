package com.orionst.bikegarage.presentation.mappers

import androidx.annotation.StringRes
import com.orionst.bikegarage.R
import com.orionst.bikegarage.domain.enums.ComponentType

@StringRes
fun ComponentType.getNameResId(): Int =
    when(this) {
        ComponentType.BATTERY -> R.string.component_type_battery
        ComponentType.BEARINGS -> R.string.component_type_bearings
        ComponentType.BOLTS -> R.string.component_type_bolts
        ComponentType.BOTTOM_BRACKET -> R.string.component_type_bottom_bracket
        ComponentType.BRAKE -> R.string.component_type_brake
        ComponentType.BRAKE_CALIPER -> R.string.component_type_brake_caliper
        ComponentType.BRAKE_LEVER -> R.string.component_type_brake_lever
        ComponentType.BRAKE_PADS -> R.string.component_type_brake_pads
        ComponentType.BRAKE_ROTOR -> R.string.component_type_brake_rotor
        ComponentType.CABLE -> R.string.component_type_cable
        ComponentType.CASSETTE -> R.string.component_type_cassette
        ComponentType.CHAIN -> R.string.component_type_chain
        ComponentType.CHAINGUIDE -> R.string.component_type_chainguide
        ComponentType.CHAINRING -> R.string.component_type_chainring
        ComponentType.CRANKS -> R.string.component_type_cranks
        ComponentType.FORK -> R.string.component_type_fork
        ComponentType.FRAME -> R.string.component_type_frame
        ComponentType.FRONT_DERAILLEUR -> R.string.component_type_front_derailleur
        ComponentType.GRIPS -> R.string.component_type_grips
        ComponentType.HANDLEBAR -> R.string.component_type_handlebar
        ComponentType.HEADSET -> R.string.component_type_headset
        ComponentType.HUB -> R.string.component_type_hub
        ComponentType.INNER_TUBE -> R.string.component_type_inner_tube
        ComponentType.PEDALS -> R.string.component_type_pedals
        ComponentType.REAR_DERAILLEUR -> R.string.component_type_rear_derailleur
        ComponentType.SADDLE -> R.string.component_type_saddle
        ComponentType.SEAT_POST -> R.string.component_type_seat_post
        ComponentType.SHIFTER -> R.string.component_type_shifter
        ComponentType.SHOCK -> R.string.component_type_shock
        ComponentType.SPROCKET -> R.string.component_type_sprocket
        ComponentType.STEM -> R.string.component_type_stem
        ComponentType.TIRE -> R.string.component_type_seat_tire
        ComponentType.WHEEL -> R.string.component_type_seat_wheel
        ComponentType.OTHER -> R.string.component_type_other
    }
