package com.orionst.bikegarage.presentation.ui.autocomplete

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AutoCompleteOutlinedTextField(
    text: String,
    label: @Composable() (() -> Unit)?,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    options: List<String>,
    onValueChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            label = label,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
//            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            singleLine = singleLine,
        )

        // filter options based on text field value (i.e. crude autocomplete)
        val filterOpts = options.filter { it.contains(text, ignoreCase = true) }

        if (filterOpts.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filterOpts.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(option)
                            focusManager.clearFocus(true)
                            expanded = false
                        }
                    ) {
                        Text(text = option)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun AutoCompleteTextFieldPreview() {
    MaterialTheme {
        var text by remember { mutableStateOf("") }
        AutoCompleteOutlinedTextField(
            text = text,
            label = { Text(text = "Label") },
            options = listOf("Merida", "Marin", "Atom", "Stark", "Stels"),
            onValueChange = {
                text = it
            }
        )
    }
}
