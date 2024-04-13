import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTocToe5{
	
	JFrame frame=new JFrame("TicTacToe");
	JPanel[] panel =new JPanel[3];
	JLabel msg= new JLabel();
	JButton[] bt= new JButton[9];
	JButton reset= new JButton("RESET");
	
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	int player=1; int count=0;
	boolean winnerFound=false;
	
	String name1,name2;
	
	public TicTocToe5()
	{
		frame.setSize(500,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.black);
		// frame ka ak method h -getContentPane(). yah container object ka reference dega
		addpanels();
		acceptPlayerName();
		frame.setVisible(true);
	}
	private void acceptPlayerName()
	{
		name1=JOptionPane.showInputDialog(frame,"Enter first player name");
		name2=JOptionPane.showInputDialog(frame,"Enter Second player name");
		msg.setText(name1+" turn..");
	}
	private void addpanels()
	{
		for(int i=0;i<3;i++)
		{
			panel[i]=new JPanel();
			frame.add(panel[i]);
		}
		panel[0].setBounds(50,30,400,40);
		panel[1].setBounds(50,100,400,370);
		panel[2].setBounds(50,500,400,40);
		addMessageLabel();
	}
	private void addMessageLabel()
	{
		panel[0].add(msg);
		msg.setFont(new Font("elephant",0,30));
		msg.setForeground(Color.blue);// text ka color change krne ke liye
		panel[0].setBackground(Color.cyan);
		
		addbuttons();
	}
	private void addbuttons()
	{
		panel[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		for(int i=0; i<9;i++)
		{
			bt[i]=new JButton();
			bt[i].addActionListener(listener);
			bt[i].setBackground(Color.yellow);
			panel[1].add(bt[i]);
		}
		addreset();
	}
	private void addreset()
	{
		panel[2].add(reset);
		reset.setFont(new Font("arial",0,20));
		panel[2].setOpaque(false);
		reset.addActionListener(new ResetListener());
		reset.setEnabled(false);
	}
	
	class TicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			//JOptionPane.showMessageDialog(frame, "called");
			JButton bb=(JButton)evt.getSource();
			if(bb.getIcon() !=null || winnerFound)
			{
				JOptionPane.showMessageDialog(frame,"Wrong clicked");			
				return; //return se method ko terminate kr diya jata h
			}
			//This code should be executed when button that was clicked has no image
			if(player==1)
			{
				bb.setIcon(icon1);
				msg.setText(name2+" turn..");
				player=2;
			}
			else if(player==2)
			{
				bb.setIcon(icon2);
				msg.setText(name1+" turn..");
				player=1;
			}
			findWinner();
			count++;
			if(count==9 && !winnerFound)
			{
				msg.setText("GAME OVER...");
				panel[0].setBackground(Color.red);
			    JOptionPane.showMessageDialog(frame,"No one is winner");
			    reset.setEnabled(true);
			}
		} // end of actionPerformed method
		
		private void findWinner()
		{
			// row Wise
			if(bt[0].getIcon()==icon1 && bt[1].getIcon()==icon1 && bt[2].getIcon()==icon1)
				announceWinner(0,1,2);
			else if(bt[0].getIcon()==icon2 && bt[1].getIcon()==icon2 && bt[2].getIcon()==icon2)
				announceWinner(0,1,2);
			else if(bt[3].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[5].getIcon()==icon1)
				announceWinner(3,4,5);
			else if(bt[3].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[5].getIcon()==icon2)
				announceWinner(3,4,5);
			else if(bt[6].getIcon()==icon1 && bt[7].getIcon()==icon1 && bt[8].getIcon()==icon1)
				announceWinner(6,7,8);
			else if(bt[6].getIcon()==icon2 && bt[7].getIcon()==icon2 && bt[8].getIcon()==icon2)
				announceWinner(6,7,8);
			
			//Colom Wise
			else if(bt[0].getIcon()==icon1 && bt[3].getIcon()==icon1 && bt[6].getIcon()==icon1)
				announceWinner(0,3,6);
			else if(bt[0].getIcon()==icon2 && bt[3].getIcon()==icon2 && bt[6].getIcon()==icon2)
				announceWinner(0,3,6);
			else if(bt[1].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[7].getIcon()==icon1)
				announceWinner(1,4,7);
			else if(bt[1].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[7].getIcon()==icon2)
				announceWinner(1,4,7);
			else if(bt[2].getIcon()==icon1 && bt[5].getIcon()==icon1 && bt[8].getIcon()==icon1)
				announceWinner(2,5,8);
			else if(bt[2].getIcon()==icon2 && bt[5].getIcon()==icon2 && bt[8].getIcon()==icon2)
				announceWinner(2,5,8);
			
			//DiagonalWise
			else if(bt[0].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[8].getIcon()==icon1)
				announceWinner(0,4,8);
			else if(bt[0].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[8].getIcon()==icon2)
				announceWinner(0,4,8);
			else if(bt[2].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[6].getIcon()==icon1)
				announceWinner(2,4,6);
			else if(bt[2].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[6].getIcon()==icon2)
				announceWinner(2,4,6);
		}//end of findWinner method
		
		private void announceWinner(int i, int j, int k)
		{  
			winnerFound=true;
			bt[i].setBackground(Color.green);
			bt[j].setBackground(Color.green);
			bt[k].setBackground(Color.green);
			msg.setText("GAME OVER...");
			panel[0].setBackground(Color.red);
			if(player==2)
				JOptionPane.showMessageDialog(frame,name1 +" has won");
			else
				JOptionPane.showMessageDialog(frame,name2 +" has won");	
			reset.setEnabled(true);
		}//end of announceWinner method
	}//end of TicListener class
	
	// fir se pura restart krna h
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			winnerFound=false;
			count=0;
			player=1;
			msg.setText("First player turn...");
			panel[0].setBackground(Color.cyan);
			for(JButton bb:bt)
			{
				bb.setBackground(Color.yellow);
				bb.setIcon(null);
			}
			reset.setEnabled(false);
		}
	}
	public static void main(String[] args) 
	{
		new TicTocToe5();
	}

}
