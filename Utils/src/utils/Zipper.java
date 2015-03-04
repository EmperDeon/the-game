package utils;

import java.io.*;
import java.util.zip.*;

public class Zipper {

 public static void zip ( String dirName, String zipName ) {
  try {
   System.out.println("Zipping " + dirName + " to " + zipName);
   try ( ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName)) ) {
    zos.setLevel(Deflater.BEST_COMPRESSION);
    zipDir(dirName, zos, dirName);
   }
  } catch ( Exception e ) {
   main.Main.LOG.addE(e);
  }
 }

 public static void zipmod ( String zipFile ) {
  try {
   try ( ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile)) ) {
    zos.setLevel(Deflater.BEST_COMPRESSION);
    zipDir("tmp" + zipFile.substring(zipFile.lastIndexOf("/"), zipFile.
                                     lastIndexOf(".mod")), zos,
           "tmp" + zipFile.substring(zipFile.lastIndexOf("/"), zipFile.
                                     lastIndexOf(".mod")));
   }
  } catch ( Exception e ) {
   main.Main.LOG.addE(e);
  }
 }

 private static void zipDir ( String dir2zip, ZipOutputStream zos,
                              String True_path ) {
  try {
   File zipDirs = new File(dir2zip);
   String[] dirList = zipDirs.list();
   byte[] readBuffer = new byte[2156];
   int bytesIn;
   for ( String dirList1 : dirList ) {
    File f = new File(zipDirs, dirList1);
    if ( f.isDirectory() ) {
     String filePath = f.getPath();
     zipDir(filePath, zos, True_path);
     continue;
    }
    try ( FileInputStream fis = new FileInputStream(f) ) {
     ZipEntry anEntry = new ZipEntry(f.getPath().substring(True_path.length()));
     zos.putNextEntry(anEntry);
     while ( (bytesIn = fis.read(readBuffer)) != -1 ) {
      zos.write(readBuffer, 0, bytesIn);
     }
    }
   }
  } catch ( Exception e ) {
   main.Main.LOG.addE(e);
  }
 }
}
