package com.ksptooi.mapper

import com.google.inject.Inject
import com.google.inject.persist.PersistService
import com.ksptooi.aci.entity.Command
import com.ksptooi.aci.jpa.mapper.AdvEntityManager
import javax.persistence.EntityManager
import javax.persistence.NoResultException

open class CommandMapperBlock:CommandMapper {

    @Inject
    lateinit var em: EntityManager


    override fun getCommandByName(name: String): Command {
        val q = em.createQuery("from Command where name=:name")
        q.setParameter("name",name)
        return q.singleResult as Command;
    }

    override fun exists(name: String): Boolean {

        try{
            this.getCommandByName(name);
        }catch (e: NoResultException){
            return false
        }

        return true;
    }


}