package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executequarkusdevelopmentmodeFinaljar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/io/quarkus/quarkus-development-mode/1.3.4.Final/quarkus-development-mode-1.3.4.Final.jar";
    String methodSignature = "<io.quarkus.dev.IsolatedDevModeMain: void stop()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}