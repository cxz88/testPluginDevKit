package com.github.cxz88.testplugindevkit.tool

/**
 * @author chenxinzhi
 * @date 2024/1/27
 */

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.baomidou.mybatisplus.generator.BladeCodeGenerator
import com.intellij.openapi.project.Project
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
inline fun Gen(project: Project?, service: MyService?, rowInfoList: String, crossinline function: () -> Unit) {
    var msg by remember {
        mutableStateOf("正在构建")
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
                    20.dp.toSp()
                },
                color = Color.White,
                modifier = Modifier.fillMaxWidth(), maxLines = 1,
                overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center
            )
            var process by remember { mutableFloatStateOf(0F) }
            val animateFloatAsState by animateFloatAsState(process, animationSpec = tween(800))
            val c by animateColorAsState(
                if (process == 1F) {
                    Color.Green
                } else if (process >= 0F) {
                    Color(0xFF3374f0)
                } else {
                    Color.Red
                }, animationSpec = tween(800)
            )
            LaunchedEffect(Unit) {
                launch(Dispatchers.IO) {
                    BladeCodeGenerator.run(SF.mutableSharedFlow, rowInfoList.split(",").toList(), project, service)
                }
                withContext(Dispatchers.IO) {
                    SF.mutableSharedFlow.collect {
                        process = it.value.coerceAtMost(1F)
                        msg = it.msg
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(0.6F).height(20.dp).border(
                    0.1.dp, Color.White,
                    RoundedCornerShape(30.dp)
                ), progress = if (animateFloatAsState < 0F) {
                    1F
                } else {
                    animateFloatAsState
                }, color = c, strokeCap = StrokeCap.Round

            )
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.height(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (process == 1F || process < 0F) {
                    TextButton(
                        onClick = {
                            function()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3374f0)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(24.dp).width(56.dp),
                        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 6.dp),
                        border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White, modifier = Modifier.size(14.dp))
                            Text(text = "返回", color = Color.White, fontSize = with(LocalDensity.current) {
                                13.dp.toSp()
                            }, textAlign = TextAlign.Center)
                        }

                    }
                }
            }


        }

    }
}

object SF {

    val mutableSharedFlow = MutableSharedFlow<MsgHandler>()

    data class MsgHandler(val msg: String, val value: Float)
}



