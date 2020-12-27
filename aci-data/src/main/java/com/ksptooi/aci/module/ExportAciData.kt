package com.ksptooi.aci.module

import com.google.inject.AbstractModule
import com.google.inject.Scope
import com.google.inject.Scopes
import com.google.inject.persist.PersistModule
import com.google.inject.persist.jpa.JpaPersistModule
import com.ksptooi.aci.jpa.mapper.AdvEntityManager
import com.ksptooi.aci.jpa.mapper.AdvEntityManagerBlock
import com.ksptooi.mapper.CommandMapper
import com.ksptooi.mapper.CommandMapperBlock

open class ExportAciData:AbstractModule() {


    override fun configure() {

        install(JpaPersistModule("asca_data"));

        bind(AdvEntityManager::class.java).to(AdvEntityManagerBlock::class.java).`in`(Scopes.SINGLETON)
        bind(CommandMapper::class.java).to(CommandMapperBlock::class.java)

    }


}