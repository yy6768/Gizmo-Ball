import { GimzoShape } from "./GimzoShape";
export class Ball extends GimzoShape{
    /**
     * 构造函数
     * @param {游戏地图 creator&信息专家} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap,c, r){
        super(gameMap, c, r);
        this.size = 1;
        //速度在x方向和y方向的分量
        this.velocity = [0,0];

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.ball_icon;
    }


    /**
     * 更新函数
     * 1、当处于布局模式是只需要重新渲染
     * 2、当处于游戏模式的时候位置发生变化
     */
    update(){
        this.render()
    }

    /**
     * 绘制函数
     */
    render(){
        // console.log(this.image);
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image, this.c * L, this.r * L, L * this.size, L * this.size);
    }

    
}