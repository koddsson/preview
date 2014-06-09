package poi;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;                              
                                                                                  
import org.apache.poi.xwpf.converter.pdf.PdfOptions;                            
import org.apache.poi.xwpf.converter.pdf.PdfConverter; 

public class Utils {
    
    public String ConvertDocxToPDF(InputStream in) {
        String timestamp = Long.toString(System.currentTimeMillis() / 1000L);
        String filename =  timestamp + ".pdf";
        try {                                                                   
            // 1) Load DOCX into XWPFDocument                                   
            XWPFDocument document = new XWPFDocument(in);                       
                                                                                
            // 2) Prepare Pdf options                                           
            PdfOptions options = PdfOptions.create();                           
                                                                                
            // 3) Convert XWPFDocument to Pdf
            OutputStream out = new FileOutputStream(new File(filename)); 
            PdfConverter.getInstance().convert(document, out, options);         
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }                                                                       
        return filename;
    }
}
