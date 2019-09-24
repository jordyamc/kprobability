package knf.test.probability

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import knf.tools.kprobability.item
import knf.tools.kprobability.percent
import knf.tools.kprobability.probabilityOf
import knf.tools.kprobability.timesIn
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("knf.test.probability", appContext.packageName)
    }

    @Test
    fun test_probability() {
        val probability = probabilityOf(1) {
            item(5, 20.percent)
            item(10, 30 timesIn 100)
        }
        var time = System.currentTimeMillis()
        repeat(10){
            val result = probability.random()
            val newTime = System.currentTimeMillis()
            val timeTaken = newTime - time
            time = newTime
            Log.e("Probability $it","Result: $result, in: $timeTaken")
        }
    }


}
