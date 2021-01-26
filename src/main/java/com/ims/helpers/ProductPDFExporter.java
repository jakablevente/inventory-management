package com.ims.helpers;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ims.model.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class ProductPDFExporter {
	
	private List<Product> listProducts;
	
	public ProductPDFExporter(List<Product> listProducts) {
		
		this.listProducts = listProducts;
	}
	
	
	private void writeTableHeader(PdfPTable table) {
		
		PdfPCell cell = new PdfPCell();
		cell.setPadding(6);
		
		  Font font = FontFactory.getFont(FontFactory.HELVETICA);
		
		  
        cell.setPhrase(new Phrase("Product ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Brand", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Category", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
        
	}
	
	private void writeTableData(PdfPTable table) {
		
		for(Product product : listProducts) {
			table.addCell(String.valueOf(product.getId()));
			table.addCell(product.getName());
			table.addCell(product.getBrands().getName());
			table.addCell(product.getCategory().getCategoryName());
			table.addCell(String.valueOf(product.getQty()));
			table.addCell(String.valueOf(product.getPrice()));
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
         
        Paragraph p = new Paragraph("Products", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f,1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }

}
