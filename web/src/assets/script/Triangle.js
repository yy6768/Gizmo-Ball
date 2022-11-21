
import { GimzoShape } from "./GimzoShape";

export class Triangle extends GimzoShape{
    /**
     * 构造函数创建新的三角形障碍物
     * @param {游戏地图} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap, c, r){
        super(gameMap, c, r);
        //当前的角度
        this.angle = 0;
        this.dx = [0, 0, 1, 1];
        this.dy = [0, 1, 1, 0];
    }


    //更新函数
    update(){
        this.render();
    }

    rotate(){
        this.angle =(this.angle + 1) % 4;
        let socket = this.gameMap.store.state.layout.socket;
        socket.send("rotate " + this.id);
    }


    /**
     * 绘制函数
     */
    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.beginPath();
        ctx.moveTo((this.c + this.dx[this.angle] * this.size) * L, (this.r + this.dy[this.angle] * this.size) *L);
        ctx.lineTo((this.c + this.dx[(this.angle + 1) % 4] * this.size) * L, (this.r + this.dy[(this.angle + 1) % 4] * this.size) *L);
        ctx.lineTo((this.c + this.dx[(this.angle + 2) % 4] * this.size) * L, (this.r + this.dy[(this.angle + 2) % 4] * this.size) *L);
        ctx.closePath();
        ctx.strokeStyle = "rgb(65,235,138)";
        ctx.stroke();
        ctx.fillStyle = "rgb(65,235,138)";
        ctx.fill();
    }
}