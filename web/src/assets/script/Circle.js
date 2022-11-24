import { GimzoShape } from "./GimzoShape";

export class Circle extends GimzoShape{
    /**
     * 构造函数创建新的圆形障碍物
     * @param {游戏地图} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap, c, r, size){
        super(gameMap, c, r,size);
        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.circle_icon;
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