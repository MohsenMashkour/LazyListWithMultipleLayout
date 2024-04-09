package com.mkrdeveloper.lazylistmultiplelayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkrdeveloper.lazylistmultiplelayout.ui.theme.LazyListMultipleLayoutTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListMultipleLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        item {
                            FirstItemUi()
                            Divider(Modifier.fillMaxWidth().padding(6.dp))
                        }
                        items(25) {item->
                            ItemsUi(text = item.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FirstItemUi(modifier: Modifier = Modifier) {
    val list = listOf(
        Icons.Default.AccountBox,
        Icons.Default.Person,
        Icons.Default.Face
    )
    var num by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { num }
            .collect { currentNum ->
                if (currentNum < list.size) {
                    delay(2000L)
                    num++
                } else {
                    num = 0
                }
            }

    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                Toast
                    .makeText(context, "First Item Clicked", Toast.LENGTH_SHORT)
                    .show()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = list[num], contentDescription = "icons", modifier.size(250.dp))
    }
}

@Composable
fun ItemsUi(text: String) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            .clickable {
                Toast
                    .makeText(context, "List Item Clicked", Toast.LENGTH_SHORT)
                    .show()
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = text, modifier = Modifier.padding(start = 8.dp), fontSize = 20.sp)
    }
}