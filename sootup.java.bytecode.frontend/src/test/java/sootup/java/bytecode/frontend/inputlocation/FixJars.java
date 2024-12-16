package sootup.java.bytecode.frontend.inputlocation;

import categories.TestCategories;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sootup.java.core.views.JavaView;

@Tag(TestCategories.JAVA_8_CATEGORY)
public class FixJars extends BaseFixJarsTest {

@Test
public void executeuitextdesktopalphajar(){
	String jarDownloadUrl = "https://repo1.maven.org/maven2/org/jetbrains/compose/ui/ui-text-desktop/1.8.0-alpha01/ui-text-desktop-1.8.0-alpha01.jar";
    String methodSignature = "<androidx.compose.ui.text.platform.ReflectionUtil: java.lang.reflect.Field findFieldInHierarchy(java.lang.Class,java.util.function.Predicate)>";
    JavaView javaView = supplyJavaView(jarDownloadUrl);
    assertMethodConversion(javaView,methodSignature);
    assertJar(javaView);
}

}