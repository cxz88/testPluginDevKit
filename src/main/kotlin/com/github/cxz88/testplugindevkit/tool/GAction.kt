package com.github.cxz88.testplugindevkit.tool

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import moe.tlaster.precompose.PreComposeApp
import javax.swing.JComponent

class GAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        //应该弹出窗口
        SampleDialog(event.project).show()

    }

    class SampleDialog(private val project: Project?) : DialogWrapper(project) {

        init {
            title = "生成代码"
            isResizable = false
            init()
        }

        override fun createCenterPanel(): JComponent {
            return ComposePanel().apply {
                setContent {
                    MaterialTheme(colors = darkColors(surface = Color.Transparent, background = Color.Transparent)) {
                        PreComposeApp {
                            with(LocalDensity.current) {
                                setBounds(0, 0, 1000.dp.roundToPx(), 600.dp.roundToPx())

                            }
                            CompositionLocalProvider(
                                LocalIndication provides NoIndication
                            ) {
                                NavApp(project)
                            }


                        }

                    }
                }
            }
        }

        override fun createSouthPanel(): JComponent? {
            return null
        }
    }

}