import app.cash.turbine.test
import com.ow.forecast.api.ApiResponse
import com.ow.forecast.api.ApiService
import com.ow.forecast.models.Weather
import com.ow.forecast.repo.WeatherRepositoryImpl
import com.ow.forecast.utilities.Constants
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals


class WeatherRepositoryImplTest {

    val apiService = mock<ApiService>()

    val repo = WeatherRepositoryImpl(apiService)

    @Test
    fun getWeather_returns_success_when_api_returns_success() = runTest {

        val cityId: String = Constants.LOCATION_ID
        val apiKey: String = Constants.API_KEY

        val weather = Weather(
            cod = "200",
            message = 0,
            cnt = 1,
            city = null,
            list = mutableListOf()
        )

        val apiResponse = ApiResponse.Success(weather)

        whenever(apiService.getWeatherForecast(cityId, apiKey)).thenReturn(weather)

        repo.getWeather().test {

            val result = awaitItem()

            assert(result is ApiResponse.Success)

            assertEquals(apiResponse, result)

            verify(apiService).getWeatherForecast(cityId, apiKey)

            awaitComplete()
        }

    }





}