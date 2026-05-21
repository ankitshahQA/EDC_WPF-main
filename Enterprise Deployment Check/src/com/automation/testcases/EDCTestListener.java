package com.automation.testcases;

import java.util.HashSet;
import java.util.Set;
import javax.swing.SwingUtilities;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * EDCTestListener — Updates ResultsLogPanel counters ONCE per test case.
 *
 * ═══════════════════════════════════════════════════════════════════
 * COUNTING HAPPENS IN ONLY ONE PLACE:
 *   → The static method updateResultCount(ITestResult)
 *   → Called ONLY from TestSuite's @AfterMethod
 *
 * ALL ITestListener and IInvokedMethodListener callbacks are NO-OP.
 * They do NOT touch any counter. This eliminates duplicate counting.
 * ═══════════════════════════════════════════════════════════════════
 *
 * A Set&lt;String&gt; (processedTests) ensures that even if the method
 * is accidentally called twice for the same test, it counts only once.
 */
public class EDCTestListener implements ITestListener, IInvokedMethodListener {

    /**
     * Tracks test names that have already been counted.
     * Prevents any duplicate increment — even if updateResultCount()
     * is called multiple times for the same test case.
     */
    private static final Set<String> processedTests = new HashSet<>();

    // ── IInvokedMethodListener — intentionally empty ──────────────────────

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) { }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // NO counting here — all counting is via updateResultCount() only
    }

    // ── ITestListener — intentionally empty (no counting) ─────────────────

    @Override public void onTestStart(ITestResult result)   { }
    @Override public void onTestSuccess(ITestResult result)  { }
    @Override public void onTestFailure(ITestResult result)  { }
    @Override public void onTestSkipped(ITestResult result)  { }
    @Override public void onStart(ITestContext context)      { }
    @Override public void onFinish(ITestContext context)     { }

    // ── Public API — called ONLY from TestSuite @AfterMethod ──────────────

    /**
     * Clears the processedTests set so a fresh run starts with clean state.
     * Call once before the suite starts (e.g. from EDCMainFrame.startExecution).
     */
    public static void resetAll() {
        processedTests.clear();
        System.out.println("[EDCTestListener] Reset — processedTests cleared");
    }

    /**
     * Updates the UI Passed / Failed / Skipped count ONCE per test case.
     *
     * MUST be called from @AfterMethod AFTER setting the final ITestResult
     * status (i.e. after the VDFailResult check block).
     *
     * The processedTests set guarantees this method counts at most once
     * per unique test name, even if called multiple times.
     *
     * @param result the ITestResult received by @AfterMethod
     */
    public static void updateResultCount(ITestResult result) {
        if (result == null) return;

        String testName = result.getName();

        // ── GUARD: prevent duplicate counting ──
        if (processedTests.contains(testName)) {
            System.out.println("[EDCTestListener] BLOCKED duplicate count for: " + testName);
            return;
        }
        processedTests.add(testName);

        // ── Determine result type ──
        final String type;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS: type = "passed";  break;
            case ITestResult.FAILURE: type = "failed";  break;
            case ITestResult.SKIP:    type = "skipped"; break;
            default:
                System.out.println("[EDCTestListener] Unknown status " + result.getStatus()
                        + " for test: " + testName + " — NOT counted");
                return;
        }

        // ── Debug log — prints EXACTLY once per test case ──
        System.out.println("[EDCTestListener] Updating result for test: "
                + testName + " → " + type.toUpperCase());

        // ── Post UI update to the EDT (Swing thread safety) ──
        SwingUtilities.invokeLater(() -> {
            ResultsLogPanel panel = EDCMainFrame.getLogPanel();
            if (panel == null) return;
            switch (type) {
                case "passed":  panel.incrementPassed();  break;
                case "failed":  panel.incrementFailed();  break;
                case "skipped": panel.incrementSkipped(); break;
            }
        });
    }
}