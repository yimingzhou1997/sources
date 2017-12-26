package one;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.bouncycastle.util.encoders.Base64Encoder;

public class ccc {
	public static void main(String[] args) throws IOException {
		/*File file = new File(
				"C:\\Users\\Administrator\\Pictures\\Saved Pictures\\20171210083321.png");
		InputStream is = new FileInputStream(file);
		byte[] array = new byte[(int) file.length()];
		is.read(array);
		System.out.println(array.length);
		OutputStream os=new FileOutputStream("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\20171210083321-ac345982b2b7d0a2d4b4a0d6ccef76094b.png");
		os.write(array, 0, array.length);*/
		String path = "C:\\Users\\Administrator\\Documents\\Tencent Files\\441902486\\Image\\Group\\9C]5]F52QLMW3A`@6CD0S2S.jpg";
		File file = new File(path);
		byte[] array = /*new byte[(int) file.length()]*/path.getBytes();
		System.out.println("c"+array.length);
		Encoder encoder = Base64.getEncoder();
		String code = encoder.encodeToString(array);
		System.out.println(code);
		System.out.println(new String(Base64.getDecoder().decode(code.getBytes())));
	}
}
