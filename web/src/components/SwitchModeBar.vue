<template>
  <div class="switch-mode-bar">
    <div class="selectBar">
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in modes"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <dir class="modeBar">
      <el-button>设计模式</el-button>
      <el-button>游玩模式</el-button>
    </dir>
  </div>
</template>

<script>
export default {
  data(){
    return{
      modes:[
        {
          value: 1,
          label: "新建游戏"
        }, {
          value: 2,
          label: "保存游戏"
        }, {
          value: 3,
          label: "读取游戏"
        }
      ],
      value:''
    }
  }
}
</script>

<style>
div.switch-mode-bar{
    height: 20vh;
}
div.selectBar{
  margin-left: 5px;
  margin-top: 5px;
}
div.modeBar{
  margin-left: -30px;
  padding: 0px;
}
.el-button{
  margin-left: 0px;
  margin-right: 50px;
  width: 150px;
  height: 60px;
}
</style>