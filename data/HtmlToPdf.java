package com.jusfoun.jxsd.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class HtmlToPdf {

   // private static final String TOPDFTOOL = "E:\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";
    /**
     * html转pdf
     * @param srcPath html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath pdf保存路径
     * @return 转换成功返回true
     */
    public static boolean convert(String srcPath, String destPath) {

        File file = new File(destPath);
        File parent = file.getParentFile();
        // 如果pdf保存路径不存在，则创建路径
        if (!parent.exists()) {
            parent.mkdirs();
        }

        //c:\wkhtmltopdf.exe http://www.csdn.net c:\csdn.pdf"
        String basepath="";
       try {
    	  
    	   basepath= ResourceUtils.getURL("classpath:").getPath();
    	   
    	   
       } catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
       }
        
        StringBuilder cmd = new StringBuilder();
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            cmd.append(basepath+"/wkhtmltopdf/bin/wkhtmltopdf.exe");

        }else {
			//linux 调用
            cmd.append("/opt/wkhtmltopdf/bin/wkhtmltopdf");

        }
        cmd.append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);

        System.out.println(basepath+"/wkhtmltopdf/bin/wkhtmltopdf.exe");
        boolean result = true;
        try {
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(
                    proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
            System.out.println("HTML2PDF成功，参数---html路径：{},pdf保存路径 ：{}"+new Object[] {srcPath, destPath });
        } catch (Exception e) {
            System.out.println(("HTML2PDF失败，srcPath地址：{},错误信息：{}"));
            result = false;
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        HtmlToPdf.convert("http://news.baidu.com/", "E://testpdf211.pdf" );
    }
}
