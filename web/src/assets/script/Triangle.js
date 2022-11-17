import { BallObject } from "./BallObject";

export class Triangle extends BallObject{
    /**
     * 构造函数创建新的三角形障碍物
     * @param {游戏地图} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap, c, r){
        super();
        this.gameMap = gameMap;
        this.r = r;
        this.c = c;

        this.size = 1;
        
        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.triangle_icon;
    }

    //初始化函数
    start(){

    }

    //更新函数
    update(){
        this.render();
    }

    /**
     * 绘制函数
     */
    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L, this.r * L, L * this.size, L * this.size);
    }
}