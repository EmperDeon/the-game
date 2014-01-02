package main;
public class Timer{
  private float ticksPerSecond;
  private long lastTime;
  public int ticks;
  public float a;
  public float timeScale = 1.0F;
  public float fps = 0.0F;
  public float passedTime = 0.0F;

  public Timer(float ticksPerSecond){
    this.ticksPerSecond = ticksPerSecond;
    this.lastTime = System.nanoTime();
  }

  public void advanceTime(){
    long passedNs = System.nanoTime() - this.lastTime;
    this.lastTime = System.nanoTime();

    if (passedNs < 0L) passedNs = 0L;
    if (passedNs > 1000000000L) passedNs = 1000000000L;

    this.fps = ((float)(1000000000L / passedNs));

    this.passedTime += (float)passedNs * this.timeScale * this.ticksPerSecond / 1.0E+009F;

    this.ticks = ((int)this.passedTime);
    if (this.ticks > 100) this.ticks = 100;
    this.passedTime -= this.ticks;
    this.a = this.passedTime;
  }
}