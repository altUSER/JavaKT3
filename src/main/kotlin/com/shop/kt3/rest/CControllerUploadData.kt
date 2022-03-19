package com.shop.kt3.rest

import com.shop.kt3.model.CGood
import com.shop.kt3.model.COrder
import com.shop.kt3.model.CUser
import com.shop.kt3.repositories.IRepositoryGoods
import com.shop.kt3.repositories.IRepositoryOrders
import com.shop.kt3.repositories.IRepositoryUsers
import com.shop.kt3.services.CServicesData
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@RestController
@RequestMapping("/upload")
class CControllerUploadData {
    @Autowired
    private lateinit var repositoryGoods: IRepositoryGoods

    @Autowired
    private lateinit var repositoryUsers: IRepositoryUsers
    @Autowired
    private lateinit var repositoryOrders: IRepositoryOrders


    @PostMapping
    fun handleFileUpload(@RequestParam("file") file: MultipartFile) {
        val targetStream = ByteArrayInputStream(file.bytes)
        val excel = XSSFWorkbook(targetStream)

        var goods: List<CGood> = CServicesData.loadGoods(excel)
        repositoryGoods.saveAllAndFlush(goods)

        var users: List<CUser> = CServicesData.loadUsers(excel)
        repositoryUsers.saveAllAndFlush(users)

        var orders: List<COrder> = CServicesData.loadOrders(excel, users, goods)
        repositoryOrders.saveAllAndFlush(orders)
    }
}