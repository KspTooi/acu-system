package com.ksptooi.acu.srv.cmd

import com.google.inject.Inject
import com.ksptooi.acu.entity.cli.CommandInput
import com.ksptooi.acu.srv.engine.CmdEngineSrv
import org.slf4j.Logger

class CmdSchedulerSrvBlock: CmdSchedulerSrv {

    @Inject
    lateinit var log: Logger

    @Inject
    lateinit var engineService: CmdEngineSrv

    @Inject
    lateinit var cmdService: CommandSrv


    override fun asyncSchedule(cio: CommandInput) {

        if(cio.name.isBlank())
            return


        log.info("ACU命令调度(ASYNC) -> ${cio}")


        //获取到数据库命令
        val dbCmd = cmdService.getCommand(cio)
            ?:throw Exception("调度执行失败,在最近的一次数据库检索中没有找到关于该命令的记录.")


        //获取到命令引擎字符串 并通过相关服务获取到命令引擎实例
        val engine = engineService.getEngine(dbCmd.engine)
            ?:throw Exception("调度执行失败,虽然该命令确实存在于数据库中,但该命令的引擎实例已经失效.")


        log.info("Target Engine Instance ->>>>> ${engine}")

        try{
            engine.invoke(cio,dbCmd)
        }catch (e:Exception){
            log.info(e.message)
        }

    }

    override fun schedule(cio: CommandInput): Boolean {
        TODO("Not yet implemented")
    }




}