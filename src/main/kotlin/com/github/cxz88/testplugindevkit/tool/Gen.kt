package com.github.cxz88.testplugindevkit.tool

/**
 * @author chenxinzhi
 * @date 2024/1/27
 */

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.baomidou.mybatisplus.generator.BladeCodeGenerator
import com.intellij.openapi.project.Project
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


@Composable
fun Gen(project: Project?, service: MyService?, rowInfoList: String, function: () -> Unit) {
    var msg by remember {
        mutableStateOf("正在生成")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = msg,
                fontSize = with(LocalDensity.current) {
                    30.dp.toSp()
                },
                color = Color.White
            )
            var process by remember { mutableFloatStateOf(0F) }
            val animateFloatAsState by animateFloatAsState(process)
            LaunchedEffect(Unit) {
                launch(Dispatchers.IO) {
                    val bladeCodeGenerator = BladeCodeGenerator()
                    bladeCodeGenerator.run(SF.mutableSharedFlow,rowInfoList.split(",").toList(),project,service)
                }
                SF.mutableSharedFlow.collect {
                    process = it
                    if (it == 1F) {
                        msg = "生成完成"
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(0.6F).height(20.dp).border(
                    0.1.dp, Color.White,
                    RoundedCornerShape(30.dp)
                ), progress = animateFloatAsState, color = Color.Green, strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(30.dp))


        }

    }
}

object SF {

    val mutableSharedFlow = MutableSharedFlow<Float>()
}



