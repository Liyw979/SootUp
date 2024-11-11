package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executegraylogsharedjar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/graylog2/graylog2-shared/1.3.4/graylog2-shared-1.3.4.jar";
    String methodSignature = "<org.graylog2.shared.system.stats.SigarService: void <init>()>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}