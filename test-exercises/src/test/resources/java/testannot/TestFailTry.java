package foo;

import org.junit.Test;
import static org.junit.Assert.fail;

public class TestFailTry {
	@Test
	public void testNoAnnot() {
		try {
			System.out.println("coucou");
		}catch(Exception ex) {
			fail();
		}
	}
}