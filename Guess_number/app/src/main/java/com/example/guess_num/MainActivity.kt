package com.example.guess_num

import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guess_num.ui.theme.Guess_numTheme
import java.io.StringReader
import androidx.compose.material.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Guess_numTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Gamezone()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Guess_numTheme {
        Gamezone()
    }
}

@Composable
fun Gamezone() {
    val init_result = (1..100).random()

    Column(verticalArrangement = Arrangement.spacedBy(15.dp),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Guess Number From 1 - 100")
        Playzone(init_result)
    }
}

@Composable
fun Playzone(init_result: Int, modifier: Modifier = Modifier){
    var result: Int by remember {
        mutableStateOf(init_result) }
    var input : String by remember {
        mutableStateOf("") }
    var count by remember {
        mutableStateOf("0") }
    var check by remember {
        mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.Title),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        User_Input(
            label = R.string.input,
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.width(300.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = check
        )
        Text(
            text = "Your Attempt $count"
        )
    }
    // Check
    Button(
        onClick = { check = check_res(result.toInt(), input.toIntOrNull() ?: 0);
            count = count(count, input.toIntOrNull() ?: 0) }
    ) {
        Text(text = "Submit")
    }
    Spacer(Modifier.width(32.dp))
    // restart
    Button(
        onClick = { result = (1..100).random();
            check = "" ;
            count = "0"; }
    ) {
        Text(text = "Reset")
    }
}

fun count(count: String, input: Int): String{
    return (if (input > 0 ) {
        (count.toInt() + 1).toString()
    }
    else {
        count
    })
}

@Composable
fun User_Input(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(stringResource(label)) }

    )
}

fun check_res(result: Int, input: Int): String {
    return if (input < 0) {
        "Invalid"
    }
    else if (input > result) {
        "Try Guess Lower"
    }
    else if (input < result) {
        "Try Guess Higher"
    }
    else {
        "Congratulations!"
    }
}





