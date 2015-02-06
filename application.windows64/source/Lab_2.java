import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Scanner; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Lab_2 extends PApplet {


Table table;
Table NewTable;
BufferedReader reader;
String line;
String[] attributeName;
String[] attributeType;
String[] name;
String[] type;
int x = 1;



int counter = 0;

public void setup() {
  table = loadTable("zoo.csv");
  reader = createReader("zoo.names");
  type = new String[18];
  name = new String[18];
  type[0] = " ";

  println(table.getRowCount() + "total rows in table");
  NewTable = new Table();
  while (name[1] != null){
  for (int a = 0; x<18; a++) {
      NewTable.addColumn(name[x]);
      print(name[x]);
    }
  }
    for (TableRow row : table.rows ()) {
      for (int x = 1; x<18; x++) {
        if (type[x] == "Boolean") {
          if (row.getInt(x) == 1) {
            row.setString(x, "TRUE");
          } else {
            row.setString(x, "FALSE");
          }
        } else {
          continue;
        }
      }
    }
    TableRow newRow = table.addRow();
    for (int x=0; x<18; x++) {
      newRow.setString(x, name[x]);
    }
    saveTable(table, "data/lab2.csv");
}

public void draw() {
  try {
    line = reader.readLine();
    counter++;
    if (counter == 46) {
      String delims = "[.:']";
      attributeName = line.split(delims);
      name[0] = attributeName[1];
    }
    if ((counter >= 47) && (counter <= 63)) {
      if (x<18) {
        String delims = "[.:']";
        attributeName = line.split(delims);
        attributeName = attributeName[1].split("\t");
        name[x] = attributeName[0];
        attributeType = attributeName[2].split(" ");
        type[x] = attributeType[0];
        x++;
      }
    }
  }
  catch (IOException e) {
    e.printStackTrace();
    line = null;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Lab_2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
