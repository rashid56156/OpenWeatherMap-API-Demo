import com.ow.forecast.api.ApiResponse
import com.ow.forecast.models.Weather
import com.ow.forecast.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherRepository(
    private val result: ApiResponse<Weather>
) : WeatherRepository {

    override fun getWeather(): Flow<ApiResponse<Weather>> = flow {
        // you can emit Loading too if you want to match prod behavior
        emit(ApiResponse.Loading)
        emit(result)
    }
}