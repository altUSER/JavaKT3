package com.shop.kt3.rest

import com.shop.kt3.repositories.IRepositoryGoods
import com.shop.kt3.services.CServicesData
import org.apache.commons.io.IOUtils
import org.apache.xmlbeans.impl.common.IOUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import java.nio.file.Files

@RestController
@RequestMapping("/report")
class CControllerReport {

    @Autowired
    private lateinit var repositoryGoods: IRepositoryGoods

    @GetMapping(produces = arrayOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
    fun getReport(): ByteArray{
        val goods = repositoryGoods.findAll()
        val file = CServicesData.createReport(goods)

        val outputStream = ByteArrayOutputStream()
        file.write(outputStream)
        file.close()

        return outputStream.toByteArray()
    }
}