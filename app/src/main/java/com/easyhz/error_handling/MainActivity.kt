package com.easyhz.error_handling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.error_handling.ui.theme.ErrorHandlingPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ErrorHandlingPracticeTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val resultState by viewModel.resultState.collectAsStateWithLifecycle(
        lifecycleOwner = LocalLifecycleOwner.current
    )
    val errorState by viewModel.errorState.collectAsStateWithLifecycle(
        lifecycleOwner = LocalLifecycleOwner.current
    )
    Column {
        GetButton("Success 200") {
            viewModel.getSuccess()
        }
        GetButton("Bad Request 400") {
            viewModel.getBadRequest()
        }
        GetButton("Internal Server Error 500") {
            viewModel.getInternalServerError()
        }
        GetButton("Not Found 404") {
            viewModel.getNotFound()
        }
        GetButton("Not Supported Status Code 73") {
            viewModel.getNotSupportedStatusCode()
        }
        GetButton("Real Not Found 404") {
            viewModel.getRealNotFound()
        }

        resultState?.let { result ->
            Text(text = "message: ${result.message} , status: ${result.status}")
        } ?: run {
            Text(
                modifier = Modifier.fillMaxWidth().border(1.dp, Color.Red),
                text = "Error $errorState"
            )
        }

    }
}

@Composable
private fun GetButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}