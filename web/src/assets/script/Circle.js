import { BallObject } from "./BallObject";

export class Circle extends BallObject{
    /**
     * 构造函数创建新的圆形障碍物
     * @param {游戏地图} gameMap 
     * @param {横坐标} r 
     * @param {纵坐标} c 
     */
    constructor(gameMap, r, c){
        super();
        this.gameMap = gameMap;
        this.r = r;
        this.c = c;

        this.size = 1;
        
        this.image = new Image();
        this.image.src = "https://s1.ax1x.com/2022/11/07/xvLZu9.png";
    }

    start(){

    }

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