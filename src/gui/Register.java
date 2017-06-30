package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result = "reg";
	
	JTabbedPane tabs;
	JPanel repTab;
	JPanel estTab;
	
	JTextField cedula;
	JTextField nombres;
	JTextField apellidos;
	JSpinner fecha_nac;
	JComboBox<String> edo_civil;
	JTextField parentezco;
	JTextField nacionalidad;
	JTextField pais_nac;
	JTextField estado_nac;
	ButtonGroup genero;
	JRadioButton m;
	JRadioButton f;
	JTextArea dir;
	JTextField tipoVivienda;
	JTextField ubicacion;
	JTextField condicion_vivienda;
	JComboBox<String> codCelular;
	JTextField celular;
	JComboBox<String> codTelfn;
	JTextField telefono;
	JTextField correo;
	JTextField profesion;
	JTextField lugar_trabajo;
	JTextField ingreso;
	///////////////////////////////
	
	
	JTextField ciEstu;
	JComboBox<String> nivel;
	JTextField seccion;
	JTextField nombreEst;
	JTextField apellidoEst;
	JComboBox<String> codCelularEst;
	JTextField celularEst;
	JComboBox<String> codTelfnEst;
	JTextField telefonoEst;
	JTextField correoEst;
	JRadioButton zurdoY;
	JRadioButton zurdoN;
	ButtonGroup zurdo;
	JRadioButton becaY;
	JRadioButton becaN;
	ButtonGroup beca;
	JRadioButton canaimaY;
	JRadioButton canaimaN;
	ButtonGroup canaima;
	JSpinner estatura;
	JSpinner peso;
	JSpinner tallaCamisa;
	JSpinner tallaPantalon;
	JSpinner tallaZapato;
	
	
	String cods[] = {"0416","0414","0412","0426","0424","0274"};
	private final String [] header = {"cedula","nombres","apellidos","Fecha de Nacimiento",
							"Edo Civil","parentezco","nacionalidad","pais","estado","genero",
							"dirección","tipo de vivienda","ubicación","condicion",
							"celular","telefono","correo","profesion","lugar de trabajo",
							"ingreso"};
	
	JScrollPane sc;
	JScrollPane sSt;
	JButton add;
	JButton exit;
	JButton clear;
	
	public Register(String title)
	{
		super(title);
		tabs = new JTabbedPane();
		
		buildRepTab();
		buildEstTab();
		buildButtons();
		sc = new JScrollPane(repTab);
		sSt = new JScrollPane(estTab);
		tabs.addTab("Repesentante", sc);
		tabs.addTab("Estudiante", sSt);
		tabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		add(tabs,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.add(clear,getConstraints(1, 0, 1, 1));
		buttonPanel.add(add,getConstraints(2, 0, 1, 1));
		buttonPanel.add(exit,getConstraints(3, 0, 1, 1));
		
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		add(buttonPanel,BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	
	private void buildRepTab()
	{
		repTab = new JPanel(new GridBagLayout());
		
		cedula = new JTextField(10);
		repTab.add(new JLabel("<html><b>Numero de Cedula: </b></html>"),getConstraints(0, 0, 1, 1));
		repTab.add(cedula,getConstraints(1, 0, 1, 1));
		
		nombres = new JTextField(25);
		repTab.add(new JLabel("<html><b>Nombres:</b></html>"),getConstraints(0, 1, 2, 1));
		repTab.add(nombres,getConstraints(1, 1, 4, 1));		
		
		apellidos = new JTextField(25);
		repTab.add(new JLabel("<html><b>Apellidos:</b></html>"),getConstraints(0, 2, 1, 1));
		repTab.add(apellidos,getConstraints(1, 2, 3, 1));
		
		fecha_nac = new JSpinner(new SpinnerDateModel());
		repTab.add(new JLabel("<html><b>Fecha de Nacimiento:</b></html>"),getConstraints(0, 3, 1, 1));
		repTab.add(fecha_nac,getConstraints(1, 3, 1, 1));
		
		edo_civil = new JComboBox<String>(new String[]{"S","C","V","D"});
		JPanel edPanel = new JPanel();
		edPanel.add(new JLabel("<html><b>Estado Civil:</b></html>"));
		edPanel.add(edo_civil);
		repTab.add(edPanel,getConstraints(2, 3, 1, 1));
		
		parentezco = new JTextField(20);
		JLabel aff = new JLabel("<html><b>Afinidad con el estudiante:</b></html>");
		aff.setToolTipText("Madre,Padre,Tio,Abuelo....");
		repTab.add(aff,getConstraints(0, 4, 1, 1));
		repTab.add(parentezco,getConstraints(1, 4, 3, 1));
		
		nacionalidad = new JTextField(20);
		repTab.add(new JLabel("<html><b>Nacionalidad:</b></html>"),getConstraints(0, 5, 1, 1));
		repTab.add(nacionalidad,getConstraints(1, 5, 1, 1));
		
		pais_nac = new JTextField(20);
		repTab.add(new JLabel("<html><b>Pais de Nacimiento:</b></html>"),getConstraints(2, 5, 1, 1));
		repTab.add(pais_nac,getConstraints(3, 5, 1, 1));
		
		estado_nac = new JTextField(20);
		repTab.add(new JLabel("<html><b>Estado de Nacimiento:</b></html>"),getConstraints(0, 6, 1, 1));
		repTab.add(estado_nac,getConstraints(1, 6, 1, 1));
		
		f = new JRadioButton("F");
		f.setSelected(true);
		m = new JRadioButton("M");
		genero = new ButtonGroup();
		genero.add(f);
		genero.add(m);
		JPanel genPanel = new JPanel();
		genPanel.add(new JLabel("<html><b>Genero:</b></html>"));
		genPanel.add(f);
		genPanel.add(m);
		repTab.add(genPanel,getConstraints(3, 6, 1, 1));
		
		dir = new JTextArea(2, 20);
		JLabel tp = new JLabel("<html><b>Dirección completa:</b></html>");
		tp.setToolTipText("Dirección donde vive");
		repTab.add(tp,getConstraints(0, 7, 1, 1));
		repTab.add(dir,getConstraints(1, 7, 3, 2));
		
		tipoVivienda = new JTextField(10);
		tp = new JLabel("<html><b>Tipo de vivienda: </b></html>");
		tp.setToolTipText("Casa, Apartamento ETC...");
		repTab.add(tp, getConstraints(0, 9, 1, 1));
		repTab.add(tipoVivienda,getConstraints(1, 9, 3, 1));
		
		ubicacion = new JTextField(20);
		JLabel ub = new JLabel("<html><b>Ubicación de la vivienda: </b></html>");
		repTab.add(ub,getConstraints(0, 10, 1, 1));
		repTab.add(ubicacion,getConstraints(1, 10, 3, 1));
		
		condicion_vivienda = new JTextField(20);
		JLabel cond = new JLabel("<html><b>Condicion: </b></html>");
		cond.setToolTipText("Propia,Alquilada, de un familiar ....");
		repTab.add(cond,getConstraints(0, 11, 1, 1));
		repTab.add(condicion_vivienda,getConstraints(1, 11, 3, 1));
		
		celular = new JTextField(7);
		codCelular = new JComboBox<String>(cods);
		codTelfn = new JComboBox<String>(cods);
		Dimension d = new Dimension(80,22);
		codCelular.setPreferredSize(d);
		codTelfn.setPreferredSize(d);
		telefono = new JTextField(7);
		
		JPanel celPanel = new JPanel();
		JPanel tlfnPanel = new JPanel();
		celPanel.add(codCelular);
		celPanel.add(celular);
		tlfnPanel.add(codTelfn);
		tlfnPanel.add(telefono);
		
		repTab.add(new JLabel("<html><b>Telefono celular: </b></html>"),getConstraints(0, 12, 1, 1));
		repTab.add(celPanel,getConstraints(1, 12, 1, 1,0));
		repTab.add(new JLabel("<html><b>Telefono de Casa: </b></html>"),getConstraints(2, 12, 1, 1));
		repTab.add(tlfnPanel,getConstraints(3, 12, 1, 1,0));
		
		correo = new JTextField(20);
		repTab.add(new JLabel("<html><b>Correo: </b></html>"),getConstraints(0, 13, 1, 1));
		repTab.add(correo,getConstraints(1, 13, 1, 1));
		
		profesion = new JTextField(15);
		repTab.add(new JLabel("<html><b>Profesión: </b></html>"),getConstraints(2, 13, 1, 1));
		repTab.add(profesion,getConstraints(3, 13, 1, 1));
		
		lugar_trabajo = new JTextField(20);
		repTab.add(new JLabel("<html><b>Lugar o Empresa donde Trabaja: </b></html>"),getConstraints(0, 14, 1, 1));
		repTab.add(lugar_trabajo,getConstraints(1, 14, 3, 1));
		
		ingreso = new JTextField(8);
		repTab.add(new JLabel("<html><b>Ingreso Promedio Familiar Mensual: </b></html>"),getConstraints(0, 15, 1, 1));
		repTab.add(ingreso,getConstraints(1, 15, 3, 1));
		
		
		TitledBorder ttle = BorderFactory.createTitledBorder("<html><b>Información del Representante</b></html>");
		repTab.setBorder(ttle);
	}
	
	
	private void buildEstTab()
	{
		estTab = new JPanel(new GridBagLayout());
		
		ciEstu = new JTextField(10);
		estTab.add(new JLabel("<html><b>Numero de Cedula</b>:</html>"),getConstraints(0, 0, 1, 1));
		estTab.add(ciEstu,getConstraints(1, 0, 1, 1));
		
		String nv[] = {"1","2","3","4","5"};
		nivel = new JComboBox<String>(nv);
		seccion = new JTextField(1);
		JPanel ayS = new JPanel();
		ayS.add(nivel);
		ayS.add(seccion);
		estTab.add(new JLabel("<html><b>Año y Sección</b>:</html>"), getConstraints(2, 0, 1, 1) );
		estTab.add(ayS,getConstraints(3, 0, 1, 1));
		
		nombreEst = new JTextField(20);
		estTab.add(new JLabel("<html><b>Nombres</b>:</html>"),getConstraints(0, 1, 1, 1));
		estTab.add(nombreEst,getConstraints(1, 1, 3, 1));
		
		apellidoEst = new JTextField(20);
		estTab.add(new JLabel("<html><b>Apellidos</b>:</html>"),getConstraints(0, 2, 1, 1));
		estTab.add(apellidoEst,getConstraints(1, 2, 3, 1));
		
		codCelularEst = new JComboBox<String>(cods);
		celularEst = new JTextField(7);
		JPanel celPanel = new JPanel();
		estTab.add(new JLabel("<html><b>Celular</b>:</html>"),getConstraints(0, 3, 1, 1));
		celPanel.add(codCelularEst);
		celPanel.add(celularEst);
		estTab.add(celPanel,getConstraints(1, 3, 1, 1));
		
		codTelfnEst = new JComboBox<String>(cods);
		telefonoEst = new JTextField(7);
		JPanel telPanel = new JPanel();
		estTab.add(new JLabel("<html><b>Telefono de casa</b>:</html>"),getConstraints(2, 3, 1, 1));
		telPanel.add(codTelfnEst);
		telPanel.add(telefonoEst);
		estTab.add(telPanel,getConstraints(3, 3, 1, 1));
		
		correoEst = new JTextField(10);
		estTab.add(new JLabel("<html><b>Correo</b>:</html>"),getConstraints(0, 4, 1, 1));
		estTab.add(correoEst,getConstraints(1, 4, 1, 1));
		
		zurdoY = new JRadioButton("Sí");
		zurdoN = new JRadioButton("No");
		zurdo = new ButtonGroup();
		zurdo.add(zurdoY);
		zurdo.add(zurdoN);
		zurdoN.setSelected(true);
		JPanel zurdoP = new JPanel();
		estTab.add(new JLabel("<html><b>¿Es zurdo?</b>:</html>"),getConstraints(2, 4, 1, 1));
		zurdoP.add(zurdoY);
		zurdoP.add(zurdoN);
		estTab.add(zurdoP,getConstraints(3, 4, 1, 1));
		
		becaY = new JRadioButton("Sí");
		becaN = new JRadioButton("No");
		beca = new ButtonGroup();
		beca.add(becaY);
		beca.add(becaN);
		becaN.setSelected(true);
		JPanel becaP = new JPanel();
		estTab.add(new JLabel("<html><b>Posee Beca Estudiantil</b>:</html>"),getConstraints(0, 5, 1, 1));
		becaP.add(becaY);
		becaP.add(becaN);
		estTab.add(becaP,getConstraints(1, 5, 1, 1));	

		canaimaY = new JRadioButton("Sí");
		canaimaN = new JRadioButton("No");
		canaima = new ButtonGroup();
		canaima.add(canaimaY);
		canaima.add(canaimaN);
		canaimaY.setSelected(true);
		JPanel canaimaP = new JPanel();
		estTab.add(new JLabel("<html><b>Posee Canaima</b>:</html>"),getConstraints(2, 5, 1, 1));
		canaimaP.add(canaimaY);
		canaimaP.add(canaimaN);
		estTab.add(canaimaP,getConstraints(3, 5, 1, 1));
		
		JSeparator js = new JSeparator();
		js.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		estTab.add(js,getConstraints(0, 6, 4, 1));
		
		estatura = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 2.5, 0.01));
		estTab.add(new JLabel("<html><b>Estatura</b>:</html>"),getConstraints(0, 7, 1, 1));
		estTab.add(estatura,getConstraints(1, 7, 1, 1));
		
		peso = new JSpinner(new SpinnerNumberModel(50.0, 0.0, 200.0, 0.01));
		peso.add(new JLabel("<html><b>Peso</b>:</html>"),getConstraints(2, 7, 1, 1));
		estTab.add(peso,getConstraints(3, 7, 1, 1));
		
		tallaCamisa = new JSpinner(new SpinnerNumberModel(10, 5, 30, 1));
		estTab.add(new JLabel("<html><b>Talla de Camisa</b>:</html>"),getConstraints(0, 8, 1, 1));
		estTab.add(tallaCamisa,getConstraints(1, 8, 1, 1));	
		
		tallaPantalon = new JSpinner(new SpinnerNumberModel(10, 5, 30, 1));
		estTab.add(new JLabel("<html><b>Talla de Pantalon</b>:</html>"),getConstraints(2, 8, 1, 1));
		estTab.add(tallaPantalon,getConstraints(3, 8, 1, 1));	
		
		tallaZapato = new JSpinner(new SpinnerNumberModel(30, 30, 45, 1));
		estTab.add(new JLabel("<html><b>Talla de Zapatos</b>:</html>"),getConstraints(0, 9, 1, 1));
		estTab.add(tallaZapato,getConstraints(1, 9, 1, 1));	
		
		estTab.setBorder(BorderFactory.createTitledBorder("<html><b>Información del Estudiante</b></html>"));
	}
	
	
	private void buildButtons()
	{
		add = new JButton("Agregar",new ImageIcon("Resources/dialog-ok.png"));
		exit = new JButton("Salir",new ImageIcon("Resources/dialog-no.png"));
		clear = new JButton("Borrar", new ImageIcon("Resources/clear.png"));
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllData();
			}
		});
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel 2007 (*.xls)","xls");
				chooser.addChoosableFileFilter(filter);
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel 2010 (*.xlsx)","xlsx"));
				int op = chooser.showSaveDialog(null);
				if(op == JFileChooser.APPROVE_OPTION)
				{
					result = chooser.getSelectedFile().getAbsolutePath();
					String fil = chooser.getFileFilter().getDescription();
					if(fil.equals("Excel 2007 (*.xls)"))
					{
						result = result + ".xls";
						generateExcel(0);
					}
					else
					{
						result = result + ".xlsx";
						generateExcel(1);
					}
				}
			}
		});
	}
	
	
	@SuppressWarnings("deprecation")
	private void clearAllData()
	{
		cedula.setText("");
		nombres.setText("");
		apellidos.setText("");
		fecha_nac.getModel().setValue(new Date(1970,1,1));
		edo_civil.setSelectedIndex(0);
		parentezco.setText("");
		nacionalidad.setText("");
		pais_nac.setText("");
		estado_nac.setText("");;
		f.setSelected(true);
		dir.setText("");;
		tipoVivienda.setText("");
		ubicacion.setText("");
		condicion_vivienda.setText("");
		codCelular.setSelectedIndex(0);;
		celular.setText("");
		codTelfn.setSelectedIndex(cods.length-1);
		telefono.setText("");
		correo.setText("");
		profesion.setText("");
		lugar_trabajo.setText("");
		ingreso.setText("");
		
		///////
		ciEstu.setText("");
		nivel.setSelectedIndex(0);
		seccion.setText("");
		nombreEst.setText("");
		apellidoEst.setText("");
		codCelularEst.setSelectedIndex(0);
		celularEst.setText("");
		codTelfnEst.setSelectedItem("0274");
		telefonoEst.setText("");
		correoEst.setText("");
		zurdoN.setSelected(true);;
		becaN.setSelected(false);
		canaimaY.setSelected(true);
		estatura.setValue(new Double(1.60));
		peso.setValue(new Double(50.0));
	}
	
	@SuppressWarnings("deprecation")
	private void generateExcel(int type)
	{
		setCursor(Cursor.WAIT_CURSOR);
		if(type == 0)
			generateXLS();
		else
			generateXLXS();

		setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	
	private void generateXLXS()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Registro de Estudiantes");
		XSSFRow row = sheet.createRow(0);
		int s = header.length;
		for(int i = 0; i < s; i++)
		{
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(header[i]);
		}
		
		row = sheet.createRow(1);
		row.createCell(0).setCellValue(cedula.getText());
		row.createCell(1).setCellValue(nombres.getText());
		row.createCell(2).setCellValue(apellidos.getText());
		row.createCell(3).setCellValue((Date)fecha_nac.getValue());
		row.createCell(4).setCellValue((String)edo_civil.getSelectedItem());
		row.createCell(5).setCellValue(parentezco.getText());
		row.createCell(6).setCellValue(nacionalidad.getText());
		row.createCell(7).setCellValue(pais_nac.getText());
		row.createCell(8).setCellValue(estado_nac.getText());
		String g = f.isSelected() ? "F" : "M";
		row.createCell(9).setCellValue(g);
		row.createCell(10).setCellValue(dir.getText());
		row.createCell(11).setCellValue(tipoVivienda.getText());
		row.createCell(12).setCellValue(ubicacion.getText());
		row.createCell(13).setCellValue(condicion_vivienda.getText());
		String tlf = (String)codCelular.getSelectedItem()+"-"+celular.getText();
		row.createCell(14).setCellValue(tlf);
		tlf = (String)codTelfn.getSelectedItem()+"-"+telefono.getText();
		row.createCell(15).setCellValue(tlf);
		row.createCell(16).setCellValue(correo.getText());
		row.createCell(17).setCellValue(profesion.getText());
		row.createCell(18).setCellValue(lugar_trabajo.getText());
		row.createCell(19).setCellValue(ingreso.getText());
		
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(result);
			try {
				workbook.write(outputStream);
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private void generateXLS()
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Registro de Estudiantes") ;

		HSSFRow row = sheet.createRow(0);
		int s = header.length;
		for(int i = 0; i < s; i++)
		{
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(header[i]);
		}
		
		row = sheet.createRow(1);
		row.createCell(0).setCellValue(cedula.getText());
		row.createCell(1).setCellValue(nombres.getText());
		row.createCell(2).setCellValue(apellidos.getText());
		row.createCell(3).setCellValue((Date)fecha_nac.getValue());
		row.createCell(4).setCellValue((String)edo_civil.getSelectedItem());
		row.createCell(5).setCellValue(parentezco.getText());
		row.createCell(6).setCellValue(nacionalidad.getText());
		row.createCell(7).setCellValue(pais_nac.getText());
		row.createCell(8).setCellValue(estado_nac.getText());
		String g = f.isSelected() ? "F" : "M";
		row.createCell(9).setCellValue(g);
		row.createCell(10).setCellValue(dir.getText());
		row.createCell(11).setCellValue(tipoVivienda.getText());
		row.createCell(12).setCellValue(ubicacion.getText());
		row.createCell(13).setCellValue(condicion_vivienda.getText());
		String tlf = (String)codCelular.getSelectedItem()+"-"+celular.getText();
		row.createCell(14).setCellValue(tlf);
		tlf = (String)codTelfn.getSelectedItem()+"-"+telefono.getText();
		row.createCell(15).setCellValue(tlf);
		row.createCell(16).setCellValue(correo.getText());
		row.createCell(17).setCellValue(profesion.getText());
		row.createCell(18).setCellValue(lugar_trabajo.getText());
		row.createCell(19).setCellValue(ingreso.getText());
		
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(result);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static GridBagConstraints getConstraints(int x,int y,int width, int height, int fill)
	{
		GridBagConstraints gbc =  new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridheight = height;
		gbc.gridwidth = width;
		gbc.fill = fill;
		gbc.anchor = GridBagConstraints.WEST;
		
		return gbc;
	}
	
	
	public static GridBagConstraints getConstraints(int x,int y,int width, int height)
	{
		return getConstraints(x, y, width, height, GridBagConstraints.HORIZONTAL);
	}
	
}
