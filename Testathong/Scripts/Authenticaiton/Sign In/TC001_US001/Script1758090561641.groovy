// File: Test Cases/Authenticaiton/Sign In/TC001_US001.groovy
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FH
import org.openqa.selenium.Keys as Keys

// Use unique names to avoid "already contains a variable" errors
final String TC001_INPUT_EMAIL = 'aaa@gmail.com'
final String TC001_INPUT_PASS  = '123456'

def TO_btnGoToFeedback = findTestObject('Object Repository/Page_Eventify/button_Go to Feedback Page_ring-offset-back_b7d330')
def TO_tfEmail         = findTestObject('Object Repository/Page_Eventify/input_Email_email')
def TO_tfPassword      = findTestObject('Object Repository/Page_Eventify/input_Password_password')
def TO_btnSubmit       = findTestObject('Object Repository/Page_Eventify/button_Password_flex w-fit items-center jus_d1da87')
def TO_lblEmail        = findTestObject('Object Repository/Page_Eventify/span_Address_max-w-120px truncate text-xs f_e14170')
def TO_spanTeStAtHoN   = findTestObject('Object Repository/Page_Eventify/span_TeStAtHoN_max-w-120px truncate text-10_ada476')
def TO_imgAvatar       = findTestObject('Object Repository/Page_Eventify/img_Address_aspect-square size-full')

try {
    WebUI.openBrowser('')
    WebUI.navigateToUrl('http://localhost:5173/sign-in')  // hard-coded as requested

    if (WebUI.verifyElementPresent(TO_btnGoToFeedback, 3, FH.OPTIONAL)) {
        WebUI.click(TO_btnGoToFeedback)
    }

    WebUI.waitForElementVisible(TO_tfEmail, 10, FH.STOP_ON_FAILURE)
    WebUI.setText(TO_tfEmail, TC001_INPUT_EMAIL)

    WebUI.waitForElementVisible(TO_tfPassword, 10, FH.STOP_ON_FAILURE)
    WebUI.setText(TO_tfPassword, TC001_INPUT_PASS)

    // FIX: do NOT pass timeout to verifyElementClickable; use waitForElementClickable instead
    if (WebUI.waitForElementClickable(TO_btnSubmit, 5, FH.OPTIONAL)) {
        WebUI.click(TO_btnSubmit)
    } else {
        WebUI.sendKeys(TO_tfPassword, Keys.chord(Keys.ENTER))
    }

    WebUI.waitForElementVisible(TO_lblEmail, 15, FH.STOP_ON_FAILURE)
    String shownEmail = (WebUI.getText(TO_lblEmail) ?: '').trim()
    WebUI.verifyMatch(shownEmail, TC001_INPUT_EMAIL, false)

    if (WebUI.verifyElementPresent(TO_spanTeStAtHoN, 3, FH.OPTIONAL)) {
        WebUI.click(TO_spanTeStAtHoN)
    }

    WebUI.waitForElementClickable(TO_imgAvatar, 10, FH.STOP_ON_FAILURE)
    WebUI.click(TO_imgAvatar)
    WebUI.waitForPageLoad(10)

    String currentUrl = WebUI.getUrl()
    WebUI.verifyMatch(currentUrl, 'http://localhost:5173/', false)  // exact match

} finally {
    WebUI.closeBrowser()
}
