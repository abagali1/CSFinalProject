import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The TetrisPanel facilitates the actual Tetris game
 * TetrisPanel shows the blocks falling down and provides functionality for moving the blocks left to right
 * TetrisPanel uses the <code>Block</code> class to show The Tetris blocks
 * @author Anup Bagali
 * @author Kevin Liu
 * @author Teja Kocherla
 * @author Amit Rajesh
 * @see JPanel
 */
public class TetrisPanel extends JPanel{
   /**
    * BufferedImage to draw onto
    */
   private BufferedImage myImage;
   /**
    * <code>Graphics2D</code> object drawing onto <code>myImage</code>
    */
   private Graphics2D myBuffer;
   /**
    * Timer objects to control <code>ActionListeners</code>
    */
   private Timer t;
   /**
    * <code>ArrayLists</code> of blocks. blocks stores random blocks, kblocks stores constant blocks
    */
   private ArrayList<Block> blocks;
   /**
    * Stores the full and empty spots in the game
    */
   private boolean[][] gameboard;
   /**
    * Stores the current index of blocks
    */
   private int blockCount = 0;
   /**
    * Current score of the tetris game
    */
   private int curr;

   private Color[][] colorBoard;

   private Timer f;
   /**
    * Creates a new TetrisPanel
    * Initializes a new BufferedImage, and Graphics2D object
    * Initializes all the <code>ArrayLists</code> and <code>Arrays</code> with random blocks and specific values
    * respectively
    */
   public TetrisPanel() {
      setLayout(new BorderLayout());

      this.myImage = new BufferedImage(201 , 402, BufferedImage.TYPE_INT_RGB);
      this.myBuffer = (Graphics2D) myImage.getGraphics();
      blocks = new ArrayList<>();


      this.t = new Timer(100, new Listener());
      this.f = new Timer(5, new Finisher());

      boolean[] trueArray = new boolean[41];
      for(boolean b: trueArray)
         b = true;


      colorBoard = new Color[21][41];
      for(int i=0;i<=colorBoard.length-1;i++)
         for(int r=0;r<=colorBoard[0].length-1;r++)
            colorBoard[i][r] = Color.BLACK;


      gameboard = new boolean[22][42];
      for(int r=0;r<=gameboard.length-1;r++){
         for(int c=0;c<=gameboard[0].length-1;c++){
            gameboard[r][c] = true;
         }
      }

      /*
     Stores all the possible y positions for a new block
    */
      int[] yPos = new int[19];
      for(int r=0;r<=200-30;r+=10)
         yPos[r/10] = r;


      for(int i=0;i<=Integer.MAX_VALUE/1000;i++)
         blocks.add(new Block(yPos[((int) (Math.random() * 19))], 0, Optional.of(yPos[((int) (Math.random() * 19))]),
                 Optional.of(((int) (Math.random() * 401))), ((int) (Math.random() * 7))));

      this.t.start();
      addKeyListener(new Key());
      requestFocus();
      setFocusable(true);

   }

   /**
    * Returns the current score
    * @return the current score
    */
   int getCScore() {
      return curr;
   }


   private class Key extends KeyAdapter {
      /**
       * Invoked when a key has been pressed.
       * See the class description for {@link KeyEvent} for a definition of
       * a key pressed event.
       *
       * @param ek the event to be processed
       */
      @Override
      public void keyPressed(KeyEvent ek) {
         if(ek.getKeyCode() == KeyEvent.VK_LEFT ){
            blocks.get(blockCount).setLAcc(true);
         }
         if(ek.getKeyCode() == KeyEvent.VK_RIGHT){
            blocks.get(blockCount).setRAcc(true);
         }
         if(ek.getKeyCode() == KeyEvent.VK_UP){
            blocks.get(blockCount).flip(myBuffer);
         }
         if(ek.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
         }
      }

      /**
       * Invoked when a key has been released.
       * See the class description for {@link KeyEvent} for a definition of
       * a key released event.
       *
       * @param ekk the event to be processed
       */
      @Override
      public void keyReleased(KeyEvent ekk) {
         if(ekk.getKeyCode() == KeyEvent.VK_LEFT ){
            blocks.get(blockCount).setLAcc(false);
         }
         if(ekk.getKeyCode() == KeyEvent.VK_RIGHT){
            blocks.get(blockCount).setRAcc(false);
         }
      }
   }

   /**
    * Calls the UI delegate's paint method, if the UI delegate
    * is non-<code>null</code>.  We pass the delegate a copy of the
    * <code>Graphics</code> object to protect the rest of the
    * paint code from irrevocable changes
    * (for example, <code>Graphics.translate</code>).
    * <p>
    * If you override this in a subclass you should not make permanent
    * changes to the passed in <code>Graphics</code>. For example, you
    * should not alter the clip <code>Rectangle</code> or modify the
    * transform. If you need to do these operations you may find it
    * easier to create a new <code>Graphics</code> from the passed in
    * <code>Graphics</code> and manipulate it. Further, if you do not
    * invoke super's implementation you must honor the opaque property, that is
    * if this component is opaque, you must completely fill in the background
    * in an opaque color. If you do not honor the opaque property you
    * will likely see visual artifacts.
    * <p>
    * The passed in <code>Graphics</code> object might
    * have a transform other than the identify transform
    * installed on it.  In this case, you might get
    * unexpected results if you cumulatively apply
    * another transform.
    *
    * @param g the <code>Graphics</code> object to protect
    * @see #paint
    * @see ComponentUI
    */
   @Override
   public void paintComponent(Graphics g) {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   private class Finisher implements ActionListener{

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
         myBuffer.setColor(Color.WHITE);
         myBuffer.fillRect(0,0,getWidth(),getHeight());
         myBuffer.setColor(Color.red);
         myBuffer.setFont(new Font("Arial", Font.BOLD,30));
         myBuffer.drawString("GAME OVER", 10,100);
         myBuffer.setFont(new Font("Monospaced",Font.ITALIC,20));
         myBuffer.drawString("Score: " + curr, 50,120);

         myBuffer.fill(new Polygon(new int[]{0,33,33}, new int[]{245,212,278},3));

         repaint();
         revalidate();
      }
   }

