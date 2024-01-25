package com.github.cxz88.testplugindevkit.tool

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "cxz123", storages = [Storage(value = "cxzOK.xml")])
@Service(Service.Level.PROJECT)
internal class MyService(

) : PersistentStateComponent<MyService> {
    var name: String = ""

    override fun getState(): MyService {
        return this
    }


    override fun loadState(state: MyService) {
        XmlSerializerUtil.copyBean(state, this);
    }


}



