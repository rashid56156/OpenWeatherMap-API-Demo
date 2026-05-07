import com.ow.forecast.api.ApiResponse
import com.ow.forecast.models.Weather
import com.ow.forecast.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherRepository(
    private val result: ApiResponse<Weather>? = null,
    private val exception: Exception? = null
) : WeatherRepository {

    override fun getWeather(): Flow<ApiResponse<Weather>> = flow {
        emit(ApiResponse.Loading)
        exception?.let { throw it }
        result?.let { emit(it) }
    }
}