   /**
    * Neseted <code>ActionListener</code> class to actually run the Tetris game
    */
   private class Listener implements ActionListener {

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public synchronized void actionPerformed(ActionEvent e) {

         myBuffer.setColor(Color.black);
         myBuffer.fillRect(0, 0, getWidth(), getHeight());
         myBuffer.setColor(Color.WHITE);

         for (int i = 0; i <= 400; i += 10) {
            myBuffer.drawLine(i, 0, i, 400);
            myBuffer.drawLine(0, i, 401, i);
         }

         myBuffer.drawLine(400, 0, 400, 400);
         myBuffer.drawLine(0, 400, 400, 400);


         blocks.get(blockCount).draw(myBuffer);
         blocks.get(blockCount).move(10,gameboard);


         Block.setFall(true);
         Block.rain(blocks, myBuffer, gameboard);


         if(Block.constantBlocks.contains(blocks.get(blockCount))) {
            updateGameboard(blocks.get(blockCount));
            finished(blocks.get(blockCount));
            blockCount++;
         }

         for(int i=39;i>=0;i--) {
            if (checkRow(i)) {
               System.out.println("ROw");
               clear(i);
            }
         }

         for(int i=0;i<=19;i++) {
            if (!gameboard[i][0])
               gameFinished(true);
         }


         repaint();
         revalidate();
      }
   }

   /**
    * Clears a filled row
    * @param i row to be cleared
    */
   private void clear(int i) {
      if(i == 0){
         gameFinished(true);
      }else{

         removeBlocks(i);
         shiftBoard(gameboard,i);
         shiftColors(colorBoard,i);
         redraw(colorBoard);
         curr += 10;

         repaint();
         revalidate();
      }
   }

   /**
    * Removes any blocks that are in a filled row
    * @param i row to be referenced
    */
   @SuppressWarnings("unchecked")
   private synchronized void removeBlocks ( int i){
      for (Block b : (ArrayList<Block>) Block.constantBlocks.clone()) {
         for(Point p: b.convertToPoints()){
            if(!gameboard[(p.x)/10][i])
               Block.constantBlocks.remove(b);
         }
      }
   }

   /**
    * Redraws the entire board adjusting to any changes
    * @param colors respective colors for each cell
    */
   private void redraw(Color[][] colors) {
      for(int r=0;r<=colors.length-1;r++){
         for(int c=0;c<=colors[0].length-1;c++){
            myBuffer.setColor(colors[r][c]);
            myBuffer.fillRect(r*10,c*10,10,10);
            repaint();
            revalidate();
         }
      }
   }

   /**
    * Shifts the gameboard down one row respective to the starting row
    * @param board gameboard
    * @param i starting row
    */
   private void shiftBoard(boolean[][] board, int i) {
      for(int r=0;r<=19;r++){
         for(int c=1;c<=i;c++){
            board[r][40-c] = board[r][i-c];
            colorBoard[r][40-c] = colorBoard[r][i-c];
         }
      }
   }

   /**
    * Shifts the colorboard down one row respective to the starting row
    * @param colors colorboard
    * @param i starting row
    */
   private void shiftColors(Color[][] colors, int i) {
      for(int r=0;r<=19;r++){
         for(int c=1;c<=i;c++){
            colors[r][40-c] = colors[r][i-c];
         }
      }
   }

   /**
    * Executes once the game has ended
    * @param b whether the game is finished or not
    */
   private void gameFinished(boolean b) {
      if(b){
         t.stop();
         f.start();
      }
   }


   /**
    * Updates the gameboard to adjust to any final blocks
    * @param block block to be inserted into the gameboard
    */
   private void updateGameboard(Block block) {
      Point[] tpoints = block.convertToPoints();
      try {
         for (Point p : tpoints) {
            gameboard[(p.x) / 10][(p.y) / 10] = false;
            colorBoard[(p.x)/10][(p.y)/10] = block.getColor();
         }
      }catch (ArrayIndexOutOfBoundsException e){
         block.move(10,"right");
      }

   }


   /**
    * Sets a block into the finished state
    * @param b Block to be set
    */
   public void finished(Block b){
      ArrayList<Rectangle2D.Double> temp = b.convert();
      for(Rectangle2D.Double d: temp){
         myBuffer.fill(d);
      }
   }


   /**
    * Gets the next 5 blocks after the <code>blockCount</code> index in the <code>blocks</code> ArrayList
    * @return Array of the next 5 blocks
    */
   Block[] getNext5Blocks(){
      Block[] temp = new Block[5];
      for(int i =0;i<=temp.length-1;i++){
         temp[i] = blocks.get(blockCount + i + 1);
      }
      return temp;
   }

   /**
    * Determines whether a row is filled or not
    * @param i row to be checked
    * @return whether the row is filled or not
    */
   private boolean checkRow(int i){
      for(int x=0;x<=19;x++) {
         if (gameboard[x][i])
            return false;
      }
      return true;
   }


}