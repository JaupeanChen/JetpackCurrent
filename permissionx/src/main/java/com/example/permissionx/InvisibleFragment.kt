package com.example.permissionx

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * 实现PermissionX
 * 其实实现思路很简单，我们在Activity中添加一个不可见的Fragment，来监听Activity传递给Fragment的回调即可，并且Fragment
 * 本身要依附于Activity，所以天然就持有了Activity的实例，就让事情更加容易了。但其实我一开始并不知道，Fragment原来是完全
 * 不用去加载界面也行的，也就是说我们并不需要给它布局加一个什么布局，然后再把它设置为invisible。
 */
//我们可以通过类型别名来定义函数类型参数，简化写法
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    companion object {
        const val REQUEST_CODE = 100
    }

    //回调
//    private var callback: ((Boolean, List<String>) -> Unit)? = null
    //应用类型别名定义替代
    private var callback: PermissionCallback? = null

    fun request(callback: PermissionCallback, vararg permissions: String) {
        this.callback = callback
        requestPermissions(permissions, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val isAllGrant = deniedList.isEmpty()
            callback?.let {
                it(isAllGrant, deniedList)
            }
        }
    }
}