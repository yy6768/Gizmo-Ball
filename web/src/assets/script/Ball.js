import { BallObject } from "./BallObject";


export class Ball extends BallObject{
    /**
     * 构造函数
     * @param {游戏地图 creator&信息专家} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap,c, r){
        super();
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;
        this.size = 1;
        //速度在x方向和y方向的分量
        this.velocity = [0,0];

        this.image = new Image();
        
        this.image.src = this.gameMap.store.state.icon.ball_icon;
    }

    /**
     * 更新函数
     * 1、当处于布局模式是只需要重新渲染
     * 2、当处于游戏模式的时候位置发生变化（TODO)
     */
    update(){
        this.render()
    }

    magnify(){
        this.size = 2;
    }

    shrink(){
        this.size = 1;
    }

    /**
     * 绘制函数
     */
    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image, this.c * L, this.r * L, L * this.size, L * this.size);
    }

    
}