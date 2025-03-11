package com.pos_main.ServiceImpl;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.pos_main.Service.PrinterService;

/**
 * Title: PrintServiceImpl.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Mar 11, 2025.
 * @version 1.0
 **/

@Component
@Slf4j
public class PrinterServiceImpl implements PrinterService{
	
	public boolean printReceipt() {
        try {
            PrintService printService = findUsbPrinter();
            if (printService == null) {
                System.err.println("❌ No USB printer found!");
                return false;
            }

            // ESC/POS font size commands
            byte[] fontCommand = new byte[]{0x1B, 0x21, 0x02}; // Font Size


            // Simulating top margin (adding extra empty lines)
            String topMargin = ""; // Adjust as needed

            // Properly formatted receipt content with line breaks
            String receiptData = """
                    
                    Delta POS System
                    ----------------------
                    Item        Qty   Price
                    Coffee      2     $5.00
                    Sandwich    1     $7.50
                    ----------------------
                    Total:          $12.50
                    
                    Thank you!
                    
                    """;

            // Simulating bottom margin (adding extra empty lines)
            String bottomMargin = "\n\n\n\n"; // Adjust as needed

            // Convert text to bytes
            byte[] receiptBytes = (topMargin + receiptData + bottomMargin).getBytes(StandardCharsets.UTF_8);

            // Cut paper command (ESC/POS)
            byte[] cutCommand = new byte[]{0x1D, 0x56, 0x00}; 

            // Combine commands (Using medium font as an example)
            byte[] combinedData = new byte[fontCommand.length + receiptBytes.length + cutCommand.length];
            System.arraycopy(fontCommand, 0, combinedData, 0, fontCommand.length);
            System.arraycopy(receiptBytes, 0, combinedData, fontCommand.length, receiptBytes.length);
            System.arraycopy(cutCommand, 0, combinedData, fontCommand.length + receiptBytes.length, cutCommand.length);

            // Send data to printer
            ByteArrayInputStream inputStream = new ByteArrayInputStream(combinedData);
            DocPrintJob printJob = printService.createPrintJob();
            Doc document = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            printJob.print(document, attributes);

            System.out.println("🖨️ Receipt sent to printer successfully with proper margins!");
            return true;
        } catch (Exception e) {
            System.err.println("❌ Printing failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private PrintService findUsbPrinter() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : printServices) {
            System.out.println("🔍 Found printer: " + printer.getName());
            if (printer.getName().equalsIgnoreCase("U80(U) 1")) {
                System.out.println("✅ Selected printer: " + printer.getName());
                return printer;
            }
        }
        return null;
    }

}


