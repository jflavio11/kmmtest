package com.jflavio.kmmtest.android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jflavio.kmmtest.Greeting
import com.jflavio.kmmtest.RocketLaunch
import dagger.hilt.android.AndroidEntryPoint

fun greet(): String {
    return Greeting().greeting()
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivity()
        }
    }

    // TODO: many things to fix here
    @Composable
    fun MainActivity() {
        Box {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "SpaceX Missions",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                when (viewModel.state.value) {
                    is MainViewModel.MainViewState.Default -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    is MainViewModel.MainViewState.Success -> {
                        LazyColumn {
                            itemsIndexed((viewModel.state.value as MainViewModel.MainViewState.Success).list) { index, item ->
                                MissionItem(rocketLaunch = item)
                            }
                        }
                    }
                    is MainViewModel.MainViewState.Error -> {
                        Toast.makeText(LocalContext.current, "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        viewModel.getLaunches()
    }

    @Composable
    fun MissionItem(rocketLaunch: RocketLaunch){
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = "Launch name: ${rocketLaunch.missionName}",
                fontSize = 18.sp
            )

            Text(
                text = if (rocketLaunch.launchSuccess == true) "Success" else "Failure",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp),
                color = if (rocketLaunch.launchSuccess == true) Color(0xFF006400) else Color.Red,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Launch year: ${rocketLaunch.launchYear}",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "Launch details: ${rocketLaunch.details}",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

            Divider()
        }

    }

    @Composable
    @Preview
    fun MainPreview() {
        MainActivity()
    }
}
