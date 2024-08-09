This is an implementation of Checkers with GUI using java.
It has the same rules as checkers(American checkers or English draughts).
If a piece can capture it must do so, even if doing so incurs a disadvantage.
If one player takes more than 5 minutes to make a turn he automatically loses the game.
![image](https://github.com/user-attachments/assets/656531f8-89a3-4d0a-ae77-d13927405ffd)
![image](https://github.com/user-attachments/assets/560626f3-726e-45e6-92c6-15a0dcebf15f)
In yellow are the pieces that can move, in green are the squares it can move and in blue is the selected piece.
![image](https://github.com/user-attachments/assets/307e5b5a-432c-437e-ba47-3e4ea24e64e5)

The logic behind this is pretty easy to understand.
It uses a 2d integer array which is the board where 1 represents black and 2 represents white.
A 0 represents an empty square, an 11 a black king and a 22 a white king.
When a move happens it changes on the 2d array and then the board updates according to it.
