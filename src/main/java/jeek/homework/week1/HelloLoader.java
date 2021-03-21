package jeek.homework.week1;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 2.���������Զ���һ�� Classloader������һ�� Hello.xlass �ļ���ִ�� hello ���������ļ�������һ�� Hello.class �ļ������ֽڣ�x=255-x���������ļ����ļ�Ⱥ���ṩ��
 */
public class HelloLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        //���乹��
        Object obj = new HelloLoader().findClass("Hello").newInstance();
        //���䷽��
        Method ms = obj.getClass().getMethod("hello");
        ms.invoke(obj);
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            //��ȡ��Դ�ļ�
            String filePath = HelloLoader.getSystemResource("week1/hello.xlass").getPath();
            File f = new File(filePath);
            int len = (int) f.length();
            //��Դ�ļ�ת����byte[]
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
