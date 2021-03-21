package jeek.homework.week1;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 2.（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 */
public class HelloLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        //反射构造
        Object obj = new HelloLoader().findClass("Hello").newInstance();
        //反射方法
        Method ms = obj.getClass().getMethod("hello");
        ms.invoke(obj);
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            //读取资源文件
            String filePath = HelloLoader.getSystemResource("week1/hello.xlass").getPath();
            File f = new File(filePath);
            int len = (int) f.length();
            //资源文件转正常byte[]
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] bs = new byte[len];
            for (int i = 0; i < len; i++) {
                byte x = (byte) fileInputStream.read();
                bs[i] = (byte) (255 - x);
            }
            return defineClass(name, bs, 0, len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
