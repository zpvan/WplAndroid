package com.knox.plugin;

import android.content.Context;

class PluginDynamic implements IDynamic{
   @Override
   public String getStringForResId(Context context) {
      return context.getResources().getString(R.string.my_plugin_hello_world);
   }
}
