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
import java.nio.file.Files

@RestController
@RequestMapping("/report")
class CControllerReport {

    @Autowired
    private lateinit var repositoryGoods: IRepositoryGoods

    @GetMapping()
    @ResponseBody
    fun getReport(): ByteArray{
        var goods = repositoryGoods.findAll()

        var file = CServicesData.createReport(goods)
        return Files.readAllBytes(file)
    }
}