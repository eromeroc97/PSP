
package com.iesmaestre;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Utilidades {
    public static BufferedReader
        getBufferedReader(InputStream is){
            InputStreamReader isr=
                    new InputStreamReader(is);
            BufferedReader bfr=
                    new BufferedReader(isr);
            return bfr;
    }
        
    public static PrintWriter
            getPrintWriter(OutputStream os){
                OutputStreamWriter osw=
                        osw=new OutputStreamWriter(os);
                PrintWriter pw= new PrintWriter(osw);
                return pw;
                        
    }
}
