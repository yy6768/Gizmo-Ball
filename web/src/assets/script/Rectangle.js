import { GimzoShape } from "./GimzoShape";

export class Rectangle extends GimzoShape{
    /**
     * 构造函数创建新的长方形障碍物
     * @param {游戏地图} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap, c, r){
        super(gameMap, c, r);

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.rectangle_icon;
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