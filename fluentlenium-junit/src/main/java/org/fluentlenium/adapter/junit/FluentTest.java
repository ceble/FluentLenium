package org.fluentlenium.adapter.junit;

import org.fluentlenium.adapter.FluentTestRunnerAdapter;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * JUnit FluentLenium Test Adapter.
 *
 * Extends this class to provide FluentLenium support to your JUnit Test class.
 */
public abstract class FluentTest extends FluentTestRunnerAdapter {
    public FluentTest() {
    }

    @Rule
    public TestRule watchman = new FluentTestRule() {

        @Override
        public void starting(Description description) {
            super.starting(description);
            FluentTest.this.starting(description.getTestClass(), description.getDisplayName());
        }

        @Override
        public void finished(Description description) {
            super.finished(description);
            FluentTest.this.finished(description.getTestClass(), description.getDisplayName());
        }

        @Override
        public void failed(Throwable e, Description description) {
            super.failed(e, description);
            FluentTest.this.failed(e, description.getTestClass(), description.getDisplayName());
        }
    };

    @ClassRule
    public static TestRule classWatchman = new TestRule() {

        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {

                @Override
                public void evaluate() throws Throwable {
                    try {
                        base.evaluate();
                    } finally {
                        afterClass(description.getTestClass());
                    }
                }
            };
        }
    };

}
