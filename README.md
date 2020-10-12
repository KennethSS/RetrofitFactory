<h1 align="center">RetrofitFactory</h1></br>

<p align="center">
This is a factory for retrofit
</p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

### Dependency Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

```gradle
dependencies {
    implementation "com.github.KennethSS:RetrofitFactory:1.0.0"
    def retrofit = "2.9.0"
    implementation "com.squareup.retrofit2:retrofit:$retrofit"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit"
}
```


## Usage
### Basic Example

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkClient.let {
            it.CONNECTION_TIMEOUT = 10L
            it.WRITE_TIMEOUT = 30L
            it.READ_TIMEOUT = 30L
            it.HOST = "https://api.github.com/"
            it.IS_DEBUG = BuildConfig.DEBUG
        }
    }
}
```

#### Create User Service
```kotlin
interface UserService {
    @GET("search/users")
    suspend fun getUserList(
        @Query("q", encoded = true) query: String
    ): GetUserResponse
}
```


#### Use it!
```kotlin
private val service by lazy {
    NetworkClient.provideService<UserService>()
}
```

