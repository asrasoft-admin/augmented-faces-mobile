import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableException
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.ReactApplication
import com.facebook.react.BuildConfig
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler

class ARCoreModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var session: Session? = null

    override fun getName(): String {
        return "ARCoreModule"
    }

    @ReactMethod
    fun isArCoreSupported(): Boolean {
        return ArCoreApk.getInstance().isSupported(reactApplicationContext)
    }

    // ...
}