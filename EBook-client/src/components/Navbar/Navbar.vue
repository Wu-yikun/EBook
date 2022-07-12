<template>
  <div>
    <nav class="navbar navbar-light border-bottom mt-1" :class="{'bg-dark':isManager}" lazy="loaded">
      <a class="navbar-brand" href="#/home">
        <div class="row" :class="{'text-white':isManager}">
          <i class="iconfont icon-shuji mt-1 mr-2 ml-2" style="font-size: 21px;"></i>
          EBook 网上书店📚
        </div>
      </a>
      <!-- router.js 的各个路由中配置 showNavDetail 信息, 从而达到在 /login 页面隐藏该导航栏 -->
      <ul class="nav nav-fill nav-pills" v-show="$route.meta.showNavDetail">
        <li class="nav-item">
          <a class="nav-link" :class="($route.path==='/home')? 'active' : 'text-muted'" href="#/home" v-if="!isManager">
            <i class="iconfont icon-shouye"></i>
            首页
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" :class="($route.path==='/books')? 'active' : 'text-muted'" href="#/books">
            <i class="iconfont icon-shu"></i>
            全部书籍
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" :class="($route.path==='/search')? 'active' : 'text-muted'" href="#/search">
            <i class="iconfont icon-sousuo"></i>
            书籍搜索
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" :class="($route.path==='/users')? 'active' : 'text-muted'" href="#/users" v-if="isManager">
            <i class="iconfont icon-guanli"></i>
            用户管理
          </a>
        </li>
        <!-- 如下两个栏目通过是否已登录判断是否显示 v-show="isLogin" -->
        <li class="nav-item" v-show="isLogin">
          <a class="nav-link" :class="($route.path==='/orders')? 'active' : 'text-muted'" href="#/orders">
            <i class="iconfont icon-dingdan"></i>
            {{ isManager? '所有': '' }}订单
          </a>
        </li>
        <li class="nav-item" v-show="isLogin">
          <a class="nav-link" :class="($route.path==='/statistics')? 'active' : 'text-muted'" href="#/statistics">
            <i class="iconfont icon-tongji2"></i>
            统计
          </a>
        </li>
      </ul>
      <div>
        <a href="#/login" class="card-link" v-if="!isLogin">
          <span></span>
          <i class="iconfont icon-denglu"></i>
          登录/注册
        </a>
        <div class="row mr-0" v-else>
          <el-dropdown class="mr-3" style="cursor: pointer" trigger="click">
            <span class="el-dropdown-link">
              <i class="iconfont icon-guanliyuan text-primary" v-if="isManager"></i>
              <i class="iconfont icon-geren text-primary" v-else></i>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div :class="($route.path==='/about')? 'active' : 'text-muted'" @click="$router.push('/about')">
                  <i class="iconfont icon-guanyu"></i>
                  关于
                </div>
              </el-dropdown-item>
              <el-dropdown-item divided>
                <div @click="logout">
                  <i class="iconfont icon-tuichu text-danger"></i>
                  退出登录
                </div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { reqLogout } from '../../api'

export default {
  name: "Navbar",
  data() {
    return {
      collapsed: false,
    }
  },
  computed: {
    ...mapState({
      isLogin: state => state.Person.isLogin,
      isManager: state => state.Person.isManager,
    }),
  },
  methods: {
    logout() {
      reqLogout().then((data) => {
        this.$store.commit('Person/changeLogin')
        if (this.isManager)
          this.$store.commit('Person/changeManager')

        this.$router.push('/home')
        this.$message.success(data)
      })
    },
    toHome() {
      this.$router.push('/home')
    }
  }
}
</script>

<style scoped>
</style>
