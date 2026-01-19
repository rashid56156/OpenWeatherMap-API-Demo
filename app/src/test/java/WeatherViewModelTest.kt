import com.ow.forecast.api.ApiResponse
import com.ow.forecast.models.Weather
import com.ow.forecast.ui.WeatherViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        val fakeWeather = Weather(
            city = null,
            cnt = 1,
            cod = "200",
            message = 0,
            list = mutableListOf()
        )

        val fakeRepo = FakeWeatherRepository(
            result = ApiResponse.Success(fakeWeather)
        )

        viewModel = WeatherViewModel(fakeRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWeatherForecast emits Success in weatherResult`() = runTest {
        // ✅ Start collecting BEFORE triggering
        val job = launch {
            viewModel.weatherResult.collect { }
        }

        // When
        viewModel.fetchWeatherForecast()

        // ✅ Allow all coroutines to run
        advanceUntilIdle()

        // Then
        val value = viewModel.weatherResult.value

        assertTrue(value is ApiResponse.Success)

        val success = value as ApiResponse.Success
        assertEquals("200", success.data.cod)

        job.cancel()
    }
}