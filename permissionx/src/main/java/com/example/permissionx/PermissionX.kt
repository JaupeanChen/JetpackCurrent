package com.example.permissionx

import androidx.fragment.app.FragmentActivity

/**
 * 通过单例来支持全局的调用能力
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    //首先我们先思考，我们对外提供了调用入口，那我们应该做什么。首先当然是要先把我们InvisibleFragment加到调用方的Activity里，
    //这样我们才能接手权限请求的事务，然后再把结果回调给调用方自己处理就行了。
    fun request(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        //先判断InvisibleFragment是否已经存在了，因为有可能是已经申请过权限了，重新再申请一次（可能不同权限），我们就没必要再添加一次了。
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val invisibleFragment = if (existedFragment != null) {
            //如果存在就直接用
            existedFragment as InvisibleFragment
        } else {
            //如果不存在那就添加，并设置好TAG，方便我们找
            val invisibleFragment = InvisibleFragment()
            //通过commitNow()方法触发立即执行动作，commit()方法的执行时机会在延后的某个时刻，所以可能导致我们走到下一步逻辑的时候，
            //还没添加，导致发生异常
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment //返回我们添加的Fragment
        }
        //这里permission传进来其实已经被转型为Array<out String>类型了，所以我们通过*来将一个数组转换成可变参数重新传递进去
        invisibleFragment.request(callback, *permission)
    }

}