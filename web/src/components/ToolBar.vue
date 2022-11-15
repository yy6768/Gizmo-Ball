<template>
  <div class="toolbar">
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="hand">
          <el-image src="https://s1.ax1x.com/2022/11/07/xvqKSg.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="ball">
          <el-image style="width: 60px; height: 60px" src="https://s1.ax1x.com/2022/11/07/xv5hdS.png" fit="contain"></el-image>
        </el-radio>
      </div>   
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="blackhole">
          <el-image src="https://missuo.ru/file/07a1fd1db96353e1a7c62.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="tringle">
          <el-image src="https://missuo.ru/file/5d1d1d451122e8425a8ff.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="circle">
          <el-image src="https://s1.ax1x.com/2022/11/07/xvLZu9.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="square">
          <el-image src="https://missuo.ru/file/ac4ebd3bfd5f4e9eec85d.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="rail">
          <el-image src="https://missuo.ru/file/5efd7b38b13b2adb5083b.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="corner">
          <el-image src="https://missuo.ru/file/e2d816f750111fc31571e.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="left">
          <el-image src="https://missuo.ru/file/cca835b48bcaae3f8ea0f.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="right">
          <el-image src="https://missuo.ru/file/cca835b48bcaae3f8ea0f.png" style="width: 60px; height: 60px" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="buttonLine">
      <el-button icon="el-icon-refresh-right" circle @click="rotate"></el-button>
      <el-button icon="el-icon-close" circle @click="close"></el-button>
      <el-button icon="el-icon-plus" circle @click="plus"></el-button>
      <el-button icon="el-icon-minus" circle @click="minus"></el-button>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      selected:''
    }
  },
  methods:{
    // 贴图组件旋转函数
    rotate(){
      console.log("img rotate")
    },

    // 贴图组件删除函数
    close(){

    },

    // 贴图组件放大函数
    plus(){

    },

    // 贴图组件缩小函数
    minus(){

    },
  }
}
</script>

<style scoped>
div.toolbar{
    height: 60vh;
}
div.colBar{
  margin-left: 100px;
  margin-top: 30px;
  display: flex;
}
div.rowBar{
  margin-right: 150px;
  margin-bottom: 30px;
}
div.buttonLine{
  display: flex;
  margin-top: 80px;
  margin-left: 70px;
}
.el-button{
  height: 80px;
  width: 80px;
  font-size: 50px;
  align-content: center;
  margin-right: 30px;
}
</style>