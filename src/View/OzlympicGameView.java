/**@Author Loso
 * OzlympicMainFrame is the main window view of the game
 * 
 */

package View;
import Controller.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class OzlympicGameView {
	//define main menu options
	public static final int SELECT_GAME 			= 1;
	public static final int SELECT_PARTICIPANT		= 2;
	public static final int START_GAME 				= 3;
	public static final int DISPLAY_FINALRESULT 	= 4;
	public static final int DISPLAY_ATHLETEPOINTS	= 5;
		
	//define game status
	public static final int GAME_DEFAULT 	= 0;
	public static final int GAME_INITIATED 	= 1;
	public static final int GAME_EXECUTED 	= 2;
	public static int gameStatus 			= GAME_DEFAULT;
	
	private JFrame 		frmOzlympicgame;
	private JMenuItem 	mntmSwimming;
	private JMenuItem 	mntmCycling;
	private JMenuItem 	mntmRunning;
	private JLabel 		lbGameType;
	private JPanel      plContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OzlympicGameView window = new OzlympicGameView();
					window.frmOzlympicgame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OzlympicGameView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOzlympicgame = new JFrame();
		frmOzlympicgame.setTitle("OzlympicGame");
		frmOzlympicgame.setBounds(100, 100, 800, 600);
		frmOzlympicgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOzlympicgame.getContentPane().setLayout(null);	
		
		JLabel lbContentDisplay = new JLabel();
		lbContentDisplay.setBounds(10, 70, 764, 460);
		lbContentDisplay.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
		frmOzlympicgame.getContentPane().add(lbContentDisplay);
		
		lbGameType = new JLabel("Instraction:");
		lbGameType.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lbGameType.setBounds(10, 10, 764, 50);
		frmOzlympicgame.getContentPane().add(lbGameType);
		
		plContent = new JPanel();
		plContent.setBounds(25, 90, 720, 400);
		String instraction = "Game instraction:\n" +
				 			 "1. Select a game type from menu bar.\n" +
				 			 "2. Choose participant and referee to run the game.\n" +
				 			 "3. Click 'Play' button to start the game";
		JTextArea textInstraction = new JTextArea(instraction);
		textInstraction.setFont(new Font("Monospaced", Font.BOLD, 30));
		textInstraction.setEditable(false);
		textInstraction.setBounds(0, 0, 720, 400);
		plContent.add(textInstraction);
		frmOzlympicgame.add(plContent);
		
		JMenuBar menuBar = new JMenuBar();
		frmOzlympicgame.setJMenuBar(menuBar);
		
		JMenu mnGameType = new JMenu("GAME TYPE");
		menuBar.add(mnGameType);
		
		mntmSwimming = new JMenuItem("Swimming");
		mntmSwimming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGameTypeLabel(mntmSwimming.getText());
			}
		});
		mnGameType.add(mntmSwimming);
		
		mntmCycling = new JMenuItem("Cycling");
		mntmCycling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGameTypeLabel(mntmCycling.getText());
			}
		});
		mnGameType.add(mntmCycling);
		
		mntmRunning = new JMenuItem("Running");
		mntmRunning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGameTypeLabel(mntmRunning.getText());
			}
		});
		mnGameType.add(mntmRunning);
		
		JMenu mnResults = new JMenu("RESULTS");
		menuBar.add(mnResults);
		
		JMenuItem mntmAthlete = new JMenuItem("Athlete points");
		mnResults.add(mntmAthlete);
		
		JMenuItem mntmGameResult = new JMenuItem("Game result");
		mnResults.add(mntmGameResult);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmGameRules = new JMenuItem("Game rules");
		mnHelp.add(mntmGameRules);
	}
	
	public void setGameTypeLabel(String str)
	{
		Driver controller = Driver.getInstance();
		if(controller != null)
			controller.processByUserInput(SELECT_GAME, str);
			
		str += " Game:";
		lbGameType.setText(str);
		
		//clear content
		plContent.removeAll();
		frmOzlympicgame.repaint();
	}
}
