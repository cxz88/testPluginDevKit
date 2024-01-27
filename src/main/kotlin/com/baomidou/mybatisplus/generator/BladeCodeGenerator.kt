//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.baomidou.mybatisplus.generator

import com.baomidou.mybatisplus.core.toolkit.IdWorker
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.builder.CustomFile
import com.github.cxz88.testplugindevkit.tool.MyService
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import kotlinx.coroutines.flow.MutableSharedFlow


class BladeCodeGenerator {
    private val SUPER_ENTITY_COLUMNS =
        listOf(
            "id",
            "create_time",
            "create_user",
            "create_dept",
            "update_time",
            "update_user",
            "status",
            "is_deleted",
            "tenant_id"
        )

    suspend fun run(
        mutableSharedFlow: MutableSharedFlow<Float>, toList: List<String>, project: Project?, service: MyService?
    ) {

        //获取模块
        project?.let { p ->
            val instance = ModuleManager.getInstance(p)
            val modules = instance.modules
            service?.let { sr ->
                for (s in toList) {
                    val info = sr.infoMap[s]
                    info?.run {
                        val find = modules.find {
                            this.service == it.name
                        }
                        val servicePath = find?.let { it1 ->
                            val roots = ModuleRootManager.getInstance(it1).contentRoots
                            if (roots.isEmpty()) {
                                throw Exception("无法获取到service路径位置")
                            }
                            roots.first().path
                        }
                        val find1 = modules.find {
                            this.`service-api` == it.name
                        }
                        val serviceApiPath = find1?.let { it1 ->
                            val roots = ModuleRootManager.getInstance(it1).contentRoots
                            if (roots.isEmpty()) {
                                throw Exception("无法获取到service-api路径位置")
                            }
                            roots.first().path
                        }
                        //组装api的文件路径
                        val packageName = info.rearEndPackage.replace(".", "/")
                        FastAutoGenerator.create(
                            "jdbc:mysql://${info.url}:${info.port}/${info.dataBaseName}", info.userName, info.passWord
                        ).globalConfig { builder ->
                            builder.author("大帅比") // 设置作者
                                .outputDir("$serviceApiPath").disableOpenDir()
                        }.packageConfig { builder ->
                            builder
                                .pathInfo(
                                    mapOf(
                                        OutputFile.entity to "${serviceApiPath}/src/main/java/${packageName}/entity/${mou}",
                                        OutputFile.xml to "${servicePath}/src/main/java/${packageName}/mapper/${mou}",
                                        OutputFile.mapper to "${servicePath}/src/main/java/${packageName}/mapper/${mou}",
                                        OutputFile.service to "${servicePath}/src/main/java/${packageName}/service/${mou}",
                                        OutputFile.serviceImpl to "${servicePath}/src/main/java/${packageName}/service/Impl/${mou}",
                                        OutputFile.controller to "${servicePath}/src/main/java/${packageName}/controller/${mou}",
                                    )

                                ) // 设置mapperXml生成路径
                        }.strategyConfig { builder ->
                            builder.apply {
                                addInclude(info.tableName)
                            }
                            builder.entityBuilder().apply {
                                addSuperEntityColumns(SUPER_ENTITY_COLUMNS)
                                superClass("org.springblade.core.tenant.mp.TenantEntity")
                                enableLombok()
                            }
                            builder.serviceBuilder().apply {
                                superServiceClass("org.springblade.core.mp.base.BaseService")
                                superServiceImplClass("org.springblade.core.mp.base.BaseServiceImpl")
                            }
                            builder.controllerBuilder().apply {
                                superClass("org.springblade.core.boot.ctrl.BladeController")
                            }
                        }.templateConfig { template ->
                            template.apply {
                                controller("templates/controllor.java.vm")
                                xml("templates/mapper.xml.vm")
                                mapper("templates/mapper.java.vm")
                                entity("templates/entity.java.vm")
                                service("templates/service.java.vm")
                                serviceImpl("templates/serviceImpl.java.vm")
                            }
                        }.injectionConfig { consumer ->
                            val map = mutableMapOf<String, Any>(
                                "codeName" to info.menuName,
                                "servicePackageLowerCase" to info.mou,
                                "serviceName" to info.service
                            )
                            consumer.beforeOutputFile { biConsumer, _ ->
                                map["entityKey"] = biConsumer.entityName.lowercase()
                                map["menuId"] = IdWorker.getId()
                                map["addMenuId"] = IdWorker.getId()
                                map["removeMenuId"] = IdWorker.getId()
                                map["viewMenuId"] = IdWorker.getId()
                                map["editMenuId"] = IdWorker.getId()
                            }
                            consumer.customFile(
                                listOf(
                                    CustomFile.Builder().filePath("${info.frontEndPackage}/src/view/${mou}")
                                        .templatePath("templates/saber/crud.vue.vm")
                                        .fileName("${info.tableName.lowercase()}.vue")
                                        .build(),
                                    CustomFile.Builder().filePath("${info.frontEndPackage}/src/api/${mou}")
                                        .templatePath("templates/saber/api.js.vm")
                                        .fileName("${info.tableName.lowercase()}.js")
                                        .build(),
                                    CustomFile.Builder().filePath("${servicePath}/src/main/java/sql/${mou}")
                                        .templatePath("templates/sql/menu.sql.vm")
                                        .fileName("${info.tableName.lowercase()}.sql")
                                        .build(),
                                    CustomFile.Builder()
                                        .filePath("${serviceApiPath}/src/main/java/${packageName}/dto/${mou}")
                                        .templatePath("templates/entityDTO.java.vm")
                                        .build(),
                                    CustomFile.Builder()
                                        .filePath("${serviceApiPath}/src/main/java/${packageName}/vo/${mou}")
                                        .templatePath("templates/entityVO.java.vm")
                                        .build()

                                ),

                                )
                            consumer.customMap(map)

                        }.execute()
                    }


                }


            }


        }

    }


}
