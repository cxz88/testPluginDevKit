package com.github.cxz88.testplugindevkit.tool

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
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

    class SampleDialog(private val project: Project?) : DialogWrapper(project, false, IdeModalityType.MODELESS) {

        init {
            title = "生成代码"
            isResizable = true
            init()
        }

        override fun createCenterPanel(): JComponent {
            setSize(1000, 600)
            return ComposePanel().apply {
                setContent {
                    MaterialTheme(colors = darkColors(surface = Color.Transparent, background = Color.Transparent)) {
                        PreComposeApp {

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