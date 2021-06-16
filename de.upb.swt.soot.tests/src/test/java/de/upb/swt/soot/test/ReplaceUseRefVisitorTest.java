package de.upb.swt.soot.test;

import static org.junit.Assert.assertTrue;

import categories.Java8Test;
import de.upb.swt.soot.core.graph.Block;
import de.upb.swt.soot.core.jimple.basic.Local;
import de.upb.swt.soot.core.jimple.basic.Value;
import de.upb.swt.soot.core.jimple.common.constant.Constant;
import de.upb.swt.soot.core.jimple.common.constant.IntConstant;
import de.upb.swt.soot.core.jimple.common.expr.JPhiExpr;
import de.upb.swt.soot.core.jimple.common.ref.Ref;
import de.upb.swt.soot.core.jimple.visitor.ReplaceUseRefVisitor;
import de.upb.swt.soot.core.signatures.FieldSignature;
import de.upb.swt.soot.java.core.JavaIdentifierFactory;
import de.upb.swt.soot.java.core.language.JavaJimple;
import de.upb.swt.soot.java.core.types.JavaClassType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/** @author Zun Wang */
@Category(Java8Test.class)
public class ReplaceUseRefVisitorTest {
  JavaIdentifierFactory factory = JavaIdentifierFactory.getInstance();
  JavaJimple javaJimple = JavaJimple.getInstance();
  JavaClassType intType = factory.getClassType("int");
  JavaClassType arrayType = factory.getClassType("Array");

  Local base = JavaJimple.newLocal("old_base", arrayType);
  Local newBase = JavaJimple.newLocal("new_base", arrayType);

  Constant conIndex = IntConstant.getInstance(0);
  Constant conNewIndex = IntConstant.getInstance(1);

  Local localIndex = JavaJimple.newLocal("index", intType);
  Local localNewIndex = JavaJimple.newLocal("newIndex", intType);

  FieldSignature fieldSignature = new FieldSignature(arrayType, "field", intType);

  Block block1 = Block.getEmptyBlock();
  Block block2 = Block.getEmptyBlock();
  Local a1 = JavaJimple.newLocal("a1", arrayType);
  Local a2 = JavaJimple.newLocal("a2", arrayType);
  Local arg1 = JavaJimple.newLocal("arg1", intType);
  Local arg2 = JavaJimple.newLocal("arg2", intType);

  /** Test use replacing in case JArrayRef. */
  @Test
  public void testCaseArrayRef() {

    // replace base with newUse
    ReplaceUseRefVisitor visitor = new ReplaceUseRefVisitor(base, newBase);
    Ref ref = javaJimple.newArrayRef(base, conIndex);
    ref.accept(visitor);
    Ref newRef = visitor.getNewRef();

    List<Value> expectedUses = new ArrayList<>();
    expectedUses.add(newBase);
    expectedUses.add(conIndex);

    assertTrue(newRef.getUses().equals(expectedUses));
    expectedUses.clear();

    // replace base with newPhi
    JPhiExpr phi = JPhiExpr.getEmptyPhi();
    phi.addArg(a1, block1);
    phi.addArg(a2, block2);

    visitor = new ReplaceUseRefVisitor(base, phi);
    ref = javaJimple.newArrayRef(base, conIndex);
    ref.accept(visitor);
    newRef = visitor.getNewRef();

    expectedUses.add(a1);
    expectedUses.add(a2);
    expectedUses.add(phi);
    expectedUses.add(conIndex);
    assertTrue(newRef.getUses().equals(expectedUses));
    expectedUses.clear();

    // replace constant index with newUse
    visitor = new ReplaceUseRefVisitor(conIndex, conNewIndex);
    ref = javaJimple.newArrayRef(base, conIndex);
    ref.accept(visitor);
    newRef = visitor.getNewRef();

    expectedUses.add(base);
    expectedUses.add(conNewIndex);
    assertTrue(newRef.getUses().equals(expectedUses));
    expectedUses.clear();

    // replace local index with newUse
    visitor = new ReplaceUseRefVisitor(localIndex, localNewIndex);
    ref = javaJimple.newArrayRef(base, localIndex);
    ref.accept(visitor);
    newRef = visitor.getNewRef();

    expectedUses.add(base);
    expectedUses.add(localNewIndex);
    assertTrue(newRef.getUses().equals(expectedUses));
    expectedUses.clear();

    // replace local index with phi
    phi = JPhiExpr.getEmptyPhi();
    phi.addArg(arg1, block1);
    phi.addArg(arg2, block2);

    visitor = new ReplaceUseRefVisitor(localIndex, phi);
    ref = javaJimple.newArrayRef(base, localIndex);
    ref.accept(visitor);
    newRef = visitor.getNewRef();

    expectedUses.add(base);
    expectedUses.add(arg1);
    expectedUses.add(arg2);
    expectedUses.add(phi);
    assertTrue(newRef.getUses().equals(expectedUses));
    expectedUses.clear();

    // no matched use
    ref = javaJimple.newArrayRef(base, conIndex);
    ref.accept(visitor);
    newRef = visitor.getNewRef();

    assertTrue(newRef.equivTo(ref));
  }

  /** Test use replacing in case JInstanceFieldRef. */
  @Test
  public void testCaseInstanceFieldRef() {

    ReplaceUseRefVisitor visitor = new ReplaceUseRefVisitor(base, newBase);

    // replace base with newUse
    Ref ref = JavaJimple.newInstanceFieldRef(base, fieldSignature);
    ref.accept(visitor);
    Ref newRef = visitor.getNewRef();

    List<Value> expectedUses = new ArrayList<>();
    expectedUses.add(newBase);

    assertTrue(newRef.getUses().equals(expectedUses));

    // no matched use
    ref = JavaJimple.newInstanceFieldRef(localIndex, fieldSignature);
    ref.accept(visitor);
    newRef = visitor.getNewRef();

    assertTrue(newRef.equals(ref));
  }
}
