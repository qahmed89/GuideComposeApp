import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.guidecomposeapp.screen.home.AlertDialogModel

@Composable
fun AlertCustomDialog(
    showShow: Boolean = false,
    alertDialogModel: AlertDialogModel?,
    action: () -> Unit,

    ) {


    if (!showShow) return
    Dialog(
        onDismissRequest = action, properties = DialogProperties(
            dismissOnBackPress = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = alertDialogModel?.title?.asString().orEmpty(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = alertDialogModel?.bodyMessage?.asString().orEmpty(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.75f)) {
                        Text(text = alertDialogModel?.doneTextButton?.asString().orEmpty())
                    }

                    TextButton(onClick = action) {
                        Text(text = alertDialogModel?.cancelTextButton?.asString().orEmpty())

                    }
                }

            }
        }
    }

}