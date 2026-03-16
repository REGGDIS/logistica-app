package com.regdevs.logistica.service;

import com.regdevs.logistica.dto.PaqueteDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteService {

    public ByteArrayInputStream generarReportePaquetes(List<PaqueteDTO> paquetes) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Reporte de Paquetes - Logística App", fontHeader);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Tabla
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3, 2});

            // Cabeceras
            Font fontTableHead = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            table.addCell(new PdfPCell(new Phrase("ID", fontTableHead)));
            table.addCell(new PdfPCell(new Phrase("Descripción", fontTableHead)));
            table.addCell(new PdfPCell(new Phrase("Destinatario", fontTableHead)));
            table.addCell(new PdfPCell(new Phrase("Estado", fontTableHead)));

            // Datos
            for (PaqueteDTO p : paquetes) {
                table.addCell(String.valueOf(p.getId()));
                table.addCell(p.getDescripcion());
                table.addCell(p.getDestinatario());
                table.addCell(p.getUltimoEstado() != null ? p.getUltimoEstado().getDescripcion() : "N/A");
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
