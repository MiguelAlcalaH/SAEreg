package gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.sun.glass.events.KeyEvent;

@SuppressWarnings("serial")
public class MainSAE extends JFrame{

	JLabel header;
	JButton create;
	JButton update;
	JButton delete;
	
	JButton generate;
	
	
	
	public MainSAE(String title)
	{
		super(title);
		JPanel root = new JPanel(new GridBagLayout());
		header = new JLabel("Registro Escolar Para el SAE");
		header.setBackground(Color.white);
		header.setOpaque(false);
		header.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		root.add(header,Register.getConstraints(0, 0, 2, 1));
		
		create = new JButton("Nuevo Registro",new ImageIcon("Resources/add.png"));
		update = new JButton("Actualizar Registro",new ImageIcon("Resources/update.png"));
		delete = new JButton("Borrar Registro",new ImageIcon("Resources/delete.png"));
		generate = new JButton("Exportar a Excel",new ImageIcon("Resources/table.png"));
		
		actions();
		
		root.add(create,Register.getConstraints(0, 2, 1, 1));
		root.add(update,Register.getConstraints(1, 2, 1, 1));
		root.add(delete,Register.getConstraints(0, 3, 1, 1));
		root.add(generate,Register.getConstraints(1, 3, 1, 1));
		
		JButton exit = new JButton("Salir", new ImageIcon("Resources/close.png"));
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		root.add(exit,Register.getConstraints(1, 4, 1, 1));
		
		root.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(root);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	private void actions()
	{
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new Register("Nuevo Registro");
					}
				});
			}
		});
		
		create.setMnemonic(KeyEvent.VK_N);
	}

	public static void main(String[] args)
	{
		try{
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		new MainSAE("Registro para SAE");
	}
}
