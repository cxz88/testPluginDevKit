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


object BladeCodeGenerator {
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
        serviceY: MyService?
    ) {

        //获取模块
        project?.apply {
            val instance = ModuleManager.getInstance(this)
            val modules = instance.modules
            var pr = 0F
            val p1 = 1F / toList.size
            val p2 = p1 / 2
            serviceY?.apply {
                try {
                    toList.map { infoMap[it] }
                        .forEach { info ->
                            (info ?: throw Exception("无法获取到具体信息")).apply {
                                val find = modules.find {
                                    this.service == it.name
                                }
                                val servicePath = find?.run {
                                    ModuleRootManager.getInstance(this).contentRoots.first().path
                                }
                                val find1 = modules.find {
                                    this.`service-api` == it.name
                                }
                                val serviceApiPath = find1?.run {
                                    ModuleRootManager.getInstance(this).contentRoots.first().path
                                }
                                //组装api的文件路径
                                val packageName = rearEndPackage.replace(".", "/")
                                pr += p2
                                mutableSharedFlow.emit(SF.MsgHandler("正在构建", pr))
                                FastAutoGenerator.create(
                                    "jdbc:mysql://${url}:${port}/${dataBaseName}",
                                    userName,
                                    passWord
                                ).run {
                                    globalConfig { builder ->
                                        with(builder) {
                                            author("大帅比") // 设置作者
                                            outputDir("$serviceApiPath")
                                            enableSwagger()
                                            disableOpenDir()
                                        }

                                    }
                                    packageConfig { builder ->
                                        with(builder) {
                                            parent(rearEndPackage)
                                            moduleName("")
                                            entity("entity.${mou}")
                                            service("service.${mou}")
                                            serviceImpl("service.impl.${mou}")
                                            mapper("mapper.${mou}")
                                            controller("controller.${mou}")
                                            pathInfo(
                                                mapOf(
                                                    OutputFile.entity to "${serviceApiPath}/src/main/java/${packageName}/entity/${mou}",
                                                    OutputFile.xml to "${servicePath}/src/main/java/${packageName}/mapper/${mou}",
                                                    OutputFile.mapper to "${servicePath}/src/main/java/${packageName}/mapper/${mou}",
                                                    OutputFile.service to "${servicePath}/src/main/java/${packageName}/service/${mou}",
                                                    OutputFile.serviceImpl to "${servicePath}/src/main/java/${packageName}/service/Impl/${mou}",
                                                    OutputFile.controller to "${servicePath}/src/main/java/${packageName}/controller/${mou}",
                                                )

                                            )
                                        }

                                    }
                                    strategyConfig { builder ->
                                        with(builder) {
                                            addInclude(tableName)
                                            with(entityBuilder()) {
                                                addSuperEntityColumns(SUPER_ENTITY_COLUMNS)
                                                superClass("org.springblade.core.tenant.mp.TenantEntity")
                                                enableLombok()
                                                enableFileOverride()
                                            }
                                            with(serviceBuilder()) {
                                                superServiceClass("org.springblade.core.mp.base.BaseService")
                                                superServiceImplClass("org.springblade.core.mp.base.BaseServiceImpl")
                                                enableFileOverride()
                                            }
                                            with(controllerBuilder()) {
                                                superClass("org.springblade.core.boot.ctrl.BladeController")
                                                enableFileOverride()
                                            }
                                            with(mapperBuilder()) {
                                                enableFileOverride()
                                            }
                                        }
                                    }
                                    templateConfig { template ->
                                        with(template) {
                                            controller("templates/mom/controller.java.vm")
                                            xml("templates/mom/mapper.xml.vm")
                                            mapper("templates/mom/mapper.java.vm")
                                            entity("templates/mom/entity.java.vm")
                                            service("templates/mom/service.java.vm")
                                            serviceImpl("templates/mom/serviceImpl.java.vm")
                                        }
                                    }
                                    injectionConfig { consumer ->
                                        val map = mutableMapOf(
                                            "codeName" to menuName,
                                            "servicePackageLowerCase" to "${
                                                rearEndPackage.split(".").last()
                                            }/${mou}",
                                            "serviceName" to service,
                                            "servicePackage" to mou,
                                            "commonFields" to SUPER_ENTITY_COLUMNS.map { it to StringUtils.underlineToCamel(it) }

                                        )
                                        with(consumer) {
                                            beforeOutputFile { biConsumer, _ ->
                                                map["entityKey"] = biConsumer.name
                                                map["menuId"] = IdWorker.getId()
                                                map["addMenuId"] = IdWorker.getId()
                                                map["removeMenuId"] = IdWorker.getId()
                                                map["viewMenuId"] = IdWorker.getId()
                                                map["editMenuId"] = IdWorker.getId()
                                            }
                                            customFile(
                                                listOf(
                                                    CustomFile.Builder().filePath(
                                                        "${frontEndPackage}/src/view/${
                                                            rearEndPackage.split(".").last()
                                                        }/${mou}"
                                                    )
                                                        .templatePath("templates/saber/crud.vue.vm")
                                                        .formatNameFunction { it.name }
                                                        .fileName(".vue")
                                                        .enableFileOverride()
                                                        .build(),
                                                    CustomFile.Builder().filePath(
                                                        "${frontEndPackage}/src/api/${
                                                            rearEndPackage.split(".").last()
                                                        }/${mou}"
                                                    )
                                                        .templatePath("templates/saber/api.js.vm")
                                                        .formatNameFunction { it.name }
                                                        .enableFileOverride()
                                                        .fileName(".js")
                                                        .build(),
                                                    CustomFile.Builder()
                                                        .filePath("${servicePath}/src/main/java/sql/${mou}")
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
                                            customMap(map)
                                        }


                                    }
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
