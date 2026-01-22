import app.cash.turbine.test
import com.ow.forecast.api.ApiResponse
import com.ow.forecast.models.Weather
import com.ow.forecast.ui.WeatherViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
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
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWeatherForecast emits Success in weatherResult`() = runTest {

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

        viewModel.weatherResult.test {

            // When
            viewModel.fetchWeatherForecast()

            // Then: First emission might be Loading
            val first = awaitItem()
            assertTrue(first is ApiResponse.Loading)

            // Then: next emission should be Success
            val second = awaitItem()
            assertTrue(second is ApiResponse.Success)

            val success = second as ApiResponse.Success
            assertEquals("200", success.data.cod)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchWeatherForecast emits Error in weatherResult`() = runTest {

        val fakeRepo = FakeWeatherRepository(
            result = ApiResponse.Error("Something went wrong", code = 500)
        )

        viewModel = WeatherViewModel(fakeRepo)

        viewModel.weatherResult.test {

            // When
            viewModel.fetchWeatherForecast()

            // Then: First emission might be Loading
            val first = awaitItem()
            assertTrue(first is ApiResponse.Loading)

            // Then: next emission should be Error
            val second = awaitItem()
            assertTrue(second is ApiResponse.Error)

            val error = second as ApiResponse.Error
            assertEquals(500, error.code)

            cancelAndIgnoreRemainingEvents()

        }

    }
}