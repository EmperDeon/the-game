package render.gui;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.*;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.newt.event.awt.AWTMouseAdapter;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import render.Renderer;
import render.gui.entitys.RList;
import utils.Action;
import utils.vec.Vec2;
import utils.vec.Vec3;
import utils.vec.Vec4;

public class Gui implements GLEventListener{
 public static boolean running = true;
 
 public Fps fps;
 
 private final Vec2<Integer> wh;

 private final RList rendlist = new RList();
 private final Renderer rend;
 private final GuiType guit = new GuiType(GuiType.Init);
 private final Vec3<Double> plcoord = new Vec3(0,0,0);
 private final Vec2<Double> cmcoord = new Vec2(0,0);
 private final TextRenderer font;
 public Gui(Renderer rend){
  this.rend = rend;
  this.font = new TextRenderer(new Font("SansSerif", Font.BOLD, 20));
  this.fps = new Fps(font);
  
  wh = new Vec2<>();
  wh.sX(640);//wh.sX(main.Main.OPTIONS.getI("gui_w"));
  wh.sY(360);//wh.sY(main.Main.OPTIONS.getI("gui_h"));
  
  frame();
 }

public final void frame(){
    java.awt.Frame frame = new java.awt.Frame("The Game");
    frame.setSize(wh.gX(),wh.gY());
    frame.setLayout(new java.awt.BorderLayout());

    final Animator animator = new Animator();
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        new Thread(new Runnable() {
        @Override
    public void run() {
        animator.stop();System.exit(0);
    }}).start();}});

    GLCanvas canvas = new GLCanvas();
    animator.add(canvas);

    final Gui gears = this;
    canvas.addGLEventListener(gears);

    frame.add(canvas, java.awt.BorderLayout.CENTER);
    frame.validate();

    Toolkit t = Toolkit.getDefaultToolkit();
    Image i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    Cursor noCursor = t.createCustomCursor(i, new Point(0, 0), "none");
    frame.setCursor(noCursor);
    
    frame.setVisible(true);
    animator.start();
   }

   @Override
   public void dispose(GLAutoDrawable gl) {
    System.err.println("Display: Dispose");
   }
    
    
    @Override
    public void display(GLAutoDrawable draw) { // Render
     if(this.guit.type != GuiType.Init)    {
      GL2 gl = draw.getGL().getGL2();

      gl.glClearColor(0.0f, 0.4f, 1.0f, 0.0f);

      if (GLProfile.isAWTAvailable() && 
       (draw instanceof javax.media.opengl.awt.GLJPanel) &&
       !((javax.media.opengl.awt.GLJPanel) draw).isOpaque() &&
       ((javax.media.opengl.awt.GLJPanel) draw).shouldPreserveColorBufferIfTranslucent()) {
       gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);
      } else {
       gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
      }
     }
    
    this.fps.render(draw); 
    this.rendlist.render(draw , this.guit.type);
   }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
     this.wh.sX(width );
     this.wh.sY(height);
      
//     GL2 gl = drawable.getGL().getGL2();
//     gl.setSwapInterval(1);
//    
//     float h = (float)height / (float)width;
//    
//     gl.glMatrixMode(GL2.GL_PROJECTION);
//
//     gl.glLoadIdentity();
//     gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
//     gl.glMatrixMode(GL2.GL_MODELVIEW);
//     gl.glLoadIdentity();
//     gl.glTranslatef(0.0f, 0.0f, -40.0f);
//     
    }

    @Override
   public void init(GLAutoDrawable draw) {  
    // 0 - Loading
    this.rendlist.addId(GuiType.Init);
    this.rendlist.addInit(GuiType.Init, draw);

    // 1 - Main menu
    this.rendlist.addId(GuiType.Menu);
    this.rendlist.addButton(GuiType.Menu, draw, new Vec4<>(10,10,200,200)," Label ", new Action() {@Override
     public void action () {
    
     }
    });

    // 2 - ?

    MouseListener Mouse = new RMouseAdapter();    
    KeyListener Keyboard = new RKeyAdapter();

    if (draw instanceof Window) {
        Window window = (Window) draw;
        window.addMouseListener(Mouse);
        window.addKeyListener(Keyboard);
    } else if (GLProfile.isAWTAvailable() && draw instanceof java.awt.Component) {
        java.awt.Component comp = (java.awt.Component) draw;
        new AWTMouseAdapter(Mouse).addTo(comp);
        new AWTKeyAdapter(Keyboard).addTo(comp);
    }
  }
public void initfinal(){
 this.guit.type = GuiType.Menu;
}
//---------------------------

  class RKeyAdapter extends KeyAdapter {      
    @Override
    public void keyPressed(KeyEvent e) {
     /*   int kc = e.getKeyCode();
        if(KeyEvent.VK_ESCAPE == kc){
         running=false;   
        } else if(KeyEvent.VK_LEFT == kc) {
         plcoord.x-= 1.5;
        } else if(KeyEvent.VK_RIGHT == kc) {
         plcoord.x += 1.5;
        } else if(KeyEvent.VK_UP == kc) {
         plcoord.y -= 1.5;
        } else if(KeyEvent.VK_DOWN == kc) {
         plcoord.y += 1.5;
        } else if(KeyEvent.VK_W == kc) {
         cmcoord.y -= 1;
        } else if(KeyEvent.VK_S == kc) {
         cmcoord.y += 1;
        } else if(KeyEvent.VK_A == kc) {
         cmcoord.x -= 1;
        } else if(KeyEvent.VK_D == kc) {
         cmcoord.gX() += 1;
        }*/
    }
  }
  
  class RMouseAdapter extends MouseAdapter {
      int prevx;
      int prevy;
      
      @Override
      public void mousePressed(MouseEvent e) {
    ////    prevMouseX = e.getX();
     //   prevMouseY = e.getY();
          
        if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
     //     mouseRButtonDown = true;
        }
      }
        
      @Override
      public void mouseReleased(MouseEvent e) {
        if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
       //   mouseRButtonDown = false;
        }
      }
        
      @Override
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        float thetaY = 360.0f * ( (float)(x-prevx)/(float)wh.gX());
        float thetaX = 360.0f * ( (float)(prevy-y)/(float)wh.gY());
        
        prevx = x;
        prevy = y;
        
  //      cmcoord.x += thetaX;
  //      cmcoord.y += thetaY;
      }
  }
   
    
  //  gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    /* make the gears 
    if(0>=gear1) {
        gear1 = gl.glGenLists(1);
        gl.glNewList(gear1, GL2.GL_COMPILE);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, red, 0);
        gear(gl, 1.0f, 4.0f, 1.0f, 20, 0.7f);
        gl.glEndList();
        System.out.println("gear1 list created: "+gear1);
    } else {
        System.out.println("gear1 list reused: "+gear1);
    }
    */   
}
