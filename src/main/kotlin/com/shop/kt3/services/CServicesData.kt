package com.shop.kt3.services

import com.shop.kt3.model.CGood
import com.shop.kt3.model.COrder
import com.shop.kt3.model.CUser
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


object CServicesData {


    fun loadGoods(excel: XSSFWorkbook): List<CGood> {
        var goods = ArrayList<CGood>()

        try {
            var sheet = excel.getSheet("Товары")

            var gid: UUID
            var name: String?
            var price: Double?
            var category: String?

            var row: Row?

            var rowsNum = sheet.lastRowNum
            for (i in 1..rowsNum) {
                row = sheet.getRow(i)
                if (row == null) continue

                gid = UUID.fromString(row.getCell(0).stringCellValue)
                name = row.getCell(1).stringCellValue
                price = row.getCell(2).numericCellValue
                category = row.getCell(3).stringCellValue

                goods.add(CGood(gid, name, price, category))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var outp: List<CGood> = goods
        return outp
    }

    fun loadUsers(excel: XSSFWorkbook): List<CUser> {
        var users = ArrayList<CUser>()

        try {
            var sheet = excel.getSheet("Пользователи")

            var uid: UUID
            var login: String?
            var name: String?
            var sex: Boolean?
            var dateOfBirth: LocalDate?

            var row: Row?

            var rowsNum = sheet.lastRowNum
            for (i in 1..rowsNum) {
                row = sheet.getRow(i)
                if (row == null) continue

                uid = UUID.fromString(row.getCell(0).stringCellValue)
                login = row.getCell(1).stringCellValue
                name = row.getCell(2).stringCellValue
                sex = row.getCell(3).stringCellValue == "ж"
                dateOfBirth = row.getCell(4).dateCellValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                users.add(CUser(uid, login, name, sex, dateOfBirth))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var outp: List<CUser> = users
        return outp
    }

    fun loadOrders(excel: XSSFWorkbook, users: List<CUser>, goods: List<CGood>): List<COrder> {
        var orders = ArrayList<COrder>()

        try {
            var sheet = excel.getSheet("Покупки")

            var owner: CUser?
            var good: CGood?
            var date: LocalDate?

            var row: Row?

            var rowsNum = sheet.lastRowNum
            for (i in 1..rowsNum) {
                row = sheet.getRow(i)
                if (row == null) continue

                owner = findUserById(users, UUID.fromString(row.getCell(0).stringCellValue))
                good = findGoodById(goods, UUID.fromString(row.getCell(1).stringCellValue))
                date = row.getCell(2).dateCellValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                orders.add(COrder(UUID.randomUUID(), owner, date, good))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var outp: List<COrder> = orders
        return outp
    }

    private fun findUserById(users: List<CUser>, id: UUID): CUser {
        var outp: CUser = CUser()

        for (i in 0..users.size) {
            if (users[i].id == id) {
                outp = users[i]
                break
            }
        }

        return outp
    }

    private fun findGoodById(goods: List<CGood>, id: UUID): CGood {
        var outp: CGood = CGood()

        for (i in 0..goods.size) {
            if (goods[i].id == id) {
                outp = goods[i]
                break
            }
        }

        return outp
    }

    fun createReport(goods: List<CGood>): XWPFDocument {
        var document = XWPFDocument()
        try {

            val par1 = document.createParagraph()
            par1.alignment = ParagraphAlignment.CENTER
            val title = par1.createRun()
            title.isBold = true //title

            title.fontFamily = "TimesNewRoman"
            title.fontSize = 16
            title.setText("Report")


            val par2 = document.createParagraph() //description

            par2.alignment = ParagraphAlignment.CENTER
            val desc = par2.createRun()
            desc.fontFamily = "TimesNewRoman"
            desc.fontSize = 13
            desc.setText("Number of orders per good")


            val table = document.createTable()
            val p: XWPFParagraph = document.createParagraph()


            val tableRowOne = table.getRow(0)
            var r = tableRowOne.getCell(0).getParagraphArray(0).createRun();
            r.setText("Name");

            r = tableRowOne.addNewTableCell().addParagraph().createRun();
            r.setText("Count");

            for (good in goods) {
                val tableRow = table.createRow()
                r = tableRow.getCell(0).addParagraph().createRun();
                r.setText(good.name)

                r = tableRow.getCell(1).addParagraph().createRun();
                r.setText(good.orders.size.toString())
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return document
    }
}