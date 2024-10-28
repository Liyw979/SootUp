package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executelombokextjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/chobit/apt/lombok-ext/0.0.4/lombok-ext-0.0.4.jar";
    String methodSignature = "<com.sun.tools.javac.file.ZipFileIndex$DirectoryEntry: void initEntries()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}