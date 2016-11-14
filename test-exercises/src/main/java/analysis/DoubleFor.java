package analysis;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFor;
import spoon.reflect.visitor.filter.AbstractFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoubleFor extends AbstractProcessor<CtFor> {
    final List<CtFor> fors = new ArrayList<CtFor>();

    public void process(final CtFor forloop) {
        AbstractFilter<CtFor> filter = new AbstractFilter<CtFor>() {
            @Override
            public boolean matches(final CtFor element) {
                return forloop!=element;
            }
        };
        if(!forloop.getElements(filter).isEmpty()) {
            fors.add(forloop);
        }
    }

    public List<CtFor> getDoubleFors() {
        return Collections.unmodifiableList(fors);
    }

}
