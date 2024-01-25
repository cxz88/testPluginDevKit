package com.github.cxz88.testplugindevkit.tool

/**
 * @author chenxinzhi
 * @date 2024/1/23
 */

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project


@Composable
fun Add(project: Project?, toMain: () -> Unit = {}) {
    var frontEndPackage by remember { mutableStateOf("") }
    var rearEndPackage by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var dataBaseName by remember { mutableStateOf("") }
    var tableName by remember { mutableStateOf("") }
    var menuName by remember { mutableStateOf("") }
    var inheritTenant by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(title = {
            }, backgroundColor = Color.Transparent, modifier = Modifier.height(30.dp), elevation = 0.dp)
        },
        bottomBar = {
            BottomAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            toMain()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C5052)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(24.dp).width(42.dp),
                        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 6.dp),
                        border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3))
                    ) {
                        Text("返回", fontSize = 13.sp, textAlign = TextAlign.Center, color = Color(0xFFd9dbdf))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextButton(
                        onClick = {

                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3374f0)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(24.dp).width(42.dp),
                        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 6.dp),
                        border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3))
                    ) {
                        Text("完成", fontSize = 13.sp, textAlign = TextAlign.Center, color = Color(0xFFd9dbdf))
                    }
                }
            }
        },
        backgroundColor = Color.Transparent, contentColor = Color(0xFFAFB1B3),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            Column  (Modifier.clip(RoundedCornerShape(5.dp))
                .border(0.1.dp, Color(0x22AFB1B3), RoundedCornerShape(5.dp))
                .padding(10.dp)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(0.35F)
                    ) {
                        Text(
                            "别名",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(0.4F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            name,
                            onValueChange = {
                                name = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )


                    }

                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 15.dp).clip(RoundedCornerShape(5.dp))
                    .border(0.1.dp, Color(0x22AFB1B3), RoundedCornerShape(5.dp))
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(
                            "选择service模块",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))
                        Select(project, true, modifier = Modifier.weight(2F))
                    }
                    Spacer(Modifier.weight(0.1F))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(
                            "选择service-api模块",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))
                        Select(project, modifier = Modifier.weight(2F))
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "后端包名",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            rearEndPackage,
                            onValueChange = {
                                rearEndPackage = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )


                    }
                    Spacer(modifier = Modifier.weight(0.1F))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "前端目录",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(0.1F))
                        BasicTextField(

                            frontEndPackage,
                            onValueChange = {
                                frontEndPackage = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "数据库连接地址",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            url,
                            onValueChange = {
                                url = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )


                    }
                    Spacer(modifier = Modifier.weight(0.1F))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "端口",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(0.1F))
                        BasicTextField(

                            port,
                            onValueChange = {
                                port = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "用户名",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            userName,
                            onValueChange = {
                                userName = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )


                    }
                    Spacer(modifier = Modifier.weight(0.1F))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "密码",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(0.1F))
                        BasicTextField(
                            passWord,
                            onValueChange = {
                                passWord = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White),

                            maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "数据库名称",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            dataBaseName,
                            onValueChange = {
                                dataBaseName = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )


                    }
                    Spacer(modifier = Modifier.weight(0.1F))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "表名",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(0.1F))
                        BasicTextField(
                            tableName,
                            onValueChange = {
                                tableName = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White),

                            maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )
                    }

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "菜单名称",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            menuName,
                            onValueChange = {
                                menuName = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color(0xFFd9dbdf),
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    it()
                                }
                            }
                        )


                    }
                    Spacer(modifier = Modifier.weight(0.1F))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(1F)
                    ) {
                        Text(
                            "需要继承租户实体类",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F)
                        )
                        Spacer(modifier = Modifier.weight(0.1F))
                        Row(
                            modifier = Modifier.weight(2F),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            CompositionLocalProvider(
                                LocalIndication provides NoIndication
                            ) {
                                Switch(
                                    checked = inheritTenant,
                                    onCheckedChange = {
                                        inheritTenant = it
                                    },
                                    modifier = Modifier.fillMaxWidth(0.3F).height(28.dp),
                                    colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF3374f0))
                                )
                            }

                        }
                    }

                }
            }

        }
    }

}

object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}

class Props(private val project: Project?, private val isService: Boolean) {
    var expanded = false
    val selectList: List<String>
        get() = project?.let { p ->
            val instance = ModuleManager.getInstance(p)
            val modules = instance.modules
            modules.map { it.name }.filter { if (isService) it.endsWith("service") else it.endsWith("api") }
                .toList()
        } ?: listOf("")
    var check: String by mutableStateOf(
        if (selectList.isEmpty()) {
            ""
        } else {
            selectList[0]
        }
    )
    lateinit var callBack: (String, Boolean) -> Unit
}


@Composable
fun Select(project: Project?, isService: Boolean = false, modifier: Modifier) {
    val props = remember(project) {
        Props(project, isService)
    }

    /**
     * 控制下拉选择框
     */
    var dropExpand by remember { mutableStateOf(props.expanded) }
    //回调函数
    props.callBack = fun(check: String, expand: Boolean) {
        dropExpand = expand
        props.check = check
    }
    val prop by remember { mutableStateOf(props) }
    val rememberScrollState = rememberScrollState()
    Column(horizontalAlignment = Alignment.Start, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            SelectedTextBox(prop)
        }
        DropdownMenu(
            scrollState = rememberScrollState,
            expanded = dropExpand,
            onDismissRequest = {
                props.expanded = false
                props.check.let { props.callBack(it, false) }
            },
            modifier = Modifier
                .background(Color(0xFF2b2d30)).fillMaxWidth(0.255F).height(220.dp)
        ) {
            SelectOption(prop)
        }


    }


}


@Composable
fun SelectedTextBox(props: Props) {

    //显示选中的文本
    BasicTextField(
        props.check,
        onValueChange = {
            props.check = it
        },
        readOnly = true,
        enabled = false,
        modifier = Modifier.height(28.dp).fillMaxWidth().clip(RoundedCornerShape(5.dp))
            .background(Color(0xFF4C5052)).hoverable(remember { MutableInteractionSource() }, true)
            .pointerHoverIcon(PointerIcon.Default)
            .clickable {
                props.check.let { props.callBack(it, true) }
            },
        textStyle = TextStyle(color = Color(0xFFd9dbdf), fontSize = with(LocalDensity.current) {
            14.dp.toSp()
        }, textAlign = TextAlign.Left),
        decorationBox = { text ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().pointerHoverIcon(PointerIcon.Default)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 9.dp).weight(1F).pointerHoverIcon(PointerIcon.Default),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    text()
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    "",
                    tint = Color(0xFFAFB1B3),
                    modifier = Modifier.padding(top = 5.dp).size(20.dp).weight(0.2F)
                        .pointerHoverIcon(PointerIcon.Default)
                )
            }

        }

    )
}


@Composable
fun SelectOption(props: Props) {
    for (it in props.selectList) {
        DropdownMenuItem(onClick = {
            props.callBack(it, false)
        }, modifier = Modifier.height(25.dp)) {
            Text(
                it.trim(),
                color = Color(0xFFd9dbdf),
                textAlign = TextAlign.Left,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = with(LocalDensity.current) {
                    14.dp.toSp()
                }, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}




