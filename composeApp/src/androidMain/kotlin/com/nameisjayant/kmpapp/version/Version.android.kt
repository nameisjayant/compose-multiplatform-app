package com.nameisjayant.kmpapp.version

import android.os.Build
import com.nameisjayant.kmpapp.utils.Constant

actual fun getPlatformVersion(): String = "Android: ${Build.VERSION.SDK_INT}"
actual fun getPlatformName(): String = Constant.ANDROID