package com.regdevs.logistica.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QrCodeService {

    public byte[] generarQrPaquete(String idPaquete) {
        try {
            // El QR contendrá una URL para ver los detalles del paquete (puedes ajustarla)
            String qrContent = "http://localhost:8080/paquetes/detalle/" + idPaquete;
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 250, 250);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            System.err.println("Error generando QR: " + e.getMessage());
            return null;
        }
    }
}
