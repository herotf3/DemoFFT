//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2017. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// ContextExtensions.kt is part of the SCICHART® Showcases. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.tf.fft.kotlin.utils

import android.content.Context
import android.util.TypedValue

/**
 * Gets the size in DIP
 */
fun Context.dip(value: Float) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, this.resources.displayMetrics)
}
