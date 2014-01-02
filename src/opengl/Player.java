package opengl;

import java.util.List;
import org.lwjgl.input.Keyboard;
import level.Level;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
public class Player
{
    
      public int ch;
      public int hand;
  private int width;
  private int height;
  private HitResult hitResult;
  public float xo;
  public float yo;
  public float zo;
  public float x;
  public float y;
  public float z;

  public float x1;
  public float y1;
  public float z1;
  public float xd;
  public float yd;
  public float zd;
  public float yRot;
  public float xRot;
  public Phys bb;
  public boolean onGround = false;
  private Level level;
  private IntBuffer viewportBuffer = BufferUtils.createIntBuffer(16);
  private IntBuffer selectBuffer = BufferUtils.createIntBuffer(2000);
  
  public void clickright(){
            this.level.replace(this.hitResult.x, this.hitResult.y, this.hitResult.z,ch, 0);
  }
  
  public void clickLeft(){
        if (this.hitResult != null)
        {
          int cx = this.hitResult.x;
          int cy = this.hitResult.y;
          int cz = this.hitResult.z;

          if (this.hitResult.f == 0) cy--;
          if (this.hitResult.f == 1) cy++;
          if (this.hitResult.f == 2) cz--;
          if (this.hitResult.f == 3) cz++;
          if (this.hitResult.f == 4) cx--;
          if (this.hitResult.f == 5) cx++;

          this.level.replace(cx,cy,cz,ch,hand);
        }
  }
  
  public void player(Level level){

  this.level=level;
  //SetPos
    this.ch=level.spawn[0];
    this.x = level.spawn[1];
    this.y = level.spawn[2];
    this.z = level.spawn[3];
    float w = 0.3F;
    float h = 0.9F;
    this.bb = new Phys(x - w, y - h, z - w, x + w, y + h, z + w);
  }
  
