package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executesquerylRCjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/squeryl/squeryl_2.9.1/0.9.6-RC2/squeryl_2.9.1-0.9.6-RC2.jar";
    String methodSignature = "<org.squeryl.Session: java.lang.Object withinTransaction(scala.Function0)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}