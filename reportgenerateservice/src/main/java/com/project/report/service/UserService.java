package com.project.report.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.report.model.EventInfo;
import com.project.report.model.UserProfile;
import com.project.report.repo.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public Flux<UserProfile> getAll() {
        return userRepo.findAll();
    }

    public Mono<List<UserProfile>> getList() {
        return getAll().collectList();
    }

    public ByteArrayInputStream convertToExcel(List<UserProfile> userList) throws IOException {
        String[] COLUMNs = { "Id", "Name", "Volunteer Hours", "Travel Hours", "Lives Impacted", "Business Unit",
                "Status", "EventId", "Event Location", "Event Name" };

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet userSheet = workbook.createSheet("Users");

            File file = new File("Report.xlsx");
            FileOutputStream fileOut;

            file.createNewFile();
            fileOut = new FileOutputStream(file);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = userSheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            AtomicInteger rowIdx = new AtomicInteger(1);
            for (UserProfile user : userList) {
                if (user.getEventlist() != null && !user.getEventlist().isEmpty()) {
                    for (EventInfo event : user.getEventlist()) {
                        Row row = userSheet.createRow(rowIdx.getAndIncrement());

                        row.createCell(0).setCellValue(user.getId());
                        row.createCell(1).setCellValue(user.getUsername());
                        row.createCell(2).setCellValue(user.getVolunteerhours());
                        row.createCell(3).setCellValue(user.getTravelhours());
                        row.createCell(4).setCellValue(user.getLivesimpacted());
                        row.createCell(5).setCellValue(user.getBusinessunit());
                        row.createCell(6).setCellValue(user.getStatus());
                        row.createCell(7).setCellValue(event.getId());
                        row.createCell(8).setCellValue(event.getBaselocation());
                        row.createCell(9).setCellValue(event.getEventname());
                    }
                } else {
                    Row row = userSheet.createRow(rowIdx.getAndIncrement());

                    row.createCell(0).setCellValue(user.getId());
                    row.createCell(1).setCellValue(user.getUsername());
                    row.createCell(2).setCellValue(user.getVolunteerhours());
                    row.createCell(3).setCellValue(user.getTravelhours());
                    row.createCell(4).setCellValue(user.getLivesimpacted());
                    row.createCell(5).setCellValue(user.getBusinessunit());
                    row.createCell(6).setCellValue(user.getStatus());
                }
            }

            for (int col = 0; col < COLUMNs.length; col++) {
                userSheet.autoSizeColumn(col);
            }

            workbook.write(out);
            workbook.write(fileOut);
            fileOut.close();
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
