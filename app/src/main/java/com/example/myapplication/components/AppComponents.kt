import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun OutlinedTextFieldDemo(
    fieldTextv: String,
    label: String
) {
    // Create a state variable to store the text input.
    val textState =
        remember { mutableStateOf("") }
    Column {
//        Add a text above the OutlinedTextField composable.
        Text(text =fieldTextv)
        // Create an OutlinedTextField composable.
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },

            label = { Text(label) }
        )
    }

//
}