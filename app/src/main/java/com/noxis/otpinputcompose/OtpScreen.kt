package com.noxis.otpinputcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.noxis.otpinputcompose.ui.theme.OtpInputComposeTheme

@Composable
fun OtpScreen(
    state: OtpState,
    onAction: (OtpAction) -> Unit,
    modifier: Modifier = Modifier,
    focusRequesters: List<FocusRequester>,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            state.code.forEachIndexed { index, number ->
                OtpInputField(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    number = number,
                    focusRequester = focusRequesters[index],
                    onNumberChanged = { newNumber ->
                        onAction(OtpAction.OnEnterNumber(newNumber, index))
                    },
                    onFocusChanged = { isFocused ->
                        if (isFocused) {
                            onAction(OtpAction.OnChangeFieldFocused(index))
                        }
                    },
                    onKeyboardBack = {
                        onAction(OtpAction.OnKeyboardBack)
                    }
                )
            }

        }
    }
}

@Preview
@Composable
private fun OtpScreenPreview() {
    OtpInputComposeTheme {
        val focusRequesters = remember {
            List(4) { FocusRequester() }
        }
        OtpScreen(
            state = OtpState(),
            onAction = {},
            focusRequesters = focusRequesters
        )
    }
}