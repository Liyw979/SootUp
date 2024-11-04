package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeserverjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/com/lightningkite/ktorbatteries/server/0.2.3/server-0.2.3.jar";
    String methodSignature = "<com.lightningkite.ktorbatteries.typed.AutoformKt: void insideHtmlForm(kotlinx.html.FORM,java.lang.String,java.lang.String,java.lang.Object,boolean)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}