package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executenewaalljar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/github/green4j/newa-all/0.1.10/newa-all-0.1.10.jar";
    String methodSignature = "<io.netty.util.internal.NativeLibraryLoader: void loadLibrary(java.lang.ClassLoader,java.lang.String,boolean)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}