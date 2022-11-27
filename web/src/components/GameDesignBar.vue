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
                <el-upload action="http://localhost:3000/upload"  :on-error="read_file_fail"
                    :on-success="read_file" :show-file-list="false">
                    <el-button :disabled="mode === 'game'">文件上传</el-button>
                </el-upload>
            </div>
            <div class="bt-bar">
                <el-button @click="download_file" :disabled="mode === 'game'">保存文件</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import { Ball } from '@/assets/script/Ball'
import { Circle } from "@/assets/script/Circle";
import { Baffle } from "@/assets/script/Baffle";
import { StraightPipe } from "@/assets/script/StraightPipe";
import { Rectangle } from "@/assets/script/Rectangle";
import { Triangle } from "@/assets/script/Triangle";
import { Blackhole } from "@/assets/script/Blackhole";
import { BendPipe } from "@/assets/script/BendPipe";
export default {
    data() {
        return {
            res_data: "",
            objects: []
        }
    },
    methods: {
        game() {
            this.$message.info("游戏开始");
            this.$store.commit('updateStatus', 'game');
            this.$store.state.layout.socket.send("startGame");
        },
        layout() {
            this.$store.state.layout.socket.send("endGame");
        },
        //上传成功
        read_file(res) {
            console.log(res);
            this.$message('上传成功');
            const res_data = res;
            if (res_data.result ==='success'){
                const objects = res_data.infos.split(' ');
                //创建地图并绑定组件
                let game_map = this.$store.state.layout.gameMap;
                game_map.clear();
                console.log(objects);
                for (const object of objects) {
                    const attr = object.split('#');
                    if(attr.length === 0) continue;
                    let obj;
                    let x = Number(attr[2]);
                    let y = Number(attr[3]);
                    if (attr[0] === 'Ball') {
                        const size = attr[4] === 'true' ? 2 : 1;
                        game_map.ball = obj = new Ball(game_map, x, y, size);
                    
                    } else if (attr[0] === 'Circle') {
                        const size = attr[4] === 'true' ? 2 : 1;
                        obj = new Circle(game_map, x, y, size);
                    } else if (attr[0] === 'Blackhole') {
                        obj = new Blackhole(game_map, x, y);
                    } else if (attr[0] === 'Rectangle') {
                        const size = attr[4] === 'true' ? 2 : 1;
                        obj = new Rectangle(game_map, x, y, size);
                    } else if (attr[0] === 'Triangle') {
                        const size = attr[4] === 'true' ? 2 : 1;
                        let angle = Number(attr[5]);
                        obj = new Triangle(game_map, x, y, size, angle);
                    } else if (attr[0] === 'StraightPipe') {
                        
                        obj = new StraightPipe(game_map, x, y, Number(attr[4]));
                    } else if (attr[0] === 'BendPipe') {
                        obj = new BendPipe(game_map, x, y, Number(attr[4]));
                    } else if (attr[0] === 'Baffle') {
                        const isLeft = attr[4] === 'true';
                        console.log(isLeft);
                        obj  = new Baffle(game_map, x, y, isLeft);
                        if(isLeft) {
                            game_map.leftBaffle = obj;
                        } else {
                            
                            game_map.rightBaffle = obj;
                        }
                    } else {
                        console.log(object);
                        console.log("wrong type");
                        return false;
                    }
                    console.log(obj);
                    obj.id = attr[1];
                }
                this.$store.commit('updateStatus', 'layout');
            } else {
                this.$message.error('文件上传失败，请检查是否上传正确的文件');
            }
        },
        //上传失败，提示
        read_file_fail(err) {
            console.log(err);
            this.$message.error('文件上传失败，请检查是否上传正确的文件');
        },
        //文件保存方法
        download_file() {
            window.open("http://localhost:3000/download", '_blank');
        }
    },
    computed: {
        mode() {
            return this.$store.state.layout.status
        }
    },
}
</script>

<style scoped>
div.game-design-bar {
    height: 11vh;
    display: block;
}

.bt-line {
    display: flex;
}

div.bt-bar {
    margin-top: 0vh;
    margin-left: 2vh;
    margin-bottom: 1vh;
}

.el-button {
    width: 18vh;
    height: 5vh;
    font-weight: 500;
}
</style>