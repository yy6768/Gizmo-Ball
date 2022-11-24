<template>
  <div class="switch-mode-bar">
    <div class="buttons-bar">
      <div class="bt-bar">
        <el-button circle type="primary" icon="el-icon-plus" @click="magnify" :disabled="mode=='game'"></el-button>
      </div>
      <div class="bt-bar">
        <el-button circle icon="el-icon-minus" @click="shrink" :disabled="mode=='game'"></el-button>
      </div>
      <div class="bt-bar">
        <el-button circle type="info" icon="el-icon-refresh-right" @click="rotate" :disabled="mode=='game'"></el-button>
      </div>
      <div class="bt-bar">
        <el-button circle type="danger" icon="el-icon-close" @click="destroy" :disabled="mode=='game'"></el-button>
      </div>
    </div>
  </div>
</template>

<script>
import icon from '../store/icon'
export default {
  data(){
    return{
      img:icon.state
    }
  },
  methods: {
    //组件放大
    magnify(){
      let object = this.$store.state.layout.object;
      if(object != null){
        this.$store.state.layout.object.magnify();
      }
    },
    //组件缩小
    shrink(){
      let object = this.$store.state.layout.object;
      if (object != null) {
        this.$store.state.layout.object.shrink();
      }
    },
    //组件旋转
    rotate(){
      let object = this.$store.state.layout.object;
      if (object != null) {
        this.$store.state.layout.object.rotate();
      }
    },
    //组件销毁
    destroy(){
      let object = this.$store.state.layout.object;
      if (object != null) {
        this.$store.state.layout.object.destroy();
      }
    }
  },
  computed:{
      mode(){
          return this.$store.state.layout.status
      }
  },
}
</script>

<style scoped>
div.switch-mode-bar{
    height: 8vh;
    display: block;
}
div.buttons-bar{
  margin-top: 2vh;
  margin-left: 4vh;
  display: flex;
}
div.bt-bar{
  margin-right: 3.5vh;
}
.el-button{
  height: 6vh;
  width: 6vh;
  text-align: center;
}
</style>