//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.baomidou.mybatisplus.generator

import com.baomidou.mybatisplus.core.toolkit.IdWorker
import com.baomidou.mybatisplus.core.toolkit.StringUtils
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.builder.CustomFile
import com.github.cxz88.testplugindevkit.tool.MyService
import com.github.cxz88.testplugindevkit.tool.SF
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
        mutableSharedFlow: MutableSharedFlow<SF.MsgHandler>,
        toList: List<String>,
        project: Project?,
        service: MyService?
    ) {

        //获取模块
        project?.let { p ->
            val instance = ModuleManager.getInstance(p)
            val modules = instance.modules
            var pr = 0F
            val p1 = 1F / toList.size
            val p2 = p1 / 2
            service?.let { sr ->
                try {
                    toList.map { sr.infoMap[it] }
                        .forEach { info ->
                            info ?: throw Exception("无法获取到具体信息")
                            info.run {
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
                                pr += p2
                                mutableSharedFlow.emit(SF.MsgHandler("正在构建", pr))

                                FastAutoGenerator.create(
                                    "jdbc:mysql://${info.url}:${info.port}/${info.dataBaseName}",
                                    info.userName,
                                    info.passWord
                                ).globalConfig { builder ->
                                    builder.author("大帅比") // 设置作者
                                        .outputDir("$serviceApiPath")
                                        .enableSwagger()
                                        .disableOpenDir()
                                }.packageConfig { builder ->
                                    builder
                                        .parent(info.rearEndPackage)
                                        .moduleName("")
                                        .entity("entity.${info.mou}")
                                        .service("service.${info.mou}")
                                        .serviceImpl("service.impl.${info.mou}")
                                        .mapper("mapper.${info.mou}")
                                        .controller("controller.${info.mou}")
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
                                        enableFileOverride()
                                    }
                                    builder.serviceBuilder().apply {
                                        superServiceClass("org.springblade.core.mp.base.BaseService")
                                        superServiceImplClass("org.springblade.core.mp.base.BaseServiceImpl")
                                        enableFileOverride()
                                    }
                                    builder.controllerBuilder().apply {
                                        superClass("org.springblade.core.boot.ctrl.BladeController")
                                        enableFileOverride()
                                    }
                                    builder.mapperBuilder().apply {
                                        enableFileOverride()
                                    }
                                }.templateConfig { template ->
                                    template.apply {
                                        controller("templates/mom/controller.java.vm")
                                        xml("templates/mom/mapper.xml.vm")
                                        mapper("templates/mom/mapper.java.vm")
                                        entity("templates/mom/entity.java.vm")
                                        service("templates/mom/service.java.vm")
                                        serviceImpl("templates/mom/serviceImpl.java.vm")
                                    }
                                }.injectionConfig { consumer ->
                                    val map = mutableMapOf(
                                        "codeName" to info.menuName,
                                        "servicePackageLowerCase" to "${
                                            info.rearEndPackage.split(".").last()
                                        }/${info.mou}",
                                        "serviceName" to info.service,
                                        "servicePackage" to info.mou,
                                        "commonFields" to SUPER_ENTITY_COLUMNS.map { StringUtils.camelToUnderline(it) to it }

                                    )
                                    consumer.beforeOutputFile { biConsumer, _ ->
                                        map["entityKey"] = biConsumer.name
                                        map["menuId"] = IdWorker.getId()
                                        map["addMenuId"] = IdWorker.getId()
                                        map["removeMenuId"] = IdWorker.getId()
                                        map["viewMenuId"] = IdWorker.getId()
                                        map["editMenuId"] = IdWorker.getId()
                                    }
                                    consumer.customFile(
                                        listOf(
                                            CustomFile.Builder().filePath(
                                                "${info.frontEndPackage}/src/view/${
                                                    info.rearEndPackage.split(".").last()
                                                }/${mou}"
                                            )
                                                .templatePath("templates/saber/crud.vue.vm")
                                                .formatNameFunction { it.name }
                                                .fileName(".vue")
                                                .enableFileOverride()
                                                .build(),
                                            CustomFile.Builder().filePath(
                                                "${info.frontEndPackage}/src/api/${
                                                    info.rearEndPackage.split(".").last()
                                                }/${mou}"
                                            )
                                                .templatePath("templates/saber/api.js.vm")
                                                .formatNameFunction { it.name }
                                                .enableFileOverride()
                                                .fileName(".js")
                                                .build(),
                                            CustomFile.Builder().filePath("${servicePath}/src/main/java/sql/${mou}")
                                                .templatePath("templates/sql/menu.sql.vm")
                                                .formatNameFunction { it.name }
                                                .enableFileOverride()
                                                .fileName(".sql")
                                                .build(),
                                            CustomFile.Builder()
                                                .filePath("${serviceApiPath}/src/main/java/${packageName}/dto/${mou}")
                                                .templatePath("templates/mom/entityDTO.java.vm")
                                                .formatNameFunction { it.entityName }
                                                .enableFileOverride()
                                                .fileName("DTO.java")
                                                .build(),
                                            CustomFile.Builder()
                                                .filePath("${serviceApiPath}/src/main/java/${packageName}/vo/${mou}")
                                                .templatePath("templates/mom/entityVO.java.vm")
                                                .formatNameFunction { it.entityName }
                                                .enableFileOverride()
                                                .fileName("VO.java")
                                                .build()

                                        ),

                                        )
                                    consumer.customMap(map)


                                }.execute()
                                pr += p2
                                mutableSharedFlow.emit(SF.MsgHandler("正在构建", pr))


                            }
                        }
                } catch (e: Exception) {
                    mutableSharedFlow.emit(SF.MsgHandler("构建失败:${e.message}", -1F))
                }
                mutableSharedFlow.emit(SF.MsgHandler("构建成功", 1F))


            }


        }

    }


}
