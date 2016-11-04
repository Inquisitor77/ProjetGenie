package analysis;

import org.junit.Before;
import org.junit.Test;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.StandardEnvironment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestAnalyzer {

    SpoonAPI spoon;
    DefaultJavaPrettyPrinter printer;

    @Before
    public void setUp() {
        StandardEnvironment env = new StandardEnvironment();
        env.setAutoImports(true);
        env.setComplianceLevel(8);
        env.useTabulations(true);
        spoon = new Launcher();
        printer = new DefaultJavaPrettyPrinter(env);
    }

    private String getFileCode(final String path) throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader buf = new BufferedReader(reader);
        String txt = buf.lines().collect(Collectors.joining("\n"));

        buf.close();
        reader.close();

        return txt;
    }

    @Test
    public void testDoubleFor() {
        DoubleFor proc = new DoubleFor();
        spoon.addProcessor(proc);
        spoon.addInputResource("src/test/resources/java/testannot/TestDoubleFor.java");
        spoon.run();
        assertEquals(1, proc.getDoubleFors().size());
    }

	@Test
	public void testNoAnnot() {
		NoTestAnnot proc = new NoTestAnnot();
		spoon.addProcessor(proc);
		spoon.addInputResource("src/test/resources/java/testannot/TestNoTestAnnot.java");
		spoon.run();
		assertEquals(1, proc.getFaultyMethods().size());
	}

//	@Test
//	public void testNoAnnotRefactor() throws IOException {
//		NoTestAnnot proc = new NoTestAnnot();
//		spoon.addProcessor(proc);
//		spoon.addInputResource("src/test/resources/java/testannot/TestNoTestAnnot.java");
//		spoon.run();
//		proc.correctTestMethods();
//		printer.calculate(null, new ArrayList<>(spoon.getModel().getAllTypes()));

//		assertEquals(getFileCode("src/test/resources/java/testannot/ExpectedTestNoTestAnnot.java"), printer.getResult());
//	}

//	@Test
//	public void testTryFail() {
//		TryFailCatch proc = new TryFailCatch();
//		spoon.addProcessor(proc);
//		spoon.addInputResource("src/test/resources/java/testannot/TestFailTry.java");
//		spoon.run();
//		assertEquals(1, proc.getFaultyCatches().size());
//	}

//	@Test
//	public void testTryFailRefactor() throws IOException {
//		TryFailCatch proc = new TryFailCatch();
//		spoon.addProcessor(proc);
//		spoon.addInputResource("src/test/resources/java/testannot/TestFailTry.java");
//		spoon.run();
//		proc.fixFaultyTest();
//		printer.calculate(null, new ArrayList<>(spoon.getModel().getAllTypes()));
//		assertEquals(getFileCode("src/test/resources/java/testannot/TestFailTryExpected.java"), printer.getResult());
//	}
}
