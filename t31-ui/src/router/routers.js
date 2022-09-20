import Main from '@/components/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  title: { String|Number|Function }
 *         显示在侧边栏、面包屑和标签栏的文字
 *         使用'{{ 多语言字段 }}'形式结合多语言使用，例子看多语言的路由配置;
 *         可以传入一个回调函数，参数是当前路由对象，例子看动态路由和带参路由
 *  hideInBread: (false) 设为true后此级路由将不会出现在面包屑中，示例看QQ群路由配置
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面在切换标签后不会缓存，如果需要缓存，无需设置这个字段，而且需要设置页面组件name属性和路由配置的name一致
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/register',
    name: 'register',
    meta: {
      title: 'Register - 注册',
      hideInMenu: true
    },
    component: () => import('@/view/admin/user/register.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true,
          icon: 'md-home'
        },
        component: () => import('@/view/single-page/home')
      }
    ]
  },

  // 个人信息
  {
    path: '/self',
    name: 'self',
    meta: {
      title: '个人信息',
      icon: 'md-person'
    },
    component: Main,
    children: [
      {
        path: 'passenger',
        name: 'list_self_passenger',
        meta: {
          title: '乘客管理',
          icon: 'md-trash'
        },
        component: () => import('@/view/self/passenger/list')
      },
      {
        path: 'passenger/edit',
        name: 'edit_self_passenger',
        meta: {
          title: '乘客修改',
          hideInMenu: true,
          icon: 'ios-man'
        },
        component: () => import('@/view/self/passenger/edit')
      },
      {
        path: 'user',
        name: 'edit_self_user',
        meta: {
          title: '个人信息',
          hideInMenu: true,
          icon: 'ios-woman'
        },
        component: () => import('@/view/self/user/self')
      }
    ]
  },

  // 票务管理
  {
    path: '/train',
    name: 'train',
    meta: {
      title: '票务管理',
      icon: 'md-train'
    },
    component: Main,
    children: [
      {
        path: 'number',
        name: 'list_train_number',
        meta: {
          title: '车次管理',
          icon: 'ios-subway'
        },
        component: () => import('@/view/train/number/list')
      },
      {
        path: 'number/edit',
        name: 'edit_train_number',
        meta: {
          title: '乘客修改',
          hideInMenu: true,
          icon: 'md-person-add'
        },
        component: () => import('@/view/train/number/edit')
      },
      {
        path: 'carriage',
        name: 'list_train_carriage',
        meta: {
          title: '车厢管理',
          icon: 'ios-train'
        },
        component: () => import('@/view/train/carriage/list')
      },
      {
        path: 'carriage/edit',
        name: 'edit_train_carriage',
        meta: {
          title: '车厢修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/train/carriage/edit')
      },
      {
        path: 'station',
        name: 'list_train_station',
        meta: {
          title: '经停站管理',
          icon: 'ios-stopwatch'
        },
        component: () => import('@/view/train/station/list')
      },
      {
        path: 'station/edit',
        name: 'edit_train_station',
        meta: {
          title: '经停站修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/train/station/edit')
      },
      {
        path: 'timetable',
        name: 'list_train_timetable',
        meta: {
          title: '时刻管理',
          icon: 'md-time'
        },
        component: () => import('@/view/train/timetable/list')
      },
      {
        path: 'timetable/edit',
        name: 'edit_train_timetable',
        meta: {
          title: '时刻表修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/train/timetable/edit')
      },
      {
        path: 'scheduler',
        name: 'list_train_scheduler',
        meta: {
          title: '列车时刻查询',
          icon: 'md-timer'
        },
        component: () => import('@/view/train/scheduler/list')
      }
    ]
  },

  // 车票购买
  {
    path: '/purchase',
    name: 'purchase',
    meta: {
      title: '购买车票',
      icon: 'md-trash'
    },
    component: Main,
    children: [
      {
        path: 'sku',
        name: 'list_purchase_sku',
        meta: {
          title: '购买车票',
          icon: 'ios-cart'
        },
        component: () => import('@/view/purchase/sku/list')
      },
      {
        path: 'sku/edit',
        name: 'edit_purchase_sku',
        meta: {
          title: '车次修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/purchase/sku/edit')
      },
      {
        path: 'sku/place',
        name: 'place_purchase_order',
        meta: {
          title: '买票下单',
          hideInMenu: true,
          icon: 'ios-boat'
        },
        component: () => import('@/view/purchase/sku/order')
      },
      {
        path: 'order',
        name: 'list_purchase_order',
        meta: {
          title: '我的订单',
          icon: 'ios-basket'
        },
        component: () => import('@/view/purchase/order/list')
      },
      {
        path: 'order/edit',
        name: 'edit_purchase_order',
        meta: {
          title: '订单查看',
          hideInMenu: true,
          icon: 'ios-list'
        },
        component: () => import('@/view/purchase/order/edit')
      }
    ]
  },

  // 系统管理
  {
    path: '/admin',
    name: 'admin',
    meta: {
      title: '系统设置',
      icon: 'md-git-branch'
    },
    component: Main,
    children: [
      {
        path: 'post',
        name: 'list_admin_post',
        meta: {
          title: '岗位管理',
          icon: 'md-school'
        },
        component: () => import('@/view/admin/post/list')
      },
      {
        path: 'post/edit',
        name: 'edit_admin_post',
        meta: {
          title: '岗位修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/admin/post/edit')
      },
      {
        path: 'dept',
        name: 'list_admin_dept',
        meta: {
          title: '部门管理',
          icon: 'md-home'
        },
        component: () => import('@/view/admin/dept/list')
      },
      {
        path: 'dept/edit',
        name: 'edit_admin_dept',
        meta: {
          title: '部门修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/admin/dept/edit')
      },
      {
        path: 'dict',
        name: 'list_admin_dict',
        meta: {
          title: '字典管理',
          icon: 'md-book'
        },
        component: () => import('@/view/admin/dict/list')
      },
      {
        path: 'dict/edit',
        name: 'edit_admin_dict',
        meta: {
          title: '字典修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/admin/dict/edit')
      },
      {
        path: 'menu',
        name: 'list_admin_menu',
        meta: {
          title: '菜单管理',
          icon: 'md-menu'
        },
        component: () => import('@/view/admin/menu/list')
      },
      {
        path: 'menu/edit',
        name: 'edit_admin_menu',
        meta: {
          title: '菜单修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/admin/menu/edit')
      },
      {
        path: 'role',
        name: 'list_admin_role',
        meta: {
          title: '角色管理',
          icon: 'ios-people'
        },
        component: () => import('@/view/admin/role/list')
      },
      {
        path: 'role/edit',
        name: 'edit_admin_role',
        meta: {
          title: '角色修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/admin/role/edit')
      },
      {
        path: 'user',
        name: 'list_admin_user',
        meta: {
          title: '用户管理',
          icon: 'md-person'
        },
        component: () => import('@/view/admin/user/list')
      },
      {
        path: 'user/edit',
        name: 'edit_admin_user',
        meta: {
          title: '用户修改',
          hideInMenu: true,
          icon: 'ios-create'
        },
        component: () => import('@/view/admin/user/edit')
      }
    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/404.vue')
  }
]
