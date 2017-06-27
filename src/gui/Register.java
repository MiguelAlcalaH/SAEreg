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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String result = "reg.xlsx";
	
	JTabbedPane tabs;
	JPanel repTab;
	JPanel studentTab;
	
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
	
	String cods[] = {"0416","0414","0412","0426","0424","0274"};
	private final String [] header = {"cedula","nombres","apellidos","Fecha de Nacimiento",
							"Edo Civil","parentezco","nacionalidad","pais","estado","genero",
							"dirección","tipo de vivienda","ubicación","condicion",
							"celular","telefono","correo","profesion","lugar de trabajo",
							"ingreso"};
	
	JScrollPane sc;
	JButton add;
	JButton exit;
	JButton clear;
	
	public Register(String title)
	{
		super(title);
		tabs = new JTabbedPane();
		
		buildRepTab();
		buildButtons();
		sc = new JScrollPane(repTab);
		tabs.addTab("Repesentante", sc);
		add(tabs,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.add(clear,getConstraints(1, 0, 1, 1));
		buttonPanel.add(add,getConstraints(2, 0, 1, 1));
		buttonPanel.add(exit,getConstraints(3, 0, 1, 1));
		
		add(buttonPanel,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
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
		repTab.add(new JLabel("<html><b>Apellidos:</b></html>"),getConstraints(0, 2, 2, 1));
		repTab.add(apellidos,getConstraints(2, 2, 3, 1));
		
		fecha_nac = new JSpinner(new SpinnerDateModel());
		repTab.add(new JLabel("<html><b>Fecha de Nacimiento:</b></html>"),getConstraints(0, 3, 1, 1));
		repTab.add(fecha_nac,getConstraints(1, 3, 1, 1));
		
		edo_civil = new JComboBox<String>(new String[]{"S","C","V","D"});
		repTab.add(new JLabel("<html><b>Estado Civil:</b></html>"),getConstraints(3, 3, 1, 1));
		repTab.add(edo_civil,getConstraints(4, 3, 1, 1));
		
		parentezco = new JTextField(20);
		JLabel aff = new JLabel("<html><b>Afinidad con el estudiante:</b></html>");
		aff.setToolTipText("Madre,Padre,Tio,Abuelo....");
		repTab.add(aff,getConstraints(0, 4, 1, 1));
		repTab.add(parentezco,getConstraints(1, 4, 3, 1));
		
		nacionalidad = new JTextField(20);
		repTab.add(new JLabel("<html><b>Nacionalidad:</b></html>"),getConstraints(0, 5, 1, 1));
		repTab.add(nacionalidad,getConstraints(1, 5, 1, 1));
		
		pais_nac = new JTextField(20);
		repTab.add(new JLabel("<html><b>Pais de Nacimiento:</b></html>"),getConstraints(3, 5, 1, 1));
		repTab.add(pais_nac,getConstraints(4, 5, 1, 1));
		
		estado_nac = new JTextField(20);
		repTab.add(new JLabel("<html><b>Estado de Nacimiento:</b></html>"),getConstraints(0, 6, 1, 1));
		repTab.add(estado_nac,getConstraints(1, 6, 1, 1));
		
		f = new JRadioButton("F");
		f.setSelected(true);
		m = new JRadioButton("M");
		genero = new ButtonGroup();
		genero.add(f);
		genero.add(m);
		repTab.add(new JLabel("<html><b>Genero:</b></html>"),getConstraints(2, 6, 1, 1));
		repTab.add(f,getConstraints(3, 6, 1, 1));
		repTab.add(m,getConstraints(4, 6, 1, 1));
		
		dir = new JTextArea(2, 20);
		repTab.add(new JLabel("<html><b>Dirección completa:</b></html>"),getConstraints(0, 7, 1, 1));
		repTab.add(dir,getConstraints(1, 7, 3, 2));
		
		tipoVivienda = new JTextField(10);
		JLabel tp = new JLabel("<html><b>Tipo de vivienda: </b></html>");
		tp.setToolTipText("Casa, Apartamento ETC...");
		repTab.add(tp, getConstraints(0, 9, 1, 1));
		repTab.add(tipoVivienda,getConstraints(1, 9, 3, 1));
		
		ubicacion = new JTextField(20);
		JLabel ub = new JLabel("<html><b>Ubicación de la viviend: </b></html>");
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
		repTab.add(new JLabel("<html><b>Telefono celular: </b></html>"),getConstraints(0, 12, 1, 1));
		repTab.add(codCelular,getConstraints(1, 12, 1, 1,0));
		repTab.add(celular,getConstraints(2, 12, 1, 1));
		repTab.add(new JLabel("<html><b>Telefono de Casa: </b></html>"),getConstraints(3, 12, 1, 1));
		repTab.add(codTelfn,getConstraints(4, 12, 1, 1,0));
		repTab.add(telefono,getConstraints(25, 12, 1, 1));
		
		correo = new JTextField(20);
		repTab.add(new JLabel("<html><b>Correo: </b></html>"),getConstraints(0, 13, 1, 1));
		repTab.add(correo,getConstraints(1, 13, 1, 1));
		
		profesion = new JTextField(15);
		repTab.add(new JLabel("<html><b>Profesión: </b></html>"),getConstraints(3, 13, 1, 1));
		repTab.add(profesion,getConstraints(4, 13, 1, 1));
		
		lugar_trabajo = new JTextField(20);
		repTab.add(new JLabel("<html><b>Lugar o Empresa donde Trabaja: </b></html>"),getConstraints(0, 14, 1, 1));
		repTab.add(lugar_trabajo,getConstraints(1, 14, 3, 1));
		
		ingreso = new JTextField(8);
		repTab.add(new JLabel("<html><b>Ingreso Promedio Familiar Mensual: </b></html>"),getConstraints(0, 15, 1, 1));
		repTab.add(ingreso,getConstraints(1, 15, 3, 1));
		
		
		TitledBorder ttle = BorderFactory.createTitledBorder("Información de Representante");
		repTab.setBorder(ttle);
	}
	
	private void buildButtons()
	{
		add = new JButton("Agregar");
		exit = new JButton("Salir");
		clear = new JButton("Borrar");
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
				generateExcel();
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
		codTelfn.setSelectedItem(cods.length-1);
		telefono.setText("");
		correo.setText("");
		profesion.setText("");
		lugar_trabajo.setText("");
		ingreso.setText("");
	}
	
	@SuppressWarnings("deprecation")
	private void generateExcel()
	{
		setCursor(Cursor.WAIT_CURSOR);
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

		setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	private GridBagConstraints getConstraints(int x,int y,int width, int height, int fill)
	{
		GridBagConstraints gbc =  new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridheight = height;
		gbc.gridwidth = width;
		gbc.fill = fill;
		
		return gbc;
	}
	
	
	private GridBagConstraints getConstraints(int x,int y,int width, int height)
	{
		return getConstraints(x, y, width, height, GridBagConstraints.HORIZONTAL);
	}
	
	
	
	public static void main(String[] args)
	{
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		new Register("SAE Registro");
	}
}
