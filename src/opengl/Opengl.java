package opengl;

import opengl.gui.Font;
import opengl.tile.ParticleEngine;
import opengl.tile.Textures;
import main.Main;
import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import level.Level;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.swing.JOptionPane;
import main.Timer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Opengl  implements Runnable{
Thread t; 
private final FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
private HitResult hitResult;
private Level level;
private Player player;
private Timer timer;
private int[][] ch=new int[20][20];// Загруженные чанки

private final FloatBuffer fogColor0 = BufferUtils.createFloatBuffer(4);
private final FloatBuffer fogColor1 = BufferUtils.createFloatBuffer(4);
private int paintTexture = 1;// Выбор блока
private ParticleEngine particleEngine; // Частицы
public volatile boolean pause = false; // Пауза
private int yMouseAxis = 1;
public Textures textures;
private Font font;
private final int editMode = 0; // Режим ( Установка\Удаление блоков)
private volatile boolean running = false; // Выполнение
private String fpsString = ""; // Строка с фпс
public boolean loaded=false;
private boolean mouseGrabbed = false; // Состояние мыши

public Opengl(){
    t = new Thread(this, "OpenGL Thread");
    t.start();
}

  public void init() throws LWJGLException, IOException{
  this.textures = new Textures();      
      
  int col0 = 16710650;
  int col1 = 920330;
  float fr = 0.5F;
  float fg = 0.8F;
  float fb = 1.0F;
  this.fogColor0.put(new float[] { (col0 >> 16 & 0xFF) / 255.0F, (col0 >> 8 & 0xFF) / 255.0F, (col0 & 0xFF) / 255.0F, 1.0F });
  this.fogColor0.flip();
  this.fogColor1.put(new float[] { (col1 >> 16 & 0xFF) / 255.0F, (col1 >> 8 & 0xFF) / 255.0F, (col1 & 0xFF) / 255.0F, 1.0F });
  this.fogColor1.flip();

  Display.setDisplayMode(new DisplayMode(640,480));
  Display.setTitle("Minecraft 0.0.11a");
   try{
    Display.create();
    }
    catch (LWJGLException e){
      try{
      Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException){}
    Display.create();
    }

  Keyboard.create();
  Mouse.create();

  checkGlError("Pre startup");

  GL11.glEnable(3553);
  GL11.glShadeModel(7425);
  GL11.glClearColor(fr, fg, fb, 0.0F);
  GL11.glClearDepth(1.0D);
  GL11.glEnable(2929);
  GL11.glDepthFunc(515);
  GL11.glEnable(3008);
  GL11.glAlphaFunc(516, 0.0F);

  GL11.glMatrixMode(5889);
  GL11.glLoadIdentity();
  GL11.glMatrixMode(5888);
  checkGlError("Startup");

  this.level = new Level();
  //this.levelRenderer = new LevelRenderer(this.level, this.textures);
  this.player = new Player();
  this.particleEngine = new ParticleEngine(this.level, this.textures);
 // this.font = new Font("default.gif", this.textures);

  IntBuffer imgData = BufferUtils.createIntBuffer(256);
  imgData.clear().limit(256);
  setMouse(true);
  checkGlError("Post startup");
  
  loaded=true;
}
  private void checkGlError(String string)
  {
  int errorCode = GL11.glGetError();
  if (errorCode != 0){
    String errorString = GLU.gluErrorString(errorCode);
    System.out.println("########## GL ERROR ##########");
    System.out.println("@ " + string);
    System.out.println(errorCode + ": " + errorString);
    System.exit(0);
    }
  }
 public void setMouse(boolean grab){
  if (this.mouseGrabbed) return;
  this.mouseGrabbed = grab;
    Mouse.setGrabbed(grab);
  }
  
public void render(float a){
    float xo = Mouse.getDX();
    float yo = Mouse.getDY();
    this.player.turn(xo, yo);
    player.pick(this.timer.a); //Camera

    while (Mouse.next()){
      if ((Mouse.getEventButton() == 0) && (Mouse.getEventButtonState())){
       System.out.println("Left");
        if (this.hitResult != null){// Left Click
          player.clickLeft();
        }
      }
      if ((Mouse.getEventButton() == 1) && (Mouse.getEventButtonState())){ //Right click
        System.out.println("Right");
        player.clickright();
      }
     }

    while (Keyboard.next()){
      if ((Keyboard.getEventKey() == 28) && (Keyboard.getEventKeyState())){
          System.out.println("!!!");
        this.level.save();
     }
    }

    GL11.glClear(16640);
    player.setupCamera(timer.a);

    GL11.glEnable(2884);
    GL11.glEnable(2912);
    GL11.glFogi(2917, 2048);
    GL11.glFogf(2914, 0.2F);
    GL11.glFog(2918, this.fogColor);

    GL11.glDisable(2912);
   // this.levelRenderer.render(this.player, 0);
    GL11.glEnable(2912);
   // this.levelRenderer.render(this.player, 1);
    GL11.glDisable(3553);

    if (this.hitResult != null){
   //   this.levelRenderer.renderHit(this.hitResult);
    }
    GL11.glDisable(2912);

    Display.update();
}
public void destroy(){
    try {
    this.level.save();
    }
    catch (Exception localException){}
  Mouse.destroy();
  Keyboard.destroy();
  Display.destroy();
}
@Override 
public void run() {
    t = new Thread(this, "OpenGL Thread");
    System.out.println("Starting OpenGL thread");
      
    this.level =Main.getLV();
    this.player=Main.getPL();   
    this.running = true;
    try{
    init();
    }catch (LWJGLException | IOException e){
    JOptionPane.showMessageDialog(null, e.toString(), "Failed to start Minecraft", 0);
    return;
    }

  long lastTime = System.currentTimeMillis();
  int frames = 0;
    try{
    while (this.running){
      if (this.pause){
        Thread.sleep(100L);
        } else{
        if (Display.isCloseRequested()){
          this.running=false;
        }

//        this.timer.advanceTime();
        for (int i = 0; i < this.timer.ticks; i++){
          tick();
          }
        checkGlError("Pre render");
        render(this.timer.a);
        checkGlError("Post render");
        frames++;

        while (System.currentTimeMillis() >= lastTime + 1000L){
          this.fpsString = (frames + " fps | "+level.upd+" :upd");
          level.upd = 0;
          lastTime += 1000L;
          frames = 0;
          }
        }
      }
    }
    catch (InterruptedException e) {
    e.printStackTrace();
    }
    finally
    {
    destroy();
    }
}

public void tick(){
  while (Mouse.next()){
    if ((!this.mouseGrabbed) && (Mouse.getEventButtonState())){
      setMouse(true);
      } else{
      if ((Mouse.getEventButton() == 0) && (Mouse.getEventButtonState())){// Левый клик
      //handleMouseClick();
      }
      if ((Mouse.getEventButton() == 1) && (Mouse.getEventButtonState())){// Правый клик
      //this.editMode = ((this.editMode + 1) % 2);
      }
     }
    }

  while (Keyboard.next())
    {
    if (Keyboard.getEventKeyState()){
      if (Keyboard.getEventKey() == 1){
        setMouse(false);
      }

      if (Keyboard.getEventKey() == 2) this.paintTexture = 1;
      if (Keyboard.getEventKey() == 3) this.paintTexture = 3;
      if (Keyboard.getEventKey() == 4) this.paintTexture = 4;
      if (Keyboard.getEventKey() == 5) this.paintTexture = 5;
      if (Keyboard.getEventKey() == 7) this.paintTexture = 6;
      if (Keyboard.getEventKey() == 21) this.yMouseAxis *= -1;
      }
    }

  this.level.tick();
  this.particleEngine.tick();

  this.player.tick();
  }
}
