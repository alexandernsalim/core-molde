package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.master.service.ExportService;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.service.OrderService;
import com.ta.coremolde.shop.service.ProductService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public ByteArrayInputStream exportProduct() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");
        List<String> headers = Arrays.asList("ID", "Name", "Description", "Weight", "Price", "Stock");
        Row header = sheet.createRow(0);
        Cell headerCell;
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

        for (int i = 0; i < headers.size(); i++) {
            headerCell = header.createCell(i);
            headerCell.setCellValue(headers.get(i));
            headerCell.setCellStyle(headerStyle);
        }

        List<Product> products = productService.getAllProduct();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(product.getId());
            cell = row.createCell(1);
            cell.setCellValue(product.getName());
            cell = row.createCell(2);
            cell.setCellValue(product.getDescription());
            cell = row.createCell(3);
            cell.setCellValue(product.getWeight());
            cell = row.createCell(4);
            cell.setCellValue(product.getPrice());
            cell = row.createCell(5);
            cell.setCellValue(product.getStock());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_EXPORT.getCode(),
                    ErrorResponse.FAILED_TO_EXPORT.getMessage()
            );
        }
    }

    @Override
    public ByteArrayInputStream exportOrder() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");
        List<String> headers = Arrays.asList("ID", "Transaction No", "Total Price", "Shipment Price", "Total Payment Price", "Transaction Date");
        Row header = sheet.createRow(0);
        Cell headerCell;
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

        for (int i = 0; i < headers.size(); i++) {
            headerCell = header.createCell(i);
            headerCell.setCellValue(headers.get(i));
            headerCell.setCellStyle(headerStyle);
        }

        List<Order> orders = orderService.getShopOrder();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(order.getId());
            cell = row.createCell(1);
            cell.setCellValue(order.getTransactionNo());
            cell = row.createCell(2);
            cell.setCellValue(order.getTotalPrice());
            cell = row.createCell(3);
            cell.setCellValue(order.getShipment().getTotalShipmentPrice());
            cell = row.createCell(4);
            cell.setCellValue(order.getTotalPaymentPrice());
            cell = row.createCell(5);
            cell.setCellValue(order.getTransactionDate());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw new MoldeException(
                    ErrorResponse.FAILED_TO_EXPORT.getCode(),
                    ErrorResponse.FAILED_TO_EXPORT.getMessage()
            );
        }
    }
}
