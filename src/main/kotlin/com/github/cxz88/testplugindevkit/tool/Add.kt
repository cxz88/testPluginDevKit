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
import androidx.compose.ui.awt.ComposeWindow
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
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.local.CoreLocalFileSystem
import com.intellij.openapi.vfs.local.CoreLocalVirtualFile
import java.nio.file.Path
import java.util.*
import javax.swing.JOptionPane


@Composable
@Suppress("DuplicatedCode")
inline fun Add(project: Project?, serviceR: MyService?, id: String? = null, crossinline toMain: (String?) -> Unit = {}) {
    val info: Info = if (serviceR != null && id != null && id != "0") {
        serviceR.infoMap[id] ?: Info()
    } else {
        Info()
    }
    var frontEndPackage by remember { mutableStateOf(info.frontEndPackage) }
    var rearEndPackage by remember { mutableStateOf(info.rearEndPackage) }
    var url by remember { mutableStateOf(info.url) }
    var port by remember { mutableStateOf(info.port) }
    var userName by remember { mutableStateOf(info.userName) }
    var passWord by remember { mutableStateOf(info.passWord) }
    var dataBaseName by remember { mutableStateOf(info.dataBaseName) }
    var tableName by remember { mutableStateOf(info.tableName) }
    var menuName by remember { mutableStateOf(info.menuName) }
    var inheritTenant by remember { mutableStateOf(info.inheritTenant) }
    var name by remember { mutableStateOf(info.name) }
    var service by remember { mutableStateOf(info.service) }
    var `service-api` by remember { mutableStateOf(info.`service-api`) }
    var mou by remember { mutableStateOf(info.mou) }


    Scaffold(
        topBar = {
            TopAppBar(title = {
            }, backgroundColor = Color.Transparent, modifier = Modifier.height(20.dp), elevation = 0.dp)
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
                            toMain(null)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C5052)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(24.dp).width(42.dp),
                        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 6.dp),
                        border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3))
                    ) {
                        Text("返回", fontSize = 13.sp, textAlign = TextAlign.Center, color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextButton(
                        onClick = la@{
                            //检查数据是否都不为空
                            if (frontEndPackage.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "前端目录不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (rearEndPackage.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "后端包不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (url.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "数据库连接地址不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (port.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "端口不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (userName.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "用户名不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (passWord.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "密码不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (dataBaseName.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "数据库名称不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (tableName.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "表名不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (menuName.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "菜单名称不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (service.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "service不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (`service-api`.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "service-api不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (name.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "别名不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            if (mou.isBlank()) {
                                JOptionPane.showMessageDialog(
                                    ComposeWindow(),
                                    "模块不可为空",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE

                                )
                                return@la

                            }
                            val info1 = Info(
                                name,
                                frontEndPackage,
                                rearEndPackage,
                                url,
                                port,
                                userName,
                                passWord,
                                dataBaseName,
                                tableName,
                                menuName,
                                inheritTenant, service, `service-api`, mou, info.sort
                            )
                            serviceR?.let { it.infoMap[(if (id=="0") null else id) ?: UUID.randomUUID().toString()] = info1 }
                            toMain(null)

                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3374f0)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(24.dp).width(42.dp),
                        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 6.dp),
                        border = BorderStroke(0.1.dp, color = Color(0x22AFB1B3))
                    ) {
                        Text("完成", fontSize = 13.sp, textAlign = TextAlign.Center, color = Color.White)
                    }
                }
            }
        },
        backgroundColor = Color.Transparent, contentColor = Color(0xFFAFB1B3),
    ) { pdv ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(pdv).fillMaxSize()
        ) {
            Column(
                Modifier.clip(RoundedCornerShape(5.dp))
                    .border(0.1.dp, Color(0x22AFB1B3), RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xff2b2d30))
                    .padding(10.dp)
            ) {
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
                            modifier = Modifier.weight(0.4F), color = Color.White
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
                                color = Color.White,
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
                modifier = Modifier.fillMaxSize().padding(top = 15.dp)
                    .border(0.1.dp, Color(0x22AFB1B3), RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xff2b2d30))
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
                            modifier = Modifier.weight(1.3F), color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(.1F))
                        Select(project, true, modifier = Modifier.weight(2F), service) { v ->
                            service = v
                        }
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
                            modifier = Modifier.weight(1.3F), color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(.1F))
                        Select(project, modifier = Modifier.weight(2F), c = `service-api`, param = { v ->
                            `service-api` = v
                        })
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(0.1F))
                        BasicTextField(

                            frontEndPackage,
                            onValueChange = {

                            },
                            readOnly = true,
                            enabled = false,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Hand)
                                .clickable {

                                    val f = CoreLocalVirtualFile(CoreLocalFileSystem(), Path.of(frontEndPackage))
                                    FileChooser.chooseFile(
                                        FileChooserDescriptor(false, true, false, false, false, false),
                                        project,
                                        f
                                    ) { vf ->

                                        frontEndPackage = vf.path
                                    }

//                                    JFileChooser(frontEndPackage).apply {
//                                        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
//                                        if (showOpenDialog(ComposeWindow()) == JFileChooser.APPROVE_OPTION) {
//                                            frontEndPackage = selectedFile.absolutePath
//                                        }
//                                    }
                                },

                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = with(LocalDensity.current) {
                                    14.dp.toSp()
                                },
                                textAlign = TextAlign.Start,
                            ), singleLine = true, cursorBrush = SolidColor(Color.White), maxLines = 1, decorationBox = {
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                                color = Color.White,
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
                            modifier = Modifier.weight(1.3F), color = Color.White
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
                            "模块名称",
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = W500,
                            modifier = Modifier.weight(1.3F), color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(.1F))

                        //显示选中的文本
                        BasicTextField(
                            mou,
                            onValueChange = {
                                mou = it
                            },
                            readOnly = false,
                            enabled = true,
                            modifier = Modifier.weight(2F).height(28.dp).clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFF4C5052)).padding(horizontal = 10.dp)
                                .hoverable(remember { MutableInteractionSource() }, true)
                                .pointerHoverIcon(PointerIcon.Text),
                            textStyle = TextStyle(
                                color = Color.White,
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

class Props(private val project: Project?, private val isService: Boolean, param: (String) -> Unit, c: String) {
    var expanded = false
    val selectList: List<String>
        get() = project?.let { p ->
            val instance = ModuleManager.getInstance(p)
            val modules = instance.modules
            modules.map { it.name }.filter { if (isService) it.endsWith("service") else it.endsWith("api") }
                .toList()
        } ?: listOf("")
    var check: String by mutableStateOf(
        c
    )
    lateinit var callBack: (String, Boolean) -> Unit
}


@Composable
fun Select(project: Project?, isService: Boolean = false, modifier: Modifier, c: String, param: (String) -> Unit) {
    val props = remember(project) {
        Props(project, isService, param, c)
    }

    /**
     * 控制下拉选择框
     */
    var dropExpand by remember { mutableStateOf(props.expanded) }
    //回调函数
    props.callBack = fun(check: String, expand: Boolean) {
        dropExpand = expand
        props.check = check
        param.invoke(check)
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
        maxLines = 1,
        enabled = false,
        modifier = Modifier.height(28.dp).fillMaxWidth().clip(RoundedCornerShape(5.dp))
            .background(Color(0xFF4C5052)).hoverable(remember { MutableInteractionSource() }, true)
            .pointerHoverIcon(PointerIcon.Default)
            .clickable {
                props.check.let { props.callBack(it, true) }
            },
        textStyle = TextStyle(color = Color.White, fontSize = with(LocalDensity.current) {
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
                color = Color.White,
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




