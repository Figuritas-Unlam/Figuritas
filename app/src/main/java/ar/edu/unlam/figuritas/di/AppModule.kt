package ar.edu.unlam.figuritas.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.figuritas.data.OpenRouteClient
import ar.edu.unlam.figuritas.data.OpenRouteService
import ar.edu.unlam.figuritas.domain.PolyLineRouteProvider
import ar.edu.unlam.figuritas.data.api.PlayerAPI
import ar.edu.unlam.figuritas.data.database.PlayerDatabase
import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://soccer.sportmonks.com/api/v2.0/"
    private const val BASE_ROUTE_URL = "https://api.openrouteservice.org"

    @Singleton
    @Provides
    @Named("player_retrofit")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePlayersApi(@Named("player_retrofit") retrofit: Retrofit): PlayerAPI {
        return retrofit.create(PlayerAPI::class.java)
    }

    @Singleton
    @Provides
    @Named("route_retrofit")
    fun provideRouteRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_ROUTE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRouteApi(@Named("route_retrofit") routeRetrofit: Retrofit): OpenRouteService {
        return routeRetrofit.create(OpenRouteService::class.java)
    }


    @Singleton
    @Provides
    fun providePolyLineProvider(openRouteService: OpenRouteClient): PolyLineRouteProvider {
        return PolyLineRouteProvider(openRouteService)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PlayerDatabase {
        return Room.databaseBuilder(
            context,
            PlayerDatabase::class.java,
            "Figuritas_Database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(database: PlayerDatabase) : PlayerDao {
        return database.playerDao()
    }
    /*
    private const val BASE_URL = "https://soccer.sportmonks.com/api/v2.0/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePlayersApi(retrofit: Retrofit): PlayerAPI {
        return retrofit.create(PlayerAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PlayerDatabase {
        return Room.databaseBuilder(
            context,
            PlayerDatabase::class.java,
            "Figuritas_Database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(database: PlayerDatabase) : PlayerDao{
        return database.playerDao()
    }
     */


}