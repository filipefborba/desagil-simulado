package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener, MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;


	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;

		image = loadImage(gate.toString());

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];
		
		this.addMouseListener(this);

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);

//		if(size == 1) {
//			add(inBoxes[0], 0, 60, 20, 20);			
//		}
//		else {
//			for(int i = 0; i < size; i++) {
//				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
//			}			
//		}

		add(outBox, 184, 60, 20, 20);

		outBox.setSelected(gate.read());
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());

		outBox.setSelected(gate.read());
		
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);
		
		
		if(gate.getSize() == 1){
			//1ª Alavanca
			g.drawArc(5, 65, 20, 20, 0, 180);
			g.fillArc(5, 65, 20, 20, 0, 180);
			if(inBoxes[0].isSelected() == false){
				g.drawLine(15, 65, 10, 55);
				g.drawOval(5, 50, 5, 5);
				g.fillOval(5, 50, 5, 5);
			}
			
			else{
				g.drawLine(15, 65, 25, 65);
				g.drawOval(25, 30, 5, 5);
				g.fillOval(25, 30, 5, 5);
			}
		}
			
		if(gate.getSize() == 2){
			//1ª Alavanca
			g.drawArc(5, 45, 20, 20, 0, 180);
			g.fillArc(5, 45, 20, 20, 0, 180);
			if(inBoxes[0].isSelected() == false){
				g.drawLine(15, 45, 10, 35);
				g.drawOval(5, 30, 5, 5);
				g.fillOval(5, 30, 5, 5);
			}
			else{
				g.drawLine(15, 45, 25, 35);
				g.drawOval(25, 30, 5, 5);
				g.fillOval(25, 30, 5, 5);
				
			}
			
			//2ª Alavanca
			g.drawArc(5, 85, 20, 20, 0, 180);
			g.fillArc(5, 85, 20, 20, 0, 180);
			
			if(inBoxes[1].isSelected() == false){
				g.drawLine(15, 85, 10, 75);
				g.drawOval(5, 70, 5, 5);
				g.fillOval(5, 70, 5, 5);
			}
			else{
				g.drawLine(15, 85, 25, 75);
				g.drawOval(25, 70, 5, 5);
				g.fillOval(25, 70, 5, 5);
			}
			
			
			
			
		repaint();
		}
	
		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }

	@Override
	public void mouseClicked(MouseEvent e){
		// Finds the location of the mouse
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();

        // Gets the x -> and y co-ordinates
        int x = (int) b.getX();
        int y = (int) b.getY();
        
        System.out.println("Mouse x: " + x);
        System.out.println("Mouse y: " + y);
        
        if(gate.getSize() == 1){
	    	if(b.getX() > 9 && b.getX() < 29 && b.getY() > 102 && b.getY() < 126) {
	        	inBoxes[0].setSelected(true);
	        }
        }
        
        if(gate.getSize() == 2){
	    	if(b.getX() > 7 && b.getX() < 30 && b.getY() > 81 && b.getY() < 105) {
	        	inBoxes[0].setSelected(true);
	        }
	        if(b.getX() > 8 && b.getX() < 28 && b.getY() > 121 && b.getY() < 144) {
	        	inBoxes[1].setSelected(true);
	        }
        }
        
        if(gate.getSize() == 3){
	    	if(b.getX() > 7 && b.getX() < 30 && b.getY() > 81 && b.getY() < 105) {
	        	inBoxes[0].setSelected(true);
	        }
	        if(b.getX() > 8 && b.getX() < 28 && b.getY() > 121 && b.getY() < 144) {
	        	inBoxes[0].setSelected(true);
	        }
        }
        
       
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
