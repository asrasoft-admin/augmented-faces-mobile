import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.soloader.SoLoader

class ARCoreModuleManager(reactApplication: ReactApplication) : ReactNativeHost(reactApplication) {
    private val reactApplicationContext: ReactApplicationContext

    init {
        SoLoader.init(reactApplication, false)
        reactApplicationContext = reactApplication.reactNativeHost.reactApplicationContext
    }

    override fun getUseDeveloperSupport(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getPackages(): List<ReactPackage> {
        return listOf(ARCoreModulePackage())
    }
}