package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unzipper {

 public static void unzip ( String file) {
  Enumeration entries;
  String dirName = main.Main.mdir + "tmp/" + file+"/";
  if ( !dirName.isEmpty() ) {
   new File(dirName).mkdirs();
  }

  try (ZipFile zip = new ZipFile(file+".zip")){
   entries = zip.entries();

   while ( entries.hasMoreElements() ) {
    ZipEntry entry = ( ZipEntry ) entries.nextElement();
    String zip_path = entry.getName();
    write(dirName , zip_path , zip.getInputStream(entry));
   }
  } catch ( IOException e ) {
   main.Main.LOG.addE("utils.Unzipper", e);
  }
 }

 private static void write ( String dirName , String FilePath , InputStream in )
         throws IOException {
  String m_FilePath = FilePath;
  String m_NewFilePath = dirName;
  boolean m_kol = true;
  while ( m_kol ) {
   if ( m_FilePath.indexOf(File.separatorChar) < 0 ) {
    m_kol = false;
   } else {
    Integer resName = m_FilePath.indexOf(File.separatorChar);
    String m_DirName = m_FilePath.substring(0 , resName);
    m_FilePath = m_FilePath.substring(resName + 1);
    new File(m_NewFilePath  + m_DirName).mkdir();
    m_NewFilePath = m_NewFilePath  + m_DirName;
   }
  }
  if ( !new File(dirName + "/" + FilePath).isDirectory() ) {
   try ( OutputStream out = new BufferedOutputStream(new FileOutputStream(
           dirName + "/" + FilePath)) ) {
    byte[] buffer = new byte[1024];
    int len;
    while ( ( len = in.read(buffer) ) >= 0 ) {
     out.write(buffer , 0 , len);
    }
    in.close();
   }
  }
 }
}