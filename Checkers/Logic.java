import java.util.*;

//this class is used for all the methods that are used for the logic in the other class
//we use the CurrentBoard variable which resembles the board with the pieces
public class Logic{

    //this method creates a new board
    public int[][] CreateNewBoard(){
        int[][] NewBoard = new int[8][8];
        //we access the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //we set the pieces according to their starting place
                if((i+j)%2!=0 && i<3){
                    NewBoard[i][j] = 2;
                } else if((i+j)%2!=0 && i>4 ){
                    NewBoard[i][j] = 1;
                } else {
                    NewBoard[i][j] = 0;
                }
            }
        }
        //we return the created board
        return NewBoard;
    }

    //this method checks if the piece at the given coordinates can move left and returns true
    public boolean CheckLeft(int x, int y, int[][] CurrentBoard){
        if((CurrentBoard[x][y]==1 || CurrentBoard[x][y]==11) && x>0 && y>0 && CurrentBoard[x-1][y-1] == 0){ 
            return true;
        } else if((CurrentBoard[x][y]==2 || CurrentBoard[x][y]==22) && x<7 && y<7 && CurrentBoard[x+1][y+1] == 0){
            return true;
        }
        //otherwise we return false
        return false;
    }

    //this method moves the current button one square to the left it can and updates the variable resembling the board
    public void MoveLeft(int x, int y, int[][] CurrentBoard){
        if(CheckLeft(x, y, CurrentBoard)){
            if(CurrentBoard[x][y]==1){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x-1][y-1] = 1; 
            } else if(CurrentBoard[x][y]==2){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x+1][y+1] = 2; 
            } else if(CurrentBoard[x][y]==11){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x-1][y-1] = 11; 
            } else if(CurrentBoard[x][y]==22){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x+1][y+1] = 22; 
            }
        }
    }

    //this method checks if the piece at the given coordinates can move right and returns true
    public boolean CheckRight(int x, int y, int[][] CurrentBoard){
        if((CurrentBoard[x][y]==2 || CurrentBoard[x][y]==22) && x<7 && y>0 && CurrentBoard[x+1][y-1] == 0){ 
            return true;
        } else if((CurrentBoard[x][y]==1 || CurrentBoard[x][y]==11) && x>0 && y<7 && CurrentBoard[x-1][y+1] == 0){
            return true;
        }
        //otherwise we return false
        return false;
    }

    //this method moves the current button one square to the right it can and updates the variable resembling the board
    public void MoveRight(int x, int y, int[][] CurrentBoard){
        if(CheckRight(x, y, CurrentBoard)){
            if(CurrentBoard[x][y]==2){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x+1][y-1] = 2; 
            } else if(CurrentBoard[x][y]==1){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x-1][y+1] = 1; 
            } else if(CurrentBoard[x][y]==22){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x+1][y-1] = 22; 
            } else if(CurrentBoard[x][y]==11){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x-1][y+1] = 11; 
            }
        }
    }

    //this method checks if the piece at the given coordinates can move backleft and returns true
    public boolean CheckBackLeft(int x, int y, int[][] CurrentBoard){
        if(CurrentBoard[x][y]==22 && x>0 && y<7 && CurrentBoard[x-1][y+1] == 0){
            return true;
        } else if(CurrentBoard[x][y]==11 && x<7 && y>0 && CurrentBoard[x+1][y-1] == 0){
            return true;
        }  
        //otherwise we return false      
        return false;
    }

    //this method moves the current button one square to the backleft it can and updates the variable resembling the board
    public void MoveBackLeft(int x, int y, int[][] CurrentBoard){
        if(CheckBackLeft(x, y, CurrentBoard)){
            if(CurrentBoard[x][y] == 11){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x+1][y-1] = 11; 
            } else if(CurrentBoard[x][y] == 22){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x-1][y+1] = 22; 
            }
        }
    }

    //this method checks if the piece at the given coordinates can move backright and returns true
    public boolean CheckBackRight(int x, int y, int[][] CurrentBoard){
        if(CurrentBoard[x][y]==11 && x<7 && y<7 && CurrentBoard[x+1][y+1]==0){
            return true;
        } else if(CurrentBoard[x][y]==22 && x>0 && y>0 && CurrentBoard[x-1][y-1]==0){
            return true;
        }
        //otherwise we return false
        return false;
    }

    //this method moves the current button one square to the backright it can and updates the variable resembling the board
    public void MoveBackRight(int x, int y, int[][] CurrentBoard){
        if(CheckBackRight(x, y, CurrentBoard)){
            if(CurrentBoard[x][y] == 11){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x+1][y+1] = 11; 
            } else if(CurrentBoard[x][y] == 22){
                CurrentBoard[x][y] = 0;
                CurrentBoard[x-1][y-1] = 22; 
            }
        }
    }

    //this method checks if the piece at the given coordinates can take right and returns true
    public boolean CheckRightTake(int x, int y, int[][] CurrentBoard){
        if((CurrentBoard[x][y]==2 || CurrentBoard[x][y]==22) && x<6 && y>1 && (CurrentBoard[x+1][y-1]==1 || CurrentBoard[x+1][y-1]==11) && CurrentBoard[x+2][y-2]==0){
            return true;
        } else if((CurrentBoard[x][y]==1 || CurrentBoard[x][y]==11) && x>1 && y<6 && (CurrentBoard[x-1][y+1]==2 || CurrentBoard[x-1][y+1]==22) && CurrentBoard[x-2][y+2]==0){
            return true;
        }
        //otherwise we return false
        return false;
    }

    //this method takes one piece to the right if it can and updates the variable resembling the board
    public void TakeRight(int x, int y, int[][] CurrentBoard){
        if(CheckRightTake(x, y, CurrentBoard)){
            if(CurrentBoard[x][y]==2){
                CurrentBoard[x][y]=0;
                CurrentBoard[x+1][y-1]=0;
                CurrentBoard[x+2][y-2]=2;
            } else if(CurrentBoard[x][y]==1){
                CurrentBoard[x][y]=0;
                CurrentBoard[x-1][y+1]=0;
                CurrentBoard[x-2][y+2]=1;
            } else if(CurrentBoard[x][y]==22){
                CurrentBoard[x][y]=0;
                CurrentBoard[x+1][y-1]=0;
                CurrentBoard[x+2][y-2]=22;
            } else if(CurrentBoard[x][y]==11){
                CurrentBoard[x][y]=0;
                CurrentBoard[x-1][y+1]=0;
                CurrentBoard[x-2][y+2]=11;
            }

        }
    }

    //this method checks if the piece at the given coordinates can take left and returns true
    public boolean CheckLeftTake(int x, int y, int[][] CurrentBoard){
        if((CurrentBoard[x][y]==2 || CurrentBoard[x][y]==22) && x<6 && y<6 && (CurrentBoard[x+1][y+1]==1 || CurrentBoard[x+1][y+1]==11) && CurrentBoard[x+2][y+2]==0){
            return true;
        } else if((CurrentBoard[x][y]==1 || CurrentBoard[x][y]==11) && x>1 && y>1 && (CurrentBoard[x-1][y-1]==2 || CurrentBoard[x-1][y-1]==22) && CurrentBoard[x-2][y-2]==0){
            return true;
        }
        //otherwise we return false
        return false;
    }

    //this method takes one piece to the left if it can and updates the variable resembling the board
    public void TakeLeft(int x, int y, int[][] CurrentBoard){
        if(CheckLeftTake(x, y, CurrentBoard)){
            if(CurrentBoard[x][y]==2){
                CurrentBoard[x][y]=0;
                CurrentBoard[x+1][y+1]=0;
                CurrentBoard[x+2][y+2]=2;
            } else if(CurrentBoard[x][y]==1){
                CurrentBoard[x][y]=0;
                CurrentBoard[x-1][y-1]=0;
                CurrentBoard[x-2][y-2]=1;
            } else if(CurrentBoard[x][y]==22){
                CurrentBoard[x][y]=0;
                CurrentBoard[x+1][y+1]=0;
                CurrentBoard[x+2][y+2]=22;
            } else if(CurrentBoard[x][y]==1){
                CurrentBoard[x][y]=0;
                CurrentBoard[x-1][y-1]=0;
                CurrentBoard[x-2][y-2]=11;
            }
        }
    }

    //this method checks if the piece at the given coordinates can take backright and returns true
    public boolean CheckBackRightTake(int x, int y, int[][] CurrentBoard){
        if(CurrentBoard[x][y]==11 && x<6 && y<6 && (CurrentBoard[x+1][y+1]==2 || CurrentBoard[x+1][y+1]==22) && CurrentBoard[x+2][y+2]==0){
            return true;
        } else if(CurrentBoard[x][y]==22 && x>1 && y>1 && (CurrentBoard[x-1][y-1]==1 || CurrentBoard[x-1][y-1]==11) && CurrentBoard[x-2][y-2]==0){
            return true;
        } 
        //otherwise we return false
        return false;
    }

    //this method takes one piece to the backright if it can and updates the variable resembling the board
    public void TakeBackRight(int x, int y, int[][] CurrentBoard){
        if(CheckBackRightTake(x, y, CurrentBoard)){
            if(CurrentBoard[x][y]==22){
                CurrentBoard[x][y]=0;
                CurrentBoard[x-1][y-1]=0;
                CurrentBoard[x-2][y-2]=22;
            } else if(CurrentBoard[x][y]==11){
                CurrentBoard[x][y]=0;
                CurrentBoard[x+1][y+1]=0;
                CurrentBoard[x+2][y+2]=11;
            }
        }
    }

    //this method checks if the piece at the given coordinates can take backleft and returns true
    public boolean CheckBackLeftTake(int x, int y, int[][] CurrentBoard){
        if(CurrentBoard[x][y]==11 && x<6 && y>1 && (CurrentBoard[x+1][y-1]==2 || CurrentBoard[x+1][y-1]==22) && CurrentBoard[x+2][y-2]==0){
            return true;
        } else if(CurrentBoard[x][y]==22 && x>1 && y<6 && (CurrentBoard[x-1][y+1]==1 || CurrentBoard[x-1][y+1]==11) && CurrentBoard[x-2][y+2]==0){
            return true;
        } 
        //otherwise we return false
        return false;
    }

    //this method takes one piece to the backleft if it can and updates the variable resembling the board
    public void TakeBackLeft(int x, int y, int[][] CurrentBoard){
        if(CheckBackLeftTake(x, y, CurrentBoard)){
            if(CurrentBoard[x][y]==22){
                CurrentBoard[x][y]=0;
                CurrentBoard[x-1][y+1]=0;
                CurrentBoard[x-2][y+2]=22;
            } else if(CurrentBoard[x][y]==11){
                CurrentBoard[x][y]=0;
                CurrentBoard[x+1][y-1]=0;
                CurrentBoard[x+2][y-2]=11;
            }
        }
    }

    // this method finds the best sequence of takes and returns it in int arrays
    // 0-left 1-right 3-backleft 4-backright
    public List<int[]> findMaxCaptures(int x, int y, int[][] CurrentBoard) {
        //we create a temporary board which is a copy of the currentboard
        int[][] tempBoard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(CurrentBoard[i], 0, tempBoard[i], 0, 8);
        }
        //this variable holds the sequences 
        List<int[]> bestSequences = new ArrayList<>();
        int maxCaptures = 0;

        //Initialize helper with initial moves
        List<MoveSequence> initialSequences = findSequences(x, y, tempBoard);
        for (MoveSequence moveSeq : initialSequences) {
            int captureCount = moveSeq.captureCount;
            //we find which sequence takes the most pieces and add it to bestsequences
            if (captureCount > maxCaptures) {
                maxCaptures = captureCount;
                bestSequences.clear();
                bestSequences.add(moveSeq.sequence);
            //if it takes the same amount we add it to bestsequences
            } else if (captureCount == maxCaptures) {
                bestSequences.add(moveSeq.sequence);
            }
        }
        //return the sequences
        return bestSequences;
    }

    // this method finds the sequences
    private List<MoveSequence> findSequences(int x, int y, int[][] CurrentBoard) {
        List<MoveSequence> sequences = new ArrayList<>();
        int[][] tempBoard;
        int[] sequence;

        // we check right captures
        if (CheckRightTake(x, y, CurrentBoard)) {
            //we make a copy of the currentboard
            tempBoard = new int[8][8];
            for (int i = 0; i < 8; i++) {
                System.arraycopy(CurrentBoard[i], 0, tempBoard[i], 0, 8);
            }
            //if its a white piece
            if (CurrentBoard[x][y] == 2 || CurrentBoard[x][y] == 22) {
                //we take right
                TakeRight(x, y, tempBoard);
                //we add a 1 resembling right to the int array
                sequence = new int[]{1};
                List<MoveSequence> rightSequences = findSequences(x + 2, y - 2, tempBoard);
                for (MoveSequence seq : rightSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1)); 
                }
            //if its a black piece
            } else if (CurrentBoard[x][y] == 1 || CurrentBoard[x][y] == 11) {
                //we take right
                TakeRight(x, y, tempBoard);
                //we add a 1 resembling right to the int array
                sequence = new int[]{1}; 
                List<MoveSequence> rightSequences = findSequences(x - 2, y + 2, tempBoard);
                for (MoveSequence seq : rightSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1)); 
                }
            }
        }

        //we check left captures
        if (CheckLeftTake(x, y, CurrentBoard)) {
            //we make a copy of the currentboard
            tempBoard = new int[8][8];
            for (int i = 0; i < 8; i++) {
                System.arraycopy(CurrentBoard[i], 0, tempBoard[i], 0, 8);
            }
            //if its a white piece
            if (CurrentBoard[x][y] == 2 || CurrentBoard[x][y] == 22) {
                //we take left
                TakeLeft(x, y, tempBoard);
                //we add a 0 resembling left to the int array
                sequence = new int[]{0};
                List<MoveSequence> leftSequences = findSequences(x + 2, y + 2, tempBoard);
                for (MoveSequence seq : leftSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1)); 
                }
                //if its a black piece
            } else if (CurrentBoard[x][y] == 1 || CurrentBoard[x][y] == 11) {
                //we take left
                TakeLeft(x, y, tempBoard);
                //we add a 0 resembling left to the int array
                sequence = new int[]{0}; 
                List<MoveSequence> leftSequences = findSequences(x - 2, y - 2, tempBoard);
                for (MoveSequence seq : leftSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1)); 
                }
            }
        }

        //we check back right captures
        if(CheckBackRightTake(x, y, CurrentBoard)){
            //we make a copy of the currentboard
            tempBoard = new int[8][8];
            for (int i = 0; i < 8; i++) {
                System.arraycopy(CurrentBoard[i], 0, tempBoard[i], 0, 8);
            }
            //if its a white king
            if(CurrentBoard[x][y] == 22){
                //we take backright
                TakeBackRight(x, y, tempBoard);
                //we add a 4 resembling backright to the int array
                sequence = new int[]{4};
                List<MoveSequence> backrightSequences = findSequences(x - 2, y - 2, tempBoard);
                for (MoveSequence seq : backrightSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1));
                }
            //if its a black king
            } else if(CurrentBoard[x][y] == 11){
                //we take backright
                TakeBackRight(x, y, tempBoard);
                //we add a 4 resembling backright to the int array
                sequence = new int[]{4};
                List<MoveSequence> backrightSequences = findSequences(x + 2, y + 2, tempBoard);
                for (MoveSequence seq : backrightSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1));
                }
            }
        }

        //we check backleft captures
        if(CheckBackLeftTake(x, y, CurrentBoard)){
            //we make a copy of the currentboard
            tempBoard = new int[8][8];
            for (int i = 0; i < 8; i++) {
                System.arraycopy(CurrentBoard[i], 0, tempBoard[i], 0, 8);
            }
            //if its a white king
            if(CurrentBoard[x][y] == 22){
                //we take back left
                TakeBackLeft(x, y, tempBoard);
                //we add a 3 resembling backleft to the int array
                sequence = new int[]{3};
                List<MoveSequence> backleftSequences = findSequences(x - 2, y + 2, tempBoard);
                for (MoveSequence seq : backleftSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1));
                }
            //if its a white king
            } else if(CurrentBoard[x][y] == 11){
                //we take back left
                TakeBackLeft(x, y, tempBoard);
                //we add a 3 resembling backleft to the int array
                sequence = new int[]{3};
                List<MoveSequence> backleftSequences = findSequences(x + 2, y - 2, tempBoard);
                for (MoveSequence seq : backleftSequences) {
                    sequences.add(new MoveSequence(concatenate(sequence, seq.sequence), seq.captureCount + 1));
                }
            }
        }

        //if its empty we return an empty sequence
        if (sequences.isEmpty()) {
            sequences.add(new MoveSequence(new int[0], 0));
        }

        //we return the sequences
        return sequences;
    }

    //tis method concatenates two int arrays
    private int[] concatenate(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    // this class holds information about a sequence
    private static class MoveSequence {
        int[] sequence;
        int captureCount;

        //this method sets the variables to the ones that were passed in
        MoveSequence(int[] sequence, int captureCount) {
            this.sequence = sequence;
            this.captureCount = captureCount;
        }
    }
}