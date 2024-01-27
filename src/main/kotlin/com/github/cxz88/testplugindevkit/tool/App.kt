package com.github.cxz88.testplugindevkit.tool

/**
 * @author chenxinzhi
 * @date 2024/1/22
 */

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.intellij.openapi.project.Project


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App(project: Project?, serviceR: MyService?, function: (String) -> Unit, toAdd: (String?) -> Unit = {}) {
    val rowInfo =
        remember {
            serviceR?.run {
                infoMap.entries.map { (key, value) ->
                    RowInfo().apply {
                        text = value.name
                        id = key
                    }
                }.toMutableStateList()
            }


        }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column {
            Row {
                TextButton(
                    onClick = {
                        toAdd(null)
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
                        Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier.size(14.dp))
                        Text(text = "添加", color = Color.White, fontSize = with(LocalDensity.current) {
                            13.dp.toSp()
                        }, textAlign = TextAlign.Center)
                    }

                }
                Spacer(modifier = Modifier.width(15.dp))
                TextButton(
                    onClick = {
                        rowInfo?.let { ss ->
                            ss.filter { it.check }.joinToString { it.id }.let {
                                if (it.isNotEmpty()) {
                                    function(it)
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3374f0)),
                    border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3)),
                    modifier = Modifier.height(24.dp).width(185.dp),
                    contentPadding = PaddingValues(vertical = 0.dp, horizontal = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Create,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp).padding(1.5.dp)
                        )
                        Text(
                            "为所选择的项目生成代码",
                            color = Color.White,
                            fontSize = with(LocalDensity.current) {
                                13.dp.toSp()
                            },
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.weight(1F).background(Color.Transparent).fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .border(BorderStroke(0.1.dp, color = Color(0x22AFB1B3)), RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xff2b2d30))
                    .padding(horizontal = 10.dp)
            ) {

                LazyColumn(modifier = Modifier) {

                    rowInfo?.forEachIndexed { index, item ->
                        item {
                            var hover by remember {
                                mutableStateOf(false)
                            }
                            val animateColorAsState by
                            animateColorAsState(if (hover) Color(0xFF333538) else Color.Transparent)
                            Row(modifier = Modifier.fillMaxWidth()
                                .height(if (rowInfo.size==1) {
                                    70.dp
                                } else if (index == 0 || index == rowInfo.size - 1) {
                                    50.dp
                                } else {
                                    30.dp
                                }
                                )
                                .padding(
                                    top = if (index == 0) 20.dp else 0.dp,
                                    bottom = if (index == rowInfo.size - 1) 20.dp else 0.dp
                                )
                                .background(animateColorAsState)
                                .onPointerEvent(
                                    PointerEventType.Move
                                ) {
                                    hover = true

                                }.onPointerEvent(eventType = PointerEventType.Exit) {
                                    hover = false
                                }, verticalAlignment = Alignment.CenterVertically
                            ) {

                                Checkbox(item.check, onCheckedChange = {
                                    item.check = it
                                }, modifier = Modifier.weight(0.1F), colors = remember {
                                    object : CheckboxColors {
                                        @Composable
                                        override fun borderColor(
                                            enabled: Boolean,
                                            state: ToggleableState
                                        ): State<Color> {
                                            return mutableStateOf(MaterialTheme.colors.onSurface.copy(alpha = 0.6f))
                                        }

                                        @Composable
                                        override fun boxColor(
                                            enabled: Boolean,
                                            state: ToggleableState
                                        ): State<Color> {
                                            return mutableStateOf(Color.Transparent)
                                        }

                                        @Composable
                                        override fun checkmarkColor(state: ToggleableState): State<Color> {
                                            return mutableStateOf(
                                                when (state) {
                                                    ToggleableState.On -> Color(0xFF3374f0)
                                                    ToggleableState.Off -> Color.Transparent
                                                    ToggleableState.Indeterminate -> Color.Transparent
                                                }
                                            )
                                        }

                                    }
                                })
                                remember {
                                    FocusRequester
                                }
                                Text(
                                    item.text,
                                    modifier = Modifier.weight(1.5F).pointerHoverIcon(PointerIcon.Hand).clickable {
                                        toAdd(item.id)
                                    },
                                    color = Color.White,
                                    fontSize = with(LocalDensity.current) {
                                        16.dp.toSp()
                                    },
                                    textAlign = TextAlign.Center
                                )
                            }

                        }

                    }


                }
            }
        }

    }
}

class RowInfo {
    var check by mutableStateOf(false)
    var text by mutableStateOf("")
    var id by mutableStateOf("")
}



