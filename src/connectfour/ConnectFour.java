package connectfour;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lefteris
 */
public class ConnectFour {
    JFrame frame;
    JPanel panel;
    JButton[][] button = new JButton[6][7];
    GridLayout myGrid = new GridLayout(6,7);
    int[][] grid = new int[6][7];
    
    final ImageIcon  c1 = new ImageIcon("red.jpg");
    final ImageIcon c2 = new ImageIcon("yellow.png");
    
    String win = "none";
    String check;
    int turn = 0;
    
    public ConnectFour(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(i==5){
                    grid[i][j] = -1;
                }else{
                    grid[i][j] = -2;
                }
            }
            
        }
        frame = new JFrame("Connect4");
        frame.setSize(1000,1000);
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(myGrid);
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                button[i][j] = new JButton("");
                button[i][j].addActionListener(new buttonListener());
                button[i][j].setBackground(Color.WHITE);
                button[i][j].setBorder(BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(Color.BLUE, 5),
                                        BorderFactory.createLineBorder(Color.BLUE, 0)));
                button[i][j].setPreferredSize(new Dimension(70,70));
                panel.add(button[i][j]);
                
                
            }
            
        }
        
        
        frame.setContentPane(panel);
	frame.setSize(1000,1000);
	frame.pack();
	frame.setVisible(true);
    }

    class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if(e.getSource() == button[i][j]){
                        if(turn ==0){
                            if (i <= 4) {
                                if (grid[i + 1][j] != -2) {
                                    button[i][j].setBackground(Color.RED);
                                    grid[i][j] = 0;
                                    turn++;
                                }
                            } else {
                                button[i][j].setBackground(Color.RED);
                                grid[i][j] = 0;
                                turn++;
                            }
                        }else if(turn ==1){
                            if(i<=4){
                                if(grid[i+1][j]!= -2){
                                    button[i][j].setBackground(Color.YELLOW);
                                    grid[i][j] = 1;
                                    turn--;
                                }
                            }else{
                                button[i][j].setBackground(Color.YELLOW);
                                grid[i][j] = 1;
                                turn--;
                            }
                            
                        }
                        check = checkWin();
                        if(check.equals("red")){
                            System.out.println("RED");
                        }else if(check.equals("green")){
                            System.out.println("GREEN");
                        }
                        
                    }
                    
                }
                
            }
                
        }
    
    }
    
    public String checkWin(){
        //red player
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length -3; col++) {
                if (grid[row][col] == 0
                        && grid[row][col] == grid[row][col+1]
                        && grid[row][col] == grid[row][col+2]
                        && grid[row][col] == grid[row][col+3]) {
                    win = "red";
                }
            }
        }
        // check for a vertical win
        for (int row = 0; row < grid.length-3; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0
                        && grid[row][col] == grid[row+1][col]
                        && grid[row][col] == grid[row+2][col]
                        && grid[row][col] == grid[row+3][col]) {
                    win = "red";
                }
            }
        }
        // check for a diagonal win (positive slope)
        for (int row = 3; row < 6; row++) {
            for (int col = 0; col < 7-3; col++) {
                if (grid[row][col] == 0
                        && grid[row][col] == grid[row - 1][col + 1]
                        && grid[row][col] == grid[row - 2][col + 2]
                        && grid[row][col] == grid[row - 3][col + 3]) {
                    win = "red";
                }
            }
        }
        // check for a diagonal win (negative slope)
        for (int row = 0; row < 6-3; row++) {
            for (int col = 0; col < 7-3; col++) {
                if (grid[row][col] == 0
                        && grid[row][col] == grid[row + 1][col + 1]
                        && grid[row][col] == grid[row + 2][col + 2]
                        && grid[row][col] == grid[row + 3][col + 3]) {
                    win = "red";
                }
            }
        }
        
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7-3; col++) {
                if (grid[row][col] == 1
                        && grid[row][col] == grid[row][col+1]
                        && grid[row][col] == grid[row][col+2]
                        && grid[row][col] == grid[row][col+3]) {
                    win = "yellow";
                }
            }
        }
        // check for a vertical win
        for (int row = 0; row < 6-3; row++) {
            for (int col = 0; col < 7; col++) {
                if (grid[row][col] == 1
                        && grid[row][col] == grid[row+1][col]
                        && grid[row][col] == grid[row+2][col]
                        && grid[row][col] == grid[row+3][col]) {
                    win = "yellow";
                }
            }
        }
        // check for a diagonal win (positive slope)
        for (int row = 3; row < 6; row++) {
            for (int col = 0; col < 7-3; col++) {
                if (grid[row][col] == 1
                        && grid[row][col] == grid[row - 1][col + 1]
                        && grid[row][col] == grid[row - 2][col + 2]
                        && grid[row][col] == grid[row - 3][col + 3]) {
                    win = "yellow";
                }
            }
        }
        // check for a diagonal win (negative slope)
        for (int row = 0; row < 6-3; row++) {
            for (int col = 0; col < 7-3; col++) {
                if (grid[row][col] == 1
                        && grid[row][col] == grid[row + 1][col + 1]
                        && grid[row][col] == grid[row + 2][col + 2]
                        && grid[row][col] == grid[row + 3][col + 3]) {
                    win = "yellow";
                }
            }
        }
        return win;
    }
    
    
    
    public static void main(String[] args) {
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {	
                            new ConnectFour();
			}
		});
	}        
        
}