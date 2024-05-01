package com.nameisjayant.kmpapp.version

import android.os.Build

actual fun getPlatformVersion(): String = "Android: ${Build.VERSION.SDK_INT}"