package com.github.cxz88.testplugindevkit.tool

/**
 * @author chenxinzhi
 * @date 2024/1/22
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.intellij.openapi.project.Project


@Composable
fun App(project: Project?, toAdd: () -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column {
            Row {
                TextButton(
                    onClick = {
                        toAdd()



                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C5052)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(24.dp).width(56.dp),
                    contentPadding = PaddingValues(vertical = 0.dp, horizontal = 6.dp),
                    border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.Add, null, tint = Color(0xFFAFB1B3), modifier = Modifier.size(14.dp))
                        Text(text = "添加", color = Color(0xFFd9dbdf), fontSize = with(LocalDensity.current) {
                            13.dp.toSp()
                        }, textAlign = TextAlign.Center)
                    }

                }
                Spacer(modifier = Modifier.width(15.dp))
                TextButton(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C5052)),
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
                            tint = Color(0xFFAFB1B3),
                            modifier = Modifier.size(14.dp).padding(1.5.dp)
                        )
                        Text(
                            "为所选择的项目生成代码",
                            color = Color(0xFFd9dbdf),
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
                    .border(BorderStroke(0.1.dp, color = Color(0x22AFB1B3)), RoundedCornerShape(8.dp))
            ) {
                LazyColumn {
                    item {
                        Text("111")
                    }

                }
            }
        }

    }
}



