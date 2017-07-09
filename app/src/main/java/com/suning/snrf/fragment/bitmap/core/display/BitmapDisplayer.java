/*******************************************************************************
 * Copyright 2011-2013
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.suning.snrf.fragment.bitmap.core.display;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.suning.snrf.fragment.bitmap.core.assist.LoadedFrom;


/**
 * Displays {@link Bitmap} in {@link ImageView}. Implementations can apply some changes to Bitmap or any animation for
 * displaying Bitmap.<br />
 * Implementations have to be thread-safe.
 *
 * @author Ryan
 * @since 1.5.6
 */
public interface BitmapDisplayer {
	/**
	 * Display bitmap in {@link ImageView}. Displayed bitmap should be returned.<br />
	 * <b>NOTE:</b> This method is called on UI thread so it's strongly recommended not to do any heavy work in it.
	 *
	 * @param bitmap     Source bitmap
	 * @param imageView  {@linkplain ImageView Image view} to display Bitmap
	 * @param loadedFrom Source of loaded image
	 * @return Bitmap which was displayed in {@link ImageView}
	 */
	Bitmap display(Bitmap bitmap, ImageView imageView, LoadedFrom loadedFrom);
}
