package com.orionst.bikegarage.presentation.ui.datefield

import android.content.Context
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.material.datepicker.MaterialDatePicker
import com.orionst.bikegarage.R
import com.orionst.bikegarage.util.DateUtil
import com.orionst.bikegarage.util.getActivity
import kotlinx.coroutines.delay
import java.text.ParseException
import java.util.*

@Composable
fun OutlinedDateField(
    modifier: Modifier = Modifier,
    onValueChange: (timestamp: Long) -> Unit
) {
    val context = LocalContext.current

    val dateFormat by rememberUpdatedState(DateUtil.getTextInputFormat())
    val formatHint by rememberUpdatedState(DateUtil.getTextInputHint(context.resources, dateFormat))

    var dateText by remember { mutableStateOf("") }
    var timestamp by remember { mutableStateOf(0L) }
    var isError by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(dateText) {
        suspend fun setError() {
            delay(1_000)
            isError = true
        }

        try {
            val date = dateFormat.parse(dateText)
        } catch (e: ParseException) {

        }
    }

    OutlinedTextField(
        modifier = modifier,
        value = dateText,
        placeholder = { Text(text = formatHint) },
        onValueChange = {
//            onValueChange.invoke(it)
        },
        label = { Text(stringResource(R.string.obtaining_date_field_label)) },
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    showDatePickerDialog(context) {
                        timestamp = it
                        dateText = dateFormat.format(Date(it))
                    }
                }
            ) {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }
        }
    )
}

private fun showDatePickerDialog(
    context: Context,
    onSelectDate: (Long) -> Unit
) {
    val dateDialog = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select obtaining date")
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
        .build()
    dateDialog.addOnPositiveButtonClickListener {
        onSelectDate.invoke(it)
    }
    dateDialog.show(context.getActivity().supportFragmentManager, "DatePicker")
}
