import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DerivativeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
		Multinomial testMul = new Multinomial();
		testMul.expression("x+25xy+33");
		testMul.mulcopy();
		testMul.derivative('x');
		// testMul.Print(testMul.forother);
		assertEquals("1+25y", testMul.Print(testMul.forother));
	}
}
