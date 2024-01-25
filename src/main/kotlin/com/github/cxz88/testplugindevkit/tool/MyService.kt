package com.github.cxz88.testplugindevkit.tool

import androidx.compose.runtime.mutableStateMapOf
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "cxz", storages = [Storage(value = "cxzProperty.xml")])
@Service(Service.Level.PROJECT)
class MyService(
    var infoMap: MutableMap<String, Info> = mutableStateMapOf()
) : PersistentStateComponent<MyService> {


    override fun getState(): MyService {
        return this
    }


    override fun loadState(state: MyService) {
        XmlSerializerUtil.copyBean(state, this);
    }


}

data class Info(
    var name: String = "",
    var frontEndPackage: String = "",
    var rearEndPackage: String = "",
    var url: String = "",
    var port: String = "",
    var userName: String = "",
    var passWord: String = "",
    var dataBaseName: String = "",
    var tableName: String = "",
    var menuName: String = "",
    var inheritTenant: Boolean = true,
    var service: String = "",
    var `service-api`: String = ""
)



