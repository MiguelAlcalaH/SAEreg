package util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class Util {
	private static final String DATABASE = "SaeReg.db";
	private static final Connection connection;
	
	
	///MODIFICADO TENIA UN ERROR
	// Y TODOS LO VIERON Y NO SE DIERON CUENTA
	private static final String PDF_HEADER = "Reciba un cordial saludo en nombre del personal que labora en el LB Carracciolo"
											+" Parra y Olmedo.\nLa presente tiene como finalidad solicitar amablemente de su parte, el llenado "+
											"del siguiente formato con información sobre usted como representante y su representado.\n"+
											"Cabe destacar que la información requerida en el mismo, es para ser cargada en el Sistema de Gestión "+
											"Escolar, el cual es una base de datos que posee el Ministerio de Educación a nivel nacional. Agradecemos "+
											"de antemano su receptividad y celeridad en el proceso."; 
	//////////////////////////////////////////////////////////////////////
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+DATABASE);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConection()
	{
		return connection;
	}
	
	public static void closeConection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/// MODIFICADO
	/// AGREGE LOS ICONOS EN LA CARPETA RESOURCE
	public static void generatePdf(String result,Object[][] dat)
	{
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
			writer.setInitialLeading(10);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
			cb.beginText();
			float w = PageSize.A4.getWidth()/2-40;
			float h = PageSize.A4.getHeight()-50;
			cb.moveText(w, h);
			cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 11);
			cb.showText("LICEO BOLIVARIANO");
			cb.endText();
			cb.beginText();
			cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 9);
			cb.moveText(w-20, h-12);
			cb.showText("\"CARRACCIOLO PARRA Y OLMEDO\"");
			cb.endText();
			cb.restoreState();
			
			Image logome = Image.getInstance("Resources/gobM.png");
			logome.setAbsolutePosition(10, PageSize.A4.getHeight()-logome.getHeight()-15);
			document.add(logome);
			
			Image Me = Image.getInstance("Resources/MeLog.png");
			Me.setAbsolutePosition(10+logome.getWidth(), PageSize.A4.getHeight()-Me.getHeight()-15);
			document.add(Me);
			
			
			Image logoCol = Image.getInstance("Resources/logoliceonew.jpg");
			logoCol.scaleAbsolute(84, 64);
			logoCol.setAbsolutePosition(PageSize.A4.getWidth()-logoCol.getScaledWidth()-20,PageSize.A4.getHeight()- logoCol.getScaledHeight()-10);
			document.add(logoCol);
			
			Paragraph head = new Paragraph("Estimado Representante:", FontFactory.getFont(FontFactory.TIMES,10,Font.BOLD | Font.UNDERLINE));
			head.setAlignment(Element.ALIGN_LEFT);
			head.setSpacingBefore(50);
			document.add(head);
			document.add(Chunk.NEWLINE);
			
			head = new Paragraph(10, PDF_HEADER,FontFactory.getFont(FontFactory.TIMES,10));
			head.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(head);
			document.add(new LineSeparator((float)1,100,BaseColor.BLACK,1,-10));
			document.add(new Paragraph("\n\n"));
			Paragraph p = new Paragraph(10);
			p.setAlignment(Element.ALIGN_CENTER);
			Chunk underline = new Chunk("INFORMACIÓN DEL REPRESENTANTE");
			underline.setFont(FontFactory.getFont(FontFactory.TIMES, 10,Font.BOLD | Font.UNDERLINE));
			p.add(underline);
			document.add(p);
			document.add(Chunk.NEWLINE);
			
			Object []rep = dat[0];
			p = getParagraph("NUMERO DE CEDULA: ");
			p.add(getUnderLine((String)rep[0],5,50));
			document.add(p);
			
			p = getParagraph("NOMBRES: ");
			p.add(getUnderLine((String)rep[1],5,100));
			document.add(p);
			
			p = getParagraph("APELLIDOS: ");
			p.add(getUnderLine((String)rep[2], 5, 100));
			document.add(p);

			p = getParagraph("ESTADO CIVIL: ");
			p.add(getUnderLine(shortStateToFull((String)rep[4]), 2, 20));
			p.add("AFINIDAD CON EL ESTUDIANTE: ");
			p.add(getUnderLine((String)rep[5], 1, 50));
			document.add(p);
			
			
			p = getParagraph("DIRECCIÓN COMPLETA: ");
			p.add(getUnderLine((String)rep[10], 2, 100));
			document.add(p);
			
			p = getParagraph("TIPO DE VIVIENDA: ");
			p.add(getUnderLine((String)rep[11],5,100));
			document.add(p);
			
			p = getParagraph("UBICACIÓN DE VIVIENDA: ");
			p.add(getUnderLine((String)rep[12], 5, 100));
			document.add(p);
			
			p = getParagraph("CONDICION DE VIVIENDA: ");
			p.add(getUnderLine((String)rep[13], 5, 100));
			document.add(p);
			
			p = getParagraph("TELEFONO CELULAR: ");
			p.add(getUnderLine((String)rep[14], 2, 30));
			p.add("  TELEFONO DE CASA: ");
			p.add(getUnderLine((String)rep[15], 2, 30));
			document.add(p);
			
			p = getParagraph("CORREO: ");
			p.add(getUnderLine((String)rep[16], 2, 40));
			p.add("   PROFESION U OFICIO: ");
			p.add(getUnderLine((String)rep[17], 2, 40));
			document.add(p);
			
			p = getParagraph("LUGAR O EMPRESA DONDE TRABAJA: ");
			p.add(getUnderLine((String)rep[18], 2, 80));
			document.add(p);
			
			p = getParagraph("INGRESO PROMEDIO FAMILIAR MENSUAL: ");
			p.add(getUnderLine((String)rep[19], 2, 80));
			document.add(p);
			
			
			p = new Paragraph(10);
			p.setAlignment(Element.ALIGN_CENTER);
		    underline = new Chunk("INFORMACIÓN DEL ESTUDIANTE");
			underline.setFont(FontFactory.getFont(FontFactory.TIMES, 10,Font.BOLD | Font.UNDERLINE));
			p.add(underline);
			p.setSpacingBefore(20);
			document.add(p);
			document.add(Chunk.NEWLINE);
			
			Object []est = dat[1];
			p = getParagraph("NUMERO DE CEDULA: ");
			p.add(getUnderLine((String)est[0], 5, 50));
			p.add("  AÑO Y SECCION: ");
			p.add(getUnderLine((String)est[1]+" "+(String)est[2],5, 40));
			document.add(p);
			
			p = getParagraph("NOMBRES: ");
			p.add(getUnderLine((String)est[3], 5, 150));
			document.add(p);
			
			p = getParagraph("APELLIDOS: ");
			p.add(getUnderLine((String)est[4], 5, 150));
			document.add(p);
			
			
			p = getParagraph("FECHA DE NACIMIENTO: ");
			p.add(getUnderLine((String)rep[3], 5, 50));
			document.add(p);
			/////MODIFICADO
			
			p = getParagraph("NACIONALIDAD: ");
			p.add(getUnderLine((String)rep[6], 5, 50));
			p.add("  PAIS DE NACIMIENTO");
			p.add(getUnderLine((String)rep[7], 5, 50));
			document.add(p);
			
			p = getParagraph("ESTADO DE NACIMIENTO: ");
			p.add(getUnderLine((String)rep[8], 2, 30));
			
			////MODIFICADO
			p.add("  MUNICIPIO DE NACIMIENTO: ");
			p.add(getUnderLine((String)est[16], 2, 40));
			document.add(p);
			
			
			
			p = getParagraph("GÉNERO: M");
			String F = ((String)rep[9]).equals("F") ? "X" : " ";
			String M = F.equals("X") ? " " : "X";
			p.add(getUnderLine(M, 2, 4));
			p.add(" F ");
			p.add(getUnderLine(F, 2, 4));
			p.add("      DEPORTE QUE PRACTICA: ");
			p.add(getUnderLine((String)est[17], 5, 50));
			document.add(p);
			
			/////////////////////////////////////
			
			p = getParagraph("TELEFONO CELULAR: ");
			p.add(getUnderLine((String)est[5], 5, 50));
			p.add("  TELEFONO DE CASA: ");
			p.add(getUnderLine((String)est[6], 5, 50));
			document.add(p);
			
			
			p = getParagraph("CORREO: ");
			p.add(getUnderLine((String)est[7], 5, 50));
			String Y = ((String)(String)est[8]).equals("Y") ? "X" : " ";
			String N = Y.equals("X") ? " " : "X";
			p.add("  ¿ES ZURDO? :  SÍ");
			p.add(getUnderLine(Y, 5, 4));
			p.add("  NO");
			p.add(getUnderLine(N, 5, 4));
			document.add(p);
			
			p = getParagraph("¿POSEE BECA ESTUDIANTIL?:   SÍ");
			Y = ((String)(String)est[9]).equals("Y") ? "X" : " ";
			N = Y.equals("X") ? " " : "X";
			p.add(getUnderLine(Y, 5, 5));
			p.add("  NO");
			p.add(getUnderLine(N, 5, 5));
			p.add("      ¿POSEE COMPUTADOR CANAIMA?:  SÍ");
			Y = ((String)(String)est[10]).equals("Y") ? "X" : " ";
			N = Y.equals("X") ? " " : "X";
			p.add(getUnderLine(Y, 5, 5));
			p.add("  NO");
			p.add(getUnderLine(N, 5, 5));
			document.add(p);
			
			p = getParagraph("ESTATURA DEL ESTUDIANTE: ");
			p.add(getUnderLine((String)est[11], 5, 40));
			p.add("    PESO DEL ESTUDIANTE: ");
			p.add(getUnderLine((String)est[12], 5, 30));
			document.add(p);
			
			p = getParagraph("TALLA DE CAMISA DEL ESTUDIANTE:");
			p.add(getUnderLine((String)est[13], 5, 50));
			document.add(p);
			
			p = getParagraph(("TALLA DE PANTALÓN DEL ESTUDIANTE:"));
			p.add(getUnderLine((String)est[14], 5, 50));
			document.add(p);
			
			p = getParagraph("TALA DE ZAPATOS DEL ESTUDIANTE:");
			p.add(getUnderLine((String)est[15], 5, 50));
			document.add(p);
			
			
			
			p = new Paragraph();
			Chunk glue = new Chunk(new VerticalPositionMark());
			p.setSpacingBefore(50);
			underline = new Chunk("FIRMA DEL REPRESENTANTE", FontFactory.getFont(FontFactory.TIMES, 10));
			underline.setUnderline(1, 10);
			p.add(underline);
			p.add(glue);
			underline = new Chunk("FIRMA DEL COORDINADOR", FontFactory.getFont(FontFactory.TIMES, 10));
			underline.setUnderline(1, 10);
			p.add(underline);
			
			
			document.add(p);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}
	
	static public Paragraph getParagraph(String str)
	{
		return new Paragraph(14,str,FontFactory.getFont(FontFactory.TIMES,10));
	}
	
	static public Chunk getUnderLine(String str,Integer in,Integer n)
	{
		int j = 0;
		n = n-str.length();
		for(int i =0; i< n;i++,j++)
		{
			if(j < in)
				str = " "+str;
			str = str + " ";
		}
		Chunk underline = new Chunk(str);
		underline.setFont(FontFactory.getFont(FontFactory.TIMES, 9));
		underline.setUnderline(1,-2);
		return underline;
	}
	
	static public String shortStateToFull(String st)
	{
		switch(st)
		{
			case "S":
				return "Soltero";
			case "C":
				return "Casado";
			case "V":
				return "Viudo";
			case "D":
				return "Divorsiado";
			
		}
		return st;
	}
	
}
