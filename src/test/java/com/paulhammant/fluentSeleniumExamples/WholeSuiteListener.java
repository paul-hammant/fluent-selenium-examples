package com.paulhammant.fluentSeleniumExamples;

import com.codahale.metrics.ConsoleReporter;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.seleniumhq.selenium.fluent.monitors.CodaHaleMetricsMonitor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class WholeSuiteListener extends RunListener {

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(codahaleMetricsMonitor.getMetrics())
                .convertRatesTo(TimeUnit.MILLISECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .outputTo(new PrintStream(baos))
                .build();

        reporter.report();

        // ... for assertion

        System.out.println(baos.toString());

    }

    public static final CodaHaleMetricsMonitor codahaleMetricsMonitor = new CodaHaleMetricsMonitor("com.paulhammant.fluentSeleniumExamples.");
}

