import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame implements ActionListener{

    //this variable determines if we have selected a piece or not
    boolean pieceflag = false;
    //this variable holds the selected piece
    JButton currentbutton;
    //these variables are used for the timers and playernames
    Timer timer2;
    Timer timer1;
    JLabel playername2;
    JLabel playername1;
    int[] time = {0,0};
    JLabel playertimer2;
    JLabel playertimer1;
    //this variable holds of the buttons on the board
    JButton[][] boardbuttons = new JButton[8][8];
    //these are the borders used for the pieces
    Border greenborder = BorderFactory.createLineBorder(Color.GREEN, 3, true);
    Border cyanborder = BorderFactory.createLineBorder(Color.cyan, 3, true);
    Border yellowborder = BorderFactory.createLineBorder(Color.yellow, 3, true);
    //this variable contains all the possible moves
    ArrayList<JButton> PossibleMoveButtons = new ArrayList<>();
    //these are our models for the pieces
    ImageIcon black = new ImageIcon("BlackPiece.png");
    ImageIcon white = new ImageIcon("WhitePiece.png");
    ImageIcon whiteking = new ImageIcon("WhiteKing.png");
    ImageIcon blackking = new ImageIcon("BlackKing.png");
    //we create a new board and store in this variable
    //this variable resembles the board
    int[][] CurrentBoard = new Logic().CreateNewBoard(); 
    
    JLabel turnlabel = new JLabel();

    //create a frame and customize it t
    Frame(){
        super("Checkers Game Player1 vs Player2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 750);
        setResizable(false);
        setLayout(new FlowLayout());

        //add the playername and timer for player 2
        DisplayPlayer2();

        //add the board
        DisplayBoard();
        //this label tells which player turns it is
        //we customize it and add it to our frame just below the board
        turnlabel.setText("Player 1's Turn");
        turnlabel.setPreferredSize(new Dimension(115, 20));
        turnlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        add(turnlabel);

        //add the playername and timer for player 1
        DisplayPlayer1();

        //disable all the pieces that cant move
        ArrayList<JButton> enabledButtons = new ArrayList<>();
        Logic logiclass = new Logic();
        FindEnabledButtons(enabledButtons, logiclass);
        EnableCertainButtons(enabledButtons);


    }

    //this method adds the board we will be playing on
    private void DisplayBoard(){
        //create a new panel and customize it and add it to the frame
        //this serves as the board
        JPanel boardpanel = new JPanel();
        boardpanel.setPreferredSize(new Dimension(500, 500));
        boardpanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        boardpanel.setLayout(new GridLayout(8, 8)); //we make it 8 x 8
        add(boardpanel);

        //using these 2 for loops we can access every button of the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b = new JButton();

                //we set the background to white for every other button
                if((i+j)%2==0){
                    b.setBackground(Color.WHITE);
                //we set the background dark green for the remaining ones
                } else {
                    b.setBackground(Color.decode("#013220"));
                }
                //this variable will hold all the buttons from the board
                boardbuttons[i][j] = b;
                //customize the button, add action listener and add it to the panel
                b.setFocusable(false);
                b.addActionListener(this);
                b.setBorder(null);
                boardpanel.add(b);
            }
        }
        //this shows all the pieces on the board
        UpdateDisplay();
    }

    //this displays the playername and timer for player 1
    private void DisplayPlayer1(){
        //we create a jpanel, customize it and add it to our frame
        //this panel will hold our timer and playername
        JPanel player1 = new JPanel();
        player1.setPreferredSize(new Dimension(550, 70));
        player1.setLayout(new FlowLayout());
        add(player1);

        //we create a new label for our playername and customize it
        playername1 = new JLabel("Player 1");
        playername1.setPreferredSize(new Dimension(150, 70));
        playername1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

        //we create a new label for our timer and customize it
        playertimer1 = new JLabel("00:00");
        playertimer1.setPreferredSize(new Dimension(300, 70));
        playertimer1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        //add the new labels to our panel
        player1.add(playertimer1);
        player1.add(playername1);

        //we create the timer
        timer1 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //this is the counter for the timer
                time[0]++;
                if(time[0]==60){
                    time[1]++;
                    time[0]=0;
                }
                //update the time on the label
                playertimeupdate(time,playertimer1);
                //if we have reached 5 minutes
                if(time[1]==5){
                    //end the game
                    endscreen(2,1);
                }
            }
        });
        //we start the timer
        timer1.start();
    }
    //this displays the playername and timer for player 2
    private void DisplayPlayer2(){
        //we create a jpanel, customize it and add it to our frame
        //this panel will hold our timer and playername
        JPanel player2 = new JPanel();
        player2.setPreferredSize(new Dimension(550, 70));
        player2.setLayout(new FlowLayout());
        add(player2);

        //we create a new label for our playername, customize it and add it to the panel
        playername2 = new JLabel("Player 2");
        playername2.setPreferredSize(new Dimension(300, 70));
        playername2.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        playername2.setForeground(Color.gray);
        player2.add(playername2);

        //we create a new label for our timer, customize it and add it to the panel
        playertimer2 = new JLabel("00:00");
        playertimer2.setPreferredSize(new Dimension(150, 70));
        playertimer2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        player2.add(playertimer2);

        //we create a new timer
        timer2 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //this is the counter
                time[0]++;
                if(time[0]==60){
                    time[1]++;
                    time[0]=0;
                }
                //update the time on the label
                playertimeupdate(time,playertimer2);
                //if we have reached 5 minutes we end the game
                if(time[1]==5){
                    endscreen(1,1);
                }
            }
        });
    }

    //this method updates the time on the label
    private void playertimeupdate(int[] time, JLabel playertimer){
        playertimer.setText(String.format("%02d:%02d", time[1], time[0]));
    }

    //this method shows all the different outcomes for the endscreen
    private void endscreen(int player,int type){
        if(player==1 && type==1){
        JOptionPane.showMessageDialog(null, "Player 1 WON by Timeout", "Player 2 LOST", JOptionPane.ERROR_MESSAGE);
        } else if(player==2 && type==1){
        JOptionPane.showMessageDialog(null, "Player 2 WON by Timeout", "Player 1 LOST", JOptionPane.ERROR_MESSAGE); 
        } else if(player==1 && type==2){
        JOptionPane.showMessageDialog(null, "Player 1 WON. Player 2 has no moves", "Player 2 LOST", JOptionPane.ERROR_MESSAGE);
        } else if(player==2 && type==2){
        JOptionPane.showMessageDialog(null, "Player 2 WON. Player 1 has no moves", "Player 1 LOST", JOptionPane.ERROR_MESSAGE);
        }
        System.exit(0);
    }

    //this is the method that is called when a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {

        //we access the button we pressed using sourcebutton
        JButton sourcebutton = (JButton) e.getSource();
        Logic logiclass = new Logic();
        ArrayList<JButton> enabledButtons = new ArrayList<>(); 

        //this sees if we have a button selected and we want to deselect it
        if (pieceflag && sourcebutton==currentbutton) {
            //we deselect it
            pieceflag = false;
            sourcebutton.setBorder(yellowborder);

            for (JButton b : PossibleMoveButtons) {
                b.setBorder(null);
            }

            //we enable all the other buttons again
            FindEnabledButtons(enabledButtons, logiclass);
            EnableCertainButtons(enabledButtons);
            return;
        }     

        //we access the position of the sourcebutton on the board
        int i = GetPos(sourcebutton)[0];
        int j = GetPos(sourcebutton)[1];

        //if we dont have a selected piece
        if(!pieceflag){
            //if we can move
            if(CheckAllMoves(i, j, logiclass)){
                pieceflag = true;
                //we store the selected button
                currentbutton = sourcebutton;
                
                //check which moves we can make
                CheckTake(logiclass, i, j, sourcebutton);

                //we set the border to cyan to signify that button is selected 
                sourcebutton.setBorder(cyanborder);
                enabledButtons.add(sourcebutton);
                for (JButton b : PossibleMoveButtons) {
                    enabledButtons.add(b);
                }

                //we enable all the buttons that our piece can move to
                EnableCertainButtons(enabledButtons);
                return;
                }
            } 

        //we access the position of the selected button
        int x = GetPos(currentbutton)[0];
        int y = GetPos(currentbutton)[1];

        //if we have a piece selected
        if(pieceflag){
            //if the button we pressed is one of our pieces possible moves
            if(PossibleMoveButtons.contains(sourcebutton)){
                pieceflag = false;

                //we make the move 
                Take(x, y, logiclass, sourcebutton);

                //if it has reached the end we turn it into a king
                TurnKing();

                //we switch the timer
                TimerSwitch();

                //we update the display
                UpdateDisplay();
                //we clear the variable
                PossibleMoveButtons.clear();

                //enable all of the buttons that should be enabled
                FindEnabledButtons(enabledButtons, logiclass);
                EnableCertainButtons(enabledButtons);

                //if we dont have a move we end the game
                if(enabledButtons.isEmpty()){
                    if(timer1.isRunning()){
                        endscreen(2, 2);
                    } else {
                        endscreen(1, 2);
                    }
                }
                return;
            } 
        }
    }

    //this method checks which moves the selected piece can make
    private void CheckTake(Logic logiclass, int i, int j, JButton sourcebutton){
        //this finds the best sequence of takes
        List<int[]> bestCaptureSequences = logiclass.findMaxCaptures(i, j, CurrentBoard);

        //we check for white
        if((CurrentBoard[i][j]==2 || CurrentBoard[i][j]==22) && timer2.isRunning()){

        //if we can take
        if(CheckAllTakes(logiclass)){

        //we find all the buttons that can take
        for(JButton b : FindAllTakeButtons(logiclass)){

            //we customize
            b.setBorder(yellowborder);
            //if the button we pressed is one of pieces that can take
            if(sourcebutton==b){

            //we display only the best sequence of moves
           for (int[] sequence : bestCaptureSequences) {
                int Xvector = 0;
                int Yvector = 0;

                //we find where the piece will end up
                for (int move : sequence) {
                    if(move==0){
                        Xvector += 2;
                        Yvector += 2;
                    } else if(move==1){
                        Xvector += 2;
                        Yvector -= 2;
                    } else if(move==3){
                        Xvector -= 2;
                        Yvector += 2;
                    }  else if(move==4){
                        Xvector -= 2;
                        Yvector -= 2;
                    }
                }

                //we add it to PossibleMoveButtons and add a border
                boardbuttons[Xvector+i][Yvector+j].setBorder(greenborder);
                PossibleMoveButtons.add(boardbuttons[Xvector+i][Yvector+j]);
                
            }
        }
        }
    
        //if we cant take
        }else {
            //if we can move left we add a border and add it to Possible moves
            if(logiclass.CheckLeft(i, j, CurrentBoard)){
                boardbuttons[i+1][j+1].setBorder(greenborder);
                PossibleMoveButtons.add(boardbuttons[i+1][j+1]);
            }
            //if we can move right we do the same
            if(logiclass.CheckRight(i, j, CurrentBoard)){
                boardbuttons[i+1][j-1].setBorder(greenborder);
                PossibleMoveButtons.add(boardbuttons[i+1][j-1]);
            }
            //if we can move backleft we do the same
            if(logiclass.CheckBackLeft(i, j, CurrentBoard)){
                boardbuttons[i-1][j+1].setBorder(greenborder);
                PossibleMoveButtons.add(boardbuttons[i-1][j+1]);
            }
            //if we can move backright we do the same
            if(logiclass.CheckBackRight(i, j, CurrentBoard)){
                boardbuttons[i-1][j-1].setBorder(greenborder);
                PossibleMoveButtons.add(boardbuttons[i-1][j-1]);
            }
        }

        //we check for black
        } else if((CurrentBoard[i][j]==1 ||CurrentBoard[i][j]==11) && timer1.isRunning()){

            //if we can take
            if(CheckAllTakes(logiclass)){

            //we find all the buttons that can take
            for(JButton b : FindAllTakeButtons(logiclass)){
            b.setBorder(yellowborder);

            //if we have pressed a button that can take
            if(sourcebutton==b){

                //we find the endpoint of our button after the taking sequence
                for (int[] sequence : bestCaptureSequences) {
                    int Xvector = 0;
                    int Yvector = 0;

                    for (int move : sequence) {
                        if(move==0){
                            Xvector -= 2;
                            Yvector -= 2;
                        } else if(move==1){
                            Xvector -= 2;
                            Yvector += 2;
                        }  else if(move==3){
                            Xvector += 2;
                            Yvector -= 2;
                        } else if(move==4){
                            Xvector += 2;
                            Yvector += 2;
                        }
                    }

                //we add it to the possible moves and set a border
                boardbuttons[Xvector+i][Yvector+j].setBorder(greenborder);
                PossibleMoveButtons.add(boardbuttons[Xvector+i][Yvector+j]);

            }}}} else {
                //if we can move left we set a border and add it to possible moves
                if(logiclass.CheckLeft(i, j, CurrentBoard)){
                    boardbuttons[i-1][j-1].setBorder(greenborder);
                    PossibleMoveButtons.add(boardbuttons[i-1][j-1]);
                }
                //if we can move right we do the same
                if(logiclass.CheckRight(i, j, CurrentBoard)){
                    boardbuttons[i-1][j+1].setBorder(greenborder);
                    PossibleMoveButtons.add(boardbuttons[i-1][j+1]);
                }
                //if we can move backleft we do the same
                if(logiclass.CheckBackLeft(i, j, CurrentBoard)){
                    boardbuttons[i+1][j-1].setBorder(greenborder);
                    PossibleMoveButtons.add(boardbuttons[i+1][j-1]);
                }
                //if we can move backright we do the same
                if(logiclass.CheckBackRight(i, j, CurrentBoard)){
                    boardbuttons[i+1][j+1].setBorder(greenborder);
                    PossibleMoveButtons.add(boardbuttons[i+1][j+1]);
                }
            }
        }
    }

    //this method makes the move we selected
    private void Take(int x, int y, Logic logiclass, JButton sourcebutton){
        //we find the best sequence of takes
        List<int[]> bestCaptureSequences = logiclass.findMaxCaptures(x, y, CurrentBoard);

        //we check for black
        if((CurrentBoard[x][y]==1 || CurrentBoard[x][y]==11)){
            //if we have pressed to move left we move left
            if( logiclass.CheckLeft(x, y, CurrentBoard) && boardbuttons[x-1][y-1] == sourcebutton)logiclass.MoveLeft(x, y, CurrentBoard);
            //if we have pressed to move right we move right
            else if( logiclass.CheckRight(x, y, CurrentBoard) && boardbuttons[x-1][y+1] == sourcebutton)logiclass.MoveRight(x, y, CurrentBoard);
            //if we have pressed to move backleft we move backleft
            else if( logiclass.CheckBackLeft(x, y, CurrentBoard) && boardbuttons[x+1][y-1] == sourcebutton)logiclass.MoveBackLeft(x, y, CurrentBoard);
            //if we have pressed to move backright we move backright
            else if( logiclass.CheckBackRight(x,y,CurrentBoard) && boardbuttons[x+1][y+1] == sourcebutton)logiclass.MoveBackRight(x, y, CurrentBoard);
            //if we have pressed to take a piece
            else{
                //we acces the best sequence of takes
                for (int[] sequence : bestCaptureSequences) {
                    int Xvector = 0;
                    int Yvector = 0;

                    for (int take : sequence) {
                        if(take==0){
                            Xvector -= 2;
                            Yvector -= 2;
                        } else if(take==1){
                            Xvector -= 2;
                            Yvector += 2;
                        }  else if(take==3){
                            Xvector += 2;
                            Yvector -= 2;
                        }  else if(take==4){
                            Xvector += 2;
                            Yvector += 2;
                        }
                    }
                    //if we have pressed the button to make the current sequence of takes
                    if(sourcebutton == boardbuttons[x+Xvector][y+Yvector]){
                        int xvector = 0;
                        int yvector = 0;

                        //we take the pieces
                        for (int take : sequence) {
                            if(take==0){
                                logiclass.TakeLeft(x+xvector, y+yvector, CurrentBoard);
                                xvector -= 2;
                                yvector -= 2;
                            } else if(take==1){
                                logiclass.TakeRight(x+xvector, y+yvector, CurrentBoard);
                                xvector -= 2;
                                yvector += 2;
                            }  else if(take==3){
                                logiclass.TakeBackLeft(x+xvector, y+yvector, CurrentBoard);
                                xvector += 2;
                                yvector -= 2;
                            }  else if(take==4){
                                logiclass.TakeBackRight(x+xvector, y+yvector, CurrentBoard);
                                xvector += 2;
                                yvector += 2;
                            }
                        }
                    }
                }
            }

            //we check for white
        } else if((CurrentBoard[x][y]==2 || CurrentBoard[x][y]==22)){
            //if we have pressed to move left we move left
            if( logiclass.CheckLeft(x, y, CurrentBoard) && boardbuttons[x+1][y+1] == sourcebutton)logiclass.MoveLeft(x, y, CurrentBoard);
            //if we have pressed to move right we move right
            else if( logiclass.CheckRight(x, y, CurrentBoard) && boardbuttons[x+1][y-1] == sourcebutton)logiclass.MoveRight(x, y, CurrentBoard);
            //if we have pressed to move backleft we move backleft
            else if( logiclass.CheckBackLeft(x, y, CurrentBoard) && boardbuttons[x-1][y+1] == sourcebutton)logiclass.MoveBackLeft(x, y, CurrentBoard);
            //if we have pressed to move backright we move backright
            else if( logiclass.CheckBackRight(x,y,CurrentBoard) && boardbuttons[x-1][y-1] == sourcebutton)logiclass.MoveBackRight(x, y, CurrentBoard);
            //if we have pressed to take a piece
            else{
                //we acces the best sequences of takes
                for (int[] sequence : bestCaptureSequences) {
                    int Xvector = 0;
                    int Yvector = 0;

                    for (int take : sequence) {
                        if(take==0){
                            Xvector += 2;
                            Yvector += 2; 
                        } else if(take==1){
                            Xvector += 2;
                            Yvector -= 2;
                        } else if(take==3){
                            Xvector -= 2;
                            Yvector += 2;
                        } else if(take==4){
                            Xvector -= 2;
                            Yvector -= 2;
                        }
                    }

                    //if we have pressed the button to make the current sequence of takes
                    if(sourcebutton == boardbuttons[x+Xvector][y+Yvector]){
                        int xvector = 0;
                        int yvector = 0;

                        //we make the takes
                        for (int take : sequence) {
                            if(take==0){
                                logiclass.TakeLeft(x+xvector, y+yvector, CurrentBoard);
                                xvector += 2;
                                yvector += 2;
                            } else if(take==1){
                                logiclass.TakeRight(x+xvector, y+yvector, CurrentBoard);
                                xvector += 2;
                                yvector -= 2;
                            } else if(take==3){
                                logiclass.TakeBackLeft(x+xvector, y+yvector, CurrentBoard);
                                xvector -= 2;
                                yvector += 2;
                            } else if(take==4){
                                logiclass.TakeBackRight(x+xvector, y+yvector, CurrentBoard);
                                xvector -= 2;
                                yvector -= 2;
                            }
                        }
                    }
                }
            }
        }
    }

    //this method checks if we have reached the end of the board and turns the piece into a king
    private void TurnKing(){
            for (int i = 0; i < 8; i++) {
                if(timer1.isRunning()){
                    if(CurrentBoard[0][i]==1){
                        CurrentBoard[0][i]=11;
                    }
                } else {
                    if(CurrentBoard[7][i]==2){
                        CurrentBoard[7][i]=22;
                    }
                }
            }
    }

    //this method checks if whoevers turn it is if he can take any piece
    private boolean CheckAllTakes(Logic logiclass){
        //we use the two for loops to access all the buttons on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //if its player 1's turn
                if(timer1.isRunning()){
                    //we check if any of his pieces can take and return true
                    if((CurrentBoard[i][j]==1 || CurrentBoard[i][j]==11) && (logiclass.CheckBackLeftTake(i, j, CurrentBoard) || logiclass.CheckBackRightTake(i, j, CurrentBoard) || logiclass.CheckLeftTake(i, j, CurrentBoard) || logiclass.CheckRightTake(i, j, CurrentBoard))){
                        return true;
                    }
                //if its player 2's turn
                } else if(timer2.isRunning()){
                    //we check if any of his pieces can take and return true
                    if((CurrentBoard[i][j]==2 || CurrentBoard[i][j]==22) && (logiclass.CheckBackLeftTake(i, j, CurrentBoard) || logiclass.CheckBackRightTake(i, j, CurrentBoard) || logiclass.CheckLeftTake(i, j, CurrentBoard) || logiclass.CheckRightTake(i, j, CurrentBoard))){
                        return true;
                    }
                }
            }
        }
        //otherwise return false
        return false;
    }

    //this method finds all the buttons that can take
    private ArrayList<JButton> FindAllTakeButtons(Logic logiclass){
        ArrayList<JButton> a = new ArrayList<>(); 
        int max = 0;

        //again we access the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //if its player 1's turn
                if(timer1.isRunning() && (CurrentBoard[i][j]==1 || CurrentBoard[i][j]==11)){
                    List<int[]> bestCaptureSequences = logiclass.findMaxCaptures(i, j, CurrentBoard);

                    //we see if it can take
                    if(logiclass.CheckLeftTake(i, j, CurrentBoard) || logiclass.CheckRightTake(i, j, CurrentBoard) || logiclass.CheckBackLeftTake(i, j, CurrentBoard) || logiclass.CheckBackRightTake(i, j, CurrentBoard)){
                        //we find the pieces with the best sequences and add it to the variable a
                        for (int[] sequence : bestCaptureSequences) {
                            if(sequence.length>=max){

                                if(sequence.length>max)a.clear();
                                max=sequence.length;
                                a.add(boardbuttons[i][j]);
                            }
                            
                         }
                     }
                     //if its player 1's turn
                } else if( timer2.isRunning() && (CurrentBoard[i][j]==2 || CurrentBoard[i][j]==22)){
                    List<int[]> bestCaptureSequences = logiclass.findMaxCaptures(i, j, CurrentBoard);

                    //we see if it can take
                    if(logiclass.CheckLeftTake(i, j, CurrentBoard) || logiclass.CheckRightTake(i, j, CurrentBoard) || logiclass.CheckBackLeftTake(i, j, CurrentBoard) || logiclass.CheckBackRightTake(i, j, CurrentBoard)){
                        //we find the pieces with the best sequences and add it to the variable a
                        for (int[] sequence : bestCaptureSequences) {
                            if(sequence.length>=max){
 
                                if(sequence.length>max)a.clear();
                                max = sequence.length;
                                a.add(boardbuttons[i][j]);
                            }
                            
                            
                         }
                     }
                }
            }
        }
        //we return the buttons we have just found
        return a;
    }

    //this method finds the buttons that should be enabled
    private void FindEnabledButtons(ArrayList<JButton> enabledButtons, Logic logiclass){

        //if he can take
        if(CheckAllTakes(logiclass)){

        //add the buttons to the enabled buttons variable
        for (JButton b : FindAllTakeButtons(logiclass)) {
            enabledButtons.add(b);
            b.setBorder(yellowborder);
            }

        //if he cant take
        } else {
            //if its player 1's turn
        if(timer1.isRunning()){
                //we access the board
                for (int k = 0; k < 8; k++) {
                    for (int k2 = 0; k2 < 8; k2++) {
                        //if it can move we add it to the enabledbuttons variable
                        if((CurrentBoard[k][k2]==1 || CurrentBoard[k][k2]==11) && (logiclass.CheckLeft(k, k2, CurrentBoard) || logiclass.CheckRight(k, k2, CurrentBoard) || logiclass.CheckBackRight(k, k2, CurrentBoard) || logiclass.CheckBackLeft(k, k2, CurrentBoard))){
                            enabledButtons.add(boardbuttons[k][k2]);
                            boardbuttons[k][k2].setBorder(yellowborder);
                        }
                    }
                }
            //if its player 2's turn
        } else if(timer2.isRunning()){
                //we access the board
                for (int k = 0; k < 8; k++) {
                    for (int k2 = 0; k2 < 8; k2++) {
                        //if it can move we add it to the enabledbuttons variable
                        if((CurrentBoard[k][k2]==2 || CurrentBoard[k][k2]==22) && (logiclass.CheckLeft(k, k2, CurrentBoard) || logiclass.CheckRight(k, k2, CurrentBoard) || logiclass.CheckBackRight(k, k2, CurrentBoard) || logiclass.CheckBackLeft(k, k2, CurrentBoard))){
                            enabledButtons.add(boardbuttons[k][k2]);
                            boardbuttons[k][k2].setBorder(yellowborder);
                        }
                    }
                }
            }
        }
    }
    
    //this method enables only the buttons on the variable that we pass it
    private void EnableCertainButtons(ArrayList<JButton> enabledButtons){
        //we access the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //we disable the buttons
                boardbuttons[i][j].setDisabledIcon(GetIcon(boardbuttons[i][j]));
                boardbuttons[i][j].setEnabled(false);
            }
        }
        //we enable the buttons on the variable
        for (JButton jButton : enabledButtons) {
            jButton.setEnabled(true);
        }
    }

    //this method finds the icon of the button we pass it
    private ImageIcon GetIcon(JButton b){
        //we access the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //we find the position of the button we passed
                if(boardbuttons[i][j]==b){
                    //we return the corresponding icon
                    if(CurrentBoard[i][j]==1){
                        return black;
                    } else if(CurrentBoard[i][j]==2){
                        return white;
                    } else if(CurrentBoard[i][j]==22){
                        return whiteking;
                    } else if(CurrentBoard[i][j]==11){
                        return blackking;
                    }
                }
            }
        }
        //otherwise we return nothing
        return null;
    }

    //this method switches the timer
    private void TimerSwitch(){
        //if its player 1's turn
        if(timer1.isRunning()){
            //we edit the text which displays whose turn it is
            turnlabel.setText("Player 2's Turn");
            //we customize player 1's name and stop the timer
            playername1.setForeground(Color.GRAY);
            timer1.stop();

            //we reset the time and start player 2's time
            time[0]=0;
            time[1]=0;
            playertimeupdate(time, playertimer1);

            timer2.start();
            playername2.setForeground(Color.BLACK);

            //if its player 2's turn
        } else if(timer2.isRunning()){
            //we edit the text which displays whose turn it is
            turnlabel.setText("Player 1's Turn");
            //we customize player 1's name and stop the timer
            playername1.setForeground(Color.black);
            timer2.stop();

            //we reset the time and start player 2's time
            time[0]=0;
            time[1]=0;
            playertimeupdate(time, playertimer2);

            timer1.start();
            playername2.setForeground(Color.gray);

        }
    }

    //this method updates the display for the corresponding pieces
    private void UpdateDisplay(){
        //we access the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                //we reset the borders
                boardbuttons[i][j].setBorder(null);

                //we set the piece icons to the corresponding buttons
                if(CurrentBoard[i][j]==0){
                    boardbuttons[i][j].setIcon(null);
                } else if(CurrentBoard[i][j]==1){
                    boardbuttons[i][j].setIcon(black);
                } else if(CurrentBoard[i][j]==2){
                    boardbuttons[i][j].setIcon(white);
                } else if(CurrentBoard[i][j]==11){
                    boardbuttons[i][j].setIcon(blackking);
                } else if(CurrentBoard[i][j]==22){
                    boardbuttons[i][j].setIcon(whiteking);
                }
            }
        }
    }

    //this methods finds the position of the passed button
    private int[] GetPos(JButton b){
        int[] pos = new int[2];
        //we access the button
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //we find the position of the passed button
                if(boardbuttons[i][j]==b){
                    pos[0]=i;
                    pos[1]=j;
                    break;
                }
            }
        }
        //return the position
        return pos;
    }

    //this method checks if the button at the given coordinates can move or take in any direction and returns true
    private boolean CheckAllMoves(int x, int y, Logic l){
        if(l.CheckBackLeft(x, y, CurrentBoard) || l.CheckBackRight(x, y, CurrentBoard) || l.CheckLeft(x, y, CurrentBoard) || l.CheckRight(x, y, CurrentBoard)){
            return true;
        }
        if(l.CheckBackLeftTake(x, y, CurrentBoard) || l.CheckBackRightTake(x, y, CurrentBoard) || l.CheckLeftTake(x,y,CurrentBoard) || l.CheckRightTake(x, y, CurrentBoard)){
            return true;
        }
        //else it returns false
        return false;
    }
}