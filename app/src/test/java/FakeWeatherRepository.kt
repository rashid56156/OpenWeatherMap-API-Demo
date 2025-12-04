import com.ow.forecast.api.ApiResult
import com.ow.forecast.models.Weather
import com.ow.forecast.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherRepository(
    private val result: ApiResult<Weather>
) : WeatherRepository {

    override fun getWeather(): Flow<ApiResult<Weather>> = flow {
        // you can emit Loading too if you want to match prod behavior
        emit(ApiResult.Loading)
        emit(result)
    }
}