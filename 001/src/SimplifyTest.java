import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SimplifyTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testSimplify() {
		// fail("Not yet implemented");
		Multinomial testMul = new Multinomial();
		testMul.expression("2*x*y+35*z");
		testMul.mulcopy();
		testMul.simplify('x', 2);
		testMul.simplify('y', 3);
		// testMul.Print(testMul.forother);
		assertEquals("12+35z", testMul.Print(testMul.forother));
	}
}