  private void moveCameraToPlayer(float a)
  {
    GL11.glTranslatef(0.0F, 0.0F, -0.3F);
    GL11.glRotatef(xRot, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(yRot, 0.0F, 1.0F, 0.0F);

    float x1 = xo + (x - xo) * a;
    float y1 = yo + (y - yo) * a;
    float z1 = zo + (z - zo) * a;
    GL11.glTranslatef(-x1, -y1, -z1);
  }

public void setupCamera(float a)
  {
    GL11.glMatrixMode(5889);
    GL11.glLoadIdentity();
    GLU.gluPerspective(70.0F, this.width / this.height, 0.05F, 1000.0F);
    GL11.glMatrixMode(5888);
    GL11.glLoadIdentity();
    moveCameraToPlayer(a);
  }

  private void setupPickCamera(float a, int x, int y)
  {
    GL11.glMatrixMode(5889);
    GL11.glLoadIdentity();
    this.viewportBuffer.clear();
    GL11.glGetInteger(2978, this.viewportBuffer);
    this.viewportBuffer.flip();
    this.viewportBuffer.limit(16);
    GLU.gluPickMatrix(x, y, 5.0F, 5.0F, this.viewportBuffer);
    GLU.gluPerspective(70.0F, this.width / this.height, 0.05F, 1000.0F);
    GL11.glMatrixMode(5888);
    GL11.glLoadIdentity();
    moveCameraToPlayer(a);
  }

 public void pick(float a)
  {
    this.selectBuffer.clear();
    GL11.glSelectBuffer(this.selectBuffer);
    GL11.glRenderMode(7170);
    setupPickCamera(a, this.width / 2, this.height / 2);
// !!!!!!!!!!!!    
  //  this.levelRenderer.pick(this.player);
    
    int hits = GL11.glRenderMode(7168);
    this.selectBuffer.flip();
    this.selectBuffer.limit(this.selectBuffer.capacity());

    long closest = 0L;
    int[] names = new int[10];
    int hitNameCount = 0;
    for (int i = 0; i < hits; i++)
    {
      int nameCount = this.selectBuffer.get();
      long minZ = this.selectBuffer.get();
      this.selectBuffer.get();

      long dist = minZ;

      if ((dist < closest) || (i == 0))
      {
        closest = dist;
        hitNameCount = nameCount;
        for (int j = 0; j < nameCount; j++)
          names[j] = this.selectBuffer.get();
      }
      else
      {
        for (int j = 0; j < nameCount; j++) {
          this.selectBuffer.get();
        }
      }
    }
    if (hitNameCount > 0)
    {
      this.hitResult = new HitResult(names[0], names[1], names[2], names[3], names[4]);
    }
    else
    {
      this.hitResult = null;
    }
  }

  private void setPos(float x, float y, float z){

  }

  public void turn(float xo, float yo){
    this.yRot = ((float)(this.yRot + xo * 0.15D));
    this.xRot = ((float)(this.xRot - yo * 0.15D));
    if (this.xRot < -90.0F) this.xRot = -90.0F;
    if (this.xRot > 90.0F) this.xRot = 90.0F;
  }

  public void tick(){
    this.xo = this.x;
    this.yo = this.y;
    this.zo = this.z;
    float xa = 0.0F;
    float ya = 0.0F;

    if ((Keyboard.isKeyDown(200)) || (Keyboard.isKeyDown(17))) ya -= 1.0F;
    if ((Keyboard.isKeyDown(208)) || (Keyboard.isKeyDown(31))) ya += 1.0F;
    if ((Keyboard.isKeyDown(203)) || (Keyboard.isKeyDown(30))) xa -= 1.0F;
    if ((Keyboard.isKeyDown(205)) || (Keyboard.isKeyDown(32))) xa += 1.0F;
    if ((Keyboard.isKeyDown(57)) || (Keyboard.isKeyDown(219))){
      if (this.onGround){// Space
        this.yd = 0.12F;
      }
   }

    moveRelative(xa, ya, this.onGround ? 0.02F : 0.005F);

    this.yd = ((float)(this.yd - 0.005D));
    move(this.xd, this.yd, this.zd);
    this.xd *= 0.91F;
    this.yd *= 0.98F;
    this.zd *= 0.91F;

    if (this.onGround)
    {
      this.xd *= 0.8F;
      this.zd *= 0.8F;
    }
  }

  public void move(float xa, float ya, float za){
    float xaOrg = xa;
    float yaOrg = ya;
    float zaOrg = za;

    List aABBs = this.level.getCubes(this.bb.expand(xa, ya, za));

    for (int i = 0; i < aABBs.size(); i++)
      ya = ((Phys)aABBs.get(i)).clipYCollide(this.bb, ya);
    this.bb.move(0.0F, ya, 0.0F);

    for (int i = 0; i < aABBs.size(); i++)
      xa = ((Phys)aABBs.get(i)).clipXCollide(this.bb, xa);
    this.bb.move(xa, 0.0F, 0.0F);

    for (int i = 0; i < aABBs.size(); i++)
      za = ((Phys)aABBs.get(i)).clipZCollide(this.bb, za);
    this.bb.move(0.0F, 0.0F, za);

    this.onGround = ((yaOrg != ya) && (yaOrg < 0.0F));

    if (xaOrg != xa) this.xd = 0.0F;
    if (yaOrg != ya) this.yd = 0.0F;
    if (zaOrg != za) this.zd = 0.0F;

    this.x = ((this.bb.x0 + this.bb.x1) / 2.0F);
    this.y = (this.bb.y0 + 1.62F);
    this.z = ((this.bb.z0 + this.bb.z1) / 2.0F);
  }

  public void moveRelative(float xa, float za, float speed){
    float dist = xa * xa + za * za;
    if (dist < 0.01F) return;

    dist = speed / (float)Math.sqrt(dist);
    xa *= dist;
    za *= dist;

    float sin = (float)Math.sin(this.yRot * 3.141592653589793D / 180.0D);
    float cos = (float)Math.cos(this.yRot * 3.141592653589793D / 180.0D);

    this.xd += xa * cos - za * sin;
    this.zd += za * cos + xa * sin;
  }
}