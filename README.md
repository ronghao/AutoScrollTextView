# [AutoScrollTextView](https://github.com/ronghao/FrameAnimationView) [![](https://jitpack.io/v/ronghao/AutoScrollTextView.svg)](https://jitpack.io/#ronghao/AutoScrollTextView) [![](https://travis-ci.org/ronghao/AutoScrollTextView.svg?branch=master)](https://travis-ci.org/ronghao/AutoScrollTextView)  [![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/ronghao/CacheManage/master/LICENSE) 
android 上下滚动播放与走马灯效果结合

### 实现原理
+ 实现上下滚动
    + 使用[ViewSwitcher](https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/widget/ViewSwitcher.java)
+ 使用scrollTo(x,y)和runable循环调用，移动textview，形成走马灯效果

# 项目添加方法
在根 build.gradle中添加

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
在项目build.gradle中添加

	dependencies {
	    compile 'com.github.ronghao:AutoScrollTextView:1.0.0'//版本号参见
	}

版本号参见![](https://jitpack.io/v/ronghao/AutoScrollTextView.svg)