import Tests.MainTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.service.ExtentTestManager;
import net.bsmch.Screenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Tests.MainTest.*;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTestManager.createMethod(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.PASS,
                iTestResult.getName() + " Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.FAIL,
                iTestResult.getName() + " Test failed");
        ExtentTestManager.getTest().info("screenshot" ,Screenshot.captureScreen("fail", MainTest.driver));

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentService.getInstance().flush();
    }
}
