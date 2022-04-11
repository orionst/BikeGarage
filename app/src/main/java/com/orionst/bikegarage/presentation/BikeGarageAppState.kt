package com.orionst.bikegarage.presentation

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.text.Format
import java.text.SimpleDateFormat

@Composable
fun rememberBikeGarageAppSate(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    BikeGarageAppState(navController, context)
}

class BikeGarageAppState(
    val navController: NavHostController,
    private val context: Context
) {
    @Deprecated("Get date format from DateUtil")
    fun getDateFormat(): String {
        val format: Format = DateFormat.getDateFormat(context)
        return (format as SimpleDateFormat).toLocalizedPattern()
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}
