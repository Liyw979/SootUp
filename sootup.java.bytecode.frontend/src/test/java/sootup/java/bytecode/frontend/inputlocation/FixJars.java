package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executejtranscgenasjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/jtransc/jtransc-gen-as3/0.6.8/jtransc-gen-as3-0.6.8.jar";
    String methodSignature = "<com.jtransc.gen.as3.As3Generator: void writeClasses(com.jtransc.vfs.SyncVfsFile)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}