<template>
  <div class="game-design-bar">
    <div class="bt-line">
        <div class="bt-bar">
            <el-button @click="game">游玩模式</el-button>
        </div>
        <div class="bt-bar">
            <el-button @click="layout">设计模式</el-button>
        </div>
    </div>
    <div class="bt-line">
        <div class="bt-bar">
            <el-upload
            :on-success="read_file"
            :file-list="fileList"
            >
                <el-button :disabled="mode==='game'">文件读取</el-button>
            </el-upload>
        </div>
        <div class="bt-bar">
            <el-button @click="download_file" :disabled="mode==='game'">保存文件</el-button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return {
            fileList: []
        }
    },
    methods: {
        game(){
            this.$store.commit('updateStatus', 'game');
            this.$store.state.layout.socket.send("startGame");
        },
        layout(){
            this.$store.commit('updateStatus', 'layout');
            this.$store.state.layout.socket.send("endGame");
        },
        read_file(){
            
        },
        download_file(){
            console.log(this.fileList[0])
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
div.game-design-bar{
    height: 11vh;
    display: block;
}
.bt-line{
    display: flex;
}
div.bt-bar{
    margin-top: 0vh;
    margin-left: 2vh;
    margin-bottom: 1vh;
}
.el-button{
    width: 18vh;
    height: 5vh;
    font-weight: 500;
}
</style>