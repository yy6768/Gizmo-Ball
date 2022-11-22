<template>
  <div class="game-design-bar">
    <div class="bt-bar">
        <el-button @click="game">游玩模式</el-button>
    </div>
    <div class="bt-bar">
        <el-button @click="layout">设计模式</el-button>
    </div>
  </div>
</template>

<script>
export default {
    methods: {
        game(){
            this.$store.commit('updateStatus', 'game');
            this.$store.state.layout.socket.send("startGame");
        },
        layout(){
            this.$store.commit('updateStatus', 'layout');
            this.$store.state.layout.socket.send("endGame");
        }
    },
}
</script>

<style>
div.game-design-bar{
    height: 5vh;
    display: flex;
}
div.bt-bar{
    margin-top: 0.5vh;
    margin-left: 2vh;
}
.el-button{
    width: 15vh;
    height: 4vh;
    font-weight: 500;
}
</style>