import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class refractionSim extends PApplet {




Boolean critApply = false;
float inc = 0;
float ri1 = 1;
float ri2 = 1;
float ref = 0;

public void setup(){
  
  textAlign(CENTER,CENTER);
  textSize(10);
}

public void draw(){
  fill(32*ri1,32*ri1,65*ri1);
  rect(0,0,width/2,height);
  fill(32*ri2,32*ri2,65*ri2);
  rect(width/2,0,width/2,height);
  //Important Bits
  stroke(255);
  strokeWeight(3);
  line(width/2-angleToPointX(inc),height/2-angleToPointY(inc)-75,width/2,height/2-75);
  line(width/2+angleToPointX(ref),height/2+angleToPointY(ref)-75,width/2,height/2-75);
  if(critApply){
    if(ref<90){
      stroke(125+(ref*125/90));
    } else {
      stroke(255);
    }
    line(width/2-angleToPointX(inc),height/2+angleToPointY(inc)-75,width/2,height/2-75);
  }
  stroke(200);
  strokeWeight(1);
  line(0,height/2-75,width,height/2-75);
  ref = 1;
  stroke(0);
  strokeWeight(2);
  //Other stuff
  fill(65,65,125);
  rect(0,height-150,width,150);
  fill(255);
  text("Incident angle",width/4,height-100);
  text(inc+"\u00b0",width/4,height-40);
  Button(3*width/16,height-60,width/16,10,false,1,inc,"-",0);
  Button(width/4,height-60,width/16,10,true,1,inc,"+",90);
  fill(255);
  text("Refractive index of Left material",13*width/28,height-100);
  text(ri1,13*width/28,height-40);
  Button(13*width/28-width/16,height-60,width/16,10,false,2,ri1,"-",1);
  Button(13*width/28,height-60,width/16,10,true,2,ri1,"+",10);
  fill(255);
  text("Refractive index of Right material",3*width/4,height-100);
  text(ri2,3*width/4,height-40);
  Button(11*width/16,height-60,width/16,10,false,3,ri2,"-",1);
  Button(3*width/4,height-60,width/16,10,true,3,ri2,"+",10);
  if(ri1>ri2){
    critApply = true;
  } else {
    critApply = false;
  }
  
  ref = degrees(asin(sin(radians(inc))*ri1/ri2));
}

public float angleToPointX(float angle){
  float x = width*cos(radians(angle));
  return x;
}

public float angleToPointY(float angle){
  float y = height*sin(radians(angle));
  return y;
}

public void Button(int x, int y, int w, int h, Boolean up, int var, float v, String t, float l){
  fill(40,40,80);
  if(mouseX>x&&mouseX<x+w&&mouseY>y&&mouseY<y+h){
    if(mousePressed){
      fill(64,64,128);
      if(up&&v<l){
        if(var == 1){
          inc+= 0.05f;
        } else if(var == 2){
          ri1+= 0.01f;
        } else if(var == 3){
          ri2+= 0.01f;
        }
      } else if (!up&&v>l) {
        if(var == 1){
          inc-= 0.05f;
        } else if(var == 2){
          ri1-= 0.01f;
        } else if(var == 3){
          ri2-= 0.01f;
        }
      }
    }
    fill(56,56,112);
  }
  rect(x,y,w,h);
  fill(255);
  text(t,x+w/2,y+h/2);
}
  public void settings() {  size(640,480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "refractionSim" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
