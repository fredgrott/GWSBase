/**
 * Calligraphy font package library
 *
 * Add your custom fonts to `assets/` all font definition is relative to this path.
 *
 * We don't package an `R.attr` with Calligraphy to keep it a Jar. So you will need to add your own Attr.
 * The most common one is: `res/values/attrs.xml`
 * <code>
 *     attr name="fontPath" format="string"
 * </code>
 *
 * Do not need ot define default config in app class, just set a default font.
 *
 * Inject into context in app class
 * <code>
 *     @Override
 * protected void attachBaseContext(Context newBase) {
 *    super.attachBaseContext(new CalligraphyContextWrapper(newBase));
 * }
 *
 * </code>
 *
 * To use in your xml define the fontPath attribute
 */
package com.grottworkshop.gwsbaselibrary.fonts